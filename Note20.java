public class Note20 extends Phone{
    boolean flatScreen;

    public Note20(int id, String pname, int pprice, int pstock, boolean flatScreen) {
        super(id, pname, pprice, pstock);
        this.flatScreen = flatScreen;
    }

    public boolean isFlatScreen() {
        return flatScreen;
    }

    public void setFlatScreen(boolean flatScreen) {
        this.flatScreen = flatScreen;
    }

    @Override
    public String toString() {
        return "Note 20 " +
                " Stock = " + getPstock() +
                " Price = "+ getPprice()+"\n";
    }
}
