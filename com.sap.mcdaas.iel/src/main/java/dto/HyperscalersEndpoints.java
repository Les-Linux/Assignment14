package dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class HyperscalersEndpoints {
    /**
     *  a pojo for storing hyperscaler endpoint(s) / api information
     */
    @JsonProperty("hyperscalers")
    private Map<String,Map<String,String>> endpoint;

    public Map<String, Map<String, String>> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Map<String, Map<String, String>> endpoint) {
        this.endpoint = endpoint;
    }
}
