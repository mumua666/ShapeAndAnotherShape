import javax.swing.*;

/**
 * @ClassName: StartGame
 * @Description 开始游戏
 * @author Linux_Mumu
 */
public class StartGame {
    public static void main(String[] args) {
        // 显示应用 GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Thread() {
                    @Override
                    public void run() {
                        new Server();
                    }
                }.start();

                new Thread() {
                    @Override
                    public void run() {
                        new LogInPanel();
                    }
                }.start();
                System.out.println(LogInPanel.tips.getText());
            }
        });

        // 显示应用 GUI
        // SwingUtilities.invokeLater(() -> {
        // new Thread(() -> new Server()).start();
        // new Thread(() -> new LogInPanel()).start();
        // System.out.println(LogInPanel.tips.getText());
        // });

    }
}
