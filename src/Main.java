import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        Minimax minimax = new Minimax();
        Scanner in = new Scanner(System.in);
        Board board = new Board(First.Oppenent);
        int depth;
        boolean gameStop = false;
        int Case;
        ColorSeeds c = null;
        int[] coups;
        while(!gameStop){
            System.out.println(board);
            if(board.getPlayer().isActivePlayerLocal()) {
                int coup=board.coupValide().size();
                depth = 6+(16-coup) / 4;
                System.out.println("Coup valide : "+coup+" Depth : "+depth);
                System.out.println("\n"+ConsoleColors.GREEN+"LOCAL TURN:"+ConsoleColors.RESET);
                coups = board.bestMoveUsingAlphaBeta( depth,true,Integer.MIN_VALUE,Integer.MAX_VALUE, minimax);
                if(coups[1]==0) {
                    c = ColorSeeds.ROUGE;
                    System.out.println(ConsoleColors.CYAN+"Local will play : "+ coups[0] + " RED\n");
                }
                else {
                    c = ColorSeeds.BLEU;
                    System.out.println(ConsoleColors.CYAN+"Local will play : "+ coups[0] + " BLUE\n");
                }
                if(board.coupValide(coups[0],c, board.getPlayer().isActivePlayerLocal(),board.getFirst()))
                    board.getPlayer().jouerCoup(coups[0],c);
                else
                {
                    System.out.println("COUP NON VALIDE, Oppenent win");
                    break;
                }

            }
            else {
                System.out.println("\n"+ConsoleColors.PURPLE+"OPPENENT TURN:"+ConsoleColors.RESET);
                String[] words = in.nextLine().split("(?<=\\d)(?=\\D)");
                Case = Integer.parseInt(words[0]);
                if(Objects.equals(words[1], "B") || Objects.equals(words[1], "b"))
                    c = ColorSeeds.BLEU;
                else if(Objects.equals(words[1], "R") || Objects.equals(words[1], "r"))
                    c = ColorSeeds.ROUGE;
                if(board.coupValide(Case,c, board.getPlayer().isActivePlayerLocal(),board.getFirst()))
                    board.getPlayer().jouerCoup(Case,c);
                else
                {
                    System.out.println("COUP NON VALIDE, Local win");
                    break;
                }
            }
            if(board.getPlayer().otherStarving()) {
                if (board.getPlayer().isActivePlayerLocal())
                    System.out.println("STARVING OPP");
                else
                    System.out.println("STARVING Local");
            }

            gameStop = board.gameStop();
        }

    }



}
