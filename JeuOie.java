import java.awt.Color;
import java.util.*;

public class JeuOie extends Jeu
{
    
    // Le jeu pose-t-il des questions a chaque case?
    public static boolean jeuQuestions=true;

    public JeuOie(int nJoueurs)
    {
	super(creeJoueurs(nJoueurs));
    }

    public JeuOie()
    {
	super(creeJoueurs(3));
    }


    /**
     * Retourne un tableau d'objets Case comme plateau.
     *
     * @return      Un plateau sous forme de tableau de Case
     * @see         Case
     */
    public static CaseJeuOie[] creePlateau()
    {
	CaseJeuOie[] plateau = new CaseJeuOie[64];
	// Coordonnees des cases sur le plateau par defaut.
	int [][] tabCase={
	    {120,510},
	    { 206,506 },// case 1
	    { 254,510 },
	    { 298,511 },
	    { 342,515 },
	    { 386,513 },
	    { 444,511 },
	    { 497,512 },
	    { 536,508 },
	    { 575,493 },
	    { 621,455 },
	    { 650,428 },
	    { 674,377 },
	    { 686,332 },
	    { 688,277 },
	    { 684,227 },
	    { 669,184 },
	    { 647,146 },
	    { 605,97 },
	    { 536,66 },
	    { 486,48 },
	    { 442,46 },
	    { 389,49 },
	    { 338,52 },
	    { 296,49 },
	    { 250,50 },
	    { 197,56 },
	    { 147,77 },
	    { 112,103 },
	    { 82,139 },
	    { 60,183 },
	    { 54,232 },
	    { 58,287 },
	    { 74,336 },
	    { 98,376 },
	    { 125,412 },
	    { 164,435 },
	    { 208,442 },
	    { 254,442 },
	    { 295,442 },
	    { 341,439 },
	    { 386,439 },
	    { 444,438 },
	    { 497,439 },
	    { 533,428 },
	    { 573,401 },
	    { 604,349 },
	    { 615,310 },
	    { 612,270 },
	    { 605,227 },
	    { 572,172 },
	    { 535,136 },
	    { 457,130 },
	    { 393,134 },
	    { 336,131 },
	    { 293,127 },
	    { 244,133 },
	    { 199,141 },
	    { 146,177 },
	    { 129,253 },
	    { 145,307 },
	    { 176,338 },
	    { 212,355 },
	    { 287,351 } // case 63
	};
	
	plateau[0] = new CaseBegin(tabCase[0][0], tabCase[0][1], 0);
	
	for(int i=1; i<64; i++){
	    if (i%9 == 0) {
		// Case de l'oie toutes les 9 cases.
		plateau[i] = new CaseOie(tabCase[i][0], tabCase[i][1], i);	    
	    }
	    else {
		// La question est une addition de 2 nombres aleatoires.
		int int1 = new Random().nextInt(50);
		int int2 = new Random().nextInt(50);
		String question = ("Resoudre l\'equation : X = "+ int1 +
			    " + " + int2 + ". X=?");
		String reponse = (int1 + int2) + "";
		if (jeuQuestions){
		    plateau[i] = new CaseJeuOie(tabCase[i][0], tabCase[i][1], i,
						question, reponse);
		}
		else {
		    plateau[i] = new CaseJeuOie(tabCase[i][0], tabCase[i][1], i);
		}
	    }
	}
	// Case de saut.
	plateau[6] = new CaseJump(tabCase[6][0], tabCase[6][1], 6, 6);
	plateau[42] = new CaseJump(tabCase[42][0], tabCase[42][1], 42, 30-42);
	plateau[58] = new CaseJump(tabCase[58][0], tabCase[58][1], 58, -58);

	// Case hotel.
	plateau[19] = new CaseHotel(tabCase[19][0], tabCase[19][1], 19, 1);

	// Case puits.
	plateau[31] = new CasePuits(tabCase[31][0], tabCase[31][1], 31);
	plateau[52] = new CasePuits(tabCase[52][0], tabCase[52][1], 52);

	plateau[63] = new CaseEnd(tabCase[63][0], tabCase[63][1], 63);
	
	return plateau;
    }

    /**
     * Retourne un tableau de Joueurs.
     *
     * @param nJoueurs Le nombre de joueurs
     * @return      Une liste de nJoueurs
     * @see         Joueur
     */
    public static Joueur[] creeJoueurs(int nJoueurs)
    {
	// Plateau par defaut.
	VueGenerale v=new VueOieBasic();
	Joueur[] joueurs = new JoueurOie[nJoueurs];
	CaseJeuOie[] plateau = creePlateau();
	// Couleurs par defaut.
        int[] couleurs = {Color.red.getRGB(),
                          Color.green.getRGB(),
                          Color.blue.getRGB(),
			  Color.orange.getRGB(),
                          Color.cyan.getRGB(),
                          Color.black.getRGB(),
			  Color.magenta.getRGB()};

	for (int i=0; i<nJoueurs; i++) {
	    // Ici on peut demander les infos au joueur si besoin.
	    if (i > 6) { // Si on a plus de couleur.
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		joueurs[i] = new JoueurOie("Joueur " + (i+1), plateau, v, 
					   new Color(r,g,b).getRGB());//, 15, true, true);
	    }
	    else {
		joueurs[i] = new JoueurOie("Joueur " + (i+1), plateau, v, 
					   couleurs[i]);//, 15, true, true);
	    }
	}

	return joueurs;
    }

    /**
     * {@inheritDoc}
     * Dans le jeu de l'oie, verifie si tous les joueurs sont bloqués en prison.
     */
    public void deroulement()
    {
	boolean finDePartie = false;
	boolean allPrison = false;

	while ((! finDePartie) && (! allPrison)) {
	    allPrison = true;
	    for(Joueur j : joueurs) {
		finDePartie = j.tour();
		allPrison = allPrison && !((JoueurOie)j).getLibre();
		if (finDePartie) {
		    break;
		}
	    }
	}
	fin();
    }


}
