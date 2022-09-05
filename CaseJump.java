public class CaseJump extends CaseJeuOie
{
    // Concerne les cases 6 - 42 - 58
    // Ces cases ne peuvent pas être occupées elles envoient le pion aux cases 
    // 6 -> 12 // 42 -> 30 // 58 -> 0
 
    private int jump;

    /**
     * Constructeur de classe sans question.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CaseJump(int x, int y, int n, int jump)
    {
	super(x, y, n);
	this.jump = jump;
    }

    /**
     * {@inheritDoc}
     * Renvoie le mouvement pour aller à la nouvelle case.
     *
     */
    @Override public int active(JoueurOie j)
    {
	System.out.println("Saut!");
	return jump;
    }
}
