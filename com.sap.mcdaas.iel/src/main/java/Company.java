public enum Company {
    AMAZON("aws"),
    GOOGLE("gcp"),
    MICROSOFT("azure");

    private String company;

    Company (final String company){
        this.company = company;
    }

    public String getID(){
        return company;
    }
}
