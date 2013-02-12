
package requestreciever;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBWorker {
    ConnectDB ConnectDB;
    Connection conn;
    public static ArrayList<String> listNames;
    public DBWorker(){
        ConnectDB = new ConnectDB();
        conn = ConnectDB.getConn();
        listNames = new ArrayList<>();
        this.install();
    }
    private void install(){
        try {
            Statement st = conn.createStatement();
            st.execute("create table if not exists users("
                    + "name text, password text, rule integer)");
            st.execute("create table if not exists requests(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "username text, reqType integer, addInfo text)");
        } catch (SQLException ex) {
            System.out.println("Таблица не была создана");
        }
    }
    
    public void setRequest(String name, String reqType, String addInfo){
        try {
            Statement st = conn.createStatement();
            st.execute("insert into requests(username, reqType, addInfo)"
                    + "values('" + name + "','" + reqType + "','" + addInfo + "') ");
        } catch (SQLException ex) {
            System.out.println("Не удалось добавить заявку");
        } 
    }
    public String[] getRequest(){
        String[] tmp = new String[4];
        try {
            Statement st = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBWorker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
    public void setUser(String name, String password, int rule){
        try {
            Statement st = conn.createStatement();
            st.execute("insert into users(name, password, rule)"
                    + "values('" + name + "','" + password + "','" + rule + "') ");
        } catch (SQLException ex) {
            System.out.println("Не удалось добавить пользователя");
        }        
    }
    public String[] getUser(String name){
        String[] tmp = new String[3];
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from users where name = '" + name + "'");
            while(rs.next()){
                tmp[0] = rs.getString("name");
                tmp[1] = rs.getString("password");
                tmp[2] = rs.getString("rule");
            }
        } catch (SQLException ex) {
            System.out.println("Не удалось извлечь пользователя или пользователь не найден");
        }
        return tmp;        
    }
    
    public void getNamesForList(){        
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select username from requests");
            while (rs.next()){
                int i = 1;
                this.listNames.add(rs.getString(i));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBWorker.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
    
    
    
}
