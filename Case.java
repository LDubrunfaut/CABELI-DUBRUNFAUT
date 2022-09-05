public abstract class Case
{
    private int numero;
    private boolean occupee;
    private int x;
    private int y;

    /**
     * Constructeur de classe
     *
     * @param  x  La position en abscisses de la case.
     * @param  y  La position en ordonnées de la case.
     * @param  n  La position de la case sur le plateau.
     */    
    public Case(int x, int y, int n)
    {
	this.x = x;
	this.y = y;
	this.numero = n;
	occupee = false;
    }

    /**
     * Modifie l'état de la case.
     *
     * @param  occupee  un boolean true si la case est occupée, false si elle ne 
     * l'est pas.
     */
    public void setOccupee(boolean occupee)
    {
	this.occupee = occupee;
    }

    /**
     * Renvoit l'état de la case.
     *
     * @return true si la case est occupée, false sinon.
     */
    public boolean isOccupee()
    {
	return occupee;
    }
}
