public class Case {
    int blue=0;
    int rouge=0;
    int id;
    public Case(int blue,int rouge, int id){
        this.blue=blue;
        this.rouge=rouge;
        this.id=id;
    }
    public Case(Case c){
        this.blue=c.getBlue();
        this.rouge=c.getRouge();
        this.id=c.getId();
    }
    public int totalSeeds(){
        return getBlue() + getRouge();
    }
    public int emptySeeds(){
        int i = totalSeeds();
        setBlue(0);
        setRouge(0);
        return i;
    }
    public boolean isEmpty(){
        return this.blue == 0 && this.rouge == 0;
    }
    public int removeColor(ColorSeeds c){
        if(c == ColorSeeds.BLEU) {
            int i = getBlue();
            setBlue(0);
            return i;
        }
        else{
            int i = getRouge();
            setRouge(0);
            return i;
        }
    }
    public void addRouge(){
        setRouge(getRouge()+1);
    }
    public void addBleu(){
        setBlue(getBlue()+1);
    }
    public void setBlue(int blue) {
        this.blue = blue;
    }
    public void setRouge(int rouge) {
        this.rouge = rouge;
    }
    public int getBlue(){
        return this.blue;
    }
    public int getRouge(){
        return this.rouge;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "("+ConsoleColors.BLUE+this.blue+"B"+ConsoleColors.RED+this.rouge+"R"+ConsoleColors.RESET+")";
    }
}
