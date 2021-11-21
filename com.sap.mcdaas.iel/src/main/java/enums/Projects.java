package enums;

public enum Projects {
    IEL("iel");

    private final String project;

    private Projects(final String project) {
        this.project = project;
    }

    public String getProjectName(){
        return project;
    }

}
