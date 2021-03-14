package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;





public class Systima {
	
	private String name ;
	private String URL ;
	private int duration ;
	private ArrayList<Rantevou> olaTaRantevou= new ArrayList<Rantevou>();
	private ArrayList<Integer> olaTaAMDoctor =new ArrayList<Integer>();
	private static Systima systima;
	
	private HashMap<Integer, Asfalismenos> asfalismenoi = new HashMap<Integer, Asfalismenos>();
	private HashMap<String, EmboliastikoKentro> kentra = new HashMap<String, EmboliastikoKentro>();
	private int uniqueCode =35;
	
	public Systima() {
		this.name = "NVP";
		this.URL ="https://www.moh.gov.gr/articles/health/dieythynsh-dhmosias-ygieinhs/"
				+ "emboliasmoi/ethniko-programma-emboliasmwn-epe-enhlikwn/"
				+ "7968-ethniko-programma-emboliasmwn-enhlikwn-2020-2021";
		this.duration = 7;
		
	}
	
	public HashMap<Integer, Asfalismenos> getAsfalismenoi(){
		return asfalismenoi;
	}
	
	public HashMap<String, EmboliastikoKentro> getKentra(){
		return kentra;
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

	public void insertAsfalismeno() throws IOException{
    	if (asfalismenoi.size()<100) {
    		int amka=0;
    		System.out.println("asfalizomenos credentials");
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		while(String.valueOf(amka).length()!=10) {
    			System.out.println("Enter AMKA (10_digit)");
    			amka = Integer.parseInt(br.readLine());
    		}
    		System.out.println("Enter NAME:");
    		String name = br.readLine();
    		System.out.println("Enter CITY:");
    		String city = br.readLine();
    	
    		asfalismenoi.put(amka,new Asfalismenos(amka,name,city));
    	}else {
    		System.out.println("You've reached maximum capacity");
    	}
    }
	
	public void insertEmbKentro() throws IOException{
    	if (kentra.size()<10) {
    		int code=0;
    		System.out.println("emboliastiko kentro credentials");	
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		while(String.valueOf(code).length()!=5) {
    			System.out.println("Enter CODE (5_digit)");
    			code = Integer.parseInt(br.readLine());
    		}
    		System.out.println("Enter NAME:");
    		String name = br.readLine();
    		System.out.println("Enter CITY:");
    		String city = br.readLine();
    	
    		if (!kentra.containsKey(city)) {
    			kentra.put(city,new EmboliastikoKentro(code,name,city));
    		
    		}
    	}else {
    		System.out.println("You've reached maximum capacity");
    	}
    }
	
	public void insertDoctor() throws IOException{
    	
    	System.out.println("doctor credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter AM");
    	int am = Integer.parseInt(br.readLine());
    	while (!checkUnique(am)){
    		System.out.println("Pls enter unique AM");
        	am = Integer.parseInt(br.readLine());
    		
    	}
    	addUniqueAM(am);
    	System.out.println("Enter NAME:");
    	String name = br.readLine();
    	System.out.println("emboliastiko kentro credentials");	
    	System.out.println("Enter CITY:");
    	String city = br.readLine();
    	
    	
    	if (kentra.containsKey(city)) {
    		if (kentra.get(city).getCatalogue().size()<5) {
    			kentra.get(city).addDoctor(new Doctor(am,name));
    		}else {
        		System.out.println("You've reached maximum capacity of doctors");
        	}
    	}
    	
    	
    }	
	
	public void toReserve() throws IOException{
    	
    	
    	System.out.println("asfalismenos credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter AMKA");
    	int amka = Integer.parseInt(br.readLine());
    	
    	Asfalismenos tempAsfal = asfalismenoi.get(amka);
    	if (tempAsfal ==null) {
    		System.out.println("There is no insured person with this AMKA \n"
    				+ "you can try inserting one");
    		return;
    	}
    	String city=asfalismenoi.get(amka).getCity();
    	EmboliastikoKentro tempKentro = kentra.get(city);
    	tempKentro.showFreeDates();
    	
    	System.out.println("Please give me day");
    	String day = br.readLine();
    	System.out.println("Please give me time");
    	String time = br.readLine();
    	Doctor best = tempKentro.findDocWithLessReser(day,time);
    	uniqueCode+=1;
    	Rantevou tempRantevou =new Rantevou(uniqueCode,day,time,tempAsfal,tempKentro,best);
    	tempKentro.putRantevou(day,time,tempRantevou);
    	addRantevou(tempRantevou);
    	showRantevouInfo(tempRantevou);
    }
	
	public void searchAndPrintRantevou() throws IOException {
		ArrayList<Rantevou> rantevou=searchRantevou();
	    if (rantevou==null) {
	    	return;
	    }
	   	showRantevousInfo(rantevou);
	    	
	}
	    
	public ArrayList<Rantevou> searchRantevou() throws IOException{
	    ArrayList<Rantevou> temp =new ArrayList<Rantevou>();
		System.out.println("Type selected search method (AMKA,EK,AM)  ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String method = br.readLine();
		if (method.equalsIgnoreCase("AMKA")) {
			System.out.println("Enter AMKA");
			int key = Integer.parseInt(br.readLine());
			for (Rantevou tempRantevou : systima.getRantevousAll()) {
				if(tempRantevou.getAsfalismeno().getAmka()==key) {
					temp.add(tempRantevou);
				}
			}
			if (temp.isEmpty()) {
				System.out.println("No randevouz found for this AMKA");
			}
	    		
		}
		else if(method.equalsIgnoreCase("EK")){
			System.out.println("Enter EK");
			int key = Integer.parseInt(br.readLine());
			
			for (Rantevou tempRantevou : systima.getRantevousAll()) {
				if(tempRantevou.getKentro().getCode()==key) {
					temp.add(tempRantevou);
				}
			}
			if (temp.isEmpty()) {
				System.out.println("No randevouz found for this EK");
			}
		}
		else if(method.equalsIgnoreCase("AM")){
			System.out.println("Enter AM");
			int key = Integer.parseInt(br.readLine());
			for (Rantevou tempRantevou : systima.getRantevousAll()) {
				if(tempRantevou.getDoctor().getAM()==key) {
					temp.add(tempRantevou);
				}
			}
			if (temp.isEmpty()) {
				System.out.println("No randevouz found for this AM");
			}
		}
		return temp;  	
	}
	 
	public void showRantevousInfo(ArrayList<Rantevou> rant) {
		for (Rantevou tempRantevou :rant) {
			System.out.println(tempRantevou.getKar());
			System.out.println(tempRantevou.getDay() +" "+tempRantevou.getTime());
			System.out.println(tempRantevou.getAsfalismeno().getAmka());
			System.out.println(tempRantevou.getDoctor().getAM());
			System.out.println(tempRantevou.getKentro().getCode());
		}

			
	}
	
	public void showRantevouInfo(Rantevou tempRantevou) {
	
		System.out.println(tempRantevou.getKar());
		System.out.println(tempRantevou.getDay() +" "+tempRantevou.getTime());
		System.out.println(tempRantevou.getAsfalismeno().getAmka());
		System.out.println(tempRantevou.getDoctor().getAM());
		System.out.println(tempRantevou.getKentro().getCode());
	}
	
	public static Systima getInstance() {
		if (systima == null) {
			systima = new Systima();
		}
		return systima;
	}

}
