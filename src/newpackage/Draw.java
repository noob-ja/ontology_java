/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
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
    
    int totalheight = 0;
    int treeSize = 0;
    int treeCenter = 0;
    final int size_x = 100;
    final int size_y = 50;
    final int size_pad = 10;
    final int level_height = 150;
    final float rx2 = (float) Math.pow(size_x / 2, 2);
    final float ry2 = (float) Math.pow(size_y / 2, 2);
    final FontRenderContext frc = new FontRenderContext(null,true,true);
    final Font f = new Font("TimesRoman", Font.BOLD, 12);
    
    public void setSize(){
        if(concepts.size()>0) treeSize = setSize(concepts.get(0),0);
        else treeSize = 0;
    }
    
    public int setSize(Concept c, int offset_x){
        int size = 0;
        if(c.getChildSize()<1){
            size = this.size_x + (this.size_pad*2);
        }else{
            for(int i=0;i<c.getChildSize();i++){
                size += setSize(c.getChild().get(i),size+offset_x);
            }
        }
        c.setGUIData("mid_x", size/2+offset_x);
        c.setGUIData("mid_y", (c.getGUIData("level")-1)*level_height+(level_height/2));
        c.setGUIData("pos_x", c.getGUIData("mid_x")-size_x/2);
        c.setGUIData("pos_y", c.getGUIData("mid_y")-size_y/2);
        if(this.totalheight<c.getGUIData("level")*level_height) this.totalheight=c.getGUIData("level")*level_height;
        return size;
    }

    public Draw(ConceptList conceptlist) {
        this.concepts = conceptlist.getAll();
        this.setLayout(new GridLayout());
        setSize();
        repaint();
        this.setPreferredSize(new Dimension(treeSize,totalheight));

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent e) {
                treeCenter = (getWidth() - treeSize)/2;
                for (int i = 0; i <concepts.size() ; i++) {
                    Concept c = concepts.get(i);
                    float a = (float) Math.pow(e.getX() - (c.getGUIData("mid_x")+treeCenter), 2);
                    float b = (float) Math.pow(e.getY() - (c.getGUIData("mid_y")), 2);
                    float total = a / rx2 + b / ry2;
                    if (total <= 1) {
                        inside = c.getValue();
                        break;
                    }else{
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
                if(!inside.equals("")){selected = inside;}
                else{selected = "";}
            }

            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            
        });
    }

    @Override
    public void paint(Graphics g) {
        if(treeCenter == 0) treeCenter = (getWidth() - treeSize)/2;
        super.paint(g);
        for (int i = 0; i < this.concepts.size(); i++) {
            Concept c = this.concepts.get(i);
            if(c.getParent()!=null){
                g.drawLine(c.getGUIData("mid_x")+treeCenter, c.getGUIData("mid_y"), c.getParent().getGUIData("mid_x")+treeCenter, c.getParent().getGUIData("mid_y"));
            }
        }
        for (int i = 0; i < this.concepts.size(); i++) {
            Concept c = this.concepts.get(i);
            if (inside.equals(c.getValue())) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.WHITE);
            }
            if(selected.equals(c.getValue())){
                g.setColor(Color.RED);
            }
            g.fillOval(c.getGUIData("pos_x") + treeCenter, c.getGUIData("pos_y"), size_x, size_y);
            g.setColor(Color.BLACK);
            Rectangle2D r2D = f.getStringBounds(c.getValue(),frc);
            int offset_x = (size_x/2) - ((int)Math.round(r2D.getWidth())/2) - (int)Math.round(r2D.getX());
            int offset_y = (size_y/2) - ((int)Math.round(r2D.getHeight())/2) - (int)Math.round(r2D.getY());
            g.drawString(c.getValue(), c.getGUIData("pos_x")+offset_x+treeCenter, c.getGUIData("pos_y")+offset_y);
        }
    }
    
    public String getSelected(){
        return this.selected;
    }

    
    

}
