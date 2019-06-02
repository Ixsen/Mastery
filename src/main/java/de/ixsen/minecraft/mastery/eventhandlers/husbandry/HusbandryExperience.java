package de.ixsen.minecraft.mastery.eventhandlers.husbandry;

import static de.ixsen.minecraft.mastery.eventhandlers.ExperienceDictionary.HUSBANDRY_ANIMAL_CHILD_SPAWN;
import static de.ixsen.minecraft.mastery.eventhandlers.ExperienceDictionary.HUSBANDRY_ENTITY_HARVESTED;
import static de.ixsen.minecraft.mastery.eventhandlers.ExperienceDictionary.HUSBANDRY_ENTITY_TAMED;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.annotations.SubscribeToCommonEventBus;
import de.ixsen.minecraft.mastery.eventhandlers.AbstractExperienceHandler;
import de.ixsen.minecraft.mastery.eventhandlers.ExperienceDictionary;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SubscribeToCommonEventBus
public class HusbandryExperience extends AbstractExperienceHandler {

    public HusbandryExperience() {
        this.spec = MasterySpec.HUSBANDRY;
    }

    @SubscribeEvent
    public void animalTame(AnimalTameEvent tameEvent) {
        this.addExperience(tameEvent.getTamer(), HUSBANDRY_ENTITY_TAMED);
    }

    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) {
        this.addExperience(babyEntitySpawnEvent.getCausedByPlayer(), HUSBANDRY_ANIMAL_CHILD_SPAWN);
    }

    @SubscribeEvent
    public void useItem(PlayerInteractEvent.EntityInteract event) {
        if (!event.getEntityPlayer().getEntityWorld().isRemote) {
            if (HusbandryUtils.isShearable(event.getTarget(), event.getItemStack().getItem())) {
                this.addExperience(event.getEntityPlayer(), HUSBANDRY_ENTITY_HARVESTED);
            } else if (HusbandryUtils.isFeedable(event.getTarget(), event.getEntityPlayer(), event.getHand())) {
                this.addExperience(event.getEntityPlayer(), ExperienceDictionary.HUSBANDRY_ENTITY_FED);
            }

        }
    }

}
