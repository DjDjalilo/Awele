import java.util.ArrayList;

public class Arbre {
    Plateau noeud;
    ArrayList<Arbre> fils;

    Arbre(Plateau noeud){
       this.noeud=noeud;
       fils= new ArrayList<>();
    }

    public void addFils(Arbre a){
            fils.add(a);
    }
    public Plateau getNoed(){
        return this.noeud;
    }

    public ArrayList<Arbre> getFils() {
        return fils;
    }


}
