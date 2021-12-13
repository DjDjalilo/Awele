public class Player {
    boolean activePlayerLocal;
    Board board;
    First first;
    public  boolean otherStarving = false;
    public Player(){

    }
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

            for (int i = 0; i < getBoard().getCases().length; i=i+2) {
                if (!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        }
        else if(!isActivePlayerLocal() && (getFirst() == First.Local)) {

            for (int i = 0; i < getBoard().getCases().length; i=i+2) {
                if (!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        }
        else if(!isActivePlayerLocal() && (getFirst() == First.Oppenent)) {

            for (int i = 1; i < getBoard().getCases().length; i = i + 2) {
                if (!getBoard().getCases()[i].isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }
    public void jouerCoup(int caseToPlay, ColorSeeds c){
        if(c == null) {
            System.out.println("ERROR");
            return;
        }
        Sowing(fillCases(caseToPlay,c));

        if(otherStarving())

        {

            if(isActivePlayerLocal()) {
                getBoard().localScore += getBoard().totalSeedsInBoard();
            }
            else {
                getBoard().enemyScore += getBoard().totalSeedsInBoard();
            }
            setOtherStarving(true);
        }
        activePlayerLocal = !activePlayerLocal;
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
    public int fillCases(int caseAjouer, ColorSeeds c){
        int j = caseAjouer-1;
        int defaultJ = j;
        int i = getBoard().getCases()[j].removeColor(c);
        if(c == ColorSeeds.ROUGE)
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

    public boolean isOtherStarving() {
        return otherStarving;
    }

    public void setOtherStarving(boolean otherStarving) {
        this.otherStarving = otherStarving;
    }
}
