
package mastery.eventhandlers.survival;

import static mastery.eventhandlers.ExperienceDictionary.SURVIVAL_HIGH_DAMAGE;
import static mastery.eventhandlers.ExperienceDictionary.SURVIVAL_LOW_DAMAGE;
import static mastery.eventhandlers.ExperienceDictionary.SURVIVAL_MIDDLE_DAMAGE;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.DamageSource;

class SurvivalUtils {

    private static List<String> lowExp = new ArrayList<>();
    private static List<String> middleExp = new ArrayList<>();
    private static List<String> highExp = new ArrayList<>();

    static {
        lowExp.add(DamageSource.ON_FIRE.damageType);
        lowExp.add(DamageSource.WITHER.damageType);
        lowExp.add(DamageSource.MAGIC.damageType); // at least i know poison debuff
        middleExp.add("thorns"); // thorns

        middleExp.add(DamageSource.DROWN.damageType);
        middleExp.add(DamageSource.STARVE.damageType);
        middleExp.add(DamageSource.FALL.damageType);
        middleExp.add("explosion.player"); // something explodes

        highExp.add(DamageSource.LIGHTNING_BOLT.damageType);
        highExp.add(DamageSource.LAVA.damageType);
        highExp.add(DamageSource.ANVIL.damageType);
        highExp.add(DamageSource.DRAGON_BREATH.damageType);
    }

    static int getExperienceFromDamageSource(DamageSource source) {
        if (lowExp.contains(source.damageType)) {
            return SURVIVAL_LOW_DAMAGE.getValue();
        } else if (middleExp.contains(source.damageType)) {
            return SURVIVAL_MIDDLE_DAMAGE.getValue();
        } else if (highExp.contains(source.damageType)) {
            return SURVIVAL_HIGH_DAMAGE.getValue();
        } else {
            return 0;
        }
    }

}
