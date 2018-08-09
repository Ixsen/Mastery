package mastery.eventhandlers.husbandry;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static mastery.eventhandlers.ExperienceDictionary.HUSBANDRY_ANIMAL_CHILD_SPAWN;
import static mastery.eventhandlers.ExperienceDictionary.HUSBANDRY_ENTITY_TAMED;

public class HusbandryExperience extends AbstractExperienceHandler {

    private ItemStack mainHand;
    private ItemStack offHand;

    private boolean secondTrigger = false;

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
}
