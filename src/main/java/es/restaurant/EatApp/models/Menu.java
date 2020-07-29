package es.restaurant.EatApp.models;

public class Menu {

	private Long id;
	private String description;
	//private List<Product> products; // TODO Add

	public Menu(Long id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Menu(String description) {
		this.description = description;
	}
	
	public boolean equals(Menu u) {
		return (this.id == u.getId() 
				&& this.description.compareTo(u.getDescription())== 0);
	}

}