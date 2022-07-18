package assess.model;

import java.util.List;
import java.util.ArrayList;

public class Body {
    private List<BodyKeyValue> urlencoded = new ArrayList<>();
    private String mode;
    private String raw;

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }

    public void setUrlencoded(List<BodyKeyValue> urlencoded) {
        this.urlencoded = urlencoded;
    }

    public List<BodyKeyValue> getUrlencoded() {
        return this.urlencoded;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getRaw() {
        return this.raw;
    }
}
