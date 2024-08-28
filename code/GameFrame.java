import javax.swing.*;

/**
 * @ClassName: GameFrame
 * @Description 游戏窗口
 * @author Linux_Mumu
 */
public class GameFrame extends JFrame {

    public GameFrame() {
        init();
    }

    public void init() {

        this.setTitle("羊了个羊第二关");
        // 添加面板
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        PlayGamePanel play = new PlayGamePanel();
        add(play);
        play.startRun();
    }
}