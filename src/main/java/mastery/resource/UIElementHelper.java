package mastery.resource;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.layout.VerticalLayout;
import mastery.capability.skillclasses.MasterySpec;
import mastery.ui.custom.elements.impl.UIImage;
import mastery.ui.custom.elements.impl.UILabel;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.elements.impl.UISlot;
import mastery.ui.custom.elements.impl.UISlot.UIMainSlotTypes;
import mastery.ui.custom.elements.impl.UISlotGroup;

/**
 * @author Subaro
 */
public class UIElementHelper {

    /**
     * Creates an image showing a default minecraft background.
     *
     * Original Texture Size: 512x333 keep that in mind for the height and with
     *
     * @return the background
     */
    public static UIImage getBackground(int width, int height) {
        UIImage background = new UIImage();
        background.setTexture(MasteryImageLoader.masteryOverviewBackground,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_TEXTURE_SIZE,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_TEXTURE_SIZE, 0, 0,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SIZE.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SIZE.getY());
        background.setSize(width, height);
        return background;
    }

    /**
     * Creates an image of an mastery class icon
     *
     * @return the image
     */
    public static UIImage getMasteryIcon(MasterySpec spec, int width, int height) {
        UIImage image = new UIImage();
        Point uv = MasteryImageLoader.getMasteryUV(spec);
        image.setTexture(MasteryImageLoader.masteryClassIconPlain,
                MasteryImageLoader.MASTERY_CLASS_ATLAS_SIZE,
                MasteryImageLoader.MASTERY_CLASS_ATLAS_SIZE);
        image.setUV(uv);
        image.setUVSize(MasteryImageLoader.MASTERY_CLASS_ICON_SIZE, MasteryImageLoader.MASTERY_CLASS_ICON_SIZE);
        image.setSize(width, height);
        return image;
    }

    /**
     * Creates a label with the given parameters.
     *
     * @return new label
     */
    public static UILabel createUILabel(
            UILabelAlignment alignment, String text, int width, int height,
            ReadableColor color) {
        UILabel title = new UILabel(alignment);
        title.setText(text);
        if (width != 0 && height != 0) {
            title.setSize(width, height);
        }
        title.setColor(color);
        return title;
    }

    /**
     * Create a menu gui slot that is toggable.
     *
     * @return new UISlot
     */
    public static UISlot createMenuSlots(UISlotGroup slotGroup, UIMainSlotTypes slot, int size) {
        switch (slot) {
        case AUTHOR:
            UISlot creditsSlot = new UISlot(false, slotGroup);
            creditsSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            creditsSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            creditsSlot.setUVDeactivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_DEACTIVE_UV);
            creditsSlot.setUVActivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_ACTIVE_UV);
            creditsSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE);
            creditsSlot.setSize(size, size);
            return creditsSlot;
        case LORE:
            UISlot infoSlot = new UISlot(false, slotGroup);
            infoSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            infoSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            infoSlot.setUVDeactivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_DEACTIVE_UV);
            infoSlot.setUVActivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_ACTIVE_UV);
            infoSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE);
            infoSlot.setSize(size, size);
            return infoSlot;
        case SETTINGS:
            UISlot settingsSlot = new UISlot(false, slotGroup);
            settingsSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            settingsSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            settingsSlot.setUVDeactivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_DEACTIVE_UV);
            settingsSlot.setUVActivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_ACTIVE_UV);
            settingsSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE);
            settingsSlot.setSize(size, size);
            return settingsSlot;
        default:
        case SKILL_TREE:
            UISlot skillTreeSlot = new UISlot(false, slotGroup);
            skillTreeSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            skillTreeSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            skillTreeSlot.setUVDeactivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_DEACTIVE_UV);
            skillTreeSlot.setUVActivated(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_ACTIVE_UV);
            skillTreeSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE);
            skillTreeSlot.setSize(size, size);
            return skillTreeSlot;
        }
    }

    /**
     * Create a UI slot that can be toogled. Fits to the left.
     *
     * @return new UISlot
     */
    public static UISlot createMasterySlotsLeft(
            UISlotGroup slotGroup, MasterySpec slot, Point size, boolean isAboveGui) {
        return UITabUtils.createMasterySlotsLeft(slotGroup, slot, size, isAboveGui);
    }

    /**
     * Create a UI slot that can be toogled. Fits to be in the middle.
     *
     * @return new UISlot
     */
    public static UISlot createMasterySlotsMiddle(
            UISlotGroup slotGroup, MasterySpec slot, Point size, boolean isAboveGui) {
        return UITabUtils.createMasterySlotsMiddle(slotGroup, slot, size, isAboveGui);
    }

    /**
     * Create a UI slot that can be toogled. Fits to be on the right.
     *
     * @return new UISlot
     */
    public static UISlot createMasterySlotsRight(
            UISlotGroup slotGroup, MasterySpec slot, Point size, boolean isAboveGui) {
        return UITabUtils.createMasterySlotsRight(slotGroup, slot, size, isAboveGui);
    }

    /**
     * Create a panel containing multiple lines of label that do not exceed the maxWidth.
     *
     * @return new panel
     */
    public static GuiPanel getMultiLabel(String text, int maxWidth) {
        GuiPanel panel = new GuiPanel();
        panel.setLayout(new VerticalLayout().setSpacing(3));

        String[] splittedText = text.split(" ");

        UILabel label = new UILabel();
        for (int i = 0; i < splittedText.length; i++) {
            String oldText = label.getText();

            label.setText(!label.getText().equals("") ? label.getText() + " " + splittedText[i] : splittedText[i]);

            if (label.calcMinSize().getWidth() >= maxWidth) {
                label.setText(oldText);
                panel.addElements(null, label);
                i--;
                label = new UILabel();
                continue;
            }
        }
        if (!label.getText().equals("")) {
            panel.addElements(null, label);
        }
        panel.setSize(panel.calcMinSize());

        return panel;
    }

}
