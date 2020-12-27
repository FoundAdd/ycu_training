package pushBox;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainFrame extends JFrame{

    public MainFrame(){
        this.setTitle("推箱子");
        this.setSize(750, 420);

        //窗口居中
        int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
        int windowWidth=this.getWidth();
        int windowHeight=this.getHeight();
        this.setLocation((screenWidth-windowWidth)/2, (screenHeight-windowHeight)/2);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // 设置背景图片
            bgMusic("audio/bg.wav");
            ImageIcon img = new ImageIcon(MainFrame.class.getClassLoader().getResource("img/bg.jpg"));
            g.drawImage(img.getImage(), 0, 0, this);
            }
        };

        jPanel.setLayout(null);//将jpanel默认的流式布局更改为空布局

        JButton jbStart = new JButton("开始游戏");
        JButton jbEnd = new JButton("结束游戏");
        JButton jbTips = new JButton("操作提示");

        // “开始游戏”按钮监听事件
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // “结束游戏”按钮监听事件
        jbEnd.addActionListener(e -> System.exit(0));
        // “操作提示”按钮监听事件
        jbTips.addActionListener(e -> JOptionPane.showMessageDialog(MainFrame.this, "人物移动: ↑上, ↓下, ←左, →右"+ "\r\n后退一步: 空格键"));

        jbStart.setBounds(630, 250, 100, 30);
        jbEnd.setBounds(630, 300, 100, 30);
        jbTips.setBounds(630, 350, 100, 30);

        jPanel.add(jbStart);
        jPanel.add(jbEnd);
        jPanel.add(jbTips);

        this.add(jPanel);
        this.setResizable(false);//设置窗口不可改变大小
        this.setVisible(true);  //手动设置重绘一次组件
    }

    public void bgMusic(String musicPath) {
        //设置背景音乐
        URL cb = MainFrame.class.getClassLoader().getResource(musicPath);

        AudioClip audioClip;
        audioClip = Applet.newAudioClip(cb);

        audioClip.play();
        audioClip.loop(); // 设置循环播放
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
