package mastery.resource;

import mastery.MasteryMod;
import mastery.capability.skillclasses.MasterySpec;
import net.minecraft.util.ResourceLocation;

public class MasteryImageLoader {

    private static final ResourceLocation alchemy = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/alchemy.png");
    private static final ResourceLocation athletics = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/athletics.png");
    private static final ResourceLocation combat = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/combat.png");
    private static final ResourceLocation crafting = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/crafting.png");
    private static final ResourceLocation farming = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/farming.png");
    private static final ResourceLocation husbandry = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/husbandry.png");
    private static final ResourceLocation mining = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/mining.png");
    private static final ResourceLocation survival = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/survival.png");
    private static final ResourceLocation trading = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/survival.png");
    private static final ResourceLocation sneaking = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/survival.png");
    private static final ResourceLocation scavenging = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/survival.png");
    private static final ResourceLocation fishing = new ResourceLocation(MasteryMod.modid,
            "textures/gui/masteries/survival.png");

    /**
     * Returns the resource location for the mastery image
     * 
     * @param mastery
     *            The requested mastery image
     * @return ResourceLocation pointing to the image for the mastery.
     */
    public static ResourceLocation getImage(MasterySpec mastery) {
        switch (mastery) {
        case ALCHEMY:
            return alchemy;
        case ATHLETICS:
            return athletics;
        case COMBAT:
            return combat;
        case CRAFTING:
            return crafting;
        case FARMING:
            return farming;
        case HUSBANDRY:
            return husbandry;
        case MINING:
            return mining;
        case TRADING:
            return trading;
        case SNEAKING:
            return sneaking;
        case SCAVENGING:
            return scavenging;
        case FISHING:
            return fishing;
        default:
            return survival;
        }
    }
}
