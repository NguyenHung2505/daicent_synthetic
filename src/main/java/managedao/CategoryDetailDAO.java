package managedao;

import model.Category;
import model.CategoryDetail;

import java.util.List;

public interface CategoryDetailDAO extends GenneralDAO<CategoryDetail>{

    List<CategoryDetail> searchByNameCategoryDetail (String valueName);

    List<CategoryDetail> searchByApproximateNameDetail (String valueName);}
