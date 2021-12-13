import java.awt.Color;
import java.util.Stack;


public class Minimax {

    public double boardValue(Board board) {
        double VALUE = 0;
        if (board.localScore > 32) {
            VALUE += 1000;

            System.out.println("winning");
        }
        else if(board.enemyScore > 32)
            VALUE -= 1000;
        if(board.localScore > board.enemyScore)
            VALUE += (board.localScore * 1.2) - board.enemyScore;
        else
            VALUE += board.localScore - board.enemyScore;
        if(board.getPlayer().isOtherStarving() && board.getPlayer().activePlayerLocal && (board.localScore > board.enemyScore)) {
            VALUE += 1000;
            System.out.println("Starving");

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


