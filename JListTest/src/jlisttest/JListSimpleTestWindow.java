
package jlisttest;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class JListSimpleTestWindow extends JFrame{
    private JList<String> countryList;

    public JListSimpleTestWindow() {
        
        this.setTitle("JList Example");
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Код ниже отвечает за создание списка
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addElement("USA");
        listModel.addElement("India");
        listModel.addElement("Vietnam");
        listModel.addElement("Canada");
        listModel.addElement("Denmark");
        listModel.addElement("France");
        listModel.addElement("Great Britain");
        listModel.addElement("Japan");
        //create the list
        countryList = new JList<>(listModel); 
        // назначаем слушателя на обработку окна
        countryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = countryList.getSelectedValuesList();
                    System.out.println(selectedValuesList);
                }
            }
        });
        // добавляем компонент с правилом размещения
        add(new JScrollPane(countryList));
        this.setVisible(true);
    }
    
    
    
}
