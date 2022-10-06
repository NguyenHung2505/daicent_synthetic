package model;

public class CategoryDetail {
    private int idCategoryDetail;
    private String nameCategorydetail;
    private Category categoryId;

    public CategoryDetail() {
    }

    public CategoryDetail(int idCategoryDetail, String nameCategorydetail, Category categoryId) {
        this.idCategoryDetail = idCategoryDetail;
        this.nameCategorydetail = nameCategorydetail;
        this.categoryId = categoryId;
    }

    public int getIdCategoryDetail() {
        return idCategoryDetail;
    }

    public void setIdCategoryDetail(int idCategoryDetail) {
        this.idCategoryDetail = idCategoryDetail;
    }

    public String getNameCategorydetail() {
        return nameCategorydetail;
    }

    public void setNameCategorydetail(String nameCategorydetail) {
        this.nameCategorydetail = nameCategorydetail;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "CategoryDetail{" +
                "idCategoryDetail=" + idCategoryDetail +
                ", nameCategorydetail='" + nameCategorydetail + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
