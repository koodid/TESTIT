import javax.swing.JOptionPane;

public class TESTIT {

	public static void main(String[] args) throws Exception {
		Nimekiri kysimused = new Nimekiri();
		kysimused.loeNimekiriVeerust("LeiaOmaTudengiorganisatsioon.csv", 4, 1);
		
		Nimekiri valikVastused = new Nimekiri();
		valikVastused.loeNimekiriVeerust("LeiaOmaTudengiorganisatsioon.csv", 4, 2);
		
		Nimekiri organisatsioonid = new Nimekiri();
		organisatsioonid.loeNimekiriReast("LeiaOmaTudengiorganisatsioon.csv", 2, 3);
		
		Nimekiri lisainfo = new Nimekiri();
		lisainfo.loeNimekiriReast("LeiaOmaTudengiorganisatsioon.csv", 3, 3);
		
		Vastused organisatsioonideVastused = new Vastused();
		organisatsioonideVastused.looVastusteListVeerust("LeiaOmaTudengiorganisatsioon.csv", 4, 3);
		organisatsioonideVastused.kodeeriVastusteList(valikVastused.getNimekiri(), organisatsioonideVastused.getVastusedListidena());
		
		TestiTutvustus tutvustus = new TestiTutvustus("LeiaOmaTudengiorganisatsioon.csv");
		tutvustus.loeTutvustust();
		
		Dialoog dialoog = new Dialoog();
		dialoog.dialoog(kysimused.getNimekiri(), valikVastused.getNimekiri(), tutvustus.getTutvustus());
		
		ParimValik sobivaimOrganisatsioon = new ParimValik(dialoog.getVastused(), organisatsioonideVastused.getKodeeritudVastusedListidena());
		//Valib ainult kõige parema tulemuse või võrdselt parimad.
		//sobivaimOrganisatsioon.valiParim(organisatsioonid, lisainfo);
		//sobivaimOrganisatsioon.kuvaParim("Sinu jaoks on parim valik: ", "");
		
		sobivaimOrganisatsioon.valiParimEdetabel(organisatsioonid, lisainfo);
		
		JOptionPane.showMessageDialog(null,
				sobivaimOrganisatsioon.kuvaWrap(sobivaimOrganisatsioon.kuvaTop(3, "Sulle sobivad: ", ""), 100),
			    "Tulemus",
			    JOptionPane.PLAIN_MESSAGE);
		

	}

}
