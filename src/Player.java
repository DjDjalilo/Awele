public class Player {
    boolean ordi;
    Plateau board;
    public boolean ordiFirst;
    public Player(boolean b){
        ordi = b;
        board = new Plateau();
        ordiFirst = b;
    }

    public void jouerCoup(int caseAjouer,Color c){
        if(c == null) {
            System.out.println("ERROR");
            return;
        }
        Sowing(fillCases(caseAjouer,c));
    }
    public void Sowing(int caseSowing)
    {
        System.out.println(caseSowing);
        for (int i = caseSowing; (getBoard().getCase(i).totalSeeds() == 2) || (getBoard().getCase(i).totalSeeds() == 3);)
        {
            System.out.println(i);
            if(ordi)
                board.pions_pris_ordi += getBoard().getCase(i).emptySeeds();
            else
                board.pions_pris_joueur += getBoard().getCase(i).emptySeeds();
            System.out.println(i);
            i--;
            i = (i+16) ;
            System.out.println(i);

        }
    }
    public int fillCases(int caseAjouer,Color c){
        int j = caseAjouer-1;
        int i = board.getCase(j).removeColor(c);
        if(c == Color.ROUGE)
        {
            while(i!=0)
            {
                j++;
                j = j % 16;
                board.getCase(j).addRouge();
                i--;
            }
            return j;
        }
        else{
            j++;
            while(i!=0)
            {
                j = j % 16;
                board.getCase(j).addBleu();
                i--;
                j+=2;
            }
            return j-2;
        }
    }
    public boolean isOrdi() {
        return ordi;
    }

    public void setOrdi(boolean ordi) {
        this.ordi = ordi;
    }

    public Plateau getBoard() {
        return board;
    }

    public void setBoard(Plateau board) {
        this.board = board;
    }
}
