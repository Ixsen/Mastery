package de.ixsen.minecraft.uilib.layout;

/**
 * Contains the layout information for a specific ui element in a horizontal layout.
 *
 * @author Subaro
 */
public class HorizontalData extends LayoutData {
    public static HorizontalData DEFAULT = new HorizontalData();

    private float paddingLeft = 0;
    private float paddingRight = 0;

    public HorizontalData(float paddingLeft, float paddingRight) {
        this.paddingLeft = paddingLeft;
        this.paddingRight = paddingRight;
    }

    public HorizontalData() {
        this.paddingLeft = 0;
        this.paddingRight = 0;
    }

    public float getPaddingLeft() {
        return this.paddingLeft;
    }

    public float getPaddingRight() {
        return this.paddingRight;
    }
}
