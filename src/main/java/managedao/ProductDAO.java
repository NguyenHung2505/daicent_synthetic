package managedao;

import model.CategoryDetail;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends GenneralDAO<Product>{
    List<Product> seachNameCategory(String value);
    List<Product> seachNameProduct (String valuee);

    ArrayList<Product> selectByIdCategoryDetail(CategoryDetail value);
}
