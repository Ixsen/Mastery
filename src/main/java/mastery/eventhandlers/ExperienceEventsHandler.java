package mastery.eventhandlers;

import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.networking.MasteryMessage;
import mastery.networking.PacketHandler;
import mastery.ui.LevelOverlayUi;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

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
    public void getHit(LivingHurtEvent getHitEvent) { // TODO COMBAT (hitting, getting hit), SURVIVAL (falling damage, drowning, lava, hunger etc)
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
    public void animalTame(AnimalTameEvent tameEvent) { //TODO HUSBANDRY
        LevelOverlayUi.currentMastery = MASTERY_SPEC.HUSBANDRY;
    }

    @SubscribeEvent
    public void brewedPotion(PlayerBrewedPotionEvent potioEvent) { // TODO ALCHEMY only limited exp for brewing!
        LevelOverlayUi.currentMastery = MASTERY_SPEC.ALCHEMY;
    }

    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) { // TODO HUSBANDRY exp
        LevelOverlayUi.currentMastery = MASTERY_SPEC.HUSBANDRY;
    }


}
