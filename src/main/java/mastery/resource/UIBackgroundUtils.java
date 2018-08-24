package mastery.resource;

import org.lwjgl.util.Point;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.utils.Colors;
import mastery.MasteryMod;
import mastery.capability.skillclasses.MasterySpec;
import mastery.ui.custom.UIElementHelper;
import mastery.ui.custom.elements.impl.UIImage;
import mastery.ui.custom.elements.impl.UILabel;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.layouts.FreeformLayout;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIBackgroundUtils {

    // Repeatable Background images
    public static final ResourceLocation REPEATABLE_ADVENTURE = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/adventure.png");
    public static final ResourceLocation REPEATABLE_COMMAND = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/command.png");
    public static final ResourceLocation REPEATABLE_CRAFTTABLE = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/crafttable.png");
    public static final ResourceLocation REPEATABLE_DIAMOND = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/diamond.png");
    public static final ResourceLocation REPEATABLE_DISPENSER = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/dispenser.png");
    public static final ResourceLocation REPEATABLE_END = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/end.png");
    public static final ResourceLocation REPEATABLE_HUSBANDRY = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/husbandry.png");
    public static final ResourceLocation REPEATABLE_NETHER = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/nether.png");
    public static final ResourceLocation REPEATABLE_NOTEBLOCK = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/noteblock.png");
    public static final ResourceLocation REPEATABLE_ROCK = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/rock.png");
    public static final ResourceLocation REPEATABLE_STONE = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/stone.png");
    public static final ResourceLocation REPEATABLE_TRADING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/trading.png");
    public static final ResourceLocation REPEATABLE_WOOD = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/wood.png");

    public static ResourceLocation getMasteryRepeatableBackground(MasterySpec spec) {
        switch (spec) {
        case ALCHEMY:
            return REPEATABLE_DISPENSER;
        case ATHLETICS:
            return REPEATABLE_STONE;
        case COMBAT:
            return REPEATABLE_NETHER;
        case CRAFTING:
            return REPEATABLE_CRAFTTABLE;
        case FARMING:
            return REPEATABLE_WOOD;
        case FISHING:
            return REPEATABLE_NOTEBLOCK;
        case HUSBANDRY:
            return REPEATABLE_HUSBANDRY;
        case MINING:
            return REPEATABLE_DIAMOND;
        case SCAVENGING:
            return REPEATABLE_ROCK;
        case SNEAKING:
            return REPEATABLE_ADVENTURE;
        case SURVIVAL:
            return REPEATABLE_END;
        case TRADING:
            return REPEATABLE_TRADING;
        default:
            return REPEATABLE_COMMAND;
        }
    }

    // Background container images
    public static final ResourceLocation ADVANCEMENTS_WINDOW = new ResourceLocation(
            "textures/gui/advancements/window.png");
    public static final int ADVANCEMENTS_WINDOW_TEXTURE_SIZE = 256;
    public static final Point ADVANCEMENTS_WINDOW_SIZE = new Point(252, 140);

    public static UIImage getAdvancementBackground(Point size) {
        UIImage background = new UIImage();
        background.setTexture(ADVANCEMENTS_WINDOW, ADVANCEMENTS_WINDOW_TEXTURE_SIZE, ADVANCEMENTS_WINDOW_TEXTURE_SIZE);
        background.setUV(0, 0);
        background.setUVSize(ADVANCEMENTS_WINDOW_SIZE.getX(), ADVANCEMENTS_WINDOW_SIZE.getY());
        background.setSize(size.getX(), size.getY());
        return background;
    }

    public static GuiPanel getAdvancementBackgroundWithTitle(Point size, String title) {
        GuiPanel panel = new GuiPanel();
        panel.setLayout(new FreeformLayout());

        UIImage background = new UIImage();
        background.setTexture(ADVANCEMENTS_WINDOW, ADVANCEMENTS_WINDOW_TEXTURE_SIZE, ADVANCEMENTS_WINDOW_TEXTURE_SIZE);
        background.setUV(0, 0);
        background.setUVSize(ADVANCEMENTS_WINDOW_SIZE.getX(), ADVANCEMENTS_WINDOW_SIZE.getY());
        background.setSize(size.getX(), size.getY());
        panel.addElements(new FreeformLayout.Data(0, 0), background);

        UILabel titleLabel = UIElementHelper.createUILabel(UILabelAlignment.TOP_LEFT, title, size.getX(), 9,
                Colors.BLACK);
        panel.addElements(new FreeformLayout.Data(8, 6), titleLabel);

        return panel;
    }

}
