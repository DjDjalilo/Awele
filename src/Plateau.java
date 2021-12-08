public class Plateau {
    Case[] cases = new Case[16];
    public boolean ordi_joue;
    public int pions_pris_joueur=0;
    public int pions_pris_ordi=0;
    public Plateau(){
        for(int i=0;i<16;i++){
            cases[i]=new Case(2,2, i+1);
        }
    }
    public Plateau(Plateau p)
    {
        this.cases = p.cases;
        this.ordi_joue = p.ordi_joue;
        this.pions_pris_ordi = p.pions_pris_ordi;
        this.pions_pris_joueur = p.pions_pris_joueur;
    }
    public boolean gameStop(){
        if(totalSeeds() < 8) {
            System.out.println("Seeds less than 8 !");
            return true;
        }
        else if(pions_pris_joueur >= 33 || pions_pris_ordi >= 33)
            return true;
        else
            return false;
    }
    public boolean coupValide(Case c, Color color, boolean isordi,boolean ordiFirst){
        if(c.isEmpty())
            return false;
        if(color == Color.ROUGE && c.getRouge()==0)
            return false;
        if(color == Color.BLEU && c.getBlue()==0)
            return false;
        if(ordiFirst && isordi && c.id % 2 == 0 )
            return false;
        if(ordiFirst && !isordi && c.id % 2 ==1)
            return false;
        if(!ordiFirst && isordi && c.id % 2 == 1 )
            return false;
        if(!ordiFirst && !isordi && c.id % 2 ==0)
            return false;
        return true;
    }
    public Case[] getCases() {
        return cases;
    }
    public Case getCase(int i){
        return cases[i];
    }
    public int totalSeeds(){
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
        r += "\n---------------------------------Score---------------------------------\nPlayer 1 : "+ pions_pris_ordi+"\nPlayer 2 : "+ pions_pris_joueur;
        return r;
    }

}
