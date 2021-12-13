import java.awt.Color;
import java.util.Stack;


public class Minimax {

    double scoreCoef=0.5;
    double seedCoef=0.7;


    public double boardValue(Board board) {
        double VALUE = 0;
        if (board.localScore > 32) {
            VALUE += 1000;

        }
        if(board.localScore-board.enemyScore>0 && board.totalSeedsInBoard()<=8){
            VALUE += 1000;
        }
        if(board.localScore-board.enemyScore<0 && board.totalSeedsInBoard()<=8){
            VALUE -= 1000;
        }
        else if(board.enemyScore > 32)
            VALUE -= 1000;

        //cas intermidiares :
        if(board.localScore > board.enemyScore) {
            VALUE += ((board.localScore - board.enemyScore) / 32.0) * 10 * scoreCoef;

            if(board.getOPSeeds()<board.getLocalSeeds()){
                VALUE += ((board.getLocalSeeds() - board.getOPSeeds())/32.0)*10*seedCoef;
            }
        }
        else {
            VALUE -= ((board.enemyScore - board.localScore) / 32.0) * 10 * scoreCoef;

            if(board.getOPSeeds()>board.getLocalSeeds()){
                VALUE += ((board.getLocalSeeds() - board.getOPSeeds())/32.0)*10*seedCoef;
            }

        }

        if(board.getOpBlueseed()>0.9*board.getOPSeeds()){
            VALUE +=2;
        }else if (board.getLocalBlueseed()>0.9*board.getLocalSeeds()){
            VALUE -=2;
        }



        if(board.getPlayer().isOtherStarving() && board.getPlayer().activePlayerLocal && (board.localScore > board.enemyScore)) {
            VALUE += 1000;

        }
        else if (board.getPlayer().isOtherStarving() && board.getPlayer().activePlayerLocal && (board.localScore < board.enemyScore))
            VALUE -= 1000;
        return VALUE;

    }

    Stack stackBoard = new Stack();
    public Minimax()
    {
        for(int i = 0; i < 20000000;i++)
            stackBoard.push(new Board());
    }


    public double alphaBeta(Board board, int depth, boolean isMaxPlayer, double alpha, double beta) {
        if(depth == 0){
            return boardValue(board);

        }
        if(isMaxPlayer)
        {
            double highestSeenValue = Integer.MIN_VALUE;
            double currentValue;
            for(int[] Move : board.coupValide())
            {
                Board tempBoard = (Board) stackBoard.pop();
                tempBoard = board.copyBoard(tempBoard);
                ColorSeeds c;
                if(Move[1] == 0)
                    c = ColorSeeds.ROUGE;
                else
                    c = ColorSeeds.BLEU;
                tempBoard.getPlayer().jouerCoup(Move[0],c);
                currentValue = alphaBeta(tempBoard,depth - 1,false,alpha,beta);
                highestSeenValue = Math.max(highestSeenValue,currentValue);
                alpha = Math.max(alpha,highestSeenValue);
                if (beta <= alpha)
                    return highestSeenValue;
                stackBoard.push(tempBoard);

            }
            return highestSeenValue;

        }
        else
        {
            double lowestSeenValue = Integer.MAX_VALUE;
            double currentValue;
            for(int[] Move : board.coupValide())
            {
                Board tempBoard = (Board) stackBoard.pop();
                tempBoard = board.copyBoard(tempBoard);
                ColorSeeds c;
                if(Move[1] == 0)
                    c = ColorSeeds.ROUGE;
                else
                    c = ColorSeeds.BLEU;
                tempBoard.getPlayer().jouerCoup(Move[0],c);
                currentValue = alphaBeta(tempBoard,depth - 1,true,alpha,beta);
                lowestSeenValue = Math.min(lowestSeenValue,currentValue);
                beta = Math.min(beta,lowestSeenValue);
                if (beta <= alpha)
                    return lowestSeenValue;
                stackBoard.push(tempBoard);

            }
            return lowestSeenValue;
        }
    }

}


