public class GalaxyS20 extends Phone{
    int exStorage;

    public GalaxyS20(int id, String pname, int pprice, int pstock, int exStorage) {
        super(id, pname, pprice, pstock);
        this.exStorage = exStorage;
    }

    public int getExStorage() {
        return exStorage;
    }

    public void setExStorage(int exStorage) {
        this.exStorage = exStorage;
    }

    @Override
    public String toString() {
        return "GalaxyS20 ," +
                " Stock = " + getPstock() +
                " Price = "+ getPprice()+"\n";
    }
}
