import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.event.*;
import java.awt.Color;
import java.awt.FlowLayout;

/**
 * @ClassName: MenuNet
 * @Description 网络模式菜单类
 * @author Linux_Mumu
 */
public class MenuNet extends JLabel {

    public static JButton button0;
    public static JButton button1;
    public static JButton button2;
    public static JButton button3;
    public static JButton button4;
    public static JButton button5;
    public static JButton button6;

    public MenuNet() {

        setLayout(new FlowLayout());
        setBounds(0, 0, 800, 36);

        button0 = new JButton("切换难度");
        button1 = new JButton("切换牌面");
        button2 = new JButton("切换牌局");
        button3 = new JButton("重新打乱");
        button4 = new JButton("撤回操作");
        button5 = new JButton("上移操作");
        button6 = new JButton("复活操作");

        button4.setBackground(Color.GRAY);
        button5.setBackground(Color.GRAY);
        button6.setBackground(Color.GRAY);

        button0.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button1.getBackground() != Color.GRAY) {
                    System.out.println("切换难度");
                    try {
                        Client.senTCP("changeHardLevel");
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                    if (Client.reply.equals("切换难度"))
                        NumCardsUtil.changeHardLevel();
                }
            }
        });

        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button1.getBackground() != Color.GRAY) {
                    System.out.println("切换牌面");
                    try {
                        Client.senTCP("reInitCardsIcon");
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                    if (Client.reply.equals("切换牌面"))
                        NumCardsUtil.reInitCardsIcon();
                }
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button2.getBackground() != Color.GRAY) {
                    System.out.println("切换牌局");
                    try {
                        Client.senTCP("changeLayout");
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                    if (Client.reply.equals("切换牌局"))
                        NumCardsUtil.changeLayout();
                }
            }
        });

        button3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button3.getBackground() != Color.GRAY) {
                    System.out.println("重新打乱");
                    try {
                        Client.senTCP("shuffle");
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                    if (Client.reply.equals("重新打乱"))
                        NumCardsUtil.shuffle();
                }
            }
        });

        button4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (button4.getBackground() != Color.GRAY) {
                    int curValue;
                    int len = PlayGamePanel.bottomNodeList.size();
                    CardNode node = PlayGamePanel.bottomNodeList.get(len - 1);
                    node.setLocation(node.getX(), node.getY() - 560);
                    curValue = Integer.parseInt(node.getText());
                    PlayGamePanel.nodeCount[curValue]--;
                    PlayGamePanel.bottomNodeList.remove(len - 1);
                    for (int j = 0; j < PlayGamePanel.bottomNodeList.size(); j++) {
                        PlayGamePanel.bottomNodeList.get(j).setLocation(PlayGamePanel.bottomPoints[j][0],
                                PlayGamePanel.bottomPoints[j][1]);
                    }
                    System.out.println("撤回操作");
                    if (PlayGamePanel.bottomNodeList.size() == 0) {
                        button4.setBackground(Color.GRAY);
                    }
                    if (PlayGamePanel.bottomNodeList.size() < 3) {
                        button5.setBackground(Color.GRAY);
                    }
                }
            }
        });

        button5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (button5.getBackground() != Color.GRAY) {
                    int curValue;
                    int count = 0;
                    for (CardNode node : PlayGamePanel.bottomNodeList) {
                        node.setLocation(node.getX(), node.getY() - 560);
                        count++;
                        curValue = Integer.parseInt(node.getText());
                        PlayGamePanel.nodeCount[curValue]--;
                        if (count == 3) {
                            break;
                        }
                    }
                    PlayGamePanel.bottomNodeList.remove(0);
                    PlayGamePanel.bottomNodeList.remove(0);
                    PlayGamePanel.bottomNodeList.remove(0);
                    for (int j = 0; j < PlayGamePanel.bottomNodeList.size(); j++) {
                        PlayGamePanel.bottomNodeList.get(j).setLocation(PlayGamePanel.bottomPoints[j][0],
                                PlayGamePanel.bottomPoints[j][1]);
                    }
                    System.out.println("上移操作");
                    if (PlayGamePanel.bottomNodeList.size() < 3) {
                        button5.setBackground(Color.GRAY);
                    }
                    if (PlayGamePanel.bottomNodeList.size() == 0) {
                        button4.setBackground(Color.GRAY);
                    }
                }
            }
        });

        button6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (button6.getBackground() != Color.GRAY) {
                    for (CardNode node : PlayGamePanel.bottomNodeList) {
                        int curValue;
                        curValue = Integer.parseInt(node.getText());
                        PlayGamePanel.nodeCount[curValue]--;
                        node.setLocation(node.getX(), node.getY() - 560);
                    }
                    PlayGamePanel.bottomNodeList.clear();
                    PlayGamePanel.tips.setText("游戏进行中...");
                    PlayGamePanel.tips.setForeground(Color.BLACK);
                    PlayGamePanel.lose = false;
                    System.out.println("复活操作");
                    button6.setBackground(Color.GRAY);
                    button3.setBackground(new ColorUIResource(238, 238, 238));
                    PlayGamePanel.hardLevel.setVisible(false);
                }
            }
        });

        add(button0);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
    }
}
