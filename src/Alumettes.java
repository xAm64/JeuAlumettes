import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

public class Alumettes {

	public static void main(String[] args) {
		int numbersAlumettes = 21, numberRest = 21;
		String replay = "non";
		Scanner scn = new Scanner(System.in);
		System.out.println("Exercice 2 Les alumettes");
		System.out.println("voici les alumettes "+viewAllumettes(numbersAlumettes, numbersAlumettes));
		System.out.println("1joueur ou 2 joueurs ?");
		String numberPlayerString = scn.nextLine();
		do {
			numberRest = numbersAlumettes;
			if (verifInt(numberPlayerString)) {
				if (Integer.parseInt(numberPlayerString) == 1) {
					System.out.println("Joueur contre ordinateur");
				} else if (Integer.parseInt(numberPlayerString) == 2) {
					int player = 1;
					do {
						System.out.println("Allumettes restantes "+viewAllumettes(numberRest, numbersAlumettes));
						System.out.println("Au tour du joueur: "+player+" . Combien d'alumettes voulez-vous prendre ? (de 1 à 4)");
						String numberTakeString = scn.nextLine();
						if (verifInt(numberTakeString)) {
							int numbRm = Integer.parseInt(numberTakeString);
							if (verifRemove(numbRm, numberRest) != 0) {
								numberRest -= numbRm;
								if (player == 2) {
									player = 1;
								} else {
									player ++;
								}
							} else {
								System.out.println("Opération non conforme");
							}
						} else {
							System.out.println("J'attends une valeur en chiffre");
						}
					} while (numberRest > 1);
				} else {
					System.out.println("Merci de saisir 1 joueur ou 2 joueurs");
				}
			} else {
				System.out.println("Appuyer sur 1 pour jouer contre l'ordinateur ou 2 pour jouer à 2");
			}
			System.out.println("Recommencer ?");
			replay = scn.nextLine();
		}while (replay.equals("oui"));
		System.out.println("Au revoir");
	}
	
	private static boolean verifInt (String x) {
		Scanner scn = new Scanner(x);
		boolean out = false;
		if (scn.hasNextInt()) {
			out = true;
		}
		return out;
	}
	private static int verifRemove(int numberRemove, int numberRest) {
		if (numberRemove >= 1 && numberRemove <= 4) {
			if (numberRemove <= numberRest) {
				return numberRemove;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	private static String viewAllumettes(int numbersAlumettes, int numberTotalAlumettes) {
		String out = "";
		for (int i=0;i<numberTotalAlumettes;i++){
			if (i<numbersAlumettes) {
				out += "¡";
			} else {
				out += "·";
			}
		}
		return out;
	}
}
