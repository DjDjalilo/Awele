enum Color{ROUGE,BLEU};


public class Main {
    public static void main(String[] args) {
        Position pos=new Position();
        System.out.println(pos);
    }


    private boolean coupValide(Position pos_courante, boolean ordi_joue, int i) {
        return true;
    }

    private int evaluation(Position pos_courante, boolean ordi_joue, int prof) {
        return 0;
    }

    private boolean positionFinale(Position pos_courante, boolean ordi_joue, int prof) {
        return true;
    }
    Position jouerCoup(Position pos_courante,boolean ordi_jouer,Color c,int i){
        if(c==Color.ROUGE){

           int r= pos_courante.getCase(i).getRouge();
           int j=i;
           while(r!=0){
               j++;
               pos_courante.getCase(j).getRouge();
               r--;
           }

        }else{

        }

        return pos_courante;

    }

    int getNextintCase(int i){
        if (i==16) return 1;
        else return i+1;
    }
}
