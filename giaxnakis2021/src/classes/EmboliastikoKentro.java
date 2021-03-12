package classes;

import java.util.ArrayList;
import java.util.HashMap;

public class EmboliastikoKentro {
	
	private int code;
	private String name;
	private String city;
	private ArrayList<Doctor> catalogue;
	private HashMap<String ,HashMap<String,ArrayList<Doctor>>> calendar=new HashMap<String ,HashMap<String,ArrayList<Doctor>>>();
	private HashMap<String,ArrayList<Doctor>> inner= new HashMap<String,ArrayList<Doctor>>();
	
	public EmboliastikoKentro(int code,String name,String city) {
		this.code=code;
		this.name=name;
		this.city=city;
		
		String days[]= {"Mon","Tue","Thur","Wed","Fri","Sat","Sun"};
		String time[]= {"9:00","9:30","10:00","10:30"};
		for(int j=0;j<4;j++) {
			inner.put(time[j],new ArrayList<Doctor>());
		}
		for (int i =0;i<7;i++) {			
			calendar.put(days[i], inner);
		}	
		
		System.out.print(calendar);
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
	
	public ArrayList<Doctor> getCatalogue() {
		return this.catalogue;
	}

	public void addDoctor(Doctor doc) {
		this.catalogue.add(doc);
	}
	
	public void showDates() {
		
		
	}
	
}
