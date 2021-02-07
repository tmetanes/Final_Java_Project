public class Chart {
    int id;
    String productName;
    int productPrice;
    int nr=0;

    public Chart(String productName, int productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString()
    {
        return  "X "+ nr++ +" Item: " + productName +
                   ", Price: " + productPrice +
                "\n";

    }
}
