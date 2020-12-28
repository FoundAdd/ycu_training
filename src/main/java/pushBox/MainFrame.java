package pushBox;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.net.URL;
import java.util.Objects;

public class MainFrame extends JFrame{

    private final JPanel jPanel;
    private AudioClip audioClip;
    private boolean isMusicPlay = true;

    public MainFrame(){
        this.setTitle("������");
        this.setSize(800, 600);

        //���ھ���
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
            // ���ñ���ͼƬ
            bgMusic("audio/bg.wav");
            ImageIcon img = new ImageIcon(Objects.requireNonNull(MainFrame.class.getClassLoader().getResource("img/bg.jpg")));
            g.drawImage(img.getImage(), 0, 0, this);
            }
        };

        jPanel.setLayout(null);//��JPanelĬ�ϵ���ʽ���ָ���Ϊ�ղ���

        JButton jbStart = new JButton("��ʼ��Ϸ");
        JButton jbEnd = new JButton("������Ϸ");
        JButton jbTips = new JButton("������ʾ");
        JButton jbVolume = new JButton("����: ��");

        // ����ʼ��Ϸ����ť�����¼�
        jbStart.addActionListener(e -> startGame());
        // ��������Ϸ����ť�����¼�
        jbEnd.addActionListener(e -> System.exit(0));
        // ��������ʾ����ť�����¼�
        jbTips.addActionListener(e -> JOptionPane.showMessageDialog(MainFrame.this,
                "�����ƶ�: ����, ����, ����, ����"+ "\r\n����һ��: �ո��"));
        // ���ֿ��ư�ť�����¼�
        jbVolume.addActionListener(e -> {
            if (isMusicPlay) {
                audioClip.stop();
                jbVolume.setText("����: ��");
                isMusicPlay = false;
            } else {
                audioClip.play();
                jbVolume.setText("����: ��");
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
        this.setResizable(false);//���ô��ڲ��ɸı��С
        this.setVisible(true);  //�ֶ������ػ�һ�����
    }

    public void bgMusic(String musicPath) {
        //���ñ�������
        URL cb = MainFrame.class.getClassLoader().getResource(musicPath);

        audioClip = Applet.newAudioClip(cb);

        audioClip.play();
        audioClip.loop(); // ����ѭ������
    }

    public void startGame() {
        audioClip.stop();
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
        this.add(jpMenu);

        JButton jbLast = new JButton("��һ��");
        JButton jbNext = new JButton("��һ��");
        JButton jbChoice = new JButton("ѡ��");
        JButton jbRestart = new JButton("���¿�ʼ");
        JButton jbVolume = new JButton("���ֿ���");
        JButton jbExit = new JButton("�˳���Ϸ");

        jpMenu.add(jbLast);
        jpMenu.add(jbNext);
        jpMenu.add(jbChoice);
        jpMenu.add(jbRestart);
        jpMenu.add(jbVolume);
        jpMenu.add(jbExit);

        jbLast.setBounds(50, 250, 100, 30);
        jbNext.setBounds(50, 300, 100, 30);
        jbChoice.setBounds(50, 350, 100, 30);
        jbRestart.setBounds(50, 400, 100, 30);
        jbVolume.setBounds(50, 450, 100, 30);
        jbExit.setBounds(50, 500, 100, 30);

        // ���ֿ��ư�ť�����¼�
        jbVolume.addActionListener(e -> {
            if (isMusicPlay) {
                audioClip.stop();
                isMusicPlay = false;
            } else {
                audioClip.play();
                isMusicPlay = true;
            }
        });
        // ���˳���Ϸ����ť�����¼�
        jbExit.addActionListener(e -> System.exit(0));

        this.bgMusic("audio/gameBg.wav");
        this.repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
