package classes;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	
	static Systima systima =Systima.getInstance() ;
	
	public static void main(String[] args) throws IOException {
        System.out.println("Welcome...");
        init_system();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String choice="0";
        while(true) {
        	showChoices();
        	choice = br.readLine();
        	switch(choice) {
        		case "1":
        			systima.insertAsfalismeno();
        			break;
        		case "2":
        			systima.insertEmbKentro();
        			break;
       		 	case "3":
       		 		systima.insertDoctor();
       		 		break;
       		 	case "4":
       		 		systima.toReserve();
       		 		break;
       		 	case "5":
       		 		systima.searchAndPrintRantevou();
       		 		break;
       		 	case "6": 
       		 		System.out.println("Adios ");
       		 		System.exit(0);
       		 	default:
       		 		System.out.println("Pls select one of the cases below ");
        	}	
        } 	
	}
    
	static void showChoices() {
		System.out.println("\n"
				+"Press 1 to insert Insured Person \n"
				+ "Press 2 to insert Vaccination Center\n"
				+ "Press 3 to insert Doctor\n"
				+ "Press 4 to plan a Randevouz\n"
				+ "Press 5 to overview your Randevouz\n"
				+ "Press 6 to exit program\n");
	}
   
    static void init_system() {
    	
    	for (int i=0;i<5;i++){
            systima.getAsfalismenoi().put((i+1)*11111111,new Asfalismenos((i+1)*11111111,"Asfalismenos "+(i+1),"XANIA"));
        }
        for (int i=5;i<9;i++) {
        	systima.getAsfalismenoi().put( (i+1)*11111111,new Asfalismenos((i+1)*11111111,"Asfalismenos "+(i+1),"RETHYMNO"));
        }
        
        systima.getKentra().put("XANIA",new EmboliastikoKentro(22222,"CH-22","XANIA"));
        systima.getKentra().put("RETHYMNO",new EmboliastikoKentro(33333,"RTH-33","RETHYMNO"));
        		
        Doctor doc1 =  new Doctor(111111,"Doctor 1");
        Doctor doc2 = new Doctor(222222,"Doctor 2");
        Doctor doc3 =new Doctor(333333,"Doctor 3");
        
        systima.addUniqueAM(111111);
        systima.addUniqueAM(2*111111);
        systima.addUniqueAM(3*111111);
        
        systima.getKentra().get("XANIA").addDoctor(doc1);
        systima.getKentra().get("XANIA").addDoctor(doc2);
        systima.getKentra().get("XANIA").addDoctor(doc3);
        
        Doctor doc4 =new Doctor(444444,"Doctor 4");
        Doctor doc5 =new Doctor(555555,"Doctor 5");
        systima.getKentra().get("RETHYMNO").addDoctor(doc4);
        systima.getKentra().get("RETHYMNO").addDoctor(doc5);
        
        Rantevou tempRantevou =new Rantevou(20,"day-1","9:00",systima.getAsfalismenoi().get(11111111),systima.getKentra().get("XANIA"),doc1);
        systima.getKentra().get("XANIA").putRantevou("day-1","9:00",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(21,"day-1","10:30",systima.getAsfalismenoi().get(2*11111111),systima.getKentra().get("XANIA"),doc1);
    	systima.getKentra().get("XANIA").putRantevou("day-1","10:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(22,"day-1","10:30",systima.getAsfalismenoi().get(3*11111111),systima.getKentra().get("XANIA"),doc2);
    	systima.getKentra().get("XANIA").putRantevou("day-1","10:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(23,"day-1","10:30",systima.getAsfalismenoi().get(4*11111111),systima.getKentra().get("XANIA"),doc3);
    	systima.getKentra().get("XANIA").putRantevou("day-1","10:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(24,"day-2","9:30",systima.getAsfalismenoi().get(5*11111111),systima.getKentra().get("XANIA"),doc1);
    	systima.getKentra().get("XANIA").putRantevou("day-2","9:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(30,"day-2","9:30",systima.getAsfalismenoi().get(6*11111111),systima.getKentra().get("RETHYMNO"),doc4);
    	systima.getKentra().get("RETHYMNO").putRantevou("day-2","9:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(31,"day-2","9:30",systima.getAsfalismenoi().get(7*11111111),systima.getKentra().get("RETHYMNO"),doc5);
    	systima.getKentra().get("RETHYMNO").putRantevou("day-2","9:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(32,"day-2","10:00",systima.getAsfalismenoi().get(8*11111111),systima.getKentra().get("RETHYMNO"),doc5);
    	systima.getKentra().get("RETHYMNO").putRantevou("day-2","10:00",tempRantevou);
    	systima.addRantevou(tempRantevou);
    	
    	tempRantevou =new Rantevou(33,"day-3","9:30",systima.getAsfalismenoi().get(9*11111111),systima.getKentra().get("RETHYMNO"),doc4);
    	systima.getKentra().get("RETHYMNO").putRantevou("day-3","9:30",tempRantevou);
    	systima.addRantevou(tempRantevou);
   
    }
}

