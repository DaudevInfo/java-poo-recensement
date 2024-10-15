package fr.diginamic.recensement.services;

import java.util.List;
import java.util.Scanner;

import fr.diginamic.recensement.entites.Recensement;
import fr.diginamic.recensement.entites.Ville;
import fr.diginamic.recensement.exception.CodeException;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Recherche et affichage de toutes les villes d'un département dont la
 * population est comprise entre une valeur min et une valeur max renseignées
 * par l'utilisateur.
 * 
 * @author DIGINAMIC
 *
 */
public class RecherchePopulationBorneService extends MenuService {

	@Override
	public void traiter(Recensement rec, Scanner scanner) throws CodeException {

		System.out.println("Quel est le code du département recherché ? ");
		String choix = scanner.nextLine();


		System.out.println("Choississez une population minimum (en milliers d'habitants): ");
		String saisieMin = scanner.nextLine();
		int a;
		int b;

		if (!NumberUtils.isDigits(saisieMin)) {
			throw new CodeException("Saisie numérique attendue" );
		} else {
			a = Integer.parseInt(saisieMin);
		}

		if	(a <1) {
			throw new CodeException("Saisie Minimum invalide" );
		}

		System.out.println("Choississez une population maximum (en milliers d'habitants): ");
		String saisieMax = scanner.nextLine();

		if (!NumberUtils.isDigits(saisieMax)) {
			throw new CodeException("Saisie numérique attendue" );
		} else {
			b = Integer.parseInt(saisieMax);
		};

		if (b <1 || b <a) {
			throw new CodeException("Saisie Maximum invalide" );
		}
		int min = Integer.parseInt(saisieMin) * 1000;
		int max = Integer.parseInt(saisieMax) * 1000;
		boolean deptTrouve = false;

		List<Ville> villes = rec.getVilles();
		for (Ville ville : villes) {
			if (ville.getCodeDepartement().equalsIgnoreCase(choix)) {
				if (ville.getPopulation() >= min && ville.getPopulation() <= max) {
					System.out.println(ville);
				}
				deptTrouve =true;
			}

		}
		if (!deptTrouve) throw new CodeException("Département inconnu" );
	}

}
