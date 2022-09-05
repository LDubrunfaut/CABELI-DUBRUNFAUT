import java.awt.Color;

public class JeuNumeri extends Jeu
{


    public JeuNumeri()
    {
	super(creeJoueurs(2));
    }

    public JeuNumeri(int nJoueurs)
    {
	super(creeJoueurs(nJoueurs));
    }

    
    /**
     * Retourne un tableau d'objets Case comme plateau.
     *
     * @return      Un plateau sous forme de tableau de Case
     * @see         Case
     */
    public static CaseNumeri[] creePlateau()
    {
	CaseNumeri[] plateau = new CaseNumeri[40];
	
	int [][] tabCase={{ 851,120 },
			  { 772,118 },
			  { 698,92 },
			  { 626,115 },
			  { 550,91 },
			  { 477,113 },
			  { 401,92 },
			  { 330,115 },
			  { 254,89 },
			  { 176,85 },
			  { 114,134 },
			  { 123,209 },
			  { 199,228 },
			  { 273,252 },
			  { 348,231 },
			  { 422,253 },
			  { 495,233 },
			  { 566,254 },
			  { 642,232 },
			  { 718,252 },
			  { 790,231 },
			  { 865,239 },
			  { 883,311 },
			  { 831,363 },
			  { 881,416 },
			  { 864,496 },
			  { 789,505 },
			  { 713,485 },
			  { 641,507 },
			  { 568,484 },
			  { 494,503 },
			  { 418,485 },
			  { 346,503 },
			  { 272,485 },
			  { 196,507 },
			  { 125,484 },
			  { 113,412 },
			  { 176,371 },
			  { 255,369 },
			  { 334,370 }};
	
	plateau[0] = new CaseNumeri(tabCase[0][0], tabCase[0][1],0,-3);
	int j =0;
	int k =15;

	for(int i=1; i<tabCase.length; i++){
	 
	    if(i==1||i==2||i==3){
		plateau[i] = new CaseNumeri(tabCase[i][0], tabCase[i][1], i, -3);
	    }else if(i==4||i==5){
		plateau[i] = new CaseNumeri(tabCase[i][0], tabCase[i][1], i, -2);
	    }else if(i==6||i==7){
		plateau[i] = new CaseNumeri(tabCase[i][0], tabCase[i][1], i, -1);
	    }else if(i==10||i==13||i==15||i==17||i==19||i==20|i==23||i==26||i==28||i==29||i==31||i==32||i==34){
		plateau[i] = new CaseNumeri(tabCase[i][0], tabCase[i][1], i, j+1);
		j ++;
	    }else if(i==35||i==37||i==38||i==39){
		plateau[i] = new CaseNumeri(tabCase[i][0], tabCase[i][1], i, k+5);
		k ++;
	    }else{
		plateau[i] = new CaseNumeri(tabCase[i][0], tabCase[i][1], i, 0);
	    }
	}	
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
	
	VueGenerale v = new VueNumeriBasic();
	Joueur[] joueurs = new Joueur[nJoueurs];
	CaseNumeri[] plateau = creePlateau();
	
	int[] couleurs = {Color.red.getRGB(),
                          Color.green.getRGB(),
                          Color.blue.getRGB()};

	for (int i=0; i<nJoueurs; i++) {
	    joueurs[i] = new JoueurNumeri("Joueur " + (1+i), plateau, v, couleurs[i]);
	}

	return joueurs;
    }

}
