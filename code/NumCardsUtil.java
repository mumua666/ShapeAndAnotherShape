import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Image;

/**
 * @ClassName: NumCardsUtil
 * @Description 工具类
 * @author Linux_Mumu
 */

public class NumCardsUtil {

    /** 随机卡片数组 */
    public static int[] cardsCount = new int[9];
    /** 所有卡片数字存储 */
    public static List<Integer> cardsList = new ArrayList<>();
    /** 所有卡片图标存储 */
    public static List<ImageIcon> cardsIconList = new ArrayList<>();
    public static List<String> pathList = new ArrayList<>();
    public static int index = 0, N = 3;
    /** 卡片name存储 */
    public static List<String> centerCardData = new ArrayList<>();

    /** 中间卡片 */
    public static List<CardNode> centerCards = new ArrayList<>();
    /** 左边卡片 */
    public static List<CardNode> leftCards1 = new ArrayList<>();
    public static List<CardNode> leftCards2 = new ArrayList<>();
    public static List<CardNode> leftCards3 = new ArrayList<>();
    /** 右边卡片 */
    public static List<CardNode> rightCards1 = new ArrayList<>();
    public static List<CardNode> rightCards2 = new ArrayList<>();
    public static List<CardNode> rightCards3 = new ArrayList<>();

    /** 样式布局 */
    public static boolean full = true;

    /** 样式难度 */
    public static String hardLevel = "Hard";

    /**
     * 初始化卡片数字
     */
    public static void initCards() {
        clearCards();

        // 初始化数字卡片
        Random r = new Random();
        int num;
        for (int i = 0; i < 270; i++) {
            num = r.nextInt(8) + 1;
            cardsCount[num]++;
            cardsList.add(num);
        }
        initCardsIcon();
        initCardList(centerCards, 55 + 36 + 49);
        initCardList(leftCards1, 19);
        initCardList(leftCards2, 23);
        initCardList(leftCards3, 23);
        initCardList(rightCards1, 19);
        initCardList(rightCards2, 23);
        initCardList(rightCards3, 23);
    }

    /***
     * 初始化卡片图标
     */
    public static void initCardsIcon() {
        index = index % 3 + 1;
        String path = "static/icon0" + index + "/0";
        String postfix = ".png";
        if (index == 1)
            postfix = ".jpg";
        for (int i = 1; i <= 9; i++) {
            ImageIcon icon = new ImageIcon(path + i + postfix);
            Image temp = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
            icon = new ImageIcon(temp);
            cardsIconList.add(icon);
        }
    }

    /**
     * 改变牌面布局样式
     */
    public static void changeLayout() {
        if (full) {
            int count = 0;
            for (int i = 1; i <= 7; i++) {
                for (int j = 1; j <= i; j++) {
                    for (int k = 1; k <= i; k++) {
                        if (j != 1 && j != i) {
                            centerCards.get(count + (j - 1) * i + k - 1).setVisible(false);
                        }
                    }
                }
                count += i * i;
            }
            full = false;
        } else {
            int count = 0;
            for (int i = 1; i <= 7; i++) {
                for (int j = 1; j <= i; j++) {
                    for (int k = 1; k <= i; k++) {
                        if (j != 1 && j != i) {
                            centerCards.get(count + (j - 1) * i + k - 1).setVisible(true);
                        }
                    }
                }
                count += i * i;
            }
            full = true;
        }
    }

    /**
     * 改变牌局难度
     */

    public static void changeHardLevel() {
        if (hardLevel.equals("Hard"))
            hardLevel = "Medium";
        else if (hardLevel.equals("Medium"))
            hardLevel = "Simple";
        else if (hardLevel.equals("Simple"))
            hardLevel = "Hard";
        switch (hardLevel) {
            case "Hard": {
                changeCardText(8);
                MenuNet.button0.setText("难度：高");
            }
                break;
            case "Medium": {
                changeCardText(6);
                MenuNet.button0.setText("难度：中");
            }
                break;
            case "Simple": {
                changeCardText(4);
                MenuNet.button0.setText("难度：低");
            }
                break;

            default:
                break;
        }
    }

    /***
     * 重新初始化卡片图标
     */
    public static void reInitCardsIcon() {
        cardsIconList.clear();
        initCardsIcon();
        for (CardNode node : centerCards) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards1) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards2) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards3) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards1) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards2) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards3) {
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
    }

    /***
     * 随机打乱
     */
    public static void shuffle() {
        for (CardNode node : centerCards) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards1) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards2) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards3) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards1) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards2) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards3) {
            node.setText(String.valueOf(Integer.valueOf(node.getText()) % 8 + 1));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        int saveCount = PlayGamePanel.nodeCount[8];
        for (int i = 8; i > 0; i--) {
            PlayGamePanel.nodeCount[i] = PlayGamePanel.nodeCount[i - 1];
        }
        PlayGamePanel.nodeCount[0] = saveCount;
    }

    public static void changeCardText(int num) {
        Random r = new Random();
        for (CardNode node : centerCards) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards1) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards2) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : leftCards3) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards1) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards2) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }
        for (CardNode node : rightCards3) {
            node.setText(String.valueOf(r.nextInt(num)));
            node.setIcon(cardsIconList.get(Integer.valueOf(node.getText())));
        }

    }

    /**
     * 初始化卡片对象
     * 
     * @param list
     * @param cardCount
     */
    public static void initCardList(List<CardNode> list, int cardCount) {
        Random r = new Random();
        int index;
        CardNode numLabel;
        for (int i = 0; i < cardCount; i++) {
            index = r.nextInt(cardsList.size());
            numLabel = new CardNode(String.valueOf(cardsList.get(index)), JLabel.CENTER);
            ImageIcon icon = cardsIconList.get(cardsList.get(index));
            numLabel.setIcon(icon);
            list.add(numLabel);
            cardsList.remove(index);
        }
    }

    /**
     * 根据层级个数生成图片，固定为7，可拓展
     * 
     * @param level
     */
    public static void initcenterCardData(int level) {

        for (int n = 1; n < level; n++) {
            for (int i = 0, x = 0, y = -1; i < n * n; i++) {
                if (i % n == 0) {
                    x = 0;
                    y++;
                }
                centerCardData.add("center-" + n + "-" + x + "-" + y);
                x++;
            }
        }
    }

    /**
     * 清空数据
     */
    public static void clearCards() {
        centerCardData.clear();
        centerCards.clear();
        leftCards1.clear();
        leftCards2.clear();
        leftCards3.clear();
        rightCards1.clear();
        rightCards2.clear();
        rightCards3.clear();
    }
}