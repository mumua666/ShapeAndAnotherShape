import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.ColorUIResource;

/**
 * @ClassName: LogInPanel
 * @Description 登陆面板
 * @author Linux_Mumu
 */

public class LogInPanel extends JFrame {
    public static JTextField username = new JTextField();
    public static JPasswordField password = new JPasswordField();
    public static JLabel usernameLabel = new JLabel("用户名: ", JLabel.CENTER);
    public static JLabel passwordLabel = new JLabel("密  码: ", JLabel.CENTER);
    public static JLabel tips = new JLabel("请登录", JLabel.CENTER);
    public static JButton button1 = new JButton("登录");
    public static JButton button2 = new JButton("注册");
    public static JButton button3 = new JButton("历史对局");
    public static String message;

    public LogInPanel() {
        init();
    }

    public void init() {
        setTitle("羊了个羊客户端登录页面");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        setLayout(null);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        tips.setFont(new Font("Dialog", Font.PLAIN, 30));
        tips.setForeground(Color.RED);
        usernameLabel.setBounds(100, 80, 150, 100);
        passwordLabel.setBounds(100, 180, 150, 100);
        tips.setBounds(280, 350, 200, 100);
        username.setBounds(250, 100, 300, 50);
        password.setBounds(250, 200, 300, 50);
        add(usernameLabel);
        add(passwordLabel);
        add(tips);
        add(username);
        add(password);

        button1.setBounds(150, 300, 100, 50);
        button2.setBounds(300, 300, 100, 50);
        button3.setBounds(450, 300, 100, 50);
        button3.setBackground(Color.GRAY);
        addListener();
        add(button1);
        add(button2);
        add(button3);
    }

    public void addListener() {

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("登录");
                System.out.println(username.getText());
                System.out.println(password.getPassword());
                message = "LogIn-" + username.getText();
                message += "-";
                message += new String(password.getPassword());
                try {
                    Client.senTCP(message);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                tips.setText(Client.reply);
                username.setText("");
                password.setText("");

                if (tips.getText().equals("登录成功")) {
                    new GameFrame();
                    // SoundEffect.playBackgroundSound();
                    LogInPanel.button3.setBackground(new ColorUIResource(238, 238, 238));
                }
                // r=238,g=238,b=238
            }
        });
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("注册");
                System.out.println(username.getText());
                System.out.println(password.getPassword());
                message = "SignUp-" + username.getText();
                message += "-";
                message += new String(password.getPassword());
                try {
                    Client.senTCP(message);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                tips.setText(Client.reply);
                username.setText("");
                password.setText("");
            }
        });
        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button3.getBackground() != Color.GRAY) {
                    System.out.println("历史对局");
                    message = "History";
                    try {
                        Client.senTCP(message);
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                    String[] reponse = Client.reply.split("[+]");
                    tips.setText(reponse[0]);
                    if (tips.getText().equals("历史对局"))
                        new History(reponse);
                }
            }
        });
    }
}
