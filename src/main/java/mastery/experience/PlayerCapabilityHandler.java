package mastery.experience;

import mastery.MasteryMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Granis on 16/03/2018.
 */
public class PlayerCapabilityHandler {

    public static final ResourceLocation MASTERY_CAPABILITY = new ResourceLocation(MasteryMod.modId, "mastery");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(MASTERY_CAPABILITY, new MasteryProvider());

        }
    }
}
