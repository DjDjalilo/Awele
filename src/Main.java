import java.util.Objects;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board board = new Board(First.Oppenent);

        boolean gameStop = false;
        int Case;
        Color c = null;
        while(!gameStop){
            System.out.println(board);
            if(board.getPlayer().isActivePlayerLocal()) {
                System.out.println("ORDI :");
                MinMax minmax = new MinMax();
                minmax.constructTree(board);
                int[] coups = minmax.findBestChild(false,minmax.tree.root.children).coup;
                System.out.println(coups[0] + " " + coups[1]);
                if(coups[1]==0)
                    c = Color.ROUGE;
                else
                    c = Color.BLEU;
                if(board.coupValide(coups[0],c, board.getPlayer().isActivePlayerLocal(),board.getFirst()))
                    board.getPlayer().jouerCoup(coups[0],c);
                else
                {
                    System.out.println("COUP NON VALIDE");
                    break;
                }

            }
            else {
                System.out.println("JOUEUR :");
                String[] words = in.nextLine().split("(?<=\\d)(?=\\D)");
                Case = Integer.parseInt(words[0]);
                if(Objects.equals(words[1], "B") || Objects.equals(words[1], "b"))
                    c = Color.BLEU;
                else if(Objects.equals(words[1], "R") || Objects.equals(words[1], "r"))
                    c = Color.ROUGE;
                if(board.coupValide(Case,c, board.getPlayer().isActivePlayerLocal(),board.getFirst()))
                    board.getPlayer().jouerCoup(Case,c);
                else
                {
                    System.out.println("COUP NON VALIDE");
                    break;
                }
            }


            gameStop = board.gameStop();
            board.getPlayer().setActivePlayerLocal(!board.getPlayer().isActivePlayerLocal());
        }

    }

}
