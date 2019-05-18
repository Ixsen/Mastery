package mastery.eventhandlers.alchemy;

import static mastery.eventhandlers.ExperienceDictionary.ALCHEMY_BREW;
import static mastery.eventhandlers.ExperienceDictionary.ALCHEMY_DRINK;
import static mastery.eventhandlers.ExperienceDictionary.ALCHEMY_THROW;

import mastery.capability.player.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import mastery.common.util.ItemTagUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AlchemyExperience extends AbstractExperienceHandler {

    public AlchemyExperience() {
        this.spec = MasterySpec.ALCHEMY;
    }

    @SubscribeEvent
    public void takePotion(PlayerBrewedPotionEvent potionEvent) {
        if (!potionEvent.getEntityPlayer().getEntityWorld().isRemote) {
            ItemStack stack = potionEvent.getStack();

            // Check if stack is marked as 'brewed'
            if (!ItemTagUtils.hasTag(stack, AlchemyUtils.TAG_BREWED_POTION)) {
                return;
            } else {
                ItemTagUtils.removeTag(stack, AlchemyUtils.TAG_BREWED_POTION);
                // Add author to potion
                ItemTagUtils.addTagString(stack, AlchemyUtils.TAG_POTION_AUTHOR,
                        "Brewed by " + potionEvent.getEntityPlayer().getName());
            }

            PotionType our = PotionUtils.getPotionFromItem(stack);
            int numberOfExp = our.getEffects().size();

            if (numberOfExp > 0) {
                this.addExperience(potionEvent.getEntity(), numberOfExp * ALCHEMY_BREW.getValue());
            }
        }
    }

    @SubscribeEvent
    public void postBrewedPotion(PotionBrewEvent.Post potionEvent) {
        ItemStack first = potionEvent.getItem(0);
        ItemStack second = potionEvent.getItem(1);
        ItemStack third = potionEvent.getItem(2);

        // First slot
        PotionType our = AlchemyUtils.isPotion(first) ? AlchemyUtils.getPotionType(first) : null;
        if (our != null && !AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(first, AlchemyUtils.TAG_BREWED_POTION, true);
        }

        // Second slot
        our = AlchemyUtils.isPotion(first) ? AlchemyUtils.getPotionType(second) : null;
        if (our != null && !AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(second, AlchemyUtils.TAG_BREWED_POTION, true);
        }

        // Third slot
        our = AlchemyUtils.isPotion(first) ? AlchemyUtils.getPotionType(third) : null;
        if (our != null && !AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(third, AlchemyUtils.TAG_BREWED_POTION, true);
        }
    }

    @SubscribeEvent
    public void useItemFinish(LivingEntityUseItemEvent.Finish useItemEvent) {
        if (!useItemEvent.getEntityLiving().getEntityWorld().isRemote) {
            ItemStack item = useItemEvent.getItem();

            // ALCHEMY - get exp by drinking potions
            if (AlchemyUtils.isPotion(item)) {
                PotionType potionType = AlchemyUtils.getPotionType(item);
                if (!AlchemyUtils.isUselessPotion(potionType)) {
                    this.addExperience(useItemEvent.getEntity(), ALCHEMY_DRINK);
                }
            }
        }
    }

    @SubscribeEvent
    public void interactItemRightClick(PlayerInteractEvent.RightClickItem useItemEvent) {
        if (!useItemEvent.getEntityLiving().getEntityWorld().isRemote) {
            ItemStack item = useItemEvent.getItemStack();

            // ALCHEMY - get exp by "throwing" splash potions
            if (AlchemyUtils.isSplashPotion(item)) {
                PotionType potionType = AlchemyUtils.getPotionType(item);
                if (!AlchemyUtils.isUselessPotion(potionType)) {
                    this.addExperience(useItemEvent.getEntity(), ALCHEMY_THROW);
                }
            }
        }
    }
}
