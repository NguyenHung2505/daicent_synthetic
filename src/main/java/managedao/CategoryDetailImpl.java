package managedao;

import database.JDBCUtil;
import model.Category;
import model.CategoryDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCUtil.getConnection;

public class CategoryDetailImpl implements CategoryDetailDAO {
    List<CategoryDetail> categoryDetails = new ArrayList<>();
    CategoryService categoryService = new CategoryImpl();




    @Override
    public List<CategoryDetail> findAll() {
        try (// b1: tạo kết nối đến CSDL <>
             Connection connection = getConnection();
             // b2 : tạo đối tượng preparedStatement = câu truy vâấn đến csdl
             PreparedStatement preparedStatement = connection.prepareStatement("select * from categoryDetail");) {
            // b3 : thực thi câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();
            // b4 : kiểm tra kqua
            while (rs.next()) {
                int idCategoryDetail = rs.getInt("idCategoryDetail");
                String nameCategoryDetail = rs.getString("nameCategoryDetail");
                int idCategory = rs.getInt("idCategory");
                Category categoryid = categoryService.findById(idCategory);
                categoryDetails.add(new CategoryDetail(idCategoryDetail , nameCategoryDetail , categoryid));
            }
            // b5 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryDetails;
    }

    @Override
    public void insert(CategoryDetail categoryDetail) {
        try ( //b1: tạo kết nối với CSDL
              Connection connection = getConnection();
              // b2: tạo đối tượng preparedStatement = câu query
              PreparedStatement preparedStatement = connection.prepareStatement("insert into categorydetail(nameCategoryDetail , idCategory) value (?,?)");) {
            preparedStatement.setString(1, categoryDetail.getNameCategorydetail());
            preparedStatement.setInt(2, categoryDetail.getCategoryId().getIdCategory());
            // b3 : thực thi câu truy vấn
            preparedStatement.executeUpdate();
            // b4 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryDetail findById(int idCategoryDetail) {
        CategoryDetail categoryDetail = new CategoryDetail();
        try {// b1 : kết nối CSDL
            Connection connection = getConnection();
            // b2 : tạo đối tượng preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from categoryDetail where idCategoryDetail = ?");
            preparedStatement.setInt(1, idCategoryDetail);
//            System.out.println(preparedStatement); //in ra câu truy vấn.
            //b3 :  thực thi câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();
            //b4 : kiểm tra
            while (rs.next()) {
              String nameCategoryDetail = rs.getString("nameCategoryDetail");
              int categoryId = rs.getInt("idCategory");
              Category category = categoryService.findById(categoryId);
                categoryDetail = new CategoryDetail(idCategoryDetail , nameCategoryDetail , category);
            }
            // b5 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return categoryDetail;
    }

    @Override
    public boolean update(CategoryDetail categoryDetail) throws SQLException {
        boolean upDate;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = getConnection().prepareStatement("update categoryDetail set nameCategoryDetail = ?   where idCategoryDetail = ?");) {
            preparedStatement.setString(1, categoryDetail.getNameCategorydetail());
            preparedStatement.setInt(2, categoryDetail.getIdCategoryDetail());
            upDate= preparedStatement.executeUpdate()>0;
        }
        return upDate;
    }

    @Override
    public boolean delete(int idCategoryDetail) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("delete from categoryDetail where idCategoryDetail=?");) {
            statement.setInt(1, idCategoryDetail);
            System.out.println(statement);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    @Override
    public List<CategoryDetail> searchByNameCategoryDetail(String nameCategoryDetail) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from categorydetail c join category c2 on c2.idCategory = c.idCategory where nameCategory = ? ;");) {
            preparedStatement.setString(1, nameCategoryDetail );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCategory = rs.getInt("idCategoryDetail");
                String nameCategoryss = rs.getString("nameCategoryDetail");
                int categoryId = rs.getInt("idCategory");

                Category categoryid = categoryService.findById(categoryId);
                categoryDetails.add(new CategoryDetail(idCategory ,nameCategoryss , categoryid ));
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return categoryDetails;
    }

    @Override
    public List<CategoryDetail> searchByApproximateNameDetail(String valueName) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from categoryDetail where nameCategoryDetail like ?");) {
            preparedStatement.setString(1, "%" + valueName + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCategoryDetail = rs.getInt("idCategoryDetail");
                String nameCategoryDetails = rs.getString("nameCategoryDetail");
                int categoryId = rs.getInt("idCategory");
                Category categoryid = categoryService.findById(categoryId);
                categoryDetails.add(new CategoryDetail(idCategoryDetail , nameCategoryDetails , categoryid));            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return categoryDetails;

    }
}
