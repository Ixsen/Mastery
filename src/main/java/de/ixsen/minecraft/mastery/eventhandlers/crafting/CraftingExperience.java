package de.ixsen.minecraft.mastery.eventhandlers.crafting;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.CraftingMastery;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.common.util.MasteryUtils;
import de.ixsen.minecraft.mastery.common.util.NetworkUtils;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@SubscribeToCommonEventBus
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
