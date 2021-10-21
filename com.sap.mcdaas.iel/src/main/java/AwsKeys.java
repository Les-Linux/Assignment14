import com.fasterxml.jackson.annotation.JsonProperty;

public class AwsKeys {
    @JsonProperty("access")
    private String access;

    @JsonProperty("secret")
    private String secret;

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
