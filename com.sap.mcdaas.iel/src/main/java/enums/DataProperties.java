package enums;

public enum DataProperties {
    TIMESTAMP("Timestamp"),
    TYPE_OF_PROTOCOL("Type_Of_Protocol"),
    LAST_SCAN_DATE("lastScanDate");

    private final String property;

    private DataProperties(final String property) {
        this.property = property;
    }

    public String getProperty(){
        return property;
    }
}
