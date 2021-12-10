import java.util.ArrayList;
import java.util.List;

public class Board {
    Case[] cases = new Case[16];
    First first;
    Player player;
    int enemyScore =0;
    int localScore =0;
    public Board(First first){
        for(int i=0;i<16;i++){
            cases[i]=new Case(2,2, i+1);
        }
        this.first = first;
        if(this.first == First.Local)
            this.player = new Player(this.first);
        else
            this.player = new Player(First.Oppenent);
        getPlayer().setBoard(this);
    }
    public Board(Board p)
    {
        for(int i=0;i<16;i++){
            this.cases[i]=new Case(p.getCases()[i]);
        }
        this.localScore = p.getLocalScore();
        this.enemyScore = p.getEnemyScore();
        this.first = p.getFirst();
        if(this.first == First.Local)
            this.player = new Player(this.first);
        else
            this.player = new Player(First.Oppenent);
        getPlayer().setBoard(this);
    }
    public List<int[]> coupValide(){
        List<int[]> result = new ArrayList<>();
        for(int i = 1; i <= getCases().length; i++){
            if(coupValide(i,Color.ROUGE, getPlayer().isActivePlayerLocal(),getFirst())){
                result.add(new int[]{i,0});
            }
            if(coupValide(i,Color.BLEU, getPlayer().isActivePlayerLocal(),getFirst())){
                result.add(new int[]{i,1});
            }

        }
        return  result;
    }
    public boolean gameStop(){
        if(totalSeedsInBoard() < 8) {
            System.out.println("Seeds less than 8 !, end of the game.");
            return true;
        }
        else if(getEnemyScore() >= 33 || getLocalScore() >= 33)
            return true;
        else if(getEnemyScore() == 32 && getLocalScore() == 32)
            return true;
        else
            return false;
    }
    public boolean coupValide(int caseId, Color color, boolean isLocal,First first){
        if(getCases()[caseId-1].isEmpty())
            return false;
        if(color == Color.ROUGE && getCases()[caseId-1].getRouge()==0)
            return false;
        if(color == Color.BLEU && getCases()[caseId-1].getBlue()==0)
            return false;
        if(isLocal && first==First.Local && caseId % 2 == 0 )
            return false;
        if(isLocal && first == First.Oppenent && caseId % 2 ==1)
            return false;
        if(!isLocal && first == First.Local && caseId % 2 == 1 )
            return false;
        if(!isLocal && first == First.Oppenent && caseId % 2 ==0)
            return false;
        return true;
    }
    public int valueBoard()
    {
        return getLocalScore() - getEnemyScore();
    }
    public int totalSeedsInBoard(){
        int t = 0;
        for(int i=0;i<getCases().length;i++){
            t += getCases()[i].totalSeeds();
        }
        return t;
    }
    @Override
    public String toString(){
        String r = "---------------------------------Board---------------------------------\n";
        for (int i = 0;i < 8;i++){

            r +=String.format("%02d",i+1)+cases[i].toString()+" ";
        }
        r+= "\n";
        for (int i = cases.length-1;i >= 8;i--){
            r +=String.format("%02d",i+1)+cases[i].toString()+" ";
        }
        r += "\n---------------------------------Score---------------------------------\nPlayer 1 : "+ localScore +"\nPlayer 2 : "+ enemyScore;
        return r;
    }

    public Case[] getCases() {
        return cases;
    }

    public void setCases(Case[] cases) {
        this.cases = cases;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public void setEnemyScore(int enemyScore) {
        this.enemyScore = enemyScore;
    }

    public int getLocalScore() {
        return localScore;
    }

    public void setLocalScore(int localScore) {
        this.localScore = localScore;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
