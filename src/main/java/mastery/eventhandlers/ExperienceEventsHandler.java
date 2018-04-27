package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import mastery.ui.LevelOverlayUi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) { // TODO MINING and FARMING
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            IMastery mastery = breakEvent.getPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            mastery.getMasteries().get(MASTERY_SPEC.MINING).increaseExperience();
            EntityPlayerMP player = (EntityPlayerMP) breakEvent.getPlayer();
            sendExpToPlayer(mastery, player);
            LevelOverlayUi.currentMastery = MASTERY_SPEC.MINING;
        }
    }

//    @SubscribeEvent
//    public void attack(AttackEntityEvent attackEvent) { // TODO COMBAT
//        if (!attackEvent.getEntityPlayer().getEntityWorld().isRemote) {
//            IMastery mastery = attackEvent.getEntityPlayer().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
//            mastery.getMasteries().get(MASTERY_SPEC.COMBAT).increaseExperience();
//            sendExpToPlayer(mastery, (EntityPlayerMP) attackEvent.getEntityPlayer());
//            LevelOverlayUi.currentMastery = MASTERY_SPEC.COMBAT;
//        }
//    }

    @SubscribeEvent
    public void killEntity(LivingDeathEvent deathEvent) { //TODO COMBAT
        if (!deathEvent.getEntity().getEntityWorld().isRemote && deathEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayerMP player = (EntityPlayerMP) deathEvent.getSource().getTrueSource();
            IMastery mastery = player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            mastery.getMasteries().get(MASTERY_SPEC.COMBAT).increaseExperience(Math.round(deathEvent.getEntityLiving().getMaxHealth()));
            sendExpToPlayer(mastery, player);
            LevelOverlayUi.currentMastery = MASTERY_SPEC.COMBAT;
        }
    }

    @SubscribeEvent
    public void getHit(LivingHurtEvent livingHurtEvent) { // TODO COMBAT (hit, getting hit), SURVIVAL (falling damage, drowning, lava, hunger etc)
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            IMastery mastery = livingHurtEvent.getSource().getTrueSource().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            if (livingHurtEvent.getAmount() >= 1.0) {
                mastery.getMasteries().get(MASTERY_SPEC.COMBAT).increaseExperience();
            }
            sendExpToPlayer(mastery, (EntityPlayerMP) livingHurtEvent.getSource().getTrueSource());
            LevelOverlayUi.currentMastery = MASTERY_SPEC.COMBAT;
        }
        if (livingHurtEvent.getEntity() instanceof EntityPlayer) {
            IMastery mastery = livingHurtEvent.getEntity().getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
            if (livingHurtEvent.getAmount() >= 1.0) {
                mastery.getMasteries().get(MASTERY_SPEC.COMBAT).increaseExperience();
            }
            sendExpToPlayer(mastery, (EntityPlayerMP) livingHurtEvent.getEntity());
            LevelOverlayUi.currentMastery = MASTERY_SPEC.COMBAT;
        }
    }


    @SubscribeEvent
    public void brewedPotion(PlayerBrewedPotionEvent potionEvent) { // TODO ALCHEMY only limited exp for brewing!
        LevelOverlayUi.currentMastery = MASTERY_SPEC.ALCHEMY;
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

        if (!itemCraftedEvent.player.getEntityWorld().isRemote) {

            IMastery mastery = itemCraftedEvent.player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);

            mastery.getMasteries().get(MASTERY_SPEC.CRAFTING).increaseExperience();
            EntityPlayerMP player = (EntityPlayerMP) itemCraftedEvent.player;
            MasteryMessage message = new MasteryMessage(mastery.toIntArray());
            PacketHandler.INSTANCE.sendTo(message, player);
            LevelOverlayUi.currentMastery = MASTERY_SPEC.CRAFTING;

        }
    }

    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent jumpEvent) { // TODO ATHLETICS

    }

    private void sendExpToPlayer(IMastery mastery, EntityPlayerMP player) {
        MasteryMessage message = new MasteryMessage(mastery.toIntArray());
        PacketHandler.INSTANCE.sendTo(message, player);
    }

    /* TODO
        TNT MINING
    */
}
