package masteryUI.layout;

/**
 * Contains the layout information for a specific ui element in a horizontal layout.
 *
 * @author Subaro
 */
public class HorizontalData extends LayoutData {
    public static HorizontalData DEFAULT = new HorizontalData(HorizontalAlignment.LEFT);

    private HorizontalAlignment alignment;

    public HorizontalData(HorizontalAlignment alignment) {
        this.alignment = alignment;
    }

    public HorizontalAlignment getAlignment() {
        return this.alignment;
    }
}
