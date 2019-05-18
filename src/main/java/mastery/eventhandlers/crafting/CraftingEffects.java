package mastery.eventhandlers.crafting;

import mastery.capability.player.skillclasses.CraftingMastery;
import mastery.common.util.MasteryUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;

public class CraftingEffects {

    @SubscribeEvent
    public void onItemCrafted(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
        if (event.player != null && !event.player.getEntityWorld().isRemote) {
            CraftingMastery craftingMastery = MasteryUtils.getCraftingMastery(event.player);
            if (craftingMastery.isDoubleCraft()) {
                // sends the item to the inventory. If all slots are full the items will be dropped before the player into the world ;)
                ItemHandlerHelper.giveItemToPlayer(event.player, new ItemStack(event.crafting.getItem()));
            }
        }
    }
}
