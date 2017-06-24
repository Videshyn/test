
package jlisttest;

import javax.swing.SwingUtilities;

public class JListTest {

    public static void main(String[] args) {
        // создание окон в отдельных потоках, для более быстрой работы при переключениях между окнами
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //new JListSimpleTestWindow();
                new JListUserClassTestWindow();
            }
        });
        
        
        
    }
    
}
