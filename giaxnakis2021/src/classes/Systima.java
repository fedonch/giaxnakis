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

	// function gia na eggrafw sto systima ena kainourgio asfalismeno
	// zitaw na mou dwsei ena ari8mo amka elegxw gia na einai 10 digits
	// mou dinei onoma kai city kai ton bazw sto systima 
	// ama exw ftasei tous 100 asfalismenous tote den ton kanw eggrafi 
	
	
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
	
	
	// function gia na eggrafw sto systima ena kainourgio emboliastiko kentro 
	// zitaw na mou dwsei ena code  elegxw gia na einai 5 digits
	// sa kleidi exw epile3ei to city giati stin ekfwnisi leei na einai monadiko
	// ama exw ftasei ta 10 kentra tote den to kanw eggrafi 
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
    		
    		}else {
    			System.out.println("This city has already an EK");
    		}
    	}else {
    		System.out.println("You've reached maximum capacity");
    	}
    }
	
	
	// function gia na eggrafw sto systima ena kainourgio giatro  
	// zitaw na mou dwsei ena AM  elegxw gia na einai monadiko
	// zitaw to emboliastiko kentro tou giatrou an denuparxei petaw la8os
	// ama exw ftasei tous 5 giatrous sto emboliastiko kentro tote den ton bazw 
	
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
    	System.out.println("emboliastiko kentro credentials ");	
    	System.out.println("Enter CITY (caps) :");
    	String city = br.readLine();
    	
    	
    	if (kentra.containsKey(city)) {
    		if (kentra.get(city).getCatalogue().size()<5) {
    			kentra.get(city).addDoctor(new Doctor(am,name));
    		}else {
        		System.out.println("You've reached maximum capacity of doctors");
        	}
    	}else {
    		System.out.println("There is no EK in this city");
    	}
    	
    	
    }	
	
	// function gia na ftiaxw kainoyrgio rantevou
	// zitaw to AMKA tou asfalimenou prepei na eina iidi sto systima alliws petaw minima\
	// emfanizw tis eleu8eres imerominies sto emboliastiko kentro tis polis tou asfalismenou
	// zitaw na mou dwsei mia apo tis parapanw imerominies 
	// ama mou dwsei mera i wra pou den einai dia8esimi i den uparxei petaw minima
	// alliws ftiaxnw to rantevou
	
	
	public void toReserve() throws IOException{
    	
    	
    	System.out.println("asfalismenos credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter AMKA");
    	int amka = Integer.parseInt(br.readLine());
    	
    	Asfalismenos tempAsfal = asfalismenoi.get(amka);
    	if (tempAsfal ==null) {
    		System.out.println("There is no insured person with this AMKA ");	
    		return;
    	}
    	String city=asfalismenoi.get(amka).getCity();
    	EmboliastikoKentro tempKentro = kentra.get(city);
    	tempKentro.showFreeDates();
    	
    	System.out.println("Please give me day");
    	String day = br.readLine();
    	System.out.println("Please give me time");
    	String time = br.readLine();
    	if (!tempKentro.checkCredentials(day,time)) {
    		
    		return;
    	}
    	Doctor best = tempKentro.findDocWithLessReser(day,time);
    	uniqueCode+=1;
    	Rantevou tempRantevou =new Rantevou(uniqueCode,day,time,tempAsfal,tempKentro,best);
    	tempKentro.putRantevou(day,time,tempRantevou);
    	addRantevou(tempRantevou);
    	showRantevouInfo(tempRantevou);
    }
	
	// function gia na kanei print ena rantevou
	
	public void searchAndPrintRantevou() throws IOException {
		ArrayList<Rantevou> rantevou=searchRantevou();
	    if (rantevou==null) {
	    	return;
	    }
	   	showRantevousInfo(rantevou);
	    	
	}
	    
	// function pou psaxnei gia rantevou me basi ena apo ta zitoumena AMKA,EK,AM
	// o xristis prepei stin arxi na epile3ei me poio apo ta 3 stoixeia 8elei na ginei i anazitisi
	// epistrefei mia lista apo rantevou 
	// gia opoiodipote la8os petaw minima
	
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
		}else {
			System.out.println("You haven't given me an available search method");
		}
		return temp;  	
	}
	 
	// diaxeirisi listas gia ektipwsi twn rantevou
	
	public void showRantevousInfo(ArrayList<Rantevou> rant) {
		for (Rantevou tempRantevou :rant) {
			showRantevouInfo(tempRantevou);	
		}	
	}
	
	// function gia na tipwnw ta stoixeia gia to rantevou
	
	public void showRantevouInfo(Rantevou tempRantevou) {
		System.out.println("Rantevou Unique Code: " + tempRantevou.getKar());
		System.out.println("Day And Time: " +tempRantevou.getDay() +" "+tempRantevou.getTime());
		System.out.println("Insured Person's AMKA: " +tempRantevou.getAsfalismeno().getAmka());
		System.out.println("Doctor's AM: " +tempRantevou.getDoctor().getAM());
		System.out.println("Center's City: " +tempRantevou.getKentro().getCity());
	}
	
	// singleton
	public static Systima getInstance() {
		if (systima == null) {
			systima = new Systima();
		}
		return systima;
	}

}
