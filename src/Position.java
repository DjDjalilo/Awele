public class Position {
    Case[] cases = new Case[16];
    Boolean ordi_joue;
    int pions_pris_joueur=0;
    int pions_pris_ordi=0;
    public Position(){
        for(int i=0;i<16;i++){
            cases[i]=new Case(2,2);
        }
    }

    public Case[] getCases() {
        return cases;
    }
    public Case getCase(int i){
        return cases[i-1];
    }
    @Override
    public String toString(){
        String r="<<";
        for (Case c:cases){
            r+=c.toString()+",";
        }
        r+=">>";
        return r;
    }

}
