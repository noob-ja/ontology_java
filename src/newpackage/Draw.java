/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author User
 */
public class Draw extends JPanel {
    
    private ArrayList<Concept> concepts;
    
//    int n = 3;
//    int level = 0;
//    int superx = 10;
//    int supery = 10;
//    String text[] = {"animal", "mammals", "reptiles"};
//    float pos_x[] = {500, 400, 700};
//    float pos_y[] = {40, 300, 300};
//    float size_x[] = {300, 150, 150};
//    float size_y[] = {50, 50, 50};
//    float midPointx[] = {650, 475, 775};
//    float midPointy[] = {65, 325, 325};
//    int connection[] = {0, 0, 0};
    String inside = "";
    String selected = "";

    public Draw(ConceptList conceptlist) {
      
        System.out.println("aaaaaaa");
        // get all concept
        this.concepts = conceptlist.getAll();
        System.out.println(this.concepts);
        repaint();

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                
                //System.out.println(e.getPoint());
                for (int i = 0; i <concepts.size() ; i++) {
                    Concept c = concepts.get(i);
//                    midPointx[i] = (int)pos_x[i] + (int)size_x[i] / 2;
//                    midPointy[i] = (int)pos_y[i] + (int)size_y[i] / 2;
                    float rx2 = (float) Math.pow(c.getGUIData("size_x") / 2, 2);
                    float ry2 = (float) Math.pow(c.getGUIData("size_y") / 2, 2);
                    float a = (float) Math.pow(e.getX() - c.getGUIData("mid_x"), 2);
                    float b = (float) Math.pow(e.getY() - c.getGUIData("mid_y"), 2);
                    float total = a / rx2 + b / ry2;
                    //System.out.println(total);
                    if (total <= 1) {
                        //System.out.println("inside");
                        inside = c.getValue();
                        //System.out.println(inside);
                        break;
                        
                    }
                    else{
                        inside = "";
                    }

                }
                repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!inside.equals("") ){
                    selected = inside;
                }
                else{
                    selected = "";
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        for (int i = 0; i < n; i++) {
//            if (inside == i) {
//                //System.out.println("yy");
//                g.setColor(Color.YELLOW);
//            } else {
//                //System.out.println("ww");
//                g.setColor(Color.WHITE);
//            }
//            g.fillOval((int) pos_x[i], (int) pos_y[i], (int) size_x[i], (int) size_y[i]);
//            g.setColor(Color.BLACK);
//            g.drawString(text[i], (int) midPointx[i], (int) midPointy[i]);
//            g.drawLine((int) midPointx[i], (int) midPointy[i], (int) midPointx[connection[i]], (int) midPointy[connection[i]]);
//
//        }
        for (int i = 0; i < this.concepts.size(); i++) {
            Concept c = this.concepts.get(i);
            if (inside.equals(c.getValue())) {
                //System.out.println("yy");
                g.setColor(Color.YELLOW);
            } else {
                //System.out.println("ww");
                g.setColor(Color.WHITE);
            }
            if(selected.equals(c.getValue())){
                g.setColor(Color.red);
            }
            g.fillOval((int) c.getGUIData("pos_x"), (int) c.getGUIData("pos_y"), (int) c.getGUIData("size_x"), (int) c.getGUIData("size_y"));
            g.setColor(Color.BLACK);
            g.drawString(c.getValue(), (int) c.getGUIData("mid_x"), (int) c.getGUIData("mid_y"));
            if(c.getParent()!=null) g.drawLine((int) c.getGUIData("mid_x"), (int) c.getGUIData("mid_y"), c.getParent().getGUIData("mid_x"), c.getParent().getGUIData("mid_y"));
            
        }
    }
    
    public String getSelected(){
        return this.selected;
    }

    
    

}
