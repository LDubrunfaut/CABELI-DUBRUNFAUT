import java.util.*;

public abstract class Jeu
{
    
    protected Joueur[] joueurs;

    public Jeu(Joueur[] joueurs)
    {
	this.joueurs = joueurs;
    }

    public static void main(String[] args)
    {

	Scanner scan = new Scanner(System.in);
	Jeu jeu;
	boolean flagjeu = true;
	boolean flagnjoueurs = true;
	
	while (flagjeu) { // Tant que le joueur n'a pas choisi de jeu.

	    System.out.println("A quel jeu voulez-vous jouer? (\"Oie\" ou \"Numeri\")");	    
	    String reponse = scan.nextLine();

	    if (reponse.equalsIgnoreCase("oie")) {
		flagjeu = false;
		while (flagnjoueurs) { // Tant que le joueur n'a pas choisi de 
		                       // nombre de joueurs.
		    System.out.println("Combien de joueurs voulez-vous?");
		    reponse = scan.nextLine();
		    try{
			jeu = new JeuOie(Integer.parseInt(reponse));
			flagnjoueurs = false;
			jeu.deroulement();
		    }catch(NumberFormatException e) {
		    }
		}
	    }
	    else if (reponse.equalsIgnoreCase("numeri")) {
		flagjeu = false;
		while (flagnjoueurs) {
		    System.out.println("Combien de joueurs voulez-vous? (max 3)");
		    reponse = scan.nextLine();
		    try{
			int nJoueurs = Integer.parseInt(reponse);
			if (nJoueurs <= 3) {
			    jeu = new JeuNumeri(nJoueurs);
			    jeu.deroulement();
			    flagnjoueurs = false;
			}
		    }catch(NumberFormatException e) {
		    }
		}
	    }
	}
    }

    /**
     * Fait jouer les joueurs un par un puis affiche le message de fin de 
     * partie.
     *
     */
    public void deroulement()
    {
	boolean finDePartie = false;
	
	while (! finDePartie) {
	    for(Joueur j : joueurs) {
		finDePartie = j.tour();
		if (finDePartie) {
		    break;
		}
	    }
	}
	fin();
    }

    /**
     * Affiche le message de fin de partie et le score de chaque joueur.
     */
    public void fin()
    {
	System.out.println("\nFIN DE PARTIE!");
	for (Joueur j:joueurs) {
	    System.out.println("Score de " + j + ": "+ j.getScore());
	}
    }

}
