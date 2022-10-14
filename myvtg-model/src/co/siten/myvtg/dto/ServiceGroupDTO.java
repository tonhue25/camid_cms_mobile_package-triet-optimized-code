package co.siten.myvtg.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author daibq
 *
 */
public class ServiceGroupDTO {

    private String groupName;
    private String groupCode;
    private List<ConsultantDTO> services;

    public ServiceGroupDTO() {
    }

    public ServiceGroupDTO(String groupName, String groupCode, ConsultantDTO service) {
        super();
        if (services == null) {
            services = new ArrayList<>();
        }
        services.add(service);
        this.groupName = groupName;
        this.groupCode = groupCode;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public List<ConsultantDTO> getServices() {
        return services;
    }

    public void setServices(List<ConsultantDTO> services) {
        this.services = services;
    }

}
