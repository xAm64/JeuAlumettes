import java.util.IllegalFormatCodePointException;
import java.util.Scanner;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;
public class Alumettes {
	public static void main(String[] args) {
		//initialise le nombre d'alumettes
		int numbersAlumettes = 21, numberRest = numbersAlumettes;
		String replay = "non";
		Scanner scn = new Scanner(System.in);
		System.out.println("Exercice 2 Les alumettes");
		System.out.println("voici les alumettes "+viewAllumettes(numbersAlumettes, numbersAlumettes));
		//démarre le jeu
		do {
			//initialise le jeu.
			System.out.println("1joueur ou 2 joueurs ?");
			String numberPlayerString = scn.nextLine();
			numberRest = numbersAlumettes;
			//vérif de saisie.
			if (verifInt(numberPlayerString)) {
				//lance le jeu contre l'ordinateur
				if (Integer.parseInt(numberPlayerString) == 1) {
					//génère qui commence aléatoirement
					int placePlayer = (int) (Math.random() * (2))+1, player = 1;
					System.out.println("À cette partie, vous êtes le joueur: "+placePlayer+".");
					do {
						System.out.println("Allumettes restantes "+viewAllumettes(numberRest, numbersAlumettes));
						//tour du joueur.
						if (placePlayer == player) {
							System.out.println("C'est à votre tour");
							if (numberRest >= 4) {
								System.out.println("Au tour du joueur: "+player+" . Combien d'alumettes voulez-vous prendre ? (de 1 à 4)");
							} else {
								System.out.println("Au tour du joueur: "+player+" . Combien d'alumettes voulez-vous prendre ? (de 1 à "+numberRest+")");
							}
							String numberTakeString = scn.nextLine();
							if (verifInt(numberTakeString)) {
								int numbRm = Integer.parseInt(numberTakeString);
								if (verifRemove(numbRm, numberRest) != 0) {
									//enlève le nombre d'alumettes choisis par l'utilisateur
									numberRest -= numbRm;
									if (numberRest == 0) {
										System.out.println("J'ai gagné");
									}
									if (player != 2) {
										player ++;
									} else {
										player = 1;
									}
								} else {
									System.out.println("C'est pas bien de tricher!");
								}
							} else {
								System.out.println("La valeur doit être écrite en chiffre");
							}
						//tour de l'ordinateur
						} else {
							System.out.println("C'est à mon tour");
							int takeAlumettes = 0;
							if (numberRest >= 4) {
								takeAlumettes = (int) (Math.random() * (4))+1;
							} else {
								takeAlumettes = (int) (Math.random() * (numberRest))+1;
							}
							numberRest -= takeAlumettes;
							if (takeAlumettes > 1) {
								System.out.println("Je prends: "+takeAlumettes+" alumettes");
							} else {
								System.out.println("Je prends: "+takeAlumettes+" alumette");
							}
							if (numberRest == 0) {
								System.out.println("Félicitations, vous m'avez battu");
							}
							if (player != 2) {
								player ++;
							} else {
								player = 1;
							}
						}
					} while (numberRest > 0);
				// 2 joueurs
				} else if (Integer.parseInt(numberPlayerString) == 2) {
					int player = 1;
					//début du jeu
					do {
						//gère les alumettes restantes
						System.out.println("Allumettes restantes "+viewAllumettes(numberRest, numbersAlumettes));
						if (numberRest >= 4) {
							System.out.println("Combien d'alumettes voulez-vous prendre ? (de 1 à 4)");
						} else {
							System.out.println("Combien d'alumettes voulez-vous prendre ? (de 1 à "+numberRest+")");
						}
						//écoute du joueur pour prendre X alumette(s)
						String numberTakeString = scn.nextLine();
						//vérifie que l'utilisateur écris un chiffre
						if (verifInt(numberTakeString)) {
							//convertir le string en int
							int numbRm = Integer.parseInt(numberTakeString);
							//Vérifie que le joueur respecte la règle (si pas respecter ça envera 0)
							if (verifRemove(numbRm, numberRest) != 0) {
								//enlève le nombre d'alumettes choisis par l'utilisateur
								numberRest -= numbRm;
								//si il en reste 0, ça enverra le message de perdu.
								if (numberRest == 0) {
									System.out.println("Le joueur: "+player+" . À perdu");
								}
								//alterne entre joueur 1 et 2.
								if (player == 2) {
									player = 1;
								} else {
									player ++;
								}
							//l'utilisateur essaye de tricher.
							} else {
								System.out.println("Opération refusé, car non conforme aux règles");
							}
						} else {
							System.out.println("La valeur doit être écrite en chiffre");
						}
					} while (numberRest > 0);
				// autre chose, le jeu ne se lance pas.
				} else {
					System.out.println("Merci de saisir 1 joueur ou 2 joueurs");
				}
			//cas de saisie autre que des chiffres.
			} else {
				System.out.println("Appuyer sur 1 pour jouer contre l'ordinateur ou 2 pour jouer à 2");
			}
			System.out.println("Recommencer ?");
			replay = scn.nextLine();
		//permet de relancer le jeu autant de fois que l'utilisateur le voudra.
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
