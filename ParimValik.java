import java.util.ArrayList;
import java.util.Collections;

public class ParimValik {
	private ArrayList<Integer> vastajaVastused;
	private ArrayList<Organisatsioon> organisatsioonideAndmed;
	private ArrayList<ArrayList<String>> sobivadValikud = new ArrayList<>();
	private ArrayList<Edetabel> edetabeliInfo = new ArrayList<>();
	
	public ArrayList<ArrayList<String>> getSobivadValikud() {
		return sobivadValikud;
	}
	public ArrayList<Edetabel> getEdetabeliInfo() {
		return edetabeliInfo;
	}
	
	public ParimValik(ArrayList<Integer> vastajaVastused, ArrayList<Organisatsioon> organisatsioonideAndmed) {
		this.vastajaVastused = vastajaVastused;
		this.organisatsioonideAndmed = organisatsioonideAndmed;
	}

	// Valib maksimaalse tulemusega variandi või maksimaalselt võrdsete tulemustega valikuvariandid.
	public ArrayList<ArrayList<String>> valiParim() {
		int i_organisatsioon;
		int maxLoendur = 0;
		for (i_organisatsioon = 0; i_organisatsioon < organisatsioonideAndmed.size(); i_organisatsioon++) {
			int loendur = 0;
			ArrayList<Integer> vastusteKomplekt = organisatsioonideAndmed.get(i_organisatsioon).getValikVastused();
			for (int i_kysimus = 0; i_kysimus < vastajaVastused.size(); i_kysimus++) {
				if(vastajaVastused.get(i_kysimus).equals(vastusteKomplekt.get(i_kysimus))) {
					loendur += 1;
				}
			}
			if ((loendur > maxLoendur) && (sobivadValikud.size() == 0) || loendur == maxLoendur) {
				ArrayList<String> sobiv = new ArrayList<>();
				sobiv.add(organisatsioonideAndmed.get(i_organisatsioon).getOrganisatsioon());
				sobiv.add(organisatsioonideAndmed.get(i_organisatsioon).getOrgLisainfo());
				sobivadValikud.add(sobiv);
				maxLoendur = loendur;
			}
			else if ((loendur > maxLoendur) && (sobivadValikud.size() != 0)) {
				sobivadValikud.clear();
				ArrayList<String> sobiv = new ArrayList<>();
				sobiv.add(organisatsioonideAndmed.get(i_organisatsioon).getOrganisatsioon());
				sobiv.add(organisatsioonideAndmed.get(i_organisatsioon).getOrgLisainfo());
				sobivadValikud.add(sobiv);
				maxLoendur = loendur;
			}
		}
		return sobivadValikud;	
	}
	
	// Loob kõikide valikuvariantide lõikes vastaja tulemuste edetabeli.
	public ArrayList<Edetabel> valiParimEdetabel() {
		int i_organisatsioon;
		
		for (i_organisatsioon = 0; i_organisatsioon < organisatsioonideAndmed.size(); i_organisatsioon++) {
			int loendur = 0;
			ArrayList<Integer> vastusteKomplekt = organisatsioonideAndmed.get(i_organisatsioon).getValikVastused();;
			for (int i_kysimus = 0; i_kysimus < vastajaVastused.size(); i_kysimus++) {
				if(vastajaVastused.get(i_kysimus).equals(vastusteKomplekt.get(i_kysimus))) {
					loendur += 1;
				}
			}
			Edetabel vastus = new Edetabel(loendur, organisatsioonideAndmed.get(i_organisatsioon).getOrganisatsioon(), organisatsioonideAndmed.get(i_organisatsioon).getOrgLisainfo());
			edetabeliInfo.add(vastus);
		}
		Collections.sort(edetabeliInfo);
		return edetabeliInfo;	
	}
	 
	// Moodustab kuvamiseks sobiva sõne ainult maksimaalse(te)st tulemus(t)est.	
	public String kuvaParim(String sobivaimaEeltekst, String lisainfoEeltekst) {
		StringBuilder sb = new StringBuilder(500);	
		for (int i = 0; i < sobivadValikud.size(); i++) {
			sb.append(sobivaimaEeltekst + sobivadValikud.get(i).get(0));
			sb.append(" _ ");
			sb.append(lisainfoEeltekst + sobivadValikud.get(i).get(1));
			sb.append(" _ ");
			sb.append(" _ ");
			}
		String tulemus = sb.toString();
		return tulemus;
	}
	
	// Moodustab kuvamiseks sobiva sõne edetabeli parimatest vastavalt soovitud parimate tulemuste arvule.
	public String kuvaTop(int parimateArv, String sobivaimaEeltekst, String lisainfoEeltekst) {
		StringBuilder sb = new StringBuilder(2000);
		for (int i = 0; i < parimateArv && i < edetabeliInfo.size(); i++) {
			sb.append(sobivaimaEeltekst + edetabeliInfo.get(i).getNimetus());
			sb.append(" (sobivus ");
			sb.append(edetabeliInfo.get(i).getPunktideHulk() * 100 / organisatsioonideAndmed.get(0).getValikVastused().size());
			sb.append(" %)");
			sb.append(" _ ");
			sb.append(lisainfoEeltekst + edetabeliInfo.get(i).getLisainfo());
			sb.append(" _ ");
			sb.append(" _ ");
			}
		String parimad = sb.toString();
		return parimad;
	}
	
	// Murrab tekstiread kuvamiseks vastavalt soovitavale laiusele.
	public String kuvaWrap(String kuvatavTekst, int tekstiLaius) {
		String tekst = kuvatavTekst;
		String[] tekstiList = tekst.split("\\s+");
		StringBuilder sb = new StringBuilder(500);
		int sonadePikkus = 0;
		for (int i = 0; i < tekstiList.length; i++) {
			String[] tahemargid = tekstiList[i].split("");
			sonadePikkus += tahemargid.length;
			if (tekstiList[i].equals("_")) {
				sb.append("\n");
				sonadePikkus = 0;
				continue;
			}
			else if (sonadePikkus >= tekstiLaius) {
				sb.append("\n");
				sb.append(tekstiList[i]);
				sb.append(" ");
				sonadePikkus = 0;
			}
			else {
				sb.append(tekstiList[i]);
				sb.append(" ");
			}
		}
		String murtudTekst = sb.toString();
		return murtudTekst;
		
	}
}
