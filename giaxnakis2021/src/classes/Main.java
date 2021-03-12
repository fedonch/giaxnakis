package classes;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main {
	
	static HashMap<Integer, Asfalismenos> asfalismenoi = new HashMap<Integer, Asfalismenos>();
	static HashMap<String, EmboliastikoKentro> kentra = new HashMap<String, EmboliastikoKentro>();
	static HashMap<String, Rantevou> hmerologio = new HashMap<String, Rantevou>();
	//static ArrayList<Asfalismenos> asfalismenoi = new ArrayList<Asfalismenos>(100);
    //static ArrayList<EmboliastikoKentro> kentra = new ArrayList<EmboliastikoKentro>(10);
    //static ArrayList<Rantevou> hmerologio = new ArrayList<Rantevou>();
	
	
	public static void main(String[] args) throws IOException {
        System.out.println("Hello World");
        
        init_system();
        //insertAsfalismeno();
        //insertEmbKentro();
        //insertDoctor();
        //toReserve();
        //findPrintAppointment();
    }

	
   
    
    
    static void insertAsfalismeno() throws IOException{
    	
    	System.out.print("emboliastiko kentro credentials");
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Enter AMKA");
    	int amka = Integer.parseInt(br.readLine());
    	System.out.print("Enter NAME:");
    	String name = br.readLine();
    	System.out.print("Enter CITY:");
    	String city = br.readLine();
    	if (asfalismenoi.size()<100) {
    		asfalismenoi.put(amka,new Asfalismenos(amka,name,city));
    	}
    	
	
    }
    
    static void insertEmbKentro() throws IOException{
    	
    	System.out.print("emboliastiko kentro credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Enter CODE");
    	int code = Integer.parseInt(br.readLine());
    	System.out.print("Enter NAME:");
    	String name = br.readLine();
    	System.out.print("Enter CITY:");
    	String city = br.readLine();
    	
    	if (!kentra.containsKey(city)) {
    		if (kentra.size()<10) {
    			kentra.put(city,new EmboliastikoKentro(code,name,city));
    		}
    		
    	}
    }

    static void insertDoctor() throws IOException{
    	
    	System.out.print("doctor credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Enter AM");
    	int am = Integer.parseInt(br.readLine());
    	System.out.print("Enter NAME:");
    	String name = br.readLine();
    	System.out.print("emboliastiko kentro credentials");	
    	System.out.print("Enter CITY:");
    	String city = br.readLine();
    	
    	
    	if (kentra.containsKey(city)) {
    		if (kentra.get(city).getCatalogue().size()<5) {
    			kentra.get(city).addDoctor(new Doctor(am,name));
    		}
    	}
    	
    	
    }	
    
    static void toReserve() throws IOException{
    	
    	String city="";
    	System.out.print("asfalismenos credentials");	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	System.out.print("Enter AMKA");
    	int amka = Integer.parseInt(br.readLine());
    	
    	Asfalismenos tempAsfal = asfalismenoi.get(amka);
    	city=asfalismenoi.get(amka).getCity();
    	EmboliastikoKentro tempKentro = kentra.get(asfalismenoi.get(amka).getCity());
    	
    	
    	
    	
    	
    }
    
    static void init_system() {
    	for (int i=0;i<5;i++) {
    		
            asfalismenoi.put((i+1)*11111111,new Asfalismenos((i+1)*11111111,"Asfalismenos "+(i+1),"XANIA"));
        }
        for (int i=5;i<9;i++) {
            asfalismenoi.put( (i+1)*11111111,new Asfalismenos((i+1)*11111111,"Asfalismenos "+(i+1),"RETHYMNO"));
        }
        kentra.put("XANIA",new EmboliastikoKentro(22222,"CH-22","XANIA"));
        //kentra.put("RETHYMNO",new EmboliastikoKentro(33333,"RTH-33","RETHYMNO"));


        //kentra.get("XANIA").addDoctor(new Doctor(111111,"Doctor 1"));
        //kentra.get("XANIA").addDoctor(new Doctor(222222,"Doctor 2"));
        //kentra.get("XANIA").addDoctor(new Doctor(333333,"Doctor 3"));

        //kentra.get("RETHYMNO").addDoctor(new Doctor(444444,"Doctor 4"));
        //kentra.get("RETHYMNO").addDoctor(new Doctor(555555,"Doctor 5"));

        //hmerologio.add(new Rantevou(20,new Date("Day 1",9:00)));

        //Class EmboliastikoKent
    //    Class Doctor
        //Class Rantevou
    }
    


}