package jlisttest;

import java.sql.DriverManager;          //механизмы загрузки драйвера в память
import java.sql.Connection;             //соединение с базами данных
import java.sql.SQLException;           //исключительные ситуации
import java.sql.Statement;              //для выполнения запросов SELECT
import java.sql.ResultSet;              //универсальная коллекция - набор данных, результат команды SELECT
import java.util.Date;                  //тип даты, дата совместимая с оракловской
import java.sql.CallableStatement;      //если нужо вызывать хранимые процедуры
import entityclasses.Employee;
import java.util.ArrayList;
import java.util.List;

public class JavaOracleConectionTest {

//    public static void main(String[] args) {
//        System.out.println("-------- Oracle JDBC Connection Testing ------");
//        if (driverClassRegistration()) {
//            return;
//        }
//        Connection connection = createOrcaleConnection();
//        // выше код для соединения с СУБД
//        // https://www.connectionstrings.com/ - сайт посвещенный тому как выглядят строки соединения с СУБД
//        //ORA-12705: Cannot access NLS data files or invalid environment specified выше будет ошибка из-за ожидания драйвером
//        //в виде кодировки АНСИИ, а приходит в другой
//        if (connection != null) {
//            System.out.println("You made it, take control your database now!");
//            testSelectEmployees(connection);
//            testCallStoredFunction(connection);
//        }
//        //выше код для вытягивания с таблицы EMPLOYEE данных и вывод на экран
//    }

    public static void testCallStoredFunction(Connection connection) {
        //
        try {
            System.out.println("Creating statement...");
            //вызов псевдокода ODBC
            //            String sql = "{call HR.PROC_FOR_TEST (?,?,?)}";
            String sql = "{? = call HR.FUNCT_FOR_TEST (?,?,?)}";
            //нумерация с еденицы    1                           2 3 4
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
            // важно соответствие типов джавы и СУБД
            int IN_PARAM1 = 7;
            stmt.setInt(2, IN_PARAM1);
            int IN_PARAM2 = 3;
            stmt.setInt(3, IN_PARAM2); // This would set ID as 102
            // Because second parameter is OUT so register it
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            //Use execute method to run stored procedure.
            System.out.println("Executing stored function...");
            stmt.execute();
            //Retrieve employee name with getXXX method
            String resultValue = stmt.getString(1);
            String outParamValue = stmt.getString(4);
            System.out.println("result value is " + resultValue + " output param value is " + outParamValue);
            stmt.close();
            connection.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
    }

    public static List<Employee> testSelectEmployees(Connection connection) {
        
        List<Employee> employees = new ArrayList<>();
        try {
            Statement st = connection.createStatement(); // похож на сканнер
            ResultSet rs = st.executeQuery("SELECT * FROM HR.EMPLOYEES");
            while (rs.next()) {
                int id = rs.getInt("EMPLOYEE_ID");
                Date d = rs.getDate("HIRE_DATE");
                String name = rs.getString("FIRST_NAME");
                float sal = rs.getFloat("SALARY");
                Employee empl = new Employee(name, id, (int)sal);
                employees.add(empl);
                //System.out.println("id = " + id + " hire_date = " + d + " name = " + name + " salary = " + sal);
            }
        } catch (SQLException ex) {
            System.out.println("executeQuery Failed! Check output console");
            ex.printStackTrace();

        }
        return employees;
    }

    public static Connection createOrcaleConnection() {
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

    public static boolean driverClassRegistration() {
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
}
