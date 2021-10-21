import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Hyperscalers {
    @JsonProperty("hyperscalers")
    private Map<String,Map<String,String>> hyperScalers;

    public Map<String, Map<String, String>> getHyperScalers() {
        return hyperScalers;
    }

    public void setHyperScalers(Map<String, Map<String, String>> hyperScalers) {
        this.hyperScalers = hyperScalers;
    }
}
