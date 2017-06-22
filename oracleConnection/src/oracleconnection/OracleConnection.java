
package oracleconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class OracleConnection {

 
    public static void main(String[] args) {
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // загрузка драйвера, для sqlite меняется строка
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;
        }
        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = null;
        
         try {
            
            java.util.Locale locale = java.util.Locale.getDefault(); // для решения проблем с кодировками
            java.util.Locale.setDefault(java.util.Locale.ENGLISH);   // устанавливается английский локаль
            
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system",
                    "123");
            java.util.Locale.setDefault(locale); // возвращает локаль обратно в дефолтную
            
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;
        }
         
         getDepartments(connection);
         
    }
    
//    public static void getEmployees(Connection connection){
//        if(connection != null){
//            try{
//                Statement st = connection.createStatement();
//                ResultSet rs = st.executeQuery("SELECT * FROM HR.MY_EMPLOYEES");
//                while(rs.next()){
//                    int id = rs.getInt("EMPLOYEES_ID");
//                    String name = rs.getString("EMPLOYEES_NAME");
//                    String phone = rs.getString("PHONE");
//                    String mail = rs.getString("MAIL");
//                    double salary = rs.getDouble("SALARY");
//                    int departments_id = rs.getInt("DEPARTMENTS_ID");
//                    int position_id = rs.getInt("POSITION_ID");
//                    System.out.println("id = " + id + " name = " + name + " phone = " + phone + 
//                            " mail = " + mail + " salary = " + salary + " departments_id = " + departments_id +
//                            " position_id = " + position_id); 
//                }
//            }catch(SQLException ex){
//                System.out.println(ex.getMessage());
//                return;
//        }
//    }
    
    
    public static void getDepartments(Connection connection){
        if(connection != null){
            try{
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM HR.MY_DEPARTMENTS");
                        while(rs.next()){
                            int depatments_id = rs.getInt("DEPARTMENTS_ID");
                            String name = rs.getString("DEPARTMENS_NAME");
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
