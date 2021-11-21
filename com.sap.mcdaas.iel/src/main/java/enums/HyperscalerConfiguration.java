package enums;

public enum HyperscalerConfiguration {
    TOKEN("token"),
    PROJECT("projectId"),
    DATA_SOURCE("data-source");

    private final String property;

    private HyperscalerConfiguration(final String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

}
