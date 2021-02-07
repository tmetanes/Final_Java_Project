public class GalaxyS20P extends Phone{
    boolean curvedScreen;

    public GalaxyS20P(int id, String pname, int pprice, int pstock, boolean curvedScreen) {
        super(id, pname, pprice, pstock);
        this.curvedScreen = curvedScreen;
    }

    public boolean isCurvedScreen() {
        return curvedScreen;
    }

    public void setCurvedScreen(boolean curvedScreen) {
        this.curvedScreen = curvedScreen;
    }

    @Override
    public String toString() {
        return "GalaxyS20 Plus, " +
                " Stock = " + getPstock() +
                " Price = "+ getPprice()+"\n";
    }
}
