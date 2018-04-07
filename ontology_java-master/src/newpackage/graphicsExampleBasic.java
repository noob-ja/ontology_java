package newpackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class graphicsExampleBasic extends JFrame {

    private boolean testColorBlack = true;
    private boolean thickLine = false;
    private int X = 50;
    private int Y = 50;
    private int maxX = 1000;
    private int maxY = 900;
    private int minX = 0;
    private int minY = 0;

    private int temporaryStore = 0;

    private int i = 0;
    private int j = 0;

    private int theTempXMouse = 0;
    private int theTempYMouse = 0;

    private int thePrevTempXMouse = 0;
    private int thePrevTempYMouse = 0;

    private boolean drawControl = false;

    private boolean[][] screen = null;

    final drawPanel myDrawPanel;

    public graphicsExampleBasic() {
        myDrawPanel = new drawPanel();
        MyKeyListener myKeyListener = new MyKeyListener();

        this.add(myDrawPanel);
        addKeyListener(myKeyListener);
        this.setVisible(true);

        int windowXSize = maxX * 125 / 100;
        int windowYSize = maxY * 125 / 100;
        
//        this.setLayout(null);
        JButton test = new JButton("test");
        test.setLocation(100, 100);
        JButton test1 = new JButton("test");
        test.setLocation(200, 200);
        JButton test2 = new JButton("test");
        test.setLocation(300, 300);
        JPanel panel = new JPanel();
        panel.add(test);
        panel.add(test1);
        panel.add(test2);
        JScrollPane scrollpane = new JScrollPane(panel);
        this.add(scrollpane);
        this.repaint();
        
        screen = new boolean[maxX][maxY];
        initializeScreen();

        this.setSize(windowXSize, windowYSize);

        MouseHandler myHandler = new MouseHandler();
        myDrawPanel.addMouseListener(myHandler);
        myDrawPanel.addMouseMotionListener(myHandler);

        myDrawPanel.setVisible(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(
                new WindowAdapter() {
            // exit when window has closed
            public void windowClosed(WindowEvent event) {
                System.exit(0);
            } // end method windowClosed
        } // end WindowAdapter inner class
        ); // end call to addWindowListener
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        public void mouseClicked(MouseEvent event) {
        }//end public void mouseClicked(mouseEvent event)

        public void mousePressed(MouseEvent event) {
            thePrevTempXMouse = theTempXMouse;
            thePrevTempYMouse = theTempYMouse;

            theTempXMouse = event.getX();
            theTempYMouse = event.getY();
        }//end public void mousePressed(mouseEvent event)

        public void mouseReleased(MouseEvent event) {
            drawControl = false;
            theTempXMouse = event.getX();
            theTempYMouse = event.getY();
            if (theTempXMouse > 0 && theTempXMouse < maxX && theTempYMouse > 0 && theTempYMouse < maxY) {
                screen[theTempXMouse][theTempYMouse] = true;

                myDrawPanel.repaint();
            }

        }//end public void mouseReleased(mouseEvent event)

        public void mouseEntered(MouseEvent event) {
            theTempXMouse = 0;
            theTempYMouse = 0;
        }//end public void mouseEntered(mouseEvent event)

        public void mouseExited(MouseEvent event) {
            theTempXMouse = 0;
            theTempYMouse = 0;
        }//end public void mouseExited(mouseEvent event)

        public void mouseDragged(MouseEvent event) {
            theTempXMouse = event.getX();
            theTempYMouse = event.getY();
            if (theTempXMouse > 0 && theTempXMouse < maxX && theTempYMouse > 0 && theTempYMouse < maxY) {
                screen[theTempXMouse][theTempYMouse] = true;

                myDrawPanel.repaint();
            }
        }//end public void mouseDragged(mouseEvent event)

        public void mouseMoved(MouseEvent event) {
            //theTempXMouse = event.getX();
            //theTempYMouse = event.getY();
        }//end public void mouseMoved(mouseEvent event)
    }//end private class MouseHandler implements MouseListener, MouseMotionListener

    private class MyKeyListener implements KeyListener {

        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_DELETE:
                    initializeScreen();
                    myDrawPanel.repaint();
                    break;
                case KeyEvent.VK_INSERT:
                    if (testColorBlack == true) {
                        testColorBlack = false;
                    } else {
                        testColorBlack = true;
                    }
                    myDrawPanel.repaint();
                    break;
                case KeyEvent.VK_K:
                    if (thickLine == true) {
                        thickLine = false;
                    } else {
                        thickLine = true;
                    }
                    myDrawPanel.repaint();
                    break;
            }
        }

        public void keyReleased(KeyEvent event) {
        }

        public void keyTyped(KeyEvent event) {
        }
    }

    private class drawPanel extends JPanel {

        private int width = 10;
        private int height = 10;

        public void paint(Graphics grap) {
            super.paint(grap);

            if (thickLine == true) {
                width = 30;
                height = 30;
            } else {
                width = 10;
                height = 10;
            }

            //System.out.println("Repaint");
            for (i = 0; i < maxX; i++) {
                for (j = 0; j < maxY; j++) {
                    if (screen[i][j] == true) {
                        if (testColorBlack == true) {
                            grap.setColor(Color.RED);
                        } else {
                            grap.setColor(Color.BLUE);
                        }
                        grap.fillOval(i, j, width, height);
                        //System.out.println("BLACK " + i + "  " + j);
                    }
                }
            }
        }

    }

    private void initializeScreen() {
        for (i = 0; i < maxX; i++) {
            for (j = 0; j < maxY; j++) {
                screen[i][j] = false;
            }
        }
    }

    public static void main(String args[]) {
        graphicsExampleBasic frame = new graphicsExampleBasic();
    }
}

/**/
