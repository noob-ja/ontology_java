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
    
    String inside = "";
    String selected = "";
    
    final int size_x = 100;
    final int size_y = 50;
    final int size_pad = 10;
    final int level_height = 150;
    
    public void setSize(){
        if(concepts.size()>0) setSize(concepts.get(0),0);
    }
    
    public int setSize(Concept c, int offset_x){
        int size = 0;
        if(c.getChildSize()<1){
            size = this.size_x + (this.size_pad*2);
        }else{
            for(int i=0;i<c.getChildSize();i++){
                size += setSize(c.getChild().get(i),size);
            }
        }
        c.setGUIData("mid_x", size/2+offset_x);
        c.setGUIData("mid_y", (c.getGUIData("level")-1)*level_height+(level_height/2));
        c.setGUIData("pos_x", c.getGUIData("mid_x")-size_x/2);
        c.setGUIData("pos_y", c.getGUIData("mid_y")-size_y/2);
        System.out.println(c.getGUIData("pos_x"));
        System.out.println(c.getGUIData("pos_y"));
        return size;
    }

    public Draw(ConceptList conceptlist) {
        // get all concept
        this.concepts = conceptlist.getAll();
        for(Concept c:concepts){
            System.out.println(c.getValue());
        }
        setSize();
        repaint();

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                
                //System.out.println(e.getPoint());
                for (int i = 0; i <concepts.size() ; i++) {
                    Concept c = concepts.get(i);
//                    midPointx[i] = (int)pos_x[i] + (int)size_x[i] / 2;
//                    midPointy[i] = (int)pos_y[i] + (int)size_y[i] / 2;
                    float rx2 = (float) Math.pow(size_x / 2, 2);
                    float ry2 = (float) Math.pow(size_y / 2, 2);
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
        for (int i = 0; i < this.concepts.size(); i++) {
            Concept c = this.concepts.get(i);
            if (inside.equals(c.getValue())) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.WHITE);
            }
            if(selected.equals(c.getValue())){
                g.setColor(Color.red);
            }
//            g.fillOval((int) c.getGUIData("pos_x"), (int) c.getGUIData("pos_y"), (int) c.getGUIData("size_x"), (int) c.getGUIData("size_y"));
            g.fillOval(c.getGUIData("pos_x"), c.getGUIData("pos_y"), size_x, size_y);
            g.setColor(Color.BLACK);
            g.drawString(c.getValue(), c.getGUIData("mid_x"), c.getGUIData("mid_y"));
            if(c.getParent()!=null){
                g.drawLine((int) c.getGUIData("mid_x"), (int) c.getGUIData("mid_y"), c.getParent().getGUIData("mid_x"), c.getParent().getGUIData("mid_y"));
            }
        }
    }
    
    public String getSelected(){
        return this.selected;
    }

    
    

}
