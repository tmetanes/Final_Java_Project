public class Note20P extends Phone{
    boolean Spen;

    public Note20P(int id, String pname, int pprice, int pstock, boolean spen) {
        super(id, pname, pprice, pstock);
        Spen = spen;
    }

    public boolean isSpen() {
        return Spen;
    }

    public void setSpen(boolean spen) {
        Spen = spen;
    }

    @Override
    public String toString() {
        return "Note 20 Plus " +
                " Stock = " + getPstock() +
                " Price = "+ getPprice()+"\n";
    }
}
