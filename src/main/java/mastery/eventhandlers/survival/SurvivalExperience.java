package mastery.eventhandlers.survival;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import static mastery.eventhandlers.ExperienceDictionary.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SurvivalExperience extends AbstractExperienceHandler {
	
	public SurvivalExperience() {
		this.spec = MasterySpec.SURVIVAL;
	}

	@SubscribeEvent
	void deathEvent(LivingDeathEvent deathEvent) {
		if (deathEvent.getEntity() instanceof EntityPlayer && !deathEvent.getEntity().getEntityWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) deathEvent.getEntity();
			this.addExperience(player, SURVIVAL_DEATH);
		}
	}
	
	@SubscribeEvent
	void eatItem(LivingEntityUseItemEvent.Finish event) {
		if (event.getEntity() instanceof EntityPlayer && !event.getEntity().getEntityWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			
			ItemStack itemStack = event.getItem();
			if(itemStack.getItem() instanceof ItemFood) {
				ItemFood itemFood = (ItemFood) itemStack.getItem();
				player.sendMessage(new TextComponentString("ESSEN:" + itemFood.getSaturationModifier(itemStack) + " and exp:" + (int)(SURVIVAL_EATING.getValue() * itemFood.getSaturationModifier(itemStack))));
				this.addExperience(player, (int)(SURVIVAL_EATING.getValue() * itemFood.getSaturationModifier(itemStack)));
			}
		}
	}
	
	@SubscribeEvent
	void hurtEvent(LivingDamageEvent event) {
		if (event.getEntity() instanceof EntityPlayer && !event.getEntity().getEntityWorld().isRemote) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			player.sendMessage(new TextComponentString("DMG:" + event.getSource().damageType));
			
			this.addExperience(player, SurvivalUtils.getExperienceFromDamageSource(event.getSource()));
		}
	}
	
	
}
