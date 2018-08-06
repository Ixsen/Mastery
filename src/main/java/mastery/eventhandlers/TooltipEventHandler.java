package mastery.eventhandlers;

import java.util.List;

import mastery.util.ItemTagUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TooltipEventHandler {

    @SubscribeEvent
    public void customTooltip(ItemTooltipEvent tooltipEvent) {
	// Show only for the client
	if (tooltipEvent.getEntityPlayer() != null && tooltipEvent.getEntityPlayer().getEntityWorld().isRemote) {
	    ItemStack item = tooltipEvent.getItemStack();
	    List<String> list = ItemTagUtils.getAllTooltipTags(item);
	    for (String string : list) {
		tooltipEvent.getToolTip().add(ItemTagUtils.getTagString(item, string));
	    }
	}
    }
}
