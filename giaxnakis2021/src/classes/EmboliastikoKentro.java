package classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmboliastikoKentro {
	
	private int code;
	private String name;
	private String city;
	private ArrayList<Doctor> catalogue =new ArrayList<Doctor> ();
	private HashMap<String ,HashMap<String,ArrayList<Rantevou>>> calendar=new HashMap<String ,HashMap<String,ArrayList<Rantevou>>>();
	private HashMap<String,ArrayList<Rantevou>> inner= new HashMap<String,ArrayList<Rantevou>>();
	private ArrayList<String> days =new ArrayList<String>();
	private ArrayList<String> times =new ArrayList<String>();
	private String time[]= {"9:00","9:30","10:00","10:30"};
	private HashMap<Doctor,Integer> doctorReservation = new HashMap<Doctor,Integer>();
	
	public EmboliastikoKentro(int code,String name,String city) {
		this.code=code;
		this.name=name;
		this.city=city;
		
		for(int j=0;j<4;j++) {
			times.add(time[j]);
		}
		
		for (int i =0;i<Systima.getInstance().getDuration();i++) {			
			days.add("day-"+String.valueOf(i+1));
		}
		for(int j=0;j<4;j++) {
			inner.put(time[j],new ArrayList<Rantevou>());
		}
		for (int i =0;i<Systima.getInstance().getDuration();i++) {			
			calendar.put(days.get(i), inner);
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

	// elegxw kai bazw giatro sti lista giatrwn tou emboliastikou kentrou
	
	public void addDoctor(Doctor doc) {
		if (catalogue.size()<5) {
			catalogue.add(doc);
			doctorReservation.put(doc,0);
		}
	}
	
	//tipwsi eleu8erwn imerominiwn gia kleisimo rantevou
	
	public void showFreeDates() {
		for (int i =0;i<Systima.getInstance().getDuration();i++) {
			for(int j=0;j<4;j++) {
				if (calendar.get(days.get(i)).get(time[j]).size()<5) {
					System.out.print(days.get(i) +" "+ time[j] + " ");
				}
			}
			System.out.println();
		}	
	}
	
	// psaxnww gia ton dia8esimo giatro 
	// diladi auton pou den exei tin sigkekrimeni wra kai mera rantevou 
	// kai exei ta ligotera rantevou ekeini tin periodo
	
	public Doctor findDocWithLessReser(String day ,String time) { // 2check
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
	
	// elegxw an mporei na kleisei rantevou o xristis
	// sti periptwsi pou ta stoixei amou edwse den einai swsta 
	
	public boolean checkCredentials(String day,String time) {
		if(!days.contains(day)) {
			System.out.println("There is no such day available");
			return false;
		}
		else if (!times.contains(time)) {
			System.out.println("There is no such time available");
			return false;
		}
		else if (calendar.get(day).get(time).size()==5) {
			System.out.println("This day and time we are full");
			return false;
		}
		else {
			return true;
		}
		
	}
	
	// elegxw an enas giatros exei rantevou 
	
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
		doctorReservation.put(rantevou.getDoctor(), doctorReservation.get(rantevou.getDoctor()) + 1);
	}
}
