public class CasePuits extends CaseJeuOie
{
    // Concerne les cases : 31 et 52
    private JoueurOie prisonnier;
    // Tant qu'un autre joueur ne vient pas prendre la place du joueur qui est
    // coincé dedans ce joueur ne peut pas jouer.
    
    /**
     * Constructeur de classe sans question.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CasePuits(int x, int y, int n)
    {
	super(x, y, n);
    }


    /**
     * {@inheritDoc}
     * <p>
     * Si un joueur tombe sur cette case il doit attendre d'etre relevé avant
     * de continuer
     * 
     * @return 0, le joueur est bloqué.
     */
    public int active(JoueurOie j){
	// méthode pour modifier l'etat de liberté du joueur qui vient d'arriver dessus
	// si un joueur était prisonnier dedans, le libérer.
	if(prisonnier != null) {
	    prisonnier.setLibre(true);
	}
	j.setLibre(false);
	prisonnier = j;
	return 0;
    }
}
