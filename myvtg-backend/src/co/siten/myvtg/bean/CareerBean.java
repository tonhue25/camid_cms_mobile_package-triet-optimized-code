package co.siten.myvtg.bean;

public class CareerBean {
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CareerBean(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

}
