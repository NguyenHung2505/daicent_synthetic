package managedao;

import database.JDBCUtil;
import model.Category;
import model.CategoryDetail;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.JDBCUtil.getConnection;

public class ProductImpl implements ProductDAO{
    List<Product> productList = new ArrayList<>();
    CategoryDetailDAO categoryDetailDAO = new CategoryDetailImpl();

    @Override
    public List<Product> findAll() {
        try (// b1: tạo kết nối đến CSDL <>
             Connection connection = getConnection();
             // b2 : tạo đối tượng preparedStatement = câu truy vâấn đến csdl
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product");) {
            // b3 : thực thi câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();
            // b4 : kiểm tra kqua
            while (rs.next()) {
                int idProduct = rs.getInt("idProduct");
                String nameProduct = rs.getString("nameProduct");
                int priceProduct = rs.getInt("priceProduct");
                int idCategoryDetail = rs.getInt("idCategoryDetail");
                CategoryDetail categoryDetail = categoryDetailDAO.findById(idCategoryDetail);
                productList.add(new Product(idProduct , nameProduct , priceProduct , categoryDetail));
            }
            // b5 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public void insert(Product product) {
        try ( //b1: tạo kết nối với CSDL
              Connection connection = getConnection();
              // b2: tạo đối tượng preparedStatement = câu query
              PreparedStatement preparedStatement = connection.prepareStatement("insert into product(nameProduct , priceProduct ,idCategoryDetail) value (?,?,?)");) {
            preparedStatement.setString(1, product.getNameProduct());
            preparedStatement.setInt(2, product.getPriceProduct());
            preparedStatement.setInt(3, product.getIdCategoryDetail().getIdCategoryDetail());
            // b3 : thực thi câu truy vấn
            preparedStatement.executeUpdate();
            // b4 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product findById(int idProduct) {
        Product product = new Product();
        try {// b1 : kết nối CSDL
            Connection connection = getConnection();
            // b2 : tạo đối tượng preparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where idProduct = ?");
            preparedStatement.setInt(1, idProduct);
//            System.out.println(preparedStatement); //in ra câu truy vấn.
            //b3 :  thực thi câu truy vấn
            ResultSet rs = preparedStatement.executeQuery();
            //b4 : kiểm tra
            while (rs.next()) {
                String nameProduct = rs.getString("nameProduct");
                int priceProduct = rs.getInt("priceProduct");
                int categoryDetailId = rs.getInt("idCategoryDetail");
                CategoryDetail categoryDetail = categoryDetailDAO.findById(categoryDetailId);
               product = new Product(idProduct , nameProduct , priceProduct , categoryDetail);
            }
            // b5 : ngắt kết nồi với database
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return product;
    }


    @Override
    public boolean delete(int idProduct) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement("delete from product where idProduct =?");) {
            statement.setInt(1, idProduct);
            System.out.println(statement);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean upDate;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = getConnection().prepareStatement("update product set namePdroduct = ? , priceProduct = ?   where idProduct = ?");) {
            preparedStatement.setString(1, product.getNameProduct());
            preparedStatement.setInt(2, product.getPriceProduct());
            preparedStatement.setInt(3, product.getIdCategoryDetail().getIdCategoryDetail());
            upDate= preparedStatement.executeUpdate()>0;
        }
        return upDate;
    }


    @Override
    public List<Product> seachNameCategory(String value) {
        List<Product> productList1 = new ArrayList<>();
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from product p\n" +
                             "join categorydetail c on c.idcategoryDetail = p.idCategoryDetail\n" +
                             "join category c2 on c2.idCategory = c.idCategory where nameCategory = ? ;");) {
            preparedStatement.setString(1, value );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               int productId = rs.getInt("idProduct");
                String productName = rs.getString("nameProduct");
                int productPrice = rs.getInt("priceProduct");
                int dategoryDetail = rs.getInt("idCategoryDetail");
                CategoryDetail categoryDetail = categoryDetailDAO.findById(dategoryDetail);
                productList1.add(new Product( productId, productName,productPrice , categoryDetail ));
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return productList1;

    }

    @Override
    public List<Product> seachNameProduct(String valuee) {
        try (Connection connection = JDBCUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from product where nameProduct = ?");) {
            preparedStatement.setString(1, valuee );
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idProductt = rs.getInt("idProduct");
                String nameProductt = rs.getString("nameProduct");
                int priceProductt = rs.getInt("priceProduct");
                int categoryDetailProduct = rs.getInt("idCategoryDetail");
                CategoryDetail categoryDetaill = categoryDetailDAO.findById(categoryDetailProduct);
                productList.add(new Product(idProductt ,nameProductt , priceProductt,categoryDetaill ));
            }
            JDBCUtil.closeConnection(connection);
        } catch (SQLException e) {
        }
        return productList;
    }

    @Override
    public ArrayList<Product> selectByIdCategoryDetail(CategoryDetail value) {
        return null;
    }


}
