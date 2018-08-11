package mastery.eventhandlers.athletics;

import static mastery.eventsystem.MasteryEventType.PLAYER_LEVEL_UP;

import mastery.MasteryMod;
import mastery.capability.skillclasses.MasterySpec;
import mastery.eventsystem.MasteryEvent;
import mastery.util.MasteryUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class AthleticsEffects {

    public AthleticsEffects() {
        MasteryMod.getEventHandler().addListener(this::setSpeedModifier);
    }

    private void setSpeedModifier(MasteryEvent masteryEvent) {
        if (masteryEvent.getType() == PLAYER_LEVEL_UP && masteryEvent.getSource() == MasterySpec.ATHLETICS) {
            MasteryUtils.getAthleticsMastery(Minecraft.getMinecraft().player)
                    .reApplyAttributeSpeedModifier((EntityPlayer) masteryEvent.getTarget());
        }
    }
}
