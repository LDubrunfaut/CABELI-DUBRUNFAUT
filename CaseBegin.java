public class CaseBegin extends CaseJeuOie
{

    /**
     * Constructeur de classe
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CaseBegin(int x, int y, int n)
    {
	super(x, y, n);
    }

    /**
     * {@inheritDoc}
     *
     * @return 0 - aucun effet.
     */
    public int active(JoueurOie j)
    {
	return 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Cette case n'est jamais occupée.
     *
     * @return false.
     */
    @Override public boolean isOccupee(){
	return false;
    }
    
}
