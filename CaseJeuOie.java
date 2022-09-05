import java.util.*;

public class CaseJeuOie extends Case
{

    private String question;
    private String reponse;

    /**
     * Constructeur de classe avec une question et réponse.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     * @param  question  Une question à poser au joueur qui tombe sur cette case.
     * @param   reponse  La bonne réponse à la question.
     */
    public CaseJeuOie(int x, int y, int n, String question, String reponse)
    {
	super(x, y, n);
	this.question = question;
	this.reponse = reponse;
    }

    /**
     * Constructeur de classe sans question.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CaseJeuOie(int x, int y, int n)
    {
	super(x, y, n);
    }
    
    /**
     * Active la case selon ses règles.
     * <p>
     * Vérifie si la case est occupée.
     * 
     * @return -1 si la case est occupée, 0 sinon.
     */
    public int active(JoueurOie j)
    {
	    if (isOccupee()) {
	        return -1;
	    }else {
	        setOccupee(true);
	        return 0;
	    }
    }

    /**
     * Pose une question au joueur et renvoit le nombre de points gagnés.
     * 
     * @return le nombre de points gagnés.
     */
    public int question() {
	if ((this.question instanceof String) && 
	    (this.reponse instanceof String)) {
	    
	    System.out.println(question);
	    Scanner scan = new Scanner(System.in);
	    String joueur_reponse = scan.nextLine();
	    if (reponse.equalsIgnoreCase(joueur_reponse)) {
		System.out.println("Bonne réponse!");
		return 1;
	    }
	    else {
		System.out.println("La réponse était " + reponse);
		return 0;
	    }
	}
	else {
	    return 0;
	}
    }
}
