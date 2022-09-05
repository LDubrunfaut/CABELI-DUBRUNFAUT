import java.util.Hashtable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.*;


public class VueGenerale {

    private JFrame fen; // La fenetre
    private JPanel pan; // l'image de fond
	
    private Hashtable<Integer,Integer[]> caseCoord; // l'association no Case, coordonnees
	
    // constructeur de la fenetre, avec une image de fond et la dimension de la fenetre
    public VueGenerale(String bgroundPic, int x, int y){    	
	fen = new JFrame ("Projet Jeu");
    	pan =new ImageSimple(bgroundPic, x,y);
	fen.setContentPane(pan); 
	fen.setSize(x,y);
	fen.setLocationRelativeTo(null);
	fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fen.setLayout(null);
	fen.setVisible(true);
	/*
	  pan.addMouseListener(new MouseAdapter() {
	  public void mousePressed(MouseEvent e) {
	  System.out.println("{ "+e.getX() + "," + e.getY()+" },");
	  }
	  });
	*/
	caseCoord=new Hashtable<Integer,Integer[]>();
    } 
	
    // methode qui associe une case a des coordonnees
    public void setCase(int casenb, int x, int y) {
	Integer t[]=new Integer[2];
	t[0]=x;
	t[1]=y;
	caseCoord.put(casenb, t);
    }


    public void positionneEnCase(int i, ImageSimple p) {
	Integer[] rep=caseCoord.get(i);;
	if (rep==null){
	    System.out.println ("La case no "+i +" n'a pas encore ete declaree avec setCase, on considere qu'elle est en 0,0");
	    p.setBounds(0, 0, p.largeur, p.hauteur);
	} else {
	    p.setBounds(rep[0], rep[1], p.largeur, p.hauteur);
	}
	pan.add(p);
    }
	
    public void deplace(ImageSimple p, int depart, int arrivee) {
	Integer[] dep=caseCoord.get(depart);;
	if (dep==null){
	    System.out.println ("La case no "+depart+" n'a pas encore ete declaree avec setCase, on considere qu'elle est en 0,0");
	    dep=new Integer[2];
	    dep[0]=0;
	    dep[1]=0;
	}
	Integer[] arr=caseCoord.get(arrivee);;
	if (arr==null){
	    System.out.println ("La case no "+arrivee+" n'a pas encore ete declaree avec setCase, on considere qu'elle est en 0,0");
	    arr=new Integer[2];
	    arr[0]=0;
	    arr[1]=0;
	}
		
	
	int x0=dep[0],y0=dep[1];
	int x1=arr[0], y1=arr[1];
	int nbStep=20;
	
	try {
	    for (int i=0;i<nbStep;i++){
		Thread.sleep(10);
		int x=x0+(x1-x0)*i/nbStep;
		int y=y0+(y1-y0)*i/nbStep;
		p.setBounds(x,y,50,50);
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }
	
	
}
