package mastery.eventhandlers;

import mastery.experience.skillclasses.*;
import mastery.util.BlockUtil;
import mastery.util.ItemTagUtils;
import mastery.util.MasteryUtils;
import mastery.util.NetworkUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ExperienceEventsHandler {

    private static final String TAG_BREWED_POTION = "isBrewed";
    private static final String TAG_POTION_AUTHOR = ItemTagUtils.TOOLTIP_TAG + "Author";

    public ExperienceEventsHandler() {
    }

    /***
     * TODO MINING and FARMING
     *
     * @param breakEvent --
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
     * Fired when an entity dies. If the true source of death is the player, gets increased experience
     *
     * @param deathEvent --
     */
    @SubscribeEvent
    public void killEntity(LivingDeathEvent deathEvent) {
        if (!deathEvent.getEntity().getEntityWorld().isRemote && deathEvent.getSource().getTrueSource() instanceof EntityPlayer) {
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
        if (livingHurtEvent.getSource().getTrueSource() instanceof EntityPlayer && livingHurtEvent.getAmount() >= CombatMastery.doDamageExpThreshold) {
            EntityPlayerMP player = (EntityPlayerMP) livingHurtEvent.getSource().getTrueSource();
            CombatMastery combatMastery = MasteryUtils.getCombatMastery(player);
            combatMastery.increaseExperience(livingHurtEvent.getAmount(), CombatMastery.EXP_TYPE.ENTITY_DAMAGED);
            NetworkUtils.sendExpToPlayer(combatMastery, player);
        }
        if (livingHurtEvent.getEntity() instanceof EntityPlayer &&
                livingHurtEvent.getSource().getTrueSource() instanceof EntityLivingBase &&
                livingHurtEvent.getAmount() >= CombatMastery.getDamagedExpThreshold) {
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
            if (!ItemTagUtils.hasTag(stack, TAG_BREWED_POTION)) {
                return;
            } else {
                ItemTagUtils.removeTag(stack, TAG_BREWED_POTION);
                // Add author to potion
                ItemTagUtils.addTagString(stack, TAG_POTION_AUTHOR,
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

        // Skip if air
        if (!first.isEmpty()) {
            PotionType our = PotionUtils.getPotionFromItem(first);
            if (!our.getEffects().isEmpty()) {
                // Tag stack as 'brewed'
                ItemTagUtils.addTagBoolean(first, TAG_BREWED_POTION, true);
            }
        }
        // Skip if air
        if (!second.isEmpty()) {
            PotionType our = PotionUtils.getPotionFromItem(first);
            if (!our.getEffects().isEmpty()) {
                // Tag stack as 'brewed'
                ItemTagUtils.addTagBoolean(second, TAG_BREWED_POTION, true);
            }
        }
        // Skip if air
        if (!third.isEmpty()) {
            PotionType our = PotionUtils.getPotionFromItem(first);
            if (!our.getEffects().isEmpty()) {
                // Tag stack as 'brewed'
                ItemTagUtils.addTagBoolean(third, TAG_BREWED_POTION, true);
            }
        }
    }

    /**
     * TODO ALCHEMY, SURVIVAL
     *
     * @param useItemEvent --
     */
    @SubscribeEvent
    public void useItem(LivingEntityUseItemEvent.Finish useItemEvent) {

    }

    /**
     * TODO FARMING
     *
     * @param harvestCheck --
     */
    @SubscribeEvent
    public void harvestCrop(PlayerEvent.HarvestCheck harvestCheck) {

    }

    /**
     * TODO FARMING
     *
     * @param bonemealEvent --
     */
    @SubscribeEvent
    public void boneEvent(BonemealEvent bonemealEvent) {

    }

    /**
     * TODO FARMING
     *
     * @param useHoeEvent --
     */
    @SubscribeEvent
    public void hoeing(UseHoeEvent useHoeEvent) {

    }

    /**
     * TODO HUSBANDRY
     *
     * @param tameEvent --
     */
    @SubscribeEvent
    public void animalTame(AnimalTameEvent tameEvent) {
    }

    /**
     * TODO HUSBANDRY
     *
     * @param interactEvent --
     */
    @SubscribeEvent
    public void feed(PlayerInteractEvent.EntityInteractSpecific interactEvent) {

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