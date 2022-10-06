package managedao;

import model.Category;

import java.util.List;

public interface CategoryService extends GenneralDAO<Category> {
    List<Category> searchByNameCategory (String nameCategory);

    List<Category> searchByApproximateName (String nameCategory);
}
