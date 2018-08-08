package mastery.eventhandlers;

import mastery.experience.skillclasses.AlchemyMastery;
import mastery.experience.skillclasses.CombatMastery;
import mastery.experience.skillclasses.CraftingMastery;
import mastery.experience.skillclasses.FarmingMastery;
import mastery.experience.skillclasses.HusbandryMastery;
import mastery.experience.skillclasses.MiningMastery;
import mastery.util.BlockUtil;
import mastery.util.ItemTagUtils;
import mastery.util.MasteryUtils;
import mastery.util.NetworkUtils;
import mastery.util.masteries.AlchemyUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.BabyEntitySpawnEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

    @SubscribeEvent
    public void animalTame(AnimalTameEvent tameEvent) {
        EntityPlayerMP player = (EntityPlayerMP) tameEvent.getTamer();
        HusbandryMastery husbandryMastery = MasteryUtils.getHusbandryMastery(player);
        husbandryMastery.increaseExperience(HusbandryMastery.EXP_TYPE.ENTITY_TAMED);
        NetworkUtils.sendExpToPlayer(husbandryMastery, player);
    }

    private Iterable<ItemStack> heldEquipment;
    private boolean secondTrigger = false;

    @SubscribeEvent
    public void animalInteraction(PlayerInteractEvent.EntityInteractSpecific interactEvent) {
        if (!interactEvent.getEntityPlayer().getEntityWorld().isRemote) {
            EntityPlayer entityPlayer = interactEvent.getEntityPlayer();
            if (this.secondTrigger) {
                this.secondTrigger = false;
                if (!this.heldEquipment.equals(interactEvent.getEntityLiving().getHeldEquipment())
                        && interactEvent.getTarget() instanceof EntityAnimal) {
                    HusbandryMastery husbandryMastery = MasteryUtils.getHusbandryMastery(entityPlayer);
                    husbandryMastery.increaseExperience(HusbandryMastery.EXP_TYPE.ANIMAL_FED);
                    NetworkUtils.sendExpToPlayer(husbandryMastery, (EntityPlayerMP) entityPlayer);
                }
            } else {
                this.secondTrigger = true;
                this.heldEquipment = interactEvent.getEntityPlayer().getHeldEquipment();
            }
        }
    }

    /**
     * Triggers server-side only
     *
     * @param babyEntitySpawnEvent --
     */
    @SubscribeEvent
    public void spawnBaby(BabyEntitySpawnEvent babyEntitySpawnEvent) {
        EntityPlayerMP player = (EntityPlayerMP) babyEntitySpawnEvent.getCausedByPlayer();
        HusbandryMastery husbandryMastery = MasteryUtils.getHusbandryMastery(player);
        husbandryMastery.increaseExperience(HusbandryMastery.EXP_TYPE.ANIMAL_CHILD_SPAWN);
        NetworkUtils.sendExpToPlayer(husbandryMastery, player);
    }
}