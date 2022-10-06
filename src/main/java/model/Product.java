package model;

public class Product {
    private int idProduct;
    private String nameProduct;

    private int priceProduct;
    private CategoryDetail idCategoryDetail;

    public Product() {
    }

    public Product(int idProduct, String nameProduct, int priceProduct, CategoryDetail idCategoryDetail) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.idCategoryDetail = idCategoryDetail;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(int priceProduct) {
        this.priceProduct = priceProduct;
    }

    public CategoryDetail getIdCategoryDetail() {
        return idCategoryDetail;
    }

    public void setIdCategoryDetail(CategoryDetail idCategoryDetail) {
        this.idCategoryDetail = idCategoryDetail;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", nameProduct='" + nameProduct + '\'' +
                ", priceProduct=" + priceProduct +
                ", idCategoryDetail=" + idCategoryDetail +
                '}';
    }
}
