package classes;

import java.util.ArrayList;

public class Doctor {

	private int AM;
	private String name;
	private ArrayList<Rantevou> personal =new ArrayList<Rantevou>();
	
	public Doctor(int AM ,String name) {
		this.AM=AM;
		this.name=name;
	}
	public Doctor() {}
	
	public int getAM() {
		return AM;
	}
	
	public String getName() {
		return name;
	}
	
	public void addRantevou(Rantevou rantevou) {
		personal.add(rantevou);
	}
	
}
