public class CaseEnd extends CaseJeuOie
{

    /**
     * Constructeur de classe
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CaseEnd(int x, int y, int n)
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
	System.out.println(j + " a gagné!");
	setOccupee(true);
	return 0;
    }

}
