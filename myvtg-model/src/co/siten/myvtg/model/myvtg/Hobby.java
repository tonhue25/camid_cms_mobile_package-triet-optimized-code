package co.siten.myvtg.model.myvtg;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HOBBY database table.
 * 
 */
@Entity
@NamedQuery(name="Hobby.findAll", query="SELECT h FROM Hobby h")
public class Hobby implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "HOBBY_SEQ", allocationSize = 1)
	private long id;

	private String des;

	@Column(name="\"LANGUAGE\"")
	private String language;

	private String name;
        
                
        @Column(name = "NAME_LC")
        private String nameLc;
        

	public Hobby() {
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