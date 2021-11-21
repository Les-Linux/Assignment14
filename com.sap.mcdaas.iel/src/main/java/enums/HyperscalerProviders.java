package enums;

/**
 *  an enum with hyperscaler descriptive names
 */
public enum HyperscalerProviders {
    AMAZON("aws"),
    GOOGLE("gcp"),
    MICROSOFT("azure");

    private String provider;

    HyperscalerProviders(final String provider){
        this.provider = provider;
    }

    public String getID(){
        return provider;
    }
}
