import java.util.ArrayList;

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
    public void jouerCoupPlateau(int caseAjouer,Color c, Plateau p){
        if(c == null) {
            System.out.println("ERROR");
            return;
        }
        Sowing(fillCasesPlateau(caseAjouer,c,p));
    }
    public void Sowing(int caseSowing)
    {

        for (int i = caseSowing; (getBoard().getCase(i).totalSeeds() == 2) || (getBoard().getCase(i).totalSeeds() == 3);)
        {
            System.out.println(i);
            if(ordi)
                board.pions_pris_ordi += getBoard().getCase(i).emptySeeds();
            else
                board.pions_pris_joueur += getBoard().getCase(i).emptySeeds();
            System.out.println(i);
            i--;
            i = (i+16) % 16;
            System.out.println(i);

        }
    }
    public void SowingPlateu(int caseSowing, Plateau p)
    {
        for (int i = caseSowing; (p.getCase(i).totalSeeds() == 2) || (p.getCase(i).totalSeeds() == 3);)
        {
            System.out.println(i);
            if(ordi)
                p.pions_pris_ordi += p.getCase(i).emptySeeds();
            else
                p.pions_pris_joueur += p.getCase(i).emptySeeds();
            System.out.println(i);
            i--;
            i = (i+16) % 16;
            System.out.println(i);

        }
    }

    public int fillCasesPlateau(int caseAjouer,Color c,Plateau p){
        int j = caseAjouer-1;
        int i = p.getCase(j).removeColor(c);
        if(c == Color.ROUGE)
        {
            while(i!=0)
            {
                j++;
                j = j % 16;
                p.getCase(j).addRouge();
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
    public ArrayList<int[]> getcoupValide(Plateau p){
        ArrayList<int[]> result = new ArrayList<int[]>();
        for(int i = 1; i <= p.getCases().length; i++){
            if(p.coupValide(p.getCase(i - 1),Color.ROUGE,ordi,ordiFirst)){
                result.add(new int[]{i,0});
            }
            if(p.coupValide(p.getCase(i - 1),Color.BLEU,ordi,ordiFirst)){
                result.add(new int[]{i,1});
            }

        }
        return  result;
    }

    public Arbre construireLarbre() {
        Plateau plateau=this.board;
        return new Arbre(plateau);
    }

    public Arbre buildTree() {
        Plateau plateau=this.board;
        Arbre a=new Arbre(plateau);
        Plateau prev;
             ArrayList<int[]> coupPossibles=getcoupValide(plateau);
             prev = new Plateau(getBoard());
             Color c;
             for (int[] coup:coupPossibles){
                 c=coup[1]==0?Color.ROUGE:Color.BLEU;
                 jouerCoupPlateau(coup[0],c,prev);
                 a.addFils(new Arbre(prev));
                 prev = new Plateau(getBoard());

        }
        return  a;


    }
}
