import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestiTutvustus {
	private String tutvustus;
	private String failinimi;
	private int reaNr;
	private int veeruNr;
	
	public String getTutvustus() {
		return tutvustus;
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
	
	public TestiTutvustus(String failinimi, int reaNr, int veeruNr) {
		this.failinimi = failinimi;
		this.reaNr = reaNr;
		this.veeruNr = veeruNr;
	}
	
	public TestiTutvustus(String failinimi) {
		this.failinimi = failinimi;
		this.reaNr = 1;
		this.veeruNr = 2;
	}

	public String loeTutvustust() throws Exception {
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
				tutvustus = osad[veeruNr-1]; 
				break;
			}
		}
		br.close();
		return tutvustus;
	}
}
