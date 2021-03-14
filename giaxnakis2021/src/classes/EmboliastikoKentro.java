package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmboliastikoKentro {
	
	private int code;
	private String name;
	private String city;
	private ArrayList<Doctor> catalogue =new ArrayList<Doctor> ();
	private HashMap<String ,HashMap<String,ArrayList<Rantevou>>> calendar=new HashMap<String ,HashMap<String,ArrayList<Rantevou>>>();
	private HashMap<String,ArrayList<Rantevou>> inner= new HashMap<String,ArrayList<Rantevou>>();
	private String days[]= {"day-1","day-2","day-3","day-4","day-5","day-6","day-7"};
	private String time[]= {"9:00","9:30","10:00","10:30"};
	private HashMap<Doctor,Integer> doctorReservation = new HashMap<Doctor,Integer>();
	
	public EmboliastikoKentro(int code,String name,String city) {
		this.code=code;
		this.name=name;
		this.city=city;
			
		for(int j=0;j<4;j++) {
			inner.put(time[j],new ArrayList<Rantevou>());
		}
		for (int i =0;i<7;i++) {			
			calendar.put(days[i], inner);
		}			
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
		if (catalogue.size()<5) {
			catalogue.add(doc);
			doctorReservation.put(doc,0);
		}
		
	}
	
	public void showFreeDates() {
		for (int i =0;i<7;i++) {
			for(int j=0;j<4;j++) {
				if (calendar.get(days[i]).get(time[j]).size()<5) {
					System.out.print(days[i] +" "+ time[j] + " ");
				}
			}
			System.out.println();
		}	
	}
	
	public Doctor findDocWithLessReser(String day ,String time) {
		int min=40;
		ArrayList <Rantevou> thisDayTime =calendar.get(day).get(time);
		Doctor less =new Doctor();
		for(Map.Entry<Doctor, Integer> entry : doctorReservation.entrySet()) {
			if (!doctorHasRantevou(thisDayTime,entry.getKey())) {
				if (doctorReservation.get(entry.getKey())<min) {
					less = entry.getKey();	
				}
			}
		}
		return less;
	}
	
	public boolean doctorHasRantevou(ArrayList <Rantevou> rant , Doctor doc) {
		for (Rantevou rantevou : rant) {
			if(rantevou.getDoctor().equals(doc)) {
				return true;
			}
		}
		return false;
	}

	public void putRantevou(String day,String time, Rantevou rantevou) {
		calendar.get(day).get(time).add(rantevou);
		doctorReservation.put(rantevou.getDoctor(), //doctorReservation.get(rantevou.getDoctor()) + 1);
				2);	
	}
	
}
