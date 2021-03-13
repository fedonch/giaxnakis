package classes;
import java.util.Date ;

public class Rantevou {

	private int KAR;
	private String day;
	private String time;
	
	private Asfalismenos asfalismenos;
	private EmboliastikoKentro kentro;
	private Doctor doctor;
	
	public Rantevou(int KAR,String day,String time,Asfalismenos asfalismenos,
			EmboliastikoKentro kentro,Doctor doctor) {
		this.KAR=KAR;
		this.day=day;
		this.time=time;
		this.asfalismenos=asfalismenos;
		this.kentro =kentro;
		this.doctor=doctor;
	}
	
	
	public int getKar() {
		return KAR;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getTime() {
		return time;
	}
	
	public Asfalismenos getAsfalismeno() {
		return asfalismenos;
	}
	
	public EmboliastikoKentro getKentro() {
		return kentro;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	
}
