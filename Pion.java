public class Pion
{
    private int pos;
    private VueGenerale vue;
    private ImageSimple img;

    /**
     * Constructeur de classe.
     *
     * @param  v  Interface graphique VueGenerale du plateau
     * @param  i  ImageSimple associée au pion
     */
    public Pion(VueGenerale v, ImageSimple i)
    {
	this.vue = v;
	this.img = i;
    }

    /**
     * Constructeur de classe.
     *
     * @param   v   Interface graphique VueGenerale du plateau
     * @param   i   ImageSimple associée au pion
     * @param color Couleur du pion
     */
    public Pion(VueGenerale v, ImageSimple i, int color)
    {
	this.vue = v;
	this.img = i;
	i.color(color);
    }

    /**
     * Déplace le pion selon les résultats du lancé de dés.
     *
     * @param  des_res  int : Résultat du lancé de dés.
     */
    public void move(int des_res)
    {
	int new_pos = pos + des_res;
        System.out.println("Case " + pos + " à " + new_pos);
	if (des_res > 0) {
	    while(pos != new_pos){
		vue.deplace(img, pos, pos+1);
		pos ++;
	    }
	}
	else {
	    while(pos != new_pos){
		vue.deplace(img, pos, pos-1);
		pos --;
	    }
	}
    }

    /**
     * Retourne la position du pion sur le plateau.
     *
     * @return La position du pion sur le plateau.
     */
    public int getPos()
    {
	return pos;
    }
}
