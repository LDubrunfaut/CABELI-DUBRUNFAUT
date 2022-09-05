import java.util.*;

public class JoueurNumeri extends Joueur
{
    private Pion[] pions;
    private boolean rejoue = false;

    /**
     * Constructeur de classe.
     *
     * @param nom Le nom du joueur.
     * @param plateau Le plateau de cases.
     * @param imagePion Image du pion sur l'interface graphique.
     * @param pions     Tableau des 6 pions du joueur.
     */
    public JoueurNumeri(String nom, CaseNumeri[] plateau, VueGenerale vuePlateau, 
			int couleur)
    {
	super(nom, plateau, vuePlateau);
	pions = new Pion[6];
        ImageSimple[] images = new ImageSimple[6];
	for (int i=0; i<6; i++){
	    images[i] = new ImageSimple((String)("ImagesChiffres/"+(i+1)+".png"),50,50);
	    vuePlateau.positionneEnCase(0,images[i]);
            pions[i] = new Pion(vuePlateau, images[i], couleur);
	}
    }

    /**
     * Constructeur de classe.
     *
     * @param nom Le nom du joueur
     * @param imagePion Image du pion sur l'interface graphique.
     * @param pions     Tableau des 6 pions du joueur.
     */
    public JoueurNumeri(String nom, CaseNumeri[] plateau, VueGenerale vuePlateau)
    {
	super(nom, plateau, vuePlateau);
	pions = new Pion[6];
        ImageSimple[] images = new ImageSimple[6];
	for (int i=0; i<6; i++){
	    images[i] = new ImageSimple((String)("ImagesChiffres/"+(i+1)+".png"),50,50);
	    vuePlateau.positionneEnCase(0, images[i]);
            pions[i] = new Pion(vuePlateau, images[i]);
	}
    }

    /**
     * Effectue le tour du joueur. Lance un dé, puis il choisit le ou les pions 
     * qu'il souhaite déplacer sur la prochaine case libre. Renvoit true si les 
     * conditions de fin de partie sont remplies.
     * 
     * @return true si la partie est finie, false sinon
     */
    public boolean tour()
    {
	System.out.println("\n Tour de " + this + ", score:" + getScore());
        Scanner clavier = new Scanner(System.in);
        int des_1 = 0;
        int des_2 = 0;
	int des = new Random().nextInt(6) + 1;
	String reponse;
	
	 
	String [] reponses;
        int flag=0;
	while (flag==0) {
	    System.out.println("Entrez le ou les numéro du ou des pion(s) dont la somme fait "+des+", que vous souhaitez déplacer, séparés par des espaces");
	    reponse = clavier.nextLine();
	    reponses = reponse.split(" ");

	    try{ 
		des_1 = Integer.parseInt(reponses[0]);
		des_2 = Integer.parseInt(reponses[1]);
		if(des_1+des_2 == des && des_1 != des_2){
		    movePion(pions[des_1-1]);
		    movePion(pions[des_2-1]);
		    flag = 1;
		}
	    }catch(NumberFormatException e) { 
		System.out.println("Voulez-vous passer le tour? (Repondre \"oui\")");
		reponse = clavier.nextLine();
		if (reponse.equalsIgnoreCase("oui")){
		    flag = 1;
		}
	    }catch(NullPointerException e) {
		if (des_1 != 0) {
		    movePion(pions[des-1]);
		    flag = 1;
		}
	    }catch(ArrayIndexOutOfBoundsException e) {
		if (des_1 != 0 && des_1 == des) {
		    movePion(pions[des_1-1]);
		    flag = 1;
		}
	    }
	}

        if(alignPion() && ! rejoue){ // Si le joueur a aligne 3 pions.
	    rejoue = true;
	    return this.tour();
	}
	rejoue = false;
	
	updateScore();
	return finDePartie();
    }

    private boolean finDePartie()
    {
	//Numeri : 3 dernières cases sont occupees
	return (getCase(37).isOccupee() && 
		getCase(38).isOccupee() && 
		getCase(39).isOccupee());
    }
    
    private boolean alignPion() //check si 3 pions sont alignes.
    {
	int[] position = new int[6];
        for(int i=0; i<pions.length; i++) {
            position[i]=pions[i].getPos();
        }
	// Trie le tableau des positions des pions
	// http://stackoverflow.com/questions/2786899/fastest-sort-of-fixed-length-6-int-array
	int j, k;
        for (j = 1; j < 6; j++) {
                int tmp = position[j];
                for (k = j; k >= 1 && tmp < position[k-1]; k--) {
		    position[k] = position[k-1];
		}
                position[k] = tmp;
        }
	for (int i=1; i<pions.length-1; i++) {
	    if ((position[i] == position[i-1]+1) &&
		(position[i] == position[i+1]-1) &&
		(position[i] != 1)) {
		System.out.println("3 pions alignes");
		return true;
	    }
	}
	return false;
    }

    private void movePion(Pion p)
    {
	int res=1;
	int pos = p.getPos();

	boolean bloque = true;
	for (int i=pos; i<40; i++) {
	    bloque = bloque && getCase(i).isOccupee(); 
	}

	if (! bloque) {
	    getCase(pos).setOccupee(false); // Libere la case.
	    do {
		pos = p.getPos();
		p.move(res);
		res = getCase(p.getPos()).active();
	    } while (res != 0);
	}
    }

    private void updateScore(){
	int new_score = 0;
	for(int j=0; j<6; j++) {
	    new_score += ((j+1)*(getCase(pions[j].getPos()).getVal()));
	}
	setScore(new_score);
    }


    /**
     * {@inheritDoc}
     * Renvoit un objet de type CaseJeuOie.
     *
     * @param i La position de la case demandée.
     * @return La case a la position demandée.
     */
    @Override public CaseNumeri getCase(int i)
    {
	return ((CaseNumeri) super.getCase(i));
    }
    
    @Override public String toString()
    {
	return nom; 
    }



}
