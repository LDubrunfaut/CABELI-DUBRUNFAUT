import java.util.*;

public class CaseOie extends CaseJeuOie
{

    // Quand on tombe sur une Oie on relance les dés.
    // Il suffit donc de relancer les dés comme si on partait de cette position.

    /**
     * Constructeur de classe sans question.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CaseOie(int x, int y, int n)
    {
	super(x, y, n);
    }


    /**
     * {@inheritDoc}
     * Quand un joueur tombe sur cette case il relance deux dés.
     *
     */
    @Override public int active(JoueurOie j)
    {

	int des_res;

	if (j.getTriche()) {
	    boolean flag = true;
	    Scanner scan = new Scanner(System.in);
	    String reponse;
	    while(flag){
		try{
		    System.out.println("Triche! Entrez le résultat du lancé de dé:");
		    reponse = scan.nextLine();
		    des_res = Integer.parseInt(reponse);
		    return des_res;
		}catch(NumberFormatException | NullPointerException e) {
		    System.out.println("Lancé aléatoire? (oui)");
		    reponse = scan.nextLine();
		    if (reponse.equalsIgnoreCase("oui")){
			flag = false;
		    }
		}
	    }
	}
	
	return(new Random().nextInt(j.getNbFacesDe()) + 
	       new Random().nextInt(j.getNbFacesDe()) + 2);
	
    }

}
