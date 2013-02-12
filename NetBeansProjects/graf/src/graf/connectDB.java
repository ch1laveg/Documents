
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graf;
import java.sql.*;
/**
 *
 * @author ПК для начинающих
 */
public class connectDB {
    Connection con;
    connectDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            try {
                con = DriverManager.getConnection("jdbc:sqlite:contacts.db");
            } catch(SQLException e) {
                System.out.println("SQL Exception");
            }
        } catch(ClassNotFoundException e){
            System.out.println("Class not found");
        }
    }
    public Connection getConnection (){
        return con;
    }
}
