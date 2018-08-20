package mastery.resource;

import org.lwjgl.util.Point;

import mastery.MasteryMod;
import mastery.capability.skillclasses.MasterySpec;
import net.minecraft.util.ResourceLocation;

public class MasteryImageLoader {

    public static final ResourceLocation masteryClassIconPlain = new ResourceLocation(MasteryMod.modid,
            "textures/gui/classicons/masteriesplain.png");

    public static final ResourceLocation masteryClassIconBadge = new ResourceLocation(MasteryMod.modid,
            "textures/gui/classicons/masteriesbadge.png");

    public static final int MASTERY_CLASS_ICON_SIZE = 64;
    public static final int MASTERY_CLASS_ATLAS_SIZE = 256;

    public static Point getMasteryUV(MasterySpec spec) {
        switch (spec) {
        case ALCHEMY:
            return new Point(0, 0);
        case ATHLETICS:
            return new Point(64, 0);
        case COMBAT:
            return new Point(128, 0);
        case CRAFTING:
            return new Point(192, 0);
        case FARMING:
            return new Point(0, 64);
        case FISHING:
            return new Point(64, 64);
        case HUSBANDRY:
            return new Point(128, 64);
        case MINING:
            return new Point(192, 64);
        case SCAVENGING:
            return new Point(0, 128);
        case SNEAKING:
            return new Point(64, 128);
        case SURVIVAL:
            return new Point(128, 128);
        case TRADING:
            return new Point(192, 128);
        default:
            return new Point(0, 0);
        }
    }
}
