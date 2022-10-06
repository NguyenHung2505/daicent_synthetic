package managedao;

import model.Category;
import model.CategoryDetail;

import java.util.List;

public interface CategoryDetailDAO extends GenneralDAO<CategoryDetail>{

    List<CategoryDetail> searchByNameCategoryD (String valueName);

    List<CategoryDetail> searchByApproximateNameDetail (String valueName);}
