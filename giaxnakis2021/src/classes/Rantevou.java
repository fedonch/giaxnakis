package classes;
import java.util.Date ;

public class Rantevou {

	private int KAR;
	private Date time;
	private Asfalismenos asfalismenos;
	private EmboliastikoKentro kentro;
	private Doctor doctor;
	
	public Rantevou(int KAR,Date time,Asfalismenos asfalismenos,
			EmboliastikoKentro kentro,Doctor doctor) {
		this.KAR=KAR;
		this.time=time;
		this.asfalismenos=asfalismenos;
		this.kentro =kentro;
		this.doctor=doctor;
	}
	
	
	public int getKar() {
		return KAR;
	}
	
	public Date getTime() {
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
