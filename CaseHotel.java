public class CaseHotel extends CaseJeuOie
{
    
    private int n_tours_hotel;

    /**
     * Constructeur de classe sans question.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     */
    public CaseHotel(int x, int y, int n, int n_tours)
    {
	super(x, y, n);
	this.n_tours_hotel = n_tours;
    }

    /**
     * {@inheritDoc}
     * Modifie l'état du joueur avec {@link JoueurOie#setToursHotel(int) setToursHotel}.
     *
     */
    @Override public int active(JoueurOie j)
    {
	j.setToursHotel(n_tours_hotel);
	return 0;
    }
}
