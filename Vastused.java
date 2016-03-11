import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Vastused {
	
	private ArrayList<Integer> kodeeritudVastused = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> kodeeritudVastusedListidena = new ArrayList<>();
	private ArrayList<ArrayList<String>> vastusedListidena = new ArrayList<>();
	private ArrayList<String> valikvastusteList;
	private ArrayList<String> vastusteList;
	
	public ArrayList<Integer> getKodeeritudVastused() {
		return kodeeritudVastused;
	}
	public ArrayList<ArrayList<Integer>> getKodeeritudVastusedListidena() {
		return kodeeritudVastusedListidena;
	}
	public ArrayList<ArrayList<String>> getVastusedListidena() {
		return vastusedListidena;
	}
	public ArrayList<String> getValikvastusteList() {
		return valikvastusteList;
	}
	public ArrayList<String> getVastusteList() {
		return vastusteList;
	}

	// vastusteList = list.getNimekiri();
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
		/* Kodeeritud vastuste listi nägemiseks
		for (int i = 0; i < kodeeritudVastused.size(); i++)
		    System.out.print(kodeeritudVastused.get(i)+" ");
		*/
		return kodeeritudVastused;
	}
	
	public ArrayList<ArrayList<Integer>> kodeeriVastusteList(ArrayList<String> valikvastusteList, ArrayList<ArrayList<String>> vastusteListid) {
		for (int i = 0; i < vastusteListid.size(); i++){
			ArrayList<Integer> kodeeritudVastusteAlamlist = new ArrayList<>();
			for (int i2 = 0; i2 < vastusteListid.get(i).size(); i2++){
				String[] valikvastused = valikvastusteList.get(i2).split(",");
				ArrayList<String> alamList = vastusteListid.get(i);
				for (int j = 0; j < valikvastused.length; j++){
					String vastus = valikvastused[j].trim();
					if ((alamList.get(i2)).equalsIgnoreCase(vastus)) {
						kodeeritudVastusteAlamlist.add(j);
					}
				}
			}
			kodeeritudVastusedListidena.add(kodeeritudVastusteAlamlist);		
		}
		/* Kodeeritud vastuste listi nägemiseks
		for (int i = 0; i < kodeeritudVastusedListidena.size(); i++){
			ArrayList<Integer> alamlist = new ArrayList<>();
			for (int j = 0; j < kodeeritudVastusedListidena.get(i).size(); j++) {
				alamlist = kodeeritudVastusedListidena.get(i);
		    	System.out.print(alamlist.get(j)+" ");
			}
		}*/
		return kodeeritudVastusedListidena;
	}
	
	public ArrayList<ArrayList<String>> looVastusteListVeerust(String failinimi, int esimeseReaNr, int veeruNr) throws Exception {
		File fail = new File(failinimi);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fail), "cp1257"));
		String rida;
		int i = 1;
		int valikuteArv = 0;
		while ((rida = br.readLine()) != null) {
			if (i < esimeseReaNr) {
				//System.out.println(rida);
				i++;
				continue;
			}
			else {
				String[] osad = rida.split(";");
				valikuteArv = osad.length;
				//System.out.println(valikuteArv);
				break;
			}
		}
		br.close();
		
		for (int j = veeruNr; j < valikuteArv+1; j++) {
			Nimekiri vastused = new Nimekiri();
			vastusedListidena.add(vastused.loeNimekiriVeerust(failinimi, esimeseReaNr, j));	
		}
		return vastusedListidena;
	}
}
