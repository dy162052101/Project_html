package cn.hr.pojo;

public class Position {
	private int id;
	private int position_number;
	private String  name;
	private String level;
	private String notes;
	
	
	
	public Position(int id, int position_number, String name, String level, String notes) {
		super();
		this.id = id;
		this.position_number = position_number;
		this.name = name;
		this.level = level;
		this.notes = notes;
	}
	public Position() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPosition_number() {
		return position_number;
	}
	public void setPosition_number(int position_number) {
		this.position_number = position_number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
}
