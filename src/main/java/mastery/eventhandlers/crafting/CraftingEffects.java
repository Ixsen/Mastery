package mastery.eventhandlers.crafting;

import java.util.Random;

import mastery.capability.skillclasses.CraftingMastery;
import mastery.util.MasteryUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CraftingEffects {
    @SubscribeEvent
    public void onItemCrafted(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent event) {
        CraftingMastery craftingMastery = (MasteryUtils.getCraftingMastery(event.player));
        Random random = new Random();
        event.crafting.setCount(event.crafting.getCount() + (random.nextInt(100) + craftingMastery.getLevel()) / 100);
    }
}
