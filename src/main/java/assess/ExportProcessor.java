package assess;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import assess.model.Body;
import assess.model.BodyKeyValue;
import assess.model.Collection;
import assess.model.Request;
import assess.model.RequestItem;
import assess.model.RequestSource;
import assess.model.FolderItem;
import burp.IBurpExtenderCallbacks;
import burp.IExtensionHelpers;
import burp.IHttpRequestResponse;
import burp.IHttpService;
import burp.IParameter;
import burp.IRequestInfo;

public class ExportProcessor {

	private List<RequestSource> reqLists = new ArrayList<>();
	private IExtensionHelpers helpers;
	private IBurpExtenderCallbacks callbacks;
	private String encoding;
	private String colName;
	private Map<String, FolderItem> folderMap = new HashMap<>();

	public static final String[] EXCLUDED_HEADERS = { "Content-Length", "Connection", "Accept-Encoding" };

	private static JFileChooser filechooser = new JFileChooser();

	public ExportProcessor(List<RequestSource> list, IBurpExtenderCallbacks callbacks, String encoding, String colName) {
		super();
		this.reqLists = list;
		this.callbacks = callbacks;
		this.helpers = callbacks.getHelpers();
		this.encoding = encoding;
		this.colName = colName;
	}

	public void process() throws Exception {
		/* generate request */
		List<Request> reqModelList = new ArrayList<>();

		for (RequestSource reqRes : reqLists) {
			reqModelList.add(generateRequest(reqRes));
		}

		/* Generate collection */
		Collection col = new Collection(colName);

		// Set request to collection or folder
		for (Request req : reqModelList) {
			RequestItem reqItem = new RequestItem();
			reqItem.setName(req.getName());
			reqItem.setRequest(req);
			String folderName = req.getFolder();
			if (folderName == null || folderName.equals("")) {
				// set request to collection
				col.getItem().add(reqItem);
			} else {
				// set request to folder
				folderMap.get(folderName).addRequest(reqItem);
			}
		}
		
		/* Set folder to collection */
		for(Map.Entry<String, FolderItem> folderItem : folderMap.entrySet()) {
			col.getItem().add(folderItem.getValue());
		}

		/* generate file */
		filechooser.setDialogTitle("Save Postman Collection json file.");
		filechooser.setSelectedFile(new File(""));

		int selected = filechooser.showSaveDialog(null);

		// if cancel is selected, go to next attack save;
		if (selected == JFileChooser.CANCEL_OPTION)
			return;

		File file;

		if (filechooser.getSelectedFile().getPath().lastIndexOf(".json") == -1) {
			file = new File(filechooser.getSelectedFile().getPath() + ".json");
		} else {
			file = new File(filechooser.getSelectedFile().getPath());
		}
		// Confirm to replace exiting file.
		if (file.exists()) {
			String m = String.format("<html>%s already exists.<br>Do you want to replace it?", file.getAbsolutePath());
			int rv = JOptionPane.showConfirmDialog(filechooser, m, "Save As", JOptionPane.YES_NO_OPTION);
			if (rv != JOptionPane.YES_OPTION) {
				// if no is selected, do nothing.
				return;
			}
		}

		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding)))) {
			pw.write(gson.toJson(col));
			pw.flush();
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.flush();
			String stackTrace = sw.toString();
			new PrintWriter(callbacks.getStderr(), true).println(stackTrace);
			JLabel label = new JLabel("Failed to be generated. See Errors Tab on Extender Tab");
			JOptionPane.showMessageDialog(null, label,"Error", JOptionPane.ERROR_MESSAGE);
		}
		java.net.URL iconUrl = 
				  getClass().getClassLoader().getResource("TG-32-32.png");
		ImageIcon imageIcon = new ImageIcon(iconUrl);
		JLabel label = new JLabel("Export was done.");
		JOptionPane.showMessageDialog(null, label, "Message", JOptionPane.PLAIN_MESSAGE, imageIcon);
	}

	private Request generateRequest(RequestSource reqSource) throws Exception {
		String name = reqSource.getReqName();
		byte[] reqBytes = reqSource.getReq().getRequest();
		IRequestInfo iReqInfo = this.helpers.analyzeRequest(reqBytes);

		Request reqModel = new Request();

		// support only json, kvm, xml, and multipart
		byte cType = iReqInfo.getContentType();
		if (cType == IRequestInfo.CONTENT_TYPE_JSON || cType == IRequestInfo.CONTENT_TYPE_MULTIPART || cType == IRequestInfo.CONTENT_TYPE_XML) {
			int bodyOffset = iReqInfo.getBodyOffset();
			byte[] reqBodyBytes = new byte[reqBytes.length - bodyOffset];
			System.arraycopy(reqBytes, bodyOffset, reqBodyBytes, 0, reqBytes.length - bodyOffset);
			Body body = new Body();
			body.setMode("raw");
			body.setRaw(new String(reqBodyBytes, encoding));
			reqModel.setBody(body);
		} else if (cType == IRequestInfo.CONTENT_TYPE_URL_ENCODED) {
			List<BodyKeyValue> bodyList = getRequestParams(iReqInfo);
			Body body = new Body();
			body.setMode("urlencoded");
			body.setUrlencoded(bodyList);
			reqModel.setBody(body);
		} else if (cType == IRequestInfo.CONTENT_TYPE_NONE ){
		} else {
			throw new Exception("[" + reqSource.getReqName() +"] This content type is not supported.");			
		}

		// Set method
		reqModel.setMethod(iReqInfo.getMethod());

		// Set headers
		List<Map<String, String>> headerList = new ArrayList<>();
		for (int num = 1; num < iReqInfo.getHeaders().size(); num++) { // exclude
																		// request
																		// line
																		// like
																		// "POST
																		// /hoge
																		// HTTP/1.1"
			String[] header = iReqInfo.getHeaders().get(num).split(": ");
			Map<String, String> headerMap = new HashMap<>();
			if (!Arrays.asList(EXCLUDED_HEADERS).contains(header[0])) {
				headerMap.put("key", header[0]);
				headerMap.put("value", header[1]);
				headerList.add(headerMap);
			}
				// sb.append(iReqInfo.getHeaders().get(num) + "\n");
		}
		// reqModel.setHeader(sb.toString());
		reqModel.setHeader(headerList);

		// Set URL
		reqModel.setUrl(getURL(reqSource.getReq()));
	
		// Set Name
		reqModel.setName(name);
		
		// Set folder name
		String folderName = reqSource.getFolderName();
		if(folderName != null && !folderName.equals("")) {
			if(!folderMap.containsKey(folderName)) {
				// create folder
				folderMap.put(folderName, new FolderItem(folderName));
			}

			reqModel.setFolder(folderName);
		}
		
		return reqModel;
	}

	private List<BodyKeyValue> getRequestParams(IRequestInfo iReqInfo) throws Exception {
		List<IParameter> paramsList = iReqInfo.getParameters();

		String urlDecodedValue;
		IParameter params;
		byte typeByte;

		List<BodyKeyValue> data = new ArrayList<>();
		for (int row = 0; row < paramsList.size(); row++) {
			params = paramsList.get(row);
			typeByte = params.getType();
			// get URLDecoded value
			try {
				urlDecodedValue = URLDecoder.decode(params.getValue(), this.encoding);
				if (typeByte == IParameter.PARAM_BODY) {
					BodyKeyValue keyValue = new BodyKeyValue();
					keyValue.setKey(params.getName());
					keyValue.setValue(urlDecodedValue);
					data.add(keyValue);
				}
			} catch (Exception e) {
				throw e;
			}
		}
		return data;
	}

	private String getURL(IHttpRequestResponse requestResponse) {
		IHttpService iHS = requestResponse.getHttpService();
		String port = Integer.toString(iHS.getPort());
		String urlPort;
		if (port.equals("80") || port.equals("443"))
			urlPort = "";
		else
			urlPort = ":" + port;

		String url;
		String uri = new String(requestResponse.getRequest()).split("\n")[0].split(" ")[1];
		if (uri.startsWith("http"))
			url = uri;
		else
			url = iHS.getProtocol() + "://" + iHS.getHost() + urlPort
					+ new String(requestResponse.getRequest()).split("\n")[0].split(" ")[1];

		return url;
	}

}