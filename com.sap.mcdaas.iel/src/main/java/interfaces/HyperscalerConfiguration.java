package interfaces;

import java.util.Map;

public interface HyperscalerConfiguration {

    public Map<String, Map<String, String>> getProjects();

    public void setProject(Map<String, Map<String, String>> projects);

}
