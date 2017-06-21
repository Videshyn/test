
package oraclehptestconnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OracleHPTestConnection {

    
    public static void main(String[] args) {
        
         System.out.println("-------- Oracle JDBC Connection Testing ------");
         if (driverClassRegistration()) {
            return;
        }
         Connection connection = createOrcaleConnection();
         if (connection != null) {
            System.out.println("You made it, take control your database now!");
            //testCallStoredFunction(connection);
            testSelectEmployees(connection);
        }
         
    }
    
    private static void testSelectEmployees(Connection connection) {
        try {
            Statement st = connection.createStatement(); // похож на сканнер
            ResultSet rs = st.executeQuery("SELECT * FROM HR.CLIENTS");
            while (rs.next()) {
                int id = rs.getInt("CLIENTS_ID");
                String name = rs.getString("CLIENTS_NAME");
                String phone = rs.getString("CLIENTS_PHONE");
                String mail = rs.getString("CLIENTS_MAIL");
                System.out.println("id = " + id + " name = " + name + " phone = " + phone + " mail = " + mail);
            }
        } catch (SQLException ex) {
            System.out.println("executeQuery Failed! Check output console");
            ex.printStackTrace();

        }
    }
    
    private static boolean driverClassRegistration() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // загрузка драйвера, для sqlite меняется строка
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return true;
        }
        System.out.println("Oracle JDBC Driver Registered!");
        return false;
    }
    
    
      private static Connection createOrcaleConnection() {
        // выше регистрация драйвера
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

        }
        return connection;
    }
      
      
      private static void testCallStoredFunction(Connection connection) {
        //
        try {
            System.out.println("Creating statement...");
            //вызов псевдокода ODBC
            String sql = "{call HR.ADD_CLIENTS (?,?,?,?)}";
            //нумерация с еденицы    1                           2 3 4
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.registerOutParameter(4, java.sql.Types.INTEGER);
            // важно соответствие типов джавы и СУБД
            String name = "name";
            String phone = "5";
            String mail = "mail";
            stmt.setString(1, name);
            stmt.setString(2, phone);
            stmt.setString(3, mail);
            int i = 0;
           
            System.out.println("Executing stored function...");
            stmt.execute(); //выполнить
           
            i = stmt.getInt(4);
            System.out.println("result id = " + i);
            
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
    }
}
