package mastery.resource;

import org.lwjgl.util.Point;

import mastery.capability.skillclasses.MasterySpec;
import mastery.ui.custom.elements.impl.UISlot;
import mastery.ui.custom.elements.impl.UISlotGroup;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UITabUtils {

    private static final ResourceLocation TABS = new ResourceLocation("textures/gui/advancements/tabs.png");
    private static final int TABS_TEXTURE_SIZE = 256;

    private static Point TAB_DOWN_LEFT_DEACTIVE = new Point(84, 0);
    private static Point TAB_DOWN_LEFT_ACTIVE = new Point(84, 32);
    private static Point TAB_DOWN_MID_DEACTIVE = new Point(112, 0);
    private static Point TAB_DOWN_MID_ACTIVE = new Point(112, 32);
    private static Point TAB_DOWN_RIGHT_DEACTIVE = new Point(140, 0);
    private static Point TAB_DOWN_RIGHT_ACTIVE = new Point(140, 32);
    private static Point TAB_UP_LEFT_DEACTIVE = new Point(0, 0);
    private static Point TAB_UP_LEFT_ACTIVE = new Point(0, 32);
    private static Point TAB_UP_MID_DEACTIVE = new Point(28, 0);
    private static Point TAB_UP_MID_ACTIVE = new Point(28, 32);
    private static Point TAB_UP_RIGHT_DEACTIVE = new Point(56, 0);
    private static Point TAB_UP_RIGHT_ACTIVE = new Point(56, 32);
    public static Point TAB_SIZE = new Point(28, 32);

    /**
     * Create a UI slot that can be toogled. Fits to the left.
     *
     * @return new UISlot
     */
    public static UISlot createMasterySlotsLeft(
            UISlotGroup slotGroup, MasterySpec slot, Point size, boolean isAboveGui) {
        UISlot creditsSlot = new UISlot(false, slotGroup);
        creditsSlot.setTexture(TABS);
        creditsSlot.setTextureSize(TABS_TEXTURE_SIZE, TABS_TEXTURE_SIZE);
        if (isAboveGui) {
            creditsSlot.setUVDeactivated(TAB_UP_LEFT_DEACTIVE);
            creditsSlot.setUVActivated(TAB_UP_LEFT_ACTIVE);
        } else {
            creditsSlot.setUVDeactivated(TAB_DOWN_LEFT_DEACTIVE);
            creditsSlot.setUVActivated(TAB_DOWN_LEFT_ACTIVE);
        }
        creditsSlot.setUVSize(TAB_SIZE);
        creditsSlot.setSize(size.getX(), size.getY());
        return creditsSlot;
    }

    /**
     * Create a UI slot that can be toogled. Fits to be in the middle.
     *
     * @return new UISlot
     */
    public static UISlot createMasterySlotsMiddle(
            UISlotGroup slotGroup, MasterySpec slot, Point size, boolean isAboveGui) {
        UISlot creditsSlot = new UISlot(false, slotGroup);
        creditsSlot.setTexture(TABS);
        creditsSlot.setTextureSize(TABS_TEXTURE_SIZE, TABS_TEXTURE_SIZE);
        if (isAboveGui) {
            creditsSlot.setUVDeactivated(TAB_UP_MID_DEACTIVE);
            creditsSlot.setUVActivated(TAB_UP_MID_ACTIVE);
        } else {
            creditsSlot.setUVDeactivated(TAB_DOWN_MID_DEACTIVE);
            creditsSlot.setUVActivated(TAB_DOWN_MID_ACTIVE);
        }
        creditsSlot.setUVSize(TAB_SIZE);
        creditsSlot.setSize(size.getX(), size.getY());
        return creditsSlot;
    }

    /**
     * Create a UI slot that can be toogled. Fits to be on the right.
     *
     * @return new UISlot
     */
    public static UISlot createMasterySlotsRight(
            UISlotGroup slotGroup, MasterySpec slot, Point size, boolean isAboveGui) {
        UISlot creditsSlot = new UISlot(false, slotGroup);
        creditsSlot.setTexture(TABS);
        creditsSlot.setTextureSize(TABS_TEXTURE_SIZE, TABS_TEXTURE_SIZE);
        if (isAboveGui) {
            creditsSlot.setUVDeactivated(TAB_UP_RIGHT_DEACTIVE);
            creditsSlot.setUVActivated(TAB_UP_RIGHT_ACTIVE);
        } else {
            creditsSlot.setUVDeactivated(TAB_DOWN_RIGHT_DEACTIVE);
            creditsSlot.setUVActivated(TAB_DOWN_RIGHT_ACTIVE);
        }
        creditsSlot.setUVSize(TAB_SIZE);
        creditsSlot.setSize(size.getX(), size.getY());
        return creditsSlot;
    }
}
