package classes;

import java.util.ArrayList;





public class Systima {
	
	private String name ;
	private String URL ;
	private int duration ;
	private ArrayList<Rantevou> olaTaRantevou= new ArrayList<Rantevou>();
	private ArrayList<Integer> olaTaAMDoctor =new ArrayList<Integer>();
	private static Systima systima;
	
	
	public Systima() {
		this.name = "NVP";
		this.URL ="https://www.moh.gov.gr/articles/health/dieythynsh-dhmosias-ygieinhs/"
				+ "emboliasmoi/ethniko-programma-emboliasmwn-epe-enhlikwn/"
				+ "7968-ethniko-programma-emboliasmwn-enhlikwn-2020-2021";
		this.duration = 7;
		
	}
	
	public String getName() {
		return name;
	}
	
	public String getUrl() {
		return URL;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public ArrayList<Rantevou> getRantevousAll(){
		return olaTaRantevou;
	}
	
	public boolean checkUnique(int am) {
		if(olaTaAMDoctor.contains(am)) {
			return false;
		}
		return true;
	}
	
	public void addRantevou(Rantevou rantevou) {
		olaTaRantevou.add(rantevou);
	}
	
	public void addUniqueAM(int am) {
		olaTaAMDoctor.add(am);
	}
	
	public static Systima getInstance() {
		if (systima == null) {
			systima = new Systima();
		}
		return systima;
	}

}
