package mx.edu.utez.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection("jdbc:mysql://" + "localhost" + ":" + 3306 +
                "/" + "sisa", "root", "root");
    }

    public static void main(String[] args) {
        try {
            Connection con = getConnection();
            System.out.println("(^_^) Connection successful ... ");
            con.close();
        } catch (SQLException e) {
            System.out.println("(o_O) Conection error ... " + e);
        }
    }

}
