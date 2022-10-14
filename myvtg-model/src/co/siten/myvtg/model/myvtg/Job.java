package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the JOB database table.
 * 
 */
@Entity
@NamedQuery(name="Job.findAll", query="SELECT j FROM Job j")
public class Job implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "JOB_SEQ", allocationSize = 1)
	private long id;

	private String des;

	@Column(name="\"LANGUAGE\"")
	private String language;

	private String name;

        
                
        @Column(name = "NAME_LC")
        private String nameLc;
        
        
	public Job() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
        
        public String getNameLc() {
            return nameLc;
        }

        public void setNameLc(String nameLc) {
            this.nameLc = nameLc;
        }

}