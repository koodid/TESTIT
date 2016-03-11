
public class Edetabel implements Comparable<Edetabel> {
	public int compareTo(Edetabel vorreldav) {
	    if (punktideHulk < vorreldav.punktideHulk)
	        return 1; 
	    if (punktideHulk > vorreldav.punktideHulk)
	        return -1; 
	    return 0; 
	    }
	
	public int punktideHulk;
	public String nimetus;
	public String lisainfo;
	
	public int getPunktideHulk() {
		return punktideHulk;
	}
	public String getNimetus() {
		return nimetus;
	}
	public String getLisainfo() {
		return lisainfo;
	}
	
	public Edetabel(int punktideHulk, String nimetus, String lisainfo) {
		this.punktideHulk = punktideHulk;
		this.nimetus = nimetus;
		this.lisainfo = lisainfo;
	}
	
	public String toString() {
		return "Punktisumma: " + punktideHulk + ", " + nimetus + ", " + lisainfo + "; ";
	}
}
