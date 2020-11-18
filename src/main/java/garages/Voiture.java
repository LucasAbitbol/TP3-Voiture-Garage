package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

	private final String immatriculation;
	private final List<Stationnement> myStationnements = new LinkedList<>();

	public Voiture(String i) {
		if (null == i) {
			throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
		}

		immatriculation = i;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	/*
	 * @param g le garage où la voiture va stationner
	 * @throws java.lang.Exception Si déjà dans un garage
	 */
	public void entreAuGarage(Garage g) throws Exception {
		if (estDansUnGarage()) {
                    throw new Exception("Déjà dans un garage");
                }
		Stationnement s = new Stationnement(this, g);
		myStationnements.add(s);
	}

	/*
	 * @throws java.lang.Exception si la voiture n'est pas dans un garage
	 */
	public void sortDuGarage() throws Exception {
            if (!this.estDansUnGarage()) {
		throw new Exception("La voiture n'est pas dans un garage");
	}
            if (this.estDansUnGarage()) {
                Stationnement dernier = myStationnements.get(myStationnements.size() -1);
                dernier.terminer();
            }
        }
	/**
	 * @return l'ensemble des garages visités par cette voiture
	 */
	public Set<Garage> garagesVisites() {
            Set<Garage> set = new HashSet<Garage>();
            for (Stationnement s : myStationnements) {
                set.add(s.getGarage());
            }
            return set;
        
	}

	/**
	 * @return vrai si la voiture est dans un garage, faux sinon
	 */
	public boolean estDansUnGarage() {
            if (myStationnements.size() - 1 == -1) {
                return false;
            }
            Stationnement dernier = myStationnements.get(myStationnements.size()-1);
            if (dernier.estEnCours()) {
                return true;
            }
            else {
                return false;
            }
		
	}

	/*
	 * @param out l'endroit où imprimer (ex: System.out)
	 */
	public void imprimeStationnements(PrintStream out) {
            List<Stationnement> stationnements = new LinkedList<>(myStationnements);
            for (int i=0; i<stationnements.size(); i++) {
                String garage = stationnements.get(i).getGarage().toString();
                out.append(garage + "\n");
                out.append(stationnements.get(i).toString() + "\n");
                for (int j = i+1 ; j<stationnements.size(); j++) {
                    if(stationnements.get(j).getGarage() == stationnements.get(i).getGarage()) {
                        out.append(stationnements.get(j).toString() + "\n");
                        stationnements.remove(stationnements.get(j));
                    }
                }
            }

	}

}
