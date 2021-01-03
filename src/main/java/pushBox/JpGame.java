package pushBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JpGame extends JPanel {
    private LoadMap loadMap;
    private int[][] map;
    private Image[] images = {
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/0.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/1.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/2.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/3.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/4.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/5.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/6.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/7.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/8.gif")),
            Toolkit.getDefaultToolkit().getImage(JpGame.class.getClassLoader().getResource("img/9.gif"))
    };
    private int manX, manY;
    private int lastimg = 2;

    public JpGame(int level) {
        this.setBounds(0, 0, 600, 600);
        loadMap = new LoadMap(level);
        this.map = loadMap.getMap();
        this.manX = loadMap.getManX();
        this.manY = loadMap.getManY();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i=0; i<20; i++) {
            for (int j=0; j<20; j++) {
                g.drawImage(images[this.map[i][j]], i*30, j*30, this);
            }
        }
    }

    public void move(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP: case KeyEvent.VK_W:
                this.map[manX][manY]=this.lastimg;
                this.lastimg=this.map[manX][manY-1];
                this.map[manX][manY-1]=8;
                this.manY--;
                this.repaint();
                break;
            case KeyEvent.VK_DOWN: case KeyEvent.VK_S:
                this.map[manX][manY]=this.lastimg;
                this.lastimg=this.map[manX][manY+1];
                this.map[manX][manY+1]=5;
                this.manY++;
                this.repaint();
                break;
            case KeyEvent.VK_LEFT: case KeyEvent.VK_A:
                this.map[manX][manY]=this.lastimg;
                this.lastimg=this.map[manX-1][manY];
                this.map[manX-1][manY]=6;
                this.manX--;
                this.repaint();
                break;
            case KeyEvent.VK_RIGHT: case KeyEvent.VK_D:
                this.map[manX][manY]=this.lastimg;
                this.lastimg=this.map[manX+1][manY];
                this.map[manX+1][manY]=7;
                this.manX++;
                this.repaint();
                break;
        }
    }
}
