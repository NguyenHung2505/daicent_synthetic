package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/manage_product?useSSL=false";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url , user ,password );
        } catch (SQLException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connect) {
        if(connect!=null) {
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//    public static void printInfo(Connection connect) {
//        if(connect!=null) {
//            try {
//                DatabaseMetaData m = connect.getMetaData();
////                System.out.println(m.getDatabaseProductName());
//
////                System.out.println(m.getDatabaseProductVersion());
//            } catch (SQLException e) {
//
//                e.printStackTrace();
//            }
//        }
//    }
}
