package mastery.eventhandlers.husbandry;

import static net.minecraft.entity.SharedMonsterAttributes.ATTACK_DAMAGE;
import static net.minecraft.entity.SharedMonsterAttributes.MAX_HEALTH;
import static net.minecraft.entity.SharedMonsterAttributes.MOVEMENT_SPEED;

import java.util.UUID;

import mastery.capability.player.skillclasses.HusbandryMastery;
import mastery.util.AttributeUtils;
import mastery.util.MasteryUtils;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.ai.attributes.AbstractAttributeMap;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HusbandryEffects {

    private static UUID uuid = UUID.fromString("9e3a7ad9-044c-4c54-89dc-60f9d011c674");

    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) {
        if (babyEntitySpawnEvent.getCausedByPlayer() != null) {
            HusbandryMastery husbandryMastery = MasteryUtils
                    .getHusbandryMastery(babyEntitySpawnEvent.getCausedByPlayer());
            EntityAgeable parent = (EntityAgeable) babyEntitySpawnEvent.getParentA();
            if (babyEntitySpawnEvent.getChild() != null && husbandryMastery.willSpawnTwins()) {
                EntityAgeable twin = (EntityAgeable) EntityList.newEntity(parent.getClass(), parent.getEntityWorld());
                twin.setGrowingAge(Math.round(-24000 * husbandryMastery.getGrowingMultiplier()));
                twin.setPosition(parent.posX, parent.posY, parent.posZ);
                parent.getEntityWorld().spawnEntity(twin);
            }
        }
    }

    @SubscribeEvent
    public void useItem(PlayerInteractEvent.EntityInteract event) {
        if (!event.getEntityPlayer().getEntityWorld().isRemote
                && HusbandryUtils.isTameable(event.getTarget(), event.getEntityPlayer(), event.getHand())) {
            HusbandryMastery husbandryMastery = MasteryUtils.getHusbandryMastery(event.getEntityPlayer());
            if (husbandryMastery.willBeTamed()) {
                ((EntityTameable) event.getTarget()).setTamedBy(event.getEntityPlayer());
                this.setTameAttributes((EntityTameable) event.getTarget(), event.getEntityPlayer());
            }
        }
    }

    @SubscribeEvent
    public void setTameEvent(AnimalTameEvent tameEvent) {
        if (tameEvent.getTamer() != null) {
            this.setTameAttributes((EntityTameable) tameEvent.getAnimal(), tameEvent.getTamer());
        }
    }

    private void setTameAttributes(EntityTameable pet, EntityPlayer player) {
        HusbandryMastery husbandryMastery = MasteryUtils.getHusbandryMastery(player);
        AbstractAttributeMap attributeMap = pet.getAttributeMap();
        AttributeUtils.applyModifier(husbandryMastery.getPetMovementModifier(),
                attributeMap.getAttributeInstance(MOVEMENT_SPEED), "Mastery Speed for Pets!", uuid);
        AttributeUtils.applyModifier(husbandryMastery.getPetHealthModifier(),
                attributeMap.getAttributeInstance(MAX_HEALTH), "Mastery Health for Pets", uuid);
        pet.setHealth(pet.getMaxHealth());
        AttributeUtils.applyModifier(husbandryMastery.getPetDamageModifier(),
                attributeMap.getAttributeInstance(ATTACK_DAMAGE), "Mastery Damage for Pets", uuid);
    }
}
