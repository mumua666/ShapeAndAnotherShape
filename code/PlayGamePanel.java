import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.plaf.ColorUIResource;

/**
 * @ClassName: PlayGamePanel
 * @Description 游戏面板
 * @author Linux_Mumu
 */

public class PlayGamePanel extends JPanel implements MouseListener {

    public static boolean lose = false;
    public static int deleteConut = 0;
    public static int[] nodeCount = new int[9];
    public static int[][] bottomPoints = new int[7][2];
    public static List<CardNode> bottomNodeList = new ArrayList<>();
    private JPanel bottomPanel = new JPanel();
    public static JLabel tips = new JLabel("游戏进行中...", JLabel.CENTER);
    public static JLabel hardLevel = new JLabel("当前难度:\t");

    public void init() {
        lose = false;
        deleteConut = 0;
        for (int i = 0; i < 9; i++)
            nodeCount[i] = 0;
        bottomNodeList.clear();
        hardLevel.setVisible(true);
        tips.setText("游戏进行中...");
    }

    public PlayGamePanel() {
        init();
        this.setLayout(null);
        bottomPanel.setLayout(null);
        bottomPanel.setBounds(115, 580, 570, 80);
        bottomPanel.setBackground(Color.LIGHT_GRAY);

        tips.setFont(new Font("Dialog", Font.PLAIN, 20));
        tips.setBounds(150, 450, 500, 80);
        add(tips);

        // Menu menu = new Menu();
        // add(menu);
        MenuNet menuNet = new MenuNet();
        add(menuNet);

        hardLevel.setFont(new Font("Dialog", Font.PLAIN, 20));
        hardLevel.setBounds(280, 30, 500, 80);
        hardLevel.setForeground(Color.RED);
        add(hardLevel);

        initCardLabel();
        // 初始化底部卡槽背景
        for (int i = 7; i > 0; i--) {
            JLabel emptyLabel = new JLabel();
            emptyLabel.setOpaque(true);
            emptyLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
            emptyLabel.setBackground(Color.GRAY);
            emptyLabel.setBounds(70 * i - 20, 25, 50, 50);
            emptyLabel.setOpaque(false);
            bottomPanel.add(emptyLabel);
        }
        // 初始化底部7个卡槽坐标
        for (int x = 0; x < 7; x++) {
            bottomPoints[x][0] = 165 + x * 70;
            bottomPoints[x][1] = 605;
        }

        ImageIcon down = new ImageIcon("./static/board.jpg");
        Image tempDown = down.getImage().getScaledInstance(570, 80, Image.SCALE_DEFAULT);
        down = new ImageIcon(tempDown);
        JLabel board = new JLabel(down);
        board.setSize(570, 80);
        board.setOpaque(false);
        bottomPanel.add(board);

        add(bottomPanel);

        ImageIcon bg = new ImageIcon("./static/grass.jpeg");
        Image tempBG = bg.getImage().getScaledInstance(800, 700, Image.SCALE_DEFAULT);
        bg = new ImageIcon(tempBG);
        JLabel background = new JLabel(bg);
        background.setSize(800, 700);
        background.setOpaque(false);
        add(background);

    }

    public void initCardLabel() {
        NumCardsUtil.initCards();
        NumCardsUtil.initcenterCardData(7);

        int curIndex = 0;
        int curLineNum = 0;
        int nextLineNum = 0;

        for (int n = 1; n <= 7; n++) {
            for (int i = 0; i < n * n; i++) {
                if (i % n == 0) {
                    nextLineNum++;
                    curLineNum = 0;
                }
                CardNode node = createNumLabel("center-" + n + "-" + curLineNum + "-" + (nextLineNum - 1),
                        NumCardsUtil.centerCards.get(curIndex++), 0, 395 - n * 25 + 50 * curLineNum,
                        225 - 25 * n + 50 * nextLineNum);
                node.setxIndex(curLineNum);
                node.setyIndex(nextLineNum - 1);
                node.setLevel(n);

                curLineNum++;
            }
            curLineNum = 0;
            nextLineNum = 0;
        }

        // 底部卡片列表
        for (int i = 18; i > 0; i--) {
            // NumCardsUtil.leftCards
            createNumLabel("left1-" + i, NumCardsUtil.leftCards1.get(i), 1, 20 + 15 * i, 500);
            createNumLabel("right1-" + i, NumCardsUtil.rightCards1.get(i), 2, 730 - 15 * i, 500);
        }

        // 中部卡片列表
        for (int i = 22; i > 0; i--) {
            // NumCardsUtil.leftCards
            createNumLabel("left2-" + i, NumCardsUtil.leftCards2.get(i), 3, 150, 100 + 15 * i);
            createNumLabel("right2-" + i, NumCardsUtil.rightCards2.get(i), 4, 600, 100 + 15 * i);
        }
        // 中外层卡片列表
        for (int i = 22; i > 0; i--) {
            // NumCardsUtil.leftCards
            createNumLabel("left3-" + i, NumCardsUtil.leftCards3.get(i), 5, 70, 100 + 15 * i);
            createNumLabel("right3-" + i, NumCardsUtil.rightCards3.get(i), 6, 680, 100 + 15 * i);
        }
    }

    /**
     * 数字卡片
     * 
     * @param numLabel
     * @param x
     * @param y
     */
    public CardNode createNumLabel(String name, CardNode numLabel, int type, int x, int y) {
        numLabel.setName(name);
        numLabel.setFont(new Font("Dialog", Font.PLAIN, 0));
        numLabel.setOpaque(true);
        numLabel.setType(type);

        numLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        numLabel.setBackground(Color.GREEN);
        numLabel.setBounds(x, y, 50, 50);
        numLabel.setPreferredSize(new Dimension(50, 50));
        numLabel.setVerticalTextPosition(JLabel.BOTTOM);
        numLabel.setHorizontalTextPosition(JLabel.CENTER);
        numLabel.addMouseListener(this);

        add(numLabel);
        return numLabel;
    }

    /**
     * 刷新面板
     */
    public void startRun() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 刷新屏幕，自动调用paint()方法
                    repaint();
                }
            }
        }.start();

        /**
         * 计算难度线程
         */
        new Thread() {
            @Override
            public void run() {
                int hardvalue = 0;
                while (!lose) {
                    switch (NumCardsUtil.hardLevel) {
                        case "Hard":
                            hardvalue = 60;
                            break;
                        case "Medium":
                            hardvalue = 40;
                            break;
                        case "Simple":
                            hardvalue = 20;
                            break;
                        default:
                            break;
                    }
                    switch (bottomNodeList.size()) {
                        case 1:
                            hardvalue += 1;
                            break;
                        case 2:
                            hardvalue += 3;
                            break;
                        case 3:
                            hardvalue += 6;
                            break;
                        case 4:
                            hardvalue += 10;
                            break;
                        case 5:
                            hardvalue += 25;
                            break;
                        case 6:
                            hardvalue += 40;
                            break;
                        default:
                            break;
                    }

                    if (NumCardsUtil.centerCards.size() < 49)
                        hardvalue += (49 - NumCardsUtil.centerCards.size()) / 10;
                    else if (NumCardsUtil.centerCards.size() < 49 + 36)
                        hardvalue += (49 + 36 - NumCardsUtil.centerCards.size()) / 8;
                    else if (NumCardsUtil.centerCards.size() < 49 + 36 + 25)
                        hardvalue += (49 + 36 + 25 - NumCardsUtil.centerCards.size()) / 6;
                    else if (NumCardsUtil.centerCards.size() < 49 + 36 + 25 + 16)
                        hardvalue += (49 + 36 + 25 + 16 - NumCardsUtil.centerCards.size()) / 4;
                    else if (NumCardsUtil.centerCards.size() < 49 + 36 + 25 + 16 + 4)
                        hardvalue += (49 + 36 + 25 + 16 + 4 - NumCardsUtil.centerCards.size()) / 2;
                    else
                        hardvalue += 10;
                    if (NumCardsUtil.leftCards1.size() > 0)
                        hardvalue += 3;
                    if (NumCardsUtil.leftCards2.size() > 0)
                        hardvalue += 3;
                    if (NumCardsUtil.leftCards3.size() > 0)
                        hardvalue += 3;
                    if (NumCardsUtil.rightCards1.size() > 0)
                        hardvalue += 3;
                    if (NumCardsUtil.rightCards2.size() > 0)
                        hardvalue += 3;
                    if (NumCardsUtil.rightCards3.size() > 0)
                        hardvalue += 3;

                    PlayGamePanel.hardLevel.setText("当前难度： " + hardvalue);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    /**
     * 是否存在父级
     * 
     * @param level
     * @param x
     * @param y
     * @return
     */
    public boolean hasParentLabel(int level, int x, int y) {
        String[] ids = { level + "-" + x + "-" + y, level + "-" + (x - 1) + "-" + y, level + "-" + x + "-" + (y - 1),
                level + "-" + (x - 1) + "-" + (y - 1) };
        for (String id : ids) {
            if (NumCardsUtil.centerCardData.contains("center-" + id)) {
                return true;
            }
        }
        return false;
    }

    public void dealUnCenterCardList(List<CardNode> list, CardNode node) {
        CardNode lastNode = list.get(list.size() - 1);
        if (lastNode != node) {
            return;
        }
        list.remove(node);
        orderList(node);
    }

    /**
     * 重排序
     * 
     * @param node
     */
    private void orderList(CardNode node) {
        MenuNet.button0.setBackground(Color.GRAY);
        MenuNet.button1.setBackground(Color.GRAY);
        MenuNet.button2.setBackground(Color.GRAY);
        int curValue = Integer.parseInt(node.getText());
        nodeCount[curValue]++;
        if (bottomNodeList.size() == 0) {
            bottomNodeList.add(node);
            node.setLocation(bottomPoints[0][0], bottomPoints[0][1]);
            MenuNet.button4.setBackground(new ColorUIResource(238, 238, 238));
            return;
        }
        int curIndex = 0;
        for (CardNode n : bottomNodeList) {
            int tempValue = Integer.parseInt(n.getText());
            if (curValue >= tempValue) {
                curIndex++;
            } else {
                break;
            }
        }
        bottomNodeList.add(curIndex, node);
        for (int j = 0; j < bottomNodeList.size(); j++) {
            bottomNodeList.get(j).setLocation(bottomPoints[j][0], bottomPoints[j][1]);
        }

        if (nodeCount[curValue] >= 3) {
            nodeCount[curValue] = 0;
            SoundEffect.playEraseSound();
            for (int i = 0; i < 3; i++) {
                bottomNodeList.get(curIndex).setVisible(false);
                bottomNodeList.remove(curIndex);
                if (curIndex > 0) {
                    curIndex--;
                }
            }
        }
        for (int j = 0; j < bottomNodeList.size(); j++) {
            bottomNodeList.get(j).setLocation(bottomPoints[j][0], bottomPoints[j][1]);
        }

        if (bottomNodeList.size() >= 3)
            MenuNet.button5.setBackground(new ColorUIResource(238, 238, 238));
        else
            MenuNet.button5.setBackground(Color.GRAY);

        if (bottomNodeList.size() > 0)
            MenuNet.button4.setBackground(new ColorUIResource(238, 238, 238));
        else
            MenuNet.button4.setBackground(Color.GRAY);

        if (deleteConut >= 263) {
            tips.setText("恭喜你,获得胜利!!!");
            tips.setForeground(Color.RED);
            MenuNet.button1.setBackground(Color.GRAY);
            MenuNet.button2.setBackground(Color.GRAY);
            MenuNet.button3.setBackground(Color.GRAY);
            MenuNet.button4.setBackground(Color.GRAY);
            MenuNet.button5.setBackground(Color.GRAY);
            MenuNet.button6.setBackground(Color.GRAY);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        Component com = e.getComponent();
        if (com instanceof CardNode && !lose) {
            CardNode node = (CardNode) com;
            SoundEffect.playClickSound();
            switch (node.getType()) {
                case 0:
                    boolean flag = hasParentLabel(node.getLevel() - 1, node.getxIndex(), node.getyIndex());
                    if (!flag) {
                        NumCardsUtil.centerCardData.remove(node.getName());
                        NumCardsUtil.centerCards.remove(node);
                        orderList(node);
                    }
                    break;
                case 1:
                    dealUnCenterCardList(NumCardsUtil.leftCards1, node);
                    break;
                case 2:
                    dealUnCenterCardList(NumCardsUtil.rightCards1, node);
                    break;
                case 3:
                    dealUnCenterCardList(NumCardsUtil.leftCards2, node);
                    break;
                case 4:
                    dealUnCenterCardList(NumCardsUtil.rightCards2, node);
                    break;
                case 5:
                    dealUnCenterCardList(NumCardsUtil.leftCards3, node);
                    break;
                case 6:
                    dealUnCenterCardList(NumCardsUtil.rightCards3, node);
                    break;
                default:
                    break;
            }

            if (bottomNodeList.size() >= 7) {
                tips.setText("很遗憾，您失败了！！！");
                tips.setForeground(Color.RED);
                PlayGamePanel.hardLevel.setText("当前难度: 999");
                // Server.history
                // .add("---" + Server.username.get(Server.username.size() - 1)
                // + "---" + NumCardsUtil.hardLevel
                // + "---失败----");
                try {
                    Client.senTCP("GetLoginName");
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                String history = "***---" + Client.reply
                        + "---" + NumCardsUtil.hardLevel
                        + "---失败---***";
                try {
                    Client.senTCP(history);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
                lose = true;
                MenuNet.button6.setBackground(new ColorUIResource(238, 238, 238));
                MenuNet.button1.setBackground(Color.GRAY);
                MenuNet.button2.setBackground(Color.GRAY);
                MenuNet.button3.setBackground(Color.GRAY);
                MenuNet.button4.setBackground(Color.GRAY);
                MenuNet.button5.setBackground(Color.GRAY);

                // this.setEnabled(false);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}