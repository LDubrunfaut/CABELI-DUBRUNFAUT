import java.util.*;

public class JoueurOie extends Joueur
{

    private boolean libre = true;
    private int sortie_hotel;
    private Pion pion;
    // Nombre de face du de.
    private int nbFacesDe;
    // TRUE si on peut depasser la derniere case, FALSE si on doit tomber
    // exactement dessus.
    private boolean victoireDepassement;
    // si TRUE le joueur peut entrer le resultat qu'il veut a chaque lance de des.
    private boolean triche;


    /**
     * Constructeur de classe.
     *
     * @param     nom    Nom du joueur.
     * @param   plateau  Plateau de jeu.
     * @param vuePlateau VueGenerale du plateau. 
     * @param   couleur  couleur du joueur de Color.getRGB().
     */
    public JoueurOie(String nom, CaseJeuOie[] plateau, VueGenerale vuePlateau, 
		     int couleur)
    {
	super(nom, plateau, vuePlateau);
        ImageSimple imagePion = new ImageSimple("oie_clipart.png",50,50);
	vuePlateau.positionneEnCase(0,imagePion);
	pion = new Pion(vuePlateau, imagePion, couleur);
	this.nbFacesDe = 6;
	this.victoireDepassement = false;
	this.triche = false;
    }

    /**
     * Constructeur de classe.
     *
     * @param     nom    Nom du joueur.
     * @param   plateau  Plateau de jeu.
     * @param vuePlateau VueGenerale du plateau. 
     * @param   couleur  couleur du joueur de Color.getRGB().
     */
    public JoueurOie(String nom, CaseJeuOie[] plateau, VueGenerale vuePlateau, 
		     int couleur, int nbFaces, boolean depassement, 
		     boolean triche)
    {
	super(nom, plateau, vuePlateau);
        ImageSimple imagePion = new ImageSimple("oie_clipart.png",50,50);
	vuePlateau.positionneEnCase(0,imagePion);
	pion = new Pion(vuePlateau, imagePion, couleur);
	this.nbFacesDe = nbFaces;
	this.victoireDepassement = depassement;
	this.triche = triche;	
    }


    /**
     * Constructeur de classe.
     *
     * @param     nom    Nom du joueur.
     * @param   plateau  Plateau de jeu.
     * @param vuePlateau VueGenerale du plateau. 
     * @param  imagePion Image du pion sur l'interface graphique.
     */
    public JoueurOie(String nom, CaseJeuOie[] plateau, VueGenerale vuePlateau, 
		     ImageSimple imagePion, int nbFaces, boolean depassement, 
		     boolean triche)
    {
	super(nom, plateau, vuePlateau);
	vuePlateau.positionneEnCase(0,imagePion);
	this.pion = new Pion(vuePlateau, imagePion);
	this.nbFacesDe = nbFaces;
	this.victoireDepassement = depassement;
	this.triche = triche;	
    }
    



    /**
     * Effectue le tour du joueur. Vérifie son état et si autorisé lance deux
     * dés, puis déplace son pion sur la nouvelle case. Renvoit true si les 
     * conditions de fin de partie sont remplies.
     * 
     * @return true si la partie est finie, false sinon
     */
    public boolean tour()
    {
	System.out.println("\n Tour de " + nom + ", score: " + getScore());

	if (sortie_hotel > 0) { // Si dans hotel.
	    System.out.println(nom + " est dans un hotel pour " + sortie_hotel
			       + " tours.");
	    sortie_hotel --;
	    return false;
	}
	else if (! libre) {
	    System.out.println(nom + " est dans un puits!");
	    return false;
	}
	else {
	    // En début de tour vérifier que le joueur est libre
	    // Vérifier qu'il n'est pas à l'hotel en comparant sa date de sortie de l'
	    // hotel avec la valeur du compteur de tour
	    int des_1 = 0;
	    int des_2 = 0;
	    int des_res = 0;
	    if (triche) {
		boolean flag = true;
		Scanner scan = new Scanner(System.in);
		String reponse;
		while(flag){
		    try{
			System.out.println("Triche! Entrez le résultat du lancé de dé:");
			reponse = scan.nextLine();
			des_res = Integer.parseInt(reponse);
			flag = false;
		    }catch(NumberFormatException | NullPointerException e) {
			System.out.println("Lancé aléatoire? (oui)");
			reponse = scan.nextLine();
			if (reponse.equalsIgnoreCase("oui")){
			    flag = false;
			    des_1 = new Random().nextInt(nbFacesDe) + 1;
			    des_2 = new Random().nextInt(nbFacesDe) + 1;
			    des_res = des_1 + des_2;
			}
		    }
		}
		
	    }

	    else {
		des_1 = new Random().nextInt(nbFacesDe) + 1;
		des_2 = new Random().nextInt(nbFacesDe) + 1;
		des_res = des_1 + des_2;
	    }

	    // Si on est sur la case 0 et que c'est le premier tour (donc on doit avoir
	    // accès à la position du pion et le numero de tour.
	    // Dans ce cas si on a : 9 = 6 + 3 -> aller à la case 26
	    // Sinon si on a 9 = 4 + 5 -> aller à la case 53
	    if (pion.getPos()==0) {
		if((des_1==3 && des_2==6) || (des_1==6 && des_2==3)) {
		    des_res=26;
		}else if((des_1==4 && des_2==5) || (des_1==5 && des_2==4)) {
		    des_res=53;
		}
	    }

	    System.out.println("Lancé de dés: " + des_1 + "+" + des_2);
	    movePion(des_res);
	    return finDePartie();
	}
    }


    private boolean finDePartie() 
    {
	// Fin du jeu si il y a un pion sur la derniere case.
	if (pion.getPos()==63) {    
	    return true;
	}
	else {
	    return false;
	}
    }
    

    private void movePion(int des_res)
    {
	int res = des_res;
	int pos = pion.getPos();
	getCase(pos).setOccupee(false); // Libere la case.

	do {
	    pos = pion.getPos();

  	    if (((res + pos) > 63)) { // En cas de depassement.
		pion.move(63-pos); // Bouge le pion.
		if (! victoireDepassement) {
		    pion.move(63-pos-res);
		}
	    }
	    else {
		pion.move(res);
	    }
	    res = getCase(pion.getPos()).active(this);
	} while (res != 0);

	addScore(getCase(pion.getPos()).question());
    }

    /**
     * Modifie l'état du joueur. S'il est en prison il doit passer son tour et 
     * décrémenter un compteur.
     *
     * @param n_tours le nombre de tours que le joueur doit passer en prison.
     */
    public void setToursHotel(int n_tours)
    {
	this.sortie_hotel += n_tours;
    }

    /**
     * Modifie l'état du joueur. S'il est dans un puits il doit attendre qu'un
     * autre joueur le releve.
     *
     * @param libre false s'il est dans un puits, true sinon.
     */
    public void setLibre(boolean libre)
    {
	this.libre = libre;
    }

    /**
     * Affiche l'état du joueur. S'il est dans un puits il doit attendre qu'un
     * autre joueur le releve.
     *
     * @return true si le joueur est libre, false sinon.
     */
    public boolean getLibre()
    {
	return libre;
    }

    /**
     * Renvoit le nombre de faces du de que le joueur utilise.
     *
     * @return Le nombre de faces du de.
     */
    public int getNbFacesDe()
    {
	return nbFacesDe;
    }

    /**
     * Renvoit le nombre de faces du de que le joueur utilise.
     *
     * @return Le nombre de faces du de.
     */
    public boolean getTriche()
    {
	return triche;
    }


    /**
     * {@inheritDoc}
     * Renvoit un objet de type CaseJeuOie.
     *
     * @param i La position de la case demandée.
     */
    @Override public CaseJeuOie getCase(int i)
    {
	return ((CaseJeuOie) super.getCase(i));
    }
    
    @Override public String toString()
    {
	return nom; 
    }



}
