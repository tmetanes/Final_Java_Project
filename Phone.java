public class Phone {
    private String Pname;
    private int Pprice;
    private int Pstock;
    private int id;


// ****************  CONSTRUCTOR  *************************//
    public Phone(int id,String pname, int pprice, int pstock) {
        this.id = id;
        this.Pname = pname;
        this.Pprice = pprice;
        this.Pstock = pstock;
    }

    public int getId() {
        return id;
    }

    public String getPname() {
        return Pname;
    }

    public int getPprice() {
        return Pprice;
    }

    public int getPstock() {
        return Pstock;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public void setPprice(int pprice) {
        Pprice = pprice;
    }

    public void setPstock(int pstock) {
        Pstock = pstock;
    }


    @Override
    public String toString() {
        return  "\n Model = " + Pname +
                ", Price = " + Pprice +
                ", stock = " + Pstock;
    }
}



