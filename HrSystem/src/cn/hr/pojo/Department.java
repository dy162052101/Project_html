package cn.hr.pojo;

public class Department {
	private int id;
	private int department_number;
	private String name;
	private String manager;
	private String telephone;
	private String address;
	private String notes;
	
	
	public Department(int id, int department_number, String name, String manager, String telephone, String address,
			String notes) {
		super();
		this.id = id;
		this.department_number = department_number;
		this.name = name;
		this.manager = manager;
		this.telephone = telephone;
		this.address = address;
		this.notes = notes;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDepartment_number() {
		return department_number;
	}
	public void setDepartment_number(int department_number) {
		this.department_number = department_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
