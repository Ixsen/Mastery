package mastery.eventhandlers.survival;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import mastery.capability.player.skillclasses.SurvivalMastery;
import mastery.common.util.MasteryUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SurvivalEffects {

    private Collection<Potion> effects = new ArrayList<>();

    @SubscribeEvent
    void eatItem(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof EntityPlayer && !event.getEntity().getEntityWorld().isRemote) {
            ItemStack itemStack = event.getItem();
            if (itemStack.getItem() instanceof ItemFood) {
                EntityPlayer player = (EntityPlayer) event.getEntity();
                SurvivalMastery survivalMastery = MasteryUtils.getSurvivalMastery(player);
                ItemFood itemFood = (ItemFood) itemStack.getItem();
                int hungerAmount = itemFood.getHealAmount(itemStack);
                float foodSaturationModifier = itemFood.getSaturationModifier(itemStack);
                player.getFoodStats().addStats(Math.round(hungerAmount * survivalMastery.getHungerBonus()),
                        foodSaturationModifier * survivalMastery.getSaturationBonus());

            }
        }
    }

    /**
     * This is bad and I feel bad
     * 
     * @param updateEvent
     *            :(
     */
    @SubscribeEvent
    public void updateEvent(LivingEvent.LivingUpdateEvent updateEvent) {
        if (updateEvent.getEntityLiving() instanceof EntityPlayer
                && !updateEvent.getEntityLiving().getEntityWorld().isRemote) {
            EntityPlayer player = (EntityPlayer) updateEvent.getEntityLiving();
            for (PotionEffect activeEffect : player.getActivePotionEffects()) {
                if (!activeEffect.getPotion().isBeneficial() && !this.effects.contains(activeEffect.getPotion())) {
                    this.effects.add(activeEffect.getPotion());
                    player.removeActivePotionEffect(activeEffect.getPotion());
                    PotionEffect effect = new PotionEffect(activeEffect.getPotion(),
                            Math.round(activeEffect.getDuration()
                                    * MasteryUtils.getSurvivalMastery(player).getDurationMultiplier()),
                            activeEffect.getAmplifier());
                    player.addPotionEffect(effect);
                }
            }
            List<Potion> potions = player.getActivePotionEffects().stream().map(PotionEffect::getPotion)
                    .collect(Collectors.toList());
            Collection<Potion> removePotions = new HashSet<>();
            for (Potion potion : this.effects) {
                if (!potions.contains(potion)) {
                    removePotions.add(potion);
                }
            }
            this.effects.removeAll(removePotions);
        }
    }
}
