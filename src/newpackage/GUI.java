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
    
    private ConceptList conceptlist;
    private Draw drawPanel;
    private JFrame frame;
    
    public GUI(){
        conceptlist = new ConceptList();
        frame= new JFrame("NM Exam");
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
                //set GUI data
                conceptlist.setConceptGUI(text,0, 0, 0, 0, 0, 0, 0);
                //recreate draw here
                redraw();
//                frame.revalidate();
//                frame.repaint();
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
//        drawPanel = new Draw(this.conceptlist);
//        frame.add(drawPanel,BorderLayout.CENTER);
        redraw();
        
        frame.setVisible(true);
        
    }
    
    public void redraw(){
        if(this.drawPanel!=null) this.frame.remove(this.drawPanel);
        this.drawPanel = new Draw(this.conceptlist);
        this.frame.add(this.drawPanel,BorderLayout.CENTER);
        this.frame.revalidate();
        this.frame.repaint();
    }
    
}


