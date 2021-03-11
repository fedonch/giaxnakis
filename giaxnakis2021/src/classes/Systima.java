package classes;

public class Systima {
	
	private String name ;
	private String URL ;
	private int duration ;
	
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
	

}
