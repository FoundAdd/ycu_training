package pushBox;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Objects;

public class MainFrame extends JFrame{
    private final JPanel jPanel;
    private JpGame jpGame;
    private boolean isMusicPlay = true;
    private AudioClip audioClip;

    public MainFrame(){
        this.setTitle("推箱子");
        this.setSize(800, 600);

        //窗口居中
        int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowWidth=this.getWidth();
        int windowHeight=this.getHeight();
        this.setLocation((screenWidth-windowWidth)/2, (screenHeight-windowHeight)/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 设置背景图片
            bgMusic("audio/bg.wav");
            ImageIcon img = new ImageIcon(Objects.requireNonNull(MainFrame.class.getClassLoader().getResource("img/bg.jpg")));
            g.drawImage(img.getImage(), 0, 0, this);
            }
        };

        jPanel.setLayout(null);//将JPanel默认的流式布局更改为空布局

        JButton jbStart = new JButton("开始游戏");
        JButton jbEnd = new JButton("结束游戏");
        JButton jbTips = new JButton("操作提示");
        JButton jbVolume = new JButton("音乐: 开");

        // “开始游戏”按钮监听事件
        jbStart.addActionListener(e -> startGame());
        // “结束游戏”按钮监听事件
        jbEnd.addActionListener(e -> System.exit(0));
        // “操作提示”按钮监听事件
        jbTips.addActionListener(e -> JOptionPane.showMessageDialog(MainFrame.this,
                "人物移动: ↑上, ↓下, ←左, →右"+ "\r\n后退一步: 空格键"));
        // 音乐控制按钮监听事件
        jbVolume.addActionListener(e -> {
            if (isMusicPlay) {
                audioClip.stop();
                jbVolume.setText("音乐: 关");
                isMusicPlay = false;
            } else {
                audioClip.play();
                jbVolume.setText("音乐: 开");
                isMusicPlay = true;
            }
        });

        jbStart.setBounds(680, 360, 100, 30);
        jbEnd.setBounds(680, 410, 100, 30);
        jbTips.setBounds(680, 460, 100, 30);
        jbVolume.setBounds(680, 510, 100, 30);

        jPanel.add(jbStart);
        jPanel.add(jbEnd);
        jPanel.add(jbTips);
        jPanel.add(jbVolume);

        this.add(jPanel);
        this.setResizable(false);//设置窗口不可改变大小
        this.setVisible(true);  //手动设置重绘一次组件
    }

    public void bgMusic(String musicPath) {
        //设置背景音乐
        URL cb = MainFrame.class.getClassLoader().getResource(musicPath);

        audioClip = Applet.newAudioClip(cb);

        audioClip.play();
        audioClip.loop(); // 设置循环播放
    }

    public void startGame() {
        isMusicPlay = true;
        audioClip.stop();

        // 移除上一个页面的组件
        for (int i=0; i<jPanel.getComponentCount(); i++) {
            jPanel.remove(i);
        }
        this.remove(this.jPanel);

        JPanel jpMenu = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img=new ImageIcon(Objects.requireNonNull(MainFrame.class.getClassLoader().getResource("img/toolImg.jpg")));
                g.drawImage(img.getImage(), 0, 0, this);
            }
        };
        this.setLayout(null);
        jpMenu.setBounds(600, 0, 200, 600);
        jpMenu.setLayout(null); //不设置为null就会在按钮点击后所有按钮布局错乱
        this.add(jpMenu);

        this.bgMusic("audio/gameBg.wav");

        JButton jbBack = new JButton("撤销");
        JButton jbLast = new JButton("上一关");
        JButton jbNext = new JButton("下一关");
        JButton jbChoice = new JButton("选关");
        JButton jbRestart = new JButton("重新开始");
        JButton jbVolume = new JButton("音乐: 开");
        JButton jbExit = new JButton("退出游戏");

        jpMenu.add(jbBack);
        jpMenu.add(jbLast);
        jpMenu.add(jbNext);
        jpMenu.add(jbChoice);
        jpMenu.add(jbRestart);
        jpMenu.add(jbVolume);
        jpMenu.add(jbExit);

        jbLast.setBounds(50, 200, 100, 30);
        jbLast.setBounds(50, 250, 100, 30);
        jbNext.setBounds(50, 300, 100, 30);
        jbChoice.setBounds(50, 350, 100, 30);
        jbRestart.setBounds(50, 400, 100, 30);
        jbVolume.setBounds(50, 450, 100, 30);
        jbExit.setBounds(50, 500, 100, 30);

        // 音乐控制按钮监听事件
        jbVolume.addActionListener(e -> {
            if (isMusicPlay) {
                audioClip.stop();
                jbVolume.setText("音乐: 关");
                isMusicPlay = false;
            } else {
                audioClip.play();
                jbVolume.setText("音乐: 开");
                isMusicPlay = true;
            }
        });
        // “退出游戏”按钮监听事件
        jbExit.addActionListener(e -> System.exit(0));

        this.jpGame = new JpGame(1);

        this.add(jpGame);
        this.jpGame.addKeyListener(new MoveListener());
        this.jpGame.requestFocus();
        this.repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }

    class MoveListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            jpGame.move(e);
        }
    }
}
