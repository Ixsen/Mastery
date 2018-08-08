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

    public ExperienceEventsHandler() {
    }

    /***
     * TODO MINING and FARMING
     *
     * @param breakEvent
     *            --
     */
    @SubscribeEvent
    public void breakBlock(BlockEvent.BreakEvent breakEvent) {
        if (!breakEvent.getPlayer().getEntityWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) breakEvent.getPlayer();
            if (BlockUtil.shouldGetFarmingExp(breakEvent.getState())) {
                FarmingMastery farmingMastery = MasteryUtils.getFarmingMastery(player);
                farmingMastery.increaseExperience();
                NetworkUtils.sendExpToPlayer(farmingMastery, player);
            } else {
                MiningMastery miningMastery = MasteryUtils.getMiningMastery(player);
                miningMastery.increaseExperience();
                NetworkUtils.sendExpToPlayer(miningMastery, player);
            }
        }
    }

    /**
     * Fired when an entity dies. If the true source of death is the player, gets
     * increased experience
     *
     * @param deathEvent --
     */
    @SubscribeEvent
    public void killEntity(LivingDeathEvent deathEvent) {
        if (!deathEvent.getEntity().getEntityWorld().isRemote
                && deathEvent.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayerMP player = (EntityPlayerMP) deathEvent.getSource().getTrueSource();
            CombatMastery combatMastery = MasteryUtils.getCombatMastery(player);
            combatMastery.increaseExperience_EntitySlain(deathEvent.getEntityLiving());
            NetworkUtils.sendExpToPlayer(combatMastery, player);
        }
    }

    /**
     * TODO SURVIVAL (falling damage, drowning, lava, hunger etc)
     *
     * @param livingHurtEvent --
     */
    @SubscribeEvent
    public void getHit(LivingHurtEvent livingHurtEvent) {
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer
                && livingHurtEvent.getAmount() >= CombatMastery.doDamageExpThreshold) {
            EntityPlayerMP player = (EntityPlayerMP) livingHurtEvent.getSource().getTrueSource();
            CombatMastery combatMastery = MasteryUtils.getCombatMastery(player);
            combatMastery.increaseExperience(livingHurtEvent.getAmount(), CombatMastery.EXP_TYPE.ENTITY_DAMAGED);
            NetworkUtils.sendExpToPlayer(combatMastery, player);
        }
        if (livingHurtEvent.getEntity() instanceof EntityPlayer
                && livingHurtEvent.getSource().getTrueSource() instanceof EntityLivingBase
                && livingHurtEvent.getAmount() >= CombatMastery.getDamagedExpThreshold) {
            EntityPlayerMP player = (EntityPlayerMP) livingHurtEvent.getEntity();
            CombatMastery combatMastery = MasteryUtils.getCombatMastery(player);
            combatMastery.increaseExperience(livingHurtEvent.getAmount(), CombatMastery.EXP_TYPE.PLAYER_DAMAGED);
            NetworkUtils.sendExpToPlayer(combatMastery, player);
        }
    }

    /**
     * TODO ALCHEMY only limited exp for brewing!
     *
     * @param potionEvent --
     */
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
            int numberOfExp = 1 * our.getEffects().size();

            if (numberOfExp > 0) {
                EntityPlayerMP player = (EntityPlayerMP) potionEvent.getEntityPlayer();
                AlchemyMastery alchemyMastery = MasteryUtils.getAlchemyMastery(player);
                alchemyMastery.increaseExperience();
                NetworkUtils.sendExpToPlayer(alchemyMastery, player);
            }
        }
    }

    /**
     * TODO ALCHEMY only limited exp for brewing!
     *
     * @param potionEvent --
     */
    @SubscribeEvent
    public void postBrewedPotion(PotionBrewEvent.Post potionEvent) {
        ItemStack first = potionEvent.getItem(0);
        ItemStack second = potionEvent.getItem(1);
        ItemStack third = potionEvent.getItem(2);

        // First slot
        PotionType our = AlchemyUtils.getPotionType(first);
        if (!AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(first, AlchemyUtils.TAG_BREWED_POTION, true);
        }

        // Second slot
        our = AlchemyUtils.getPotionType(second);
        if (!AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(second, AlchemyUtils.TAG_BREWED_POTION, true);
        }

        // Third slot
        our = AlchemyUtils.getPotionType(third);
        if (!AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(third, AlchemyUtils.TAG_BREWED_POTION, true);
        }
    }

    /**
     * TODO ALCHEMY, SURVIVAL
     *
     * @param useItemEvent --
     */
    @SubscribeEvent
    public void useItem(LivingEntityUseItemEvent.Finish useItemEvent) {
        ItemStack item = useItemEvent.getItem();

        // ProjectileLaunchEvent
        if (AlchemyUtils.isPotion(item)) {
            Minecraft.getMinecraft().player.sendChatMessage("");
        }
    }

    /**
     * Triggers server-side only
     *
     * @param tameEvent --
     */
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

    @SubscribeEvent
    public void interAct(PlayerInteractEvent.EntityInteract entityInteract) {
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

    /**
     * TODO CRAFTING
     *
     * @param itemCraftedEvent --
     */
    @SubscribeEvent
    public void craftItem(net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent itemCraftedEvent) {
        if (!itemCraftedEvent.player.getEntityWorld().isRemote) {
            EntityPlayerMP player = (EntityPlayerMP) itemCraftedEvent.player;
            CraftingMastery craftingMastery = MasteryUtils.getCraftingMastery(player);
            craftingMastery.increaseExperience();
            NetworkUtils.sendExpToPlayer(craftingMastery, player);
        }
    }

    /**
     * TODO ATHLETICS
     *
     * @param jumpEvent --
     */
    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent jumpEvent) {

    }

    /*
     * TODO TNT MINING
     */
}