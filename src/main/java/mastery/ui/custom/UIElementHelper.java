package mastery.ui.custom;

import org.lwjgl.util.ReadableColor;

import mastery.resource.MasteryImageLoader;
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
    public static UIImage createBackground(int width, int height) {
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
            creditsSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_DEACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_DEACTIVE_UV.getY());
            creditsSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_ACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_ACTIVE_UV.getY());
            creditsSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
            creditsSlot.setSize(size, size);
            return creditsSlot;
        case LORE:
            UISlot infoSlot = new UISlot(false, slotGroup);
            infoSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            infoSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            infoSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_DEACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_DEACTIVE_UV.getY());
            infoSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_ACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_ACTIVE_UV.getY());
            infoSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
            infoSlot.setSize(size, size);
            return infoSlot;
        case SETTINGS:
            UISlot settingsSlot = new UISlot(false, slotGroup);
            settingsSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            settingsSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            settingsSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_DEACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_DEACTIVE_UV.getY());
            settingsSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_ACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_ACTIVE_UV.getY());
            settingsSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
            settingsSlot.setSize(size, size);
            return settingsSlot;
        default:
        case SKILL_TREE:
            UISlot skillTreeSlot = new UISlot(false, slotGroup);
            skillTreeSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
            skillTreeSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                    MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
            skillTreeSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_DEACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_DEACTIVE_UV.getY());
            skillTreeSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_ACTIVE_UV.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_ACTIVE_UV.getY());
            skillTreeSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                    MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
            skillTreeSlot.setSize(size, size);
            return skillTreeSlot;
        }
    }

}
