package mastery.eventhandlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import mastery.ui.LevelOverlayUi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class ExperienceEventsHandler {
	
	/** Contains all types of items that give no exp.*/
	private List<ItemStack> useLessPotions = new ArrayList<>();
	/** Contains all brewed potions.*/
	private Map<PotionType, Integer> brewedPotions = new HashMap<>();
	
	public ExperienceEventsHandler() {
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.MUNDANE));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.AWKWARD));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.THICK));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.WATER));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.SPLASH_POTION), PotionTypes.MUNDANE));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.AWKWARD));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.THICK));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.WATER));
		useLessPotions.add(PotionUtils.addPotionToItemStack(new ItemStack(Items.LINGERING_POTION), PotionTypes.MUNDANE));
		useLessPotions.add(new ItemStack(Items.GLASS_BOTTLE));
	}
	
    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) { // TODO MINING and FARMING
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            IMastery mastery = breakEvent.getPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            mastery.getMasteries().get(MASTERY_SPEC.MINING).increaseExperience();
            EntityPlayerMP player = (EntityPlayerMP) breakEvent.getPlayer();
            MasteryMessage message = new MasteryMessage(mastery.toIntArray());
            PacketHandler.INSTANCE.sendTo(message, player);
            LevelOverlayUi.currentMastery = MASTERY_SPEC.MINING;
        }
    }

    @SubscribeEvent
    public void attack(AttackEntityEvent attackEvent) { // TODO COMBAT

    }

    @SubscribeEvent
    public void getHit(LivingHurtEvent getHitEvent) { // TODO COMBAT (getting hit), SURVIVAL (falling damage, drowning, lava, hunger etc)
        if (getHitEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            getHitEvent.getSource().getTrueSource().sendMessage(new TextComponentString("You hit " + getHitEvent.getEntity().getName() + " for " + getHitEvent.getAmount()));
            LevelOverlayUi.currentMastery = MASTERY_SPEC.COMBAT;
        }
        if (getHitEvent.getEntity() instanceof EntityPlayer) {
            getHitEvent.getEntity().sendMessage(new TextComponentString("You got hit by " + getHitEvent.getSource()));
            LevelOverlayUi.currentMastery = MASTERY_SPEC.SURVIVAL;
        }
    }


    @SubscribeEvent
    public void brewedPotion(PlayerBrewedPotionEvent potionEvent) { // TODO ALCHEMY only limited exp for brewing!
        if (!potionEvent.getEntityPlayer().getEntityWorld().isRemote) {
        	ItemStack stack = potionEvent.getStack();
        	
        	//Check Glass Bottle
        	if(stack.getItem().equals(new ItemStack(Items.GLASS_BOTTLE).getItem())) {
        		//Useless => no exp
        		return;
        	} else if (stack.getItem().equals(new ItemStack(Items.POTIONITEM).getItem()) || stack.getItem().equals(new ItemStack(Items.SPLASH_POTION).getItem()) || stack.getItem().equals(new ItemStack(Items.LINGERING_POTION).getItem())) {
            	//Check Potion, Splash Potion, Lingering Potion
        		PotionType our = PotionUtils.getPotionFromItem(stack);
        		if(our.getEffects().isEmpty()) {
        			//Useless
        			return;
        		}
        		//Check if any quantity is available
        		int quantityAvailable = brewedPotions.get(our);
        		if(quantityAvailable > 0) {
        			brewedPotions.put(our, quantityAvailable-1);
        		} else {
        			return;
        		}
        	} else {
        		//Useless, everything except potions
        		return;
        	}
        	
        	IMastery mastery = potionEvent.getEntityPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            mastery.getMasteries().get(MASTERY_SPEC.ALCHEMY).increaseExperience();
            EntityPlayerMP player = (EntityPlayerMP) potionEvent.getEntityPlayer();
            MasteryMessage message = new MasteryMessage(mastery.toIntArray());
            PacketHandler.INSTANCE.sendTo(message, player);
            LevelOverlayUi.currentMastery = MASTERY_SPEC.ALCHEMY;
		}
    }
    
    @SubscribeEvent
    public void brewedPotion(PotionBrewEvent.Post potionEvent) { // TODO ALCHEMY only limited exp for brewing!
    	ItemStack first = potionEvent.getItem(0);
    	ItemStack second = potionEvent.getItem(1);
    	ItemStack third = potionEvent.getItem(2);

    	//Skip if air
    	if(!first.isEmpty()) {
    		PotionType our = PotionUtils.getPotionFromItem(first);
    		if(!our.getEffects().isEmpty()) {
    			//Increase Stack size by 1
    			if(brewedPotions.containsKey(our)) {
    				brewedPotions.put(our, brewedPotions.get(our)+1);
    			} else {
    				brewedPotions.put(our, 1);
    			}
    		}
    	}
    	//Skip if air
    	if(!second.isEmpty()) {
    		PotionType our = PotionUtils.getPotionFromItem(first);
    		if(!our.getEffects().isEmpty()) {
    			//Increase Stack size by 1
    			if(brewedPotions.containsKey(our)) {
    				brewedPotions.put(our, brewedPotions.get(our)+1);
    			} else {
    				brewedPotions.put(our, 1);
    			}
    		}
    	}
    	//Skip if air
    	if(!third.isEmpty()) {
    		PotionType our = PotionUtils.getPotionFromItem(first);
    		if(!our.getEffects().isEmpty()) {
    			//Increase Stack size by 1
    			if(brewedPotions.containsKey(our)) {
    				brewedPotions.put(our, brewedPotions.get(our)+1);
    			} else {
    				brewedPotions.put(our, 1);
    			}
    		}
    	} 
    }

    @SubscribeEvent
    public void useItem(LivingEntityUseItemEvent.Finish useItemEvent) { // TODO ALCHEMY, SURVIVAL

    }

    @SubscribeEvent
    public void harvestCrop(PlayerEvent.HarvestCheck harvestCheck) {//TODO FARMING

    }

    @SubscribeEvent
    public void boneEvent(BonemealEvent bonemealEvent) { // TODO FARMING

    }

    @SubscribeEvent
    public void hoeing(UseHoeEvent useHoeEvent) { // TODO FARMING

    }

    @SubscribeEvent
    public void animalTame(AnimalTameEvent tameEvent) { //TODO HUSBANDRY
        LevelOverlayUi.currentMastery = MASTERY_SPEC.HUSBANDRY;
    }

    @SubscribeEvent
    public void feed(PlayerInteractEvent.EntityInteractSpecific interactEvent) {//TODO HUSBANDRY

    }

    @SubscribeEvent
    public void craftItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent itemCraftedEvent) {//TODO CRAFTING
        LevelOverlayUi.currentMastery = MASTERY_SPEC.CRAFTING;
    }

    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent jumpEvent) { // TODO ATHLETICS

    }

    /* TODO
        TNT MINING
    */
}
