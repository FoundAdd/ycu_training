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
        this.setTitle("������");
        this.setSize(750, 420);

        //���ھ���
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
            // ���ñ���ͼƬ
            bgMusic("audio/bg.wav");
            ImageIcon img = new ImageIcon(MainFrame.class.getClassLoader().getResource("img/bg.jpg"));
            g.drawImage(img.getImage(), 0, 0, this);
            }
        };

        jPanel.setLayout(null);//��jpanelĬ�ϵ���ʽ���ָ���Ϊ�ղ���

        JButton jbStart = new JButton("��ʼ��Ϸ");
        JButton jbEnd = new JButton("������Ϸ");
        JButton jbTips = new JButton("������ʾ");

        // ����ʼ��Ϸ����ť�����¼�
        jbStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        // ��������Ϸ����ť�����¼�
        jbEnd.addActionListener(e -> System.exit(0));
        // ��������ʾ����ť�����¼�
        jbTips.addActionListener(e -> JOptionPane.showMessageDialog(MainFrame.this, "�����ƶ�: ����, ����, ����, ����"+ "\r\n����һ��: �ո��"));

        jbStart.setBounds(630, 250, 100, 30);
        jbEnd.setBounds(630, 300, 100, 30);
        jbTips.setBounds(630, 350, 100, 30);

        jPanel.add(jbStart);
        jPanel.add(jbEnd);
        jPanel.add(jbTips);

        this.add(jPanel);
        this.setResizable(false);//���ô��ڲ��ɸı��С
        this.setVisible(true);  //�ֶ������ػ�һ�����
    }

    public void bgMusic(String musicPath) {
        //���ñ�������
        URL cb = MainFrame.class.getClassLoader().getResource(musicPath);

        AudioClip audioClip;
        audioClip = Applet.newAudioClip(cb);

        audioClip.play();
        audioClip.loop(); // ����ѭ������
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
