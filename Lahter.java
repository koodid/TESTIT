import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Lahter {
	private String lahtriSisu;
	private String failinimi;
	private int reaNr;
	private int veeruNr;
	
	public String getTutvustus() {
		return lahtriSisu;
	}
	public String getFailinimi() {
		return failinimi;
	}
	public int getReaNr() {
		return reaNr;
	}
	public int getVeeruNr() {
		return veeruNr;
	}
	
	public Lahter(String failinimi, int reaNr, int veeruNr) {
		this.failinimi = failinimi;
		this.reaNr = reaNr;
		this.veeruNr = veeruNr;
	}
	
	public Lahter(String failinimi) {
		this.failinimi = failinimi;
		this.reaNr = 1;
		this.veeruNr = 2;
	}

	public String loeLahter() throws Exception {
		File fail = new File(failinimi);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "cp1257"));
		String rida;
		int i = 1;
		while ((rida = br.readLine()) != null) {
			if (i < reaNr) {
				i++;
				continue;
			}
			else {
				String[] osad = rida.split(";");
				lahtriSisu = osad[veeruNr-1]; 
				break;
			}
		}
		br.close();
		return lahtriSisu;
	}
}
