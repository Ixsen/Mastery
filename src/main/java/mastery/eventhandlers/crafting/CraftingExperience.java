package mastery.eventhandlers.crafting;

import mastery.capability.player.skillclasses.CraftingMastery;
import mastery.common.util.MasteryUtils;
import mastery.common.util.NetworkUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CraftingExperience {

    @SubscribeEvent
    public void craftItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent itemCraftedEvent) {
        if (!itemCraftedEvent.player.getEntityWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) itemCraftedEvent.player;
            CraftingMastery craftingMastery = MasteryUtils.getCraftingMastery(player);
            craftingMastery.increaseExperience();
            NetworkUtils.sendExpToPlayer(craftingMastery, player);
        }
    }
}
