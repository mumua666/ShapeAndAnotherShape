import javax.swing.JLabel;

/**
 * @ClassName: CardNode
 * @Description 卡片
 * @author Linux_Mumu
 */
public class CardNode extends JLabel {

    /** 区域类型 */
    private int type;
    /** 堆放级别 */
    private int level;
    /** 横坐标索引 */
    private int xIndex;
    /** 纵坐标索引 */
    private int yIndex;

    public CardNode(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public CardNode() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getxIndex() {
        return xIndex;
    }

    public void setxIndex(int xIndex) {
        this.xIndex = xIndex;
    }

    public int getyIndex() {
        return yIndex;
    }

    public void setyIndex(int yIndex) {
        this.yIndex = yIndex;
    }
}