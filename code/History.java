import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;

/**
 * @ClassName: History
 * @Description 历史记录
 * @author Linux_Mumu
 */

public class History extends JFrame {
    public History(String[] historyArray) {
        init(historyArray);
    }

    public void init(String[] historyArray) {
        setTitle("羊了个羊历史对局");
        // 添加面板
        setSize(700, 500);
        setLayout(new GridLayout(1 + Server.history.size(), 2));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        JLabel title = new JLabel("---登录用户---对局难度---对局结果---", JLabel.CENTER);
        title.setFont(new Font("Dialog", Font.PLAIN, 20));
        title.setVisible(true);
        title.setBounds(100, 10, 200, 100);
        add(title);
        int disY = 60;
        for (int i = 1; i < historyArray.length; i++) {
            String history = historyArray[i];
            System.out.println(history);
            JLabel label = new JLabel(history, JLabel.CENTER);
            label.setBounds(100, disY, 200, 100);
            label.setVisible(true);
            label.setFont(new Font("Dialog", Font.PLAIN, 20));
            add(label);
            disY += 10;
        }
    }
}
