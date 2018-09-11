package masteryUI.layout;

/**
 * Contains the layout information for a specific ui element in a vertical layout.
 *
 * @author Subaro
 */
public class VerticalData extends LayoutData {
    public static VerticalData DEFAULT = new VerticalData();

    private float paddingTop = 0;
    private float paddingBot = 0;

    public VerticalData(float paddingLeft, float paddingRight) {
        this.paddingTop = paddingLeft;
        this.paddingBot = paddingRight;
    }

    public VerticalData() {
        this.paddingTop = 0;
        this.paddingBot = 0;
    }

    public float getPaddingTop() {
        return this.paddingTop;
    }

    public float getPaddingBot() {
        return this.paddingBot;
    }
}
