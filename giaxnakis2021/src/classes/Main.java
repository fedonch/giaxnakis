package classes;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	
	static HashMap<Integer, Asfalismenos> asfalismenoi = new HashMap<Integer, Asfalismenos>();
	static HashMap<String, EmboliastikoKentro> kentra = new HashMap<String, EmboliastikoKentro>();
	
	static int uniqueCode =35;
	static Systima systima ;
	
	
	
	
	public static void main(String[] args) throws IOException {
        System.out.println("Welcome...");
        init_system();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice="0";
        while(true) {
        	showChoices();
        	choice = br.readLine();
        	switch(choice) 
        	{
        		case "1":
        			insertAsfalismeno();
        			break;
        		case "2":
        			insertEmbKentro();
        			break;
       		 	case "3":
       		 		insertDoctor();
       		 		break;
       		 	case "4":
       		 		toReserve();
       		 		break;
       		 	case "5":
       		 		searchAndPrintRantevou();
       		 		break;
        			 
       		 	default:
       		 		System.out.println("Pls select one of the cases below ");
        	}
        	
        }
        	
        	
	}
        
  

	static void showChoices() {
		System.out.println("Press 1 to insert Insured Person \n"
				+ "Press 2 to insert Vaccination Center\n"
				+ "Press 3 to insert Doctor\n"
				+ "Press 4 to plan a Randevouz\n"
				+ "Press 5 to overview your Randevouz\n");
	}
   
    
    
    static void insertAsfalismeno() throws IOException{
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
    	if (asfalismenoi.size()<100) {
    		asfalismenoi.put(amka,new Asfalismenos(amka,name,city));
    	}
    	
	
    }
    
    static void insertEmbKentro() throws IOException{
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
    		if (kentra.size()<10) {
    			kentra.put(city,new EmboliastikoKentro(code,name,city));
    		}
    		
    	}
    }

    static void insertDoctor() throws IOException{
    	
    	System.out.println("doctor credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter AM");
    	int am = Integer.parseInt(br.readLine());
    	while (!systima.getInstance().checkUnique(am)){
    		System.out.println("Pls enter unique AM");
        	am = Integer.parseInt(br.readLine());
    		
    	}
    	systima.getInstance().addUniqueAM(am);
    	System.out.println("Enter NAME:");
    	String name = br.readLine();
    	System.out.println("emboliastiko kentro credentials");	
    	System.out.println("Enter CITY:");
    	String city = br.readLine();
    	
    	
    	if (kentra.containsKey(city)) {
    		if (kentra.get(city).getCatalogue().size()<5) {
    			kentra.get(city).addDoctor(new Doctor(am,name));
    		}
    	}
    	
    	
    }	
    
    static void toReserve() throws IOException{
    	
    	String city="";
    	System.out.println("asfalismenos credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Enter AMKA");
    	int amka = Integer.parseInt(br.readLine());
    	
    	Asfalismenos tempAsfal = asfalismenoi.get(amka);
    	city=asfalismenoi.get(amka).getCity();
    	EmboliastikoKentro tempKentro = kentra.get(asfalismenoi.get(amka).getCity());
    	tempKentro.showFreeDates();
    	
    	System.out.println("Please give me day");
    	String day = br.readLine();
    	System.out.println("Please give me time");
    	String time = br.readLine();
    	Doctor best = tempKentro.findDocWithLessReser(day,time);
    	uniqueCode+=1;
    	Rantevou tempRantevou =new Rantevou(uniqueCode,day,time,tempAsfal,tempKentro,best);
    	
    	tempKentro.putRantevou(day,time,tempRantevou);
    	
    	showRantevouInfo(tempRantevou);
    	
    	systima.getInstance().addRantevou(tempRantevou);
    	
    	
    	
 	
    }
    
    static void searchAndPrintRantevou() throws IOException {
    	Rantevou rantevou=searchRantevou();
    	if (rantevou==null) {
    		return;
    	}
    	showRantevouInfo(rantevou);
    	
    }
    
    static Rantevou searchRantevou() throws IOException{
    	
    	System.out.println("Type selected search method (AMKA,EK,AM)  ");
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String method = br.readLine();
    	if (method.equalsIgnoreCase("AMKA")) {
    		System.out.println("Enter AMKA");
    		int key = Integer.parseInt(br.readLine());
    		for (Rantevou tempRantevou : systima.getInstance().getRantevousAll()) {
    			if(tempRantevou.getAsfalismeno().getAmka()==key) {
    				return tempRantevou;
    			}
    		}
    		
    	}
    	else if(method.equalsIgnoreCase("EK")){
    		System.out.println("Enter EK");
    		int key = Integer.parseInt(br.readLine());
    		for (Rantevou tempRantevou : systima.getInstance().getRantevousAll()) {
    			if(tempRantevou.getKentro().getCode()==key) {
    				return tempRantevou;
    			}
    		}	
    	}
    	else if(method.equalsIgnoreCase("AM")){
    		System.out.println("Enter AM");
    		int key = Integer.parseInt(br.readLine());
    		for (Rantevou tempRantevou : systima.getInstance().getRantevousAll()) {
    			if(tempRantevou.getDoctor().getAM()==key) {
    				return tempRantevou;
    			}
    		}
    	}
    	return null;
    	
    	
    	
    }
    
    
    static void showRantevouInfo(Rantevou rant) {
    	System.out.println(rant.getKar());
    	System.out.println(rant.getDay() +" "+rant.getTime());
    	System.out.println(rant.getAsfalismeno().getName());
    	System.out.println(rant.getDoctor().getName());
    	System.out.println(rant.getKentro().getName());
		
	}
    
    static void init_system() {
    	
    	for (int i=0;i<5;i++){
    		
            asfalismenoi.put((i+1)*11111111,new Asfalismenos((i+1)*11111111,"Asfalismenos "+(i+1),"XANIA"));
        }
        for (int i=5;i<9;i++) {
            asfalismenoi.put( (i+1)*11111111,new Asfalismenos((i+1)*11111111,"Asfalismenos "+(i+1),"RETHYMNO"));
        }
        
        kentra.put("XANIA",new EmboliastikoKentro(22222,"CH-22","XANIA"));
        kentra.put("RETHYMNO",new EmboliastikoKentro(33333,"RTH-33","RETHYMNO"));
        
        kentra.get("XANIA").addDoctor(new Doctor(111111,"Doctor 1"));
        kentra.get("XANIA").addDoctor(new Doctor(222222,"Doctor 2"));
        kentra.get("XANIA").addDoctor(new Doctor(333333,"Doctor 3"));

        kentra.get("RETHYMNO").addDoctor(new Doctor(444444,"Doctor 4"));
        kentra.get("RETHYMNO").addDoctor(new Doctor(555555,"Doctor 5"));

        //hmerologio.add(new Rantevou(20,new Date("Day 1",9:00)));

        //Class EmboliastikoKent
    //    Class Doctor
        //Class Rantevou
    }
    


}