package managedao;

import database.JDBCUtil;
import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements CategoryService{
    List<Category> categoryList = new ArrayList<>();

    @Override
    public List<Category> findAll() {
        try (// b1: tạo kết nối đến CSDL <>
                Connection connection = JDBCUtil.getConnection();
                // b2 : tạo đối tượng preparedStatement = câu truy vâấn đến csdl
        PreparedStatement preparedStatement = connection.prepareStatement("select *from Category");) {
//            System.out.println(preparedStatement); // in ra câu truy vấn
            // b3 : thực thi câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();
            // b4 : kiểm tra kqua
            while (rs.next()) {
                int idCategory = rs.getInt("idCategory");
                String nameCategory = rs.getString("nameCategory");
                categoryList.add(new Category(idCategory, nameCategory));
            }
            // b5 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryList;
    }

    @Override
    public void insert(Category category) {
        try ( //b1: tạo kết nối với CSDL
                Connection connection = JDBCUtil.getConnection();
                // b2: tạo đối tượng preparedStatement = câu query
              PreparedStatement preparedStatement = connection.prepareStatement("insert into category(nameCategory) value (?)");) {
            preparedStatement.setString(1, category.getNameCategory());
            // b3 : thực thi câu truy vấn
            preparedStatement.executeUpdate();
            // b4 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Category findById(int idCategory) {
        Category categorys = new Category();

        try {// b1 : kết nối CSDL
            Connection connection = JDBCUtil.getConnection();
            // b2 : tạo đối tượng preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category where idCategory = ?");
            preparedStatement.setInt(1, idCategory);
//            System.out.println(preparedStatement); //in ra câu truy vấn.
            //b3 :  thực thi câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();
            //b4 : kiểm tra
            while (rs.next()) {
                categorys.setIdCategory(rs.getInt("idCategory"));
                categorys.setNameCategory(rs.getString("nameCategory"));
            }
            // b5 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return categorys;
    } ;




    @Override
    public boolean update(Category category) {
        boolean upDate;
        try (Connection connection = JDBCUtil.getConnection();
            PreparedStatement preparedStatement = JDBCUtil.getConnection().prepareStatement("update category set nameCategory = ?  where idCategory = ?");) {
            preparedStatement.setString(1, category.getNameCategory());
            preparedStatement.setInt(2, category.getIdCategory());
            upDate= preparedStatement.executeUpdate()>0;
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return upDate;

    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtil.getConnection(); PreparedStatement statement = connection.prepareStatement("delete from category where idCategory=?");) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }



    @Override
    public List<Category> searchByNameCategory(String nameCategory) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select nameCategory from category where nameCategory = ?");) {
            preparedStatement.setString(1, nameCategory );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String s = rs.getString("nameCategory");
                categoryList.add(new Category(s));
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return categoryList;
    }

    @Override
    public List<Category> searchByApproximateName(String nameCategory) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from category where nameCategory like ?");) {
            preparedStatement.setString(1, "%" + nameCategory + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idCategory = rs.getInt("idCategory");
                String categoryName = rs.getString("nameCategory");
                categoryList.add(new Category(idCategory , categoryName));
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return categoryList;

    }
}
