import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable{
    public Case[] cases = new Case[16];
    public First first;
    public Player player;
    public int enemyScore =0;
    public int localScore =0;
    public void resetCases()
    {
        for(int i=0;i<16;i++){
            cases[i].setBlue(0);
            cases[i].setRouge(0);
        }
    }
    public Board(){

    }
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
    public Board copyBoard(Board boardAllocated){
        for(int i=0;i<16;i++){
            boardAllocated.cases[i]=new Case(this.cases[i].blue,this.cases[i].rouge, i+1);
        }
        boardAllocated.first = this.first;
        boardAllocated.player = new Player(boardAllocated.first);
        boardAllocated.player.activePlayerLocal = this.player.activePlayerLocal;
        boardAllocated.enemyScore = this.enemyScore;
        boardAllocated.localScore = this.localScore;
        boardAllocated.player.board = boardAllocated;
        return boardAllocated;

    }
    public List<int[]> coupValide(){
        List<int[]> result = new ArrayList<>();
        for(int i = 1; i <= getCases().length; i++){
            if(coupValide(i, ColorSeeds.ROUGE, getPlayer().isActivePlayerLocal(),getFirst())){
                result.add(new int[]{i,0});
            }
            if(coupValide(i, ColorSeeds.BLEU, getPlayer().isActivePlayerLocal(),getFirst())){
                result.add(new int[]{i,1});
            }

        }
        return  result;
    }
    public boolean gameStop(){
        if(totalSeedsInBoard() < 8) {
            System.out.println("Seeds less than 8 !, end of the game.");
            if(getLocalScore() > getEnemyScore())
                System.out.println("Local Win with : "+ getLocalScore()+" seed");
            else if (getLocalScore() < getEnemyScore())
                System.out.println("Oppenent Win with : "+ getEnemyScore()+" seed");
            else
                System.out.println("DRAW !");

            return true;
        }
        else if(getEnemyScore() >= 33) {
            System.out.println("Oppenent Win with : "+ getEnemyScore()+" seed");
            return true;
        }
        else if(getLocalScore() >= 33) {
            System.out.println("Local Win with : "+ getLocalScore()+" seed");
            return true;
        }
        else if(getEnemyScore() == 32 && getLocalScore() == 32) {
            System.out.println("DRAW !");
            return true;
        }
        else
            return false;
    }
    public boolean coupValide(int caseId, ColorSeeds colorSeeds, boolean isLocal, First first){
        if(getCases()[caseId-1].isEmpty())
            return false;
        else if(colorSeeds == ColorSeeds.ROUGE && getCases()[caseId-1].getRouge()==0)
            return false;
        else if(colorSeeds == ColorSeeds.BLEU && getCases()[caseId-1].getBlue()==0)
            return false;
        else if(isLocal && first==First.Local && caseId % 2 == 0 )
            return false;
        else if(isLocal && first == First.Oppenent && caseId % 2 ==1)
            return false;
        else if(!isLocal && first == First.Local && caseId % 2 == 1 )
            return false;
        else if(!isLocal && first == First.Oppenent && caseId % 2 ==0)
            return false;
        else
            return true;
    }

    public int totalSeedsInBoard(){
        int t = 0;
        for(int i=0;i<getCases().length;i++){
            t += getCases()[i].totalSeeds();
        }
        return t;
    }

    public int[] bestMoveUsingAlphaBeta(int depth, boolean isMaxPlayer, int alpha, int beta, Minimax minimax)
    {
        System.out.println(localScore);
        long time = System.currentTimeMillis();
        int[] BestMove = null;
        double score;
        if(isMaxPlayer) {
            double bestMoveScore = Integer.MIN_VALUE;
            double moveScore;
            for(int[] Move : coupValide())
            {
                Board tempBoard = new Board();

                tempBoard = copyBoard(tempBoard);
                ColorSeeds c;
                if(Move[1] == 0)
                    c = ColorSeeds.ROUGE;
                else
                    c = ColorSeeds.BLEU;

                tempBoard.getPlayer().jouerCoup(Move[0],c);
                moveScore = minimax.alphaBeta(tempBoard,depth, false,alpha,beta);
                score = Math.max(moveScore,bestMoveScore);
                if(score > bestMoveScore)
                {
                    bestMoveScore = score;
                    BestMove = Move;
                }
            }
            //System.out.println("Best move score : "+bestMoveScore);
        }
        else{
            double bestMoveScore = Integer.MAX_VALUE;
            double moveScore;
            for(int[] Move : coupValide())
            {
                Board tempBoard = new Board();
                tempBoard = copyBoard(tempBoard);
                ColorSeeds c;
                if(Move[1] == 0)
                    c = ColorSeeds.ROUGE;
                else
                    c = ColorSeeds.BLEU;
                tempBoard.getPlayer().jouerCoup(Move[0],c);
                moveScore = minimax.alphaBeta(tempBoard,depth, true, alpha,beta);
                score = Math.min(moveScore,bestMoveScore);
                if(score < bestMoveScore)
                {
                    bestMoveScore = score;
                    BestMove = Move;
                }
            }
            //System.out.println("Best move score : "+bestMoveScore);
        }
        long timeEnd = System.currentTimeMillis()-time;
        //System.out.println("Time = "+timeEnd+"ms");
        return BestMove;

    }
    @Override
    public String toString(){
        String r = ConsoleColors.BLACK_BACKGROUND+"---------------------------------Board---------------------------------"+ConsoleColors.RESET+"\n";

        for (int i = 0;i < 8;i++){

            r +=String.format(ConsoleColors.YELLOW+"%02d"+ ConsoleColors.RESET,i+1)+cases[i].toString()+" ";
        }
        r+= "\n";
        for (int i = cases.length-1;i >= 8;i--){
            r +=String.format(ConsoleColors.YELLOW+"%02d"+ ConsoleColors.RESET,i+1)+cases[i].toString()+" ";
        }

        r += "\n"+ConsoleColors.BLACK_BACKGROUND+"---------------------------------Score---------------------------------"+ConsoleColors.RESET+"\n\n\t"+ConsoleColors.GREEN+"Local : "+ localScore +" seeds"+ConsoleColors.RESET+"\t\t\t\t\t\t\t"+ConsoleColors.PURPLE+"Oppenent : "+ enemyScore+" seeds"+ConsoleColors.RESET;
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
