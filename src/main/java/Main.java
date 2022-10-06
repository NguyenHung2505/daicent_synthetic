import managedao.CategoryDetailImpl;
import managedao.CategoryImpl;
import managedao.ProductImpl;
import model.Category;
import model.CategoryDetail;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//      CategoryImpl category = new CategoryImpl();
//        List<Category> categoryList = category.searchByApproximateName("c");
//        for (Category c: categoryList) {
//            System.out.println(c);
//        }
//        category.insert(new Category(3,"ba lô"));
//        category.delete(4);
//        System.out.println(category.findAll());
//        System.out.println(category.findById(2).toString());
//        System.out.println(category.searchByApproximateName("s"));
//
//        List<Category> categoryList = category.searchByNameCategory("chợ");
//        for (Category c: categoryList
//             ) {
//            System.out.println(c);
//        }
//        System.out.println(category.searchByNameCategory("chợ"));


//      CategoryDetailImpl categoryDetail = new CategoryDetailImpl();
//        List<CategoryDetail> categoryDetailList = categoryDetail.findAll();
//        for (CategoryDetail c: categoryDetailList) {
//            System.out.println(c.toString());
//        }
//       categoryDetail.insert(new CategoryDetail(5,"hung", new Category(1,"chợ")));
//        System.out.println(categoryDetail.findById(2));
//       List<CategoryDetail> categoryDetailList = categoryDetail.searchByNameCategoryDetail("chợ");
//
//        for (CategoryDetail c: categoryDetailList
//             ) {
//            System.out.println(c);
//        }

//        List<CategoryDetail> categoryDetails = categoryDetail.searchByApproximateNameDetail("h");
//        for (CategoryDetail c: categoryDetails) {
//            System.out.println(c);
//        }


        ProductImpl productImpl = new ProductImpl();
//       List<Product> productList = productImpl.findAll();
//        for (Product p: productList) {
//            System.out.println(p.toString());
//        }
 //       productImpl.insert(new Product(8,"bút mầu đỏ",23000,new CategoryDetail()));
//        System.out.println(productImpl.findById(1));

        List<Product> products = productImpl.seachNameCategory("chợ");
        for (Product p: products) {
            System.out.println(p);
        }
  //     System.out.println(productImpl.seachNameProduct("bút"));
    }
}
