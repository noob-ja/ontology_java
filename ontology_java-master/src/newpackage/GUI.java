/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class GUI {
    
    public GUI(){
        ConceptList conceptlist = new ConceptList();
        JFrame frame= new JFrame("NM Exam");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        JButton button1 = new JButton("Add super");
        button1.setSize(100,100);
        button1.setLocation(10, 10);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text;
                text = JOptionPane.showInputDialog("Input Super Node");
                conceptlist.setSuper(text);
                
                frame.revalidate();
                frame.repaint();
                
            }
        });
        frame.add(button1);
        
                
        JButton button2 = new JButton("Add parent");
        button2.setSize(100,100);
        button2.setLocation(10, 110);
        //frame.add(button2);
        
        JButton button3 = new JButton("Add child");
        button3.setSize(100,100);
        button3.setLocation(10, 210);
        //frame.add(button3);
        
        JPanel panelB = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        panelB.add(button1,c);
        c.gridx = 0;
        c.gridy = 1;
        panelB.add(button2,c);
        c.gridx = 0;
        c.gridy = 2;
        panelB.add(button3,c);
        frame.add(panelB,BorderLayout.WEST);
        
//        frame.add(draw);
        JPanel panel1 = new Draw();
        frame.add(panel1,BorderLayout.CENTER);
        
        frame.setVisible(true);
        
    }
    
}


