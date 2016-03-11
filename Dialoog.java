
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Dialoog {
	public ArrayList<Integer> Vastused = new ArrayList<>();
	
	public ArrayList<Integer> getVastused(){
		return Vastused;
	}

	public ArrayList<Integer> dialoog(ArrayList<String> kysimused, ArrayList<String> valikud, String kysimustikuKirjeldus){
		JOptionPane.showMessageDialog(null, 
				kysimustikuKirjeldus, 
				"Tere tulemast!", 
				JOptionPane.INFORMATION_MESSAGE);
		
		String[] eraldatud = valikud.get(0).split(",");
		String[] valik = new String[eraldatud.length];
		for (int i=0; i<eraldatud.length; i++){
		valik[i] = eraldatud[i].trim();
		}
		for (int i=0; i < kysimused.size(); i++){
			int sisestus = JOptionPane.showOptionDialog(null, 
					kysimused.get(i), "Vali sobiv vastus", JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE, null, valik, valik[0]);
			if (sisestus == -1){
				System.exit(0);
			}
			Vastused.add(sisestus);	
		}
		return Vastused;
		}
	}