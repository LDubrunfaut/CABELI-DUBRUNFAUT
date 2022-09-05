public abstract class Joueur
{
    private int score;
    protected String nom;
    // Plateau de jeu avec des Case differentes.
    private Case[] plateau;
    // Interface graphique du plateau.
    private VueGenerale vue;

    /**
     * Constructeur de classe.
     *
     * @param     nom    Le nom du joueur.
     * @param   plateau  Tableau de Case.
     * @param vuePlateau Interface graphique du plateau.
     */
    public Joueur(String nom, Case[] plateau, VueGenerale vuePlateau)
    {
	this.score = 0;
	this.nom = nom;
	this.plateau = plateau;
	this.vue = vuePlateau;
    }

    /**
     * Effectue le tour du joueur selon les règles du jeu.
     * 
     * @return true si la partie est finie, false sinon
     */
    public abstract boolean tour();

    /**
     * Renvoit la Case du plateau à la position passée en argument.
     *
     * @param i La position de la Case demandée.
     * @return L'objet Case en position i.
     */
    public Case getCase(int i)
    {
	return plateau[i];
    }

    /**
     * Ajoute un entier au score du joueur.
     *
     * @param i La valeur à ajouter au score.
     */
    public void addScore(int i)
    {
	score += i;
    }

    /**
     * Change le score du joueur.
     *
     * @param i La valeur du score.
     */
    public void setScore(int i)
    {
	score = i;
    }

    /**
     * Retourne le score du joueur.
     *
     * @return La valeur du score.
     */
    public int getScore()
    {
	return score;
    }


}
