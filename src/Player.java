import java.util.ArrayList;

public class Player {
    boolean activePlayerLocal;
    Board board;
    First first;
    public Player(First first){
        if(first == First.Local)
            activePlayerLocal = true;
        else
            activePlayerLocal = false;
        this.first = first;
    }
    public boolean otherStarving(){
        if(isActivePlayerLocal() && (getFirst() == First.Local)) {
            for (int i = 1; i < getBoard().getCases().length; i = i + 2) {
                if (!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        }
        else if(isActivePlayerLocal() && (getFirst() == First.Oppenent)) {
            for (int i = 0; i < getBoard().getCases().length; i++) {
                if (!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        }
        else if(!isActivePlayerLocal() && (getFirst() == First.Local)) {
            for (int i = 0; i < getBoard().getCases().length; i++) {
                if (!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        }
        else if(!isActivePlayerLocal() && (getFirst() == First.Oppenent))
            for(int i = 1; i < getBoard().getCases().length; i= i +2) {
                if(!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        return true;
    }
    public void jouerCoup(int caseToPlay,Color c){
        if(c == null) {
            System.out.println("ERROR");
            return;
        }
        Sowing(fillCases(caseToPlay,c));
    }
    public void Sowing(int caseSowing)
    {
        for (int i = caseSowing; (getBoard().getCases()[i].totalSeeds() == 2) || (getBoard().getCases()[i].totalSeeds() == 3);)
        {
            if(activePlayerLocal)
                getBoard().setLocalScore(getBoard().getLocalScore() + getBoard().getCases()[i].emptySeeds());
            else
                getBoard().setEnemyScore(getBoard().getEnemyScore() + getBoard().getCases()[i].emptySeeds());
            i--;
            i = (i+16) % 16;
        }
    }
    public int fillCases(int caseAjouer,Color c){
        int j = caseAjouer-1;
        int defaultJ = j;
        int i = getBoard().getCases()[j].removeColor(c);
        if(i == -1) {
            System.out.println("ERROR");
            return -1;
        }
        if(c == Color.ROUGE)
        {
            while(i!=0)
            {
                if(defaultJ == j) {
                    j++;
                }
                else {
                    j = j % 16;
                    getBoard().getCases()[j].addRouge();
                    j++;
                    i--;
                }
            }
            return j-1;
        }
        else{
            while(i!=0)
            {
                if(defaultJ == j) {
                    j++;
                }
                else {
                    j = j % 16;
                    getBoard().getCases()[j].addBleu();
                    i--;
                    j += 2;
                }
            }
            return j-2;
        }
    }

    /*public ArrayList<int[]> getcoupValide(Board p){
        ArrayList<int[]> result = new ArrayList<int[]>();
        for(int i = 1; i <= p.getCases().length; i++){
            if(p.coupValide(p.getCase(i - 1),Color.ROUGE, activePlayerLocal,ordiFirst)){
                result.add(new int[]{i,0});
            }
            if(p.coupValide(p.getCase(i - 1),Color.BLEU, activePlayerLocal,ordiFirst)){
                result.add(new int[]{i,1});
            }

        }
        return  result;
    }

    public Arbre construireLarbre() {
        Board board =this.board;
        return new Arbre(board);
    }

    public Arbre buildTree() {
        Board board =this.board;
        Arbre a=new Arbre(board);
        Board prev;
             ArrayList<int[]> coupPossibles=getcoupValide(board);
             prev = new Board(getBoard());
             Color c;
             for (int[] coup:coupPossibles){
                 c=coup[1]==0?Color.ROUGE:Color.BLEU;
                 jouerCoupPlateau(coup[0],c,prev);
                 a.addFils(new Arbre(prev));
                 prev = new Board(getBoard());

        }
        return  a;


    }*/

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public boolean isActivePlayerLocal() {
        return activePlayerLocal;
    }

    public void setActivePlayerLocal(boolean activePlayerLocal) {
        this.activePlayerLocal = activePlayerLocal;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }
}
