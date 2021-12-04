public class Case {
    int blue=0;
    int rouge=0;
    public Case(int blue,int rouge){
        this.blue=blue;
        this.rouge=rouge;
    }

    public boolean isEmpty(){
        if(this.blue==0 && this.rouge==0) return true ;
        else return false;
    }

    public int getBlue(){
        return this.blue;
    }
    public void addBleu(){
        this.blue++;
    }
    public void addRouge(){
        this.rouge++;
    }
    public int getRouge(){
        return this.rouge;
    }
    @Override
    public String toString(){
        return "[ B:"+this.blue+" R: "+this.rouge+"]";
    }
}
