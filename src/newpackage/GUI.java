/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 *
 * @author User
 */
public class GUI {
    
    private ConceptList conceptlist;
    private Draw drawPanel;
    private JFrame frame;
    private JScrollPane jsp;
    
    public GUI(){
        conceptlist = new ConceptList();
        frame= new JFrame("NM Exam");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton button1 = new JButton("Add super");
        button1.setSize(100,100);
        button1.setLocation(10, 10);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                    String text;
                    text = JOptionPane.showInputDialog("Input Super Node");
                    if(text != null){
                        conceptlist.setSuper(text);
                        redraw();
                    }
                    
                
                
            }
        });
                
        JButton button2 = new JButton("Add parent");
        button2.setSize(100,100);
        button2.setLocation(10, 110);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawPanel.getSelected()==""){
                    //prompt user to select a concept
                     JOptionPane.showMessageDialog(null, "Please select a node.");
                }else{
                    String text;
                    text = JOptionPane.showInputDialog("Input Parent Node");
                    if(text != null){
                        conceptlist.addParent(drawPanel.getSelected(), text);
                        redraw();
                    }
                    
                }
            }
        });
        
        JButton button3 = new JButton("Add child");
        button3.setSize(100,100);
        button3.setLocation(10, 210);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawPanel.getSelected()==""){
                    //prompt user to select a concept
                     JOptionPane.showMessageDialog(null, "Please select a node.");
                }else{
                    String text;
                    text = JOptionPane.showInputDialog(null,"Input Child Node");
                    if(text != null){
                        conceptlist.addChild(drawPanel.getSelected(), text);
                        redraw();
                    }
                    
                }
            }
        });
        
        JButton button4 = new JButton("Delete current & child");
        button4.setSize(100,100);
        button4.setLocation(10, 210);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawPanel.getSelected()==""){
                    JOptionPane.showMessageDialog(null, "Please select a node.");
                }else{
                    conceptlist.removeBranch(drawPanel.getSelected());
                    redraw();
                }
            }
        });
        
        JButton button5 = new JButton("Delete current");
        button5.setSize(100,100);
        button5.setLocation(10, 210);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(drawPanel.getSelected()==""){
                    JOptionPane.showMessageDialog(null, "Please select a node.");
                }else{
                    conceptlist.removeSelf(drawPanel.getSelected());
                    redraw();
                }
            }
        });
        
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
        c.gridx = 0;
        c.gridy = 3;
        panelB.add(button4,c);
        c.gridx = 0;
        c.gridy = 4;
        panelB.add(button5,c);
        frame.add(panelB,BorderLayout.WEST);
        
        redraw();
        
        frame.setVisible(true);
    }
    
    public void redraw(){
        if(this.drawPanel!=null) this.frame.remove(this.jsp);
        this.drawPanel = new Draw(this.conceptlist);
        this.jsp = new JScrollPane(this.drawPanel);
        this.drawPanel.setBackground(Color.gray);
        this.conceptlist.printTreeGraphic();
        this.frame.add(this.jsp, BorderLayout.CENTER);
        this.frame.revalidate();
        this.frame.repaint();
    }
    
    public void checkOverlapping(ArrayList<Concept> concepts) {
        String overlap = "";
        for (int i = 0; i < concepts.size(); i++) {
            Concept con = concepts.get(i);
            for (int j = 0; j < concepts.size(); j++) {
                if(i == j){
                    continue;
                }
                else{
                    Concept con2 = concepts.get(j);
                    float rx2 = (float) Math.pow(con2.getGUIData("size_x") / 2, 2);
                    float ry2 = (float) Math.pow(con2.getGUIData("size_y") / 2, 2);
                    float a = (float) Math.pow(con.getGUIData("pos_x") - con2.getGUIData("mid_x"), 2);
                    float b = (float) Math.pow(con.getGUIData("mid_y") - con2.getGUIData("mid_y"), 2);
                    float total = a / rx2 + b / ry2;
                    float a2 = (float) Math.pow(con.getGUIData("pos_x")+con.getGUIData(("size_x")) - con2.getGUIData("mid_x"), 2);
                    float b2 = (float) Math.pow(con.getGUIData("mid_y") - con2.getGUIData("mid_y"), 2);
                    float total2 = a2 / rx2 + b2 / ry2;
                    if (total <= 1 || total <= 1) {
                        overlap = con.getValue();
                        break;
                    }
                    else{
                        overlap = "";
                    }
                }
            }
            
        }
    }
    
}


