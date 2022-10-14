package co.siten.myvtg.model.myvtg;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "CONFIG_DONATE")
public class ConfigDonate implements Serializable {
    @Id
    @Basic(optional = false)
    @NotNull
    @SequenceGenerator(name = "CONFIG_DONATE_SEQ_GENERATOR", sequenceName = "CONFIG_DONATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONFIG_DONATE_SEQ_GENERATOR")
    @Column(name = "ID")
    private Long id;
    @Column(name = "PARAM_NAME")
    private String paramName;
    @Column(name = "PARAM_KEY")
    private String paramKey;
    @Column(name = "PARAM_VALUE")
    private String pramValue;
    @Column(name = "STATUS")
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getPramValue() {
        return pramValue;
    }

    public void setPramValue(String pramValue) {
        this.pramValue = pramValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
