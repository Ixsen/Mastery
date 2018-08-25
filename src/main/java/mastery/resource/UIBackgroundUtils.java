package mastery.resource;

import org.lwjgl.util.Point;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.utils.Colors;
import mastery.MasteryMod;
import mastery.capability.skillclasses.MasterySpec;
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
    public static final ResourceLocation REPEATABLE_ALCHEMY = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/soul_sand.png");
    public static final ResourceLocation REPEATABLE_ATHLETICS = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/piston_top.png");
    public static final ResourceLocation REPEATABLE_COMBAT = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/iron_block.png");
    public static final ResourceLocation REPEATABLE_CRAFTING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/cactus.png");
    public static final ResourceLocation REPEATABLE_FARMING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/farmland.png");
    public static final ResourceLocation REPEATABLE_FISHING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/prismarine_bricks.png");
    public static final ResourceLocation REPEATABLE_HUSBANDRY = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/hay_block_side.png");
    public static final ResourceLocation REPEATABLE_MINING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/iron_ore.png");
    public static final ResourceLocation REPEATABLE_SCAVENGING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/mossy_stone_bricks.png");
    public static final ResourceLocation REPEATABLE_SNEAKING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/snow.png");
    public static final ResourceLocation REPEATABLE_SURVIVAL = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/magma.png");
    public static final ResourceLocation REPEATABLE_TRADING = new ResourceLocation(MasteryMod.modid,
            "textures/gui/backgrounds/emerald.png");

    public static ResourceLocation getMasteryRepeatableBackground(MasterySpec spec) {
        switch (spec) {
        case ALCHEMY:
            return REPEATABLE_ALCHEMY;
        case ATHLETICS:
            return REPEATABLE_ATHLETICS;
        case COMBAT:
            return REPEATABLE_COMBAT;
        case CRAFTING:
            return REPEATABLE_CRAFTING;
        case FARMING:
            return REPEATABLE_FARMING;
        case FISHING:
            return REPEATABLE_FISHING;
        case HUSBANDRY:
            return REPEATABLE_HUSBANDRY;
        case MINING:
            return REPEATABLE_MINING;
        case SCAVENGING:
            return REPEATABLE_SCAVENGING;
        case SNEAKING:
            return REPEATABLE_SNEAKING;
        case SURVIVAL:
            return REPEATABLE_SURVIVAL;
        case TRADING:
            return REPEATABLE_TRADING;
        default:
            return REPEATABLE_TRADING;
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
