package v1;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


/**
 * gere une liste IP et �vite la redondance
 *
 */
public class ListeIp {

	/**
	 * une ip est une chaine
	 */
	Set<String> ips;

	
	/**
	 * en fonction de la liste tri�e ou non, on utilise une classe concrete differente
	 * 
	 * @param tri boolean qui indique si on souhaite trier la liste ou non 
	 */
	public ListeIp(boolean tri) {
		if (tri) {
			this.ips = new TreeSet<String>();
		} else {
			this.ips = new HashSet<String>();
		}
	}

	/**
	 * parcourt le fichier texte et traite les logs 
	 * @param name nom du fichier de log
	 * @throws IOException probleme de lecture fichier
	 */
	public void chargerFichier(String name) throws IOException {
		Reader r = new FileReader(name);
		BufferedReader bf = new BufferedReader(r);

		String ligne = bf.readLine();
		while (ligne != null) {
			//System.out.println(ligne);
			traiter(ligne);
			ligne = bf.readLine();
		}
	}

	/**
	 * trait une ligne du fichier de log en ajoutant l'IP � l'ensemble
	 * @param ligne la ligne � traiter
	 */
	private void traiter(String ligne) {
		String[] decoup = ligne.split(" ");
		// il faudrait traiter les problemes de parsing...
		this.ips.add(decoup[0]);
	}

	/**
	 * permet d'afficher l'ensemble des Ips uniques
	 */
	public void AfficheIp() {
		Iterator<String> it = this.ips.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public static void main(String[] args) throws IOException {
		ListeIp listeIp = new ListeIp(false);
		listeIp.chargerFichier("logs.txt");
		listeIp.AfficheIp();
	}

}
