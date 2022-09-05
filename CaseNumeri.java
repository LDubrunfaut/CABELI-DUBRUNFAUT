public class CaseNumeri extends Case
{
    private int valeur;

    /**
     * Constructeur de classe.
     *
     * @param      x     La position en abscisses de la case.
     * @param      y     La position en ordonnées de la case.
     * @param      n     La position de la case sur le plateau.
     * @param  valeur    La valeur de la case sur le plateau.
     */
    public CaseNumeri(int x, int y, int n, int valeur)
    {
	super(x, y, n);
	this.valeur = valeur;
    }

    /**
     * Vérifie si la case est occupée.
     * 
     * @return +1 si la case est occupée, 0 sinon.
     */
    public int active()
    {
	if (isOccupee()) {
	    return +1;
	}else {
	    setOccupee(true);
	    return 0;
	}
    }

    public int getVal()
    {
	return valeur;
    }
}
