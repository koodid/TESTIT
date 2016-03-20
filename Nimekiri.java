import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Nimekiri {
	private String failinimi;
	private ArrayList<String> nimekiri = new ArrayList<>();
	private int reaNr;
	private int veeruNr;
	private ArrayList<Integer> kodeeritudVastused = new ArrayList<>();
	
	public String getFailinimi() {
		return failinimi;
	}
	public ArrayList<String> getNimekiri() {
		return nimekiri;
	}	
	public int getReaNr() {
		return reaNr;
	}
	public int getVeeruNr() {
		return veeruNr;
	}
	
	// Nii veergudes kui ka ridades tuleb konstruktorisse esimene rida märkida numbriga 1 
	// ja arvestada alates sellest numbrist.
	public ArrayList<String> loeNimekiriVeerust(String failinimi, int esimeseReaNr, int veeruNr) throws Exception {
		File fail = new File(failinimi);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "cp1257"));
		String rida;
		int i = 1;
		while ((rida = br.readLine()) != null) {
			if (i < esimeseReaNr) {
				i++;
				continue;
			}
			else {
				String[] osad = rida.split(";");
				nimekiri.add(osad[veeruNr-1]); 
			}
		}
		br.close();
		return nimekiri;
	}
	
	public ArrayList<String> loeNimekiriReast(String failinimi, int reaNr, int esimeseVeeruNr) throws Exception {
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
				for (int j = esimeseVeeruNr - 1; j < osad.length; j++){
					nimekiri.add(osad[j]); 
				}
				break;
			}
		}
		br.close();
		return nimekiri;
	}	
	
	public ArrayList<Integer> kodeeriVastused(ArrayList<String> valikvastusteList, ArrayList<String> vastusteList) {
		for (int i = 0; i < vastusteList.size(); i++){
			String[] valikvastused = valikvastusteList.get(i).split(",");
			for (int j = 0; j < valikvastused.length; j++){
				String vastus = valikvastused[j].trim();
				if ((vastusteList.get(i)).equalsIgnoreCase(vastus)) {
					kodeeritudVastused.add(j);
				}
			}
		}
		return kodeeritudVastused;
	}
}
	
