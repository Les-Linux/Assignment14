package enums;

public enum AwsResources {
    // property names
    BUCKET("bucket"),
    REGION("region"),
    OBJECT_NAME("objectName");

    private final String resource;

    private AwsResources(final String resource) {
        this.resource = resource;
    }

    public String getResourceName(){
        return resource;
    }
}
