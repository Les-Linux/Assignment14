package dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import interfaces.HyperscalerConfiguration;

import java.util.Map;

/**
 *  a pojo which stores project related configuration details
 *  read from the awsProjectConfiguration.yaml file
 */

public class HyperscalerConfigurationImpl implements HyperscalerConfiguration {
    @JsonProperty("projects")
    private Map<String, Map<String,String>> projects;

    public Map<String, Map<String, String>> getProjects() {
        return projects;
    }

    public void setProject(Map<String, Map<String, String>> projects) {
        this.projects = projects;
    }

    private String getProject(){
        return "project";
    }
}
