import javax.swing.JOptionPane;

public class TESTIT {

	public static void main(String[] args) throws Exception {
		Nimekiri kysimused = new Nimekiri();
		kysimused.loeNimekiriVeerust("LeiaOmaTudengiorganisatsioon.csv", 4, 1);
		
		Nimekiri valikVastused = new Nimekiri();
		valikVastused.loeNimekiriVeerust("LeiaOmaTudengiorganisatsioon.csv", 4, 2);
		
		Organisatsioon organisatsioonid = new Organisatsioon();
		organisatsioonid.loeOrganisatsioonideAndmed("LeiaOmaTudengiorganisatsioon.csv");
	
		Lahter tutvustus = new Lahter("LeiaOmaTudengiorganisatsioon.csv");
		tutvustus.loeLahter();
		
		Dialoog dialoog = new Dialoog();
		dialoog.dialoog(kysimused.getNimekiri(), valikVastused.getNimekiri(), tutvustus.getTutvustus());
		
		ParimValik sobivaimOrganisatsioon = new ParimValik(dialoog.getVastused(), organisatsioonid.getOrganisatsioonid());
		sobivaimOrganisatsioon.valiParimEdetabel();
		
		JOptionPane.showMessageDialog(null,
			sobivaimOrganisatsioon.kuvaWrap(sobivaimOrganisatsioon.kuvaTop(3, "Sulle sobib: ", ""), 100),
			"Tulemus",
			JOptionPane.PLAIN_MESSAGE);
		
	}

}
