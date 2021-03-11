package classes;

public class EmboliastikoKentro {
	
	private int code;
	private String name;
	private String city;
	
	
	public EmboliastikoKentro(int code,String name,String city) {
		this.code=code;
		this.name=name;
		this.city=city;
	}

	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCity() {
		return city;
	}
	
}
