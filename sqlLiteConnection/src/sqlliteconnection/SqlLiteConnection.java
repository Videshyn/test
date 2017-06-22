
package sqlliteconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlLiteConnection {

    
    public static void main(String[] args) {
        
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("org.sqlite.JDBC"); // загрузка драйвера, для sqlite меняется строка
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        System.out.println("Oracle JDBC Driver Registered!");
        Connection conn = null;
        try {
            
            java.util.Locale locale = java.util.Locale.getDefault(); // для решения проблем с кодировками
            java.util.Locale.setDefault(java.util.Locale.ENGLISH);   // устанавливается английский локаль
            
            conn = DriverManager.getConnection("jdbc:sqlite:E:\\sqliteDB\\test_skj3.db");
            java.util.Locale.setDefault(locale); // возвращает локаль обратно в дефолтную
            
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
        getDepartments(conn);
        
    }
    public static void getDepartments(Connection connection){
        if(connection != null){
            try{
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM DEPARTMENTS");
                        while(rs.next()){
                            int depatments_id = rs.getInt("DEPARTMENTS_ID");
                            String name = rs.getString("DEPARTMENTS_NAME");
                            String region = rs.getString("REGION_INFO");
                            System.out.println("department_id = " + depatments_id + 
                                    " departments_name = " + name + " region_infp = " + region);
                        }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
                return;
            }
        }
    }
    
}
