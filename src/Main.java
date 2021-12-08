import java.util.Objects;
import java.util.Scanner;

enum Color{ROUGE,BLEU};


public class Main {
    Player player;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Player player = new Player(true);
        boolean gameStop = false;
        int Case;

        Color c = null;
        if(player.ordiFirst)
            player.getBoard().ordi_joue = true;
        else
            player.getBoard().ordi_joue = false;
        while(!gameStop){
            System.out.println(player.getBoard());
            if(player.isOrdi())
            {
                System.out.println("ORDI :");
                player.construireLarbre();
            }
            else
                System.out.println("JOUEUR :");




           Arbre a= player.buildTree();
           for(Arbre x:a.getFils()){
               System.out.println(x.getNoed());
           }




            String[] words = in.nextLine().split("(?<=\\d)(?=\\D)");
            Case = Integer.parseInt(words[0]);

            if(Objects.equals(words[1], "B") || Objects.equals(words[1], "b"))
                c = Color.BLEU;
            else if(Objects.equals(words[1], "R") || Objects.equals(words[1], "r"))
                c = Color.ROUGE;


            if(player.getBoard().coupValide(player.getBoard().getCase(Case - 1),c, player.getBoard().ordi_joue,player.ordiFirst))
                player.jouerCoup(Case,c);
            else
            {
                System.out.println("COUP NON VALIDE");
                break;
            }


            gameStop = player.getBoard().gameStop();
            player.ordi = !player.isOrdi();
            player.getBoard().ordi_joue = !player.getBoard().ordi_joue;
        }

    }


}
