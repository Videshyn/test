
package jlisttest;


import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import entityclasses.Employee;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static jlisttest.JavaOracleConectionTest.createOrcaleConnection;


public class JListUserClassTestWindow extends JFrame{
    
     private JList<Employee> employees;

    public JListUserClassTestWindow() {
        this.setTitle("JList For USer Class Example");
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //
         DefaultListModel<Employee> listModel = new DefaultListModel<>();
         
         JavaOracleConectionTest jTest = new JavaOracleConectionTest();
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        if (jTest.driverClassRegistration()) {
            return;
        }
        Connection connection = createOrcaleConnection();
        List<Employee> list = new ArrayList<>();
        if (connection != null) {
            System.out.println("You made it, take control your database now!");
           list = jTest.testSelectEmployees(connection);
        }
        
        for(int i = 0, n = list.size(); i < n; i ++){
            listModel.addElement(list.get(i));
        }
        
        //create the list
        employees = new JList<>(listModel); 
        // назначаем слушателя на обработку окна
        employees.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<Employee> selectedValuesList = employees.getSelectedValuesList();
                    System.out.println(selectedValuesList);
                }
            }
        });
        // добавляем компонент с правилом размещения
        add(new JScrollPane(employees));
        //
        this.setVisible(true);
        
    }
    
    
    
}
