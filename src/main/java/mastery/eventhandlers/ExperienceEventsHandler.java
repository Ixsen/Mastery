package mastery.eventhandlers;

import mastery.experience.skillclasses.AlchemyMastery;
import mastery.experience.skillclasses.AthleticsMastery;
import mastery.experience.skillclasses.CombatMastery;
import mastery.experience.skillclasses.CraftingMastery;
import mastery.experience.skillclasses.FarmingMastery;
import mastery.experience.skillclasses.MiningMastery;
import mastery.util.ItemTagUtils;
import mastery.util.MasteryUtils;
import mastery.util.NetworkUtils;
import mastery.util.masteries.AlchemyUtils;
import mastery.util.masteries.AthleticsUtils;
import mastery.util.masteries.FarmingUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemSplashPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.brewing.PlayerBrewedPotionEvent;
import net.minecraftforge.event.brewing.PotionBrewEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.AnimalTameEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
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
            if (FarmingUtils.shouldGetFarmingExp(breakEvent.getState())) {
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
     * @param deathEvent
     *            --
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
     * @param livingHurtEvent
     *            --
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
     * @param potionEvent
     *            --
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
     * @param potionEvent
     *            --
     */
    @SubscribeEvent
    public void postBrewedPotion(PotionBrewEvent.Post potionEvent) {
        ItemStack first = potionEvent.getItem(0);
        ItemStack second = potionEvent.getItem(1);
        ItemStack third = potionEvent.getItem(2);

        // First slot
        PotionType our = AlchemyUtils.getPotionType(first);
        if (our != null && !AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(first, AlchemyUtils.TAG_BREWED_POTION, true);
        }

        // Second slot
        our = AlchemyUtils.getPotionType(second);
        if (our != null && !AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(second, AlchemyUtils.TAG_BREWED_POTION, true);
        }

        // Third slot
        our = AlchemyUtils.getPotionType(third);
        if (our != null && !AlchemyUtils.isUselessPotion(our)) {
            ItemTagUtils.addTagBoolean(third, AlchemyUtils.TAG_BREWED_POTION, true);
        }
    }

    /**
     * TODO SURVIVAL
     *
     * @param useItemEvent
     *            --
     */
    @SubscribeEvent
    public void useItemFinish(LivingEntityUseItemEvent.Finish useItemEvent) {
        if (!useItemEvent.getEntityLiving().getEntityWorld().isRemote) {
            ItemStack item = useItemEvent.getItem();

            // ALCHEMY - get exp by drinking potions
            if (AlchemyUtils.isPotion(item)) {
                PotionType potionType = AlchemyUtils.getPotionType(item);
                if (!AlchemyUtils.isUselessPotion(potionType)) {
                    EntityPlayerMP player = (EntityPlayerMP) useItemEvent.getEntityLiving();
                    AlchemyMastery alchemyMastery = MasteryUtils.getAlchemyMastery(player);
                    alchemyMastery.increaseExperience();
                    NetworkUtils.sendExpToPlayer(alchemyMastery, player);
                }
            }
        }
    }

    @SubscribeEvent
    public void interactItemRightClick(PlayerInteractEvent.RightClickItem useItemEvent) {
        if (!useItemEvent.getEntityLiving().getEntityWorld().isRemote) {
            ItemStack item = useItemEvent.getItemStack();

            // ALCHEMY - get exp by "throwing" splash potions
            if (AlchemyUtils.isPotion(item) && item.getItem() instanceof ItemSplashPotion) {
                PotionType potionType = AlchemyUtils.getPotionType(item);
                if (!AlchemyUtils.isUselessPotion(potionType)) {
                    EntityPlayerMP player = (EntityPlayerMP) useItemEvent.getEntityLiving();
                    AlchemyMastery alchemyMastery = MasteryUtils.getAlchemyMastery(player);
                    alchemyMastery.increaseExperience();
                    NetworkUtils.sendExpToPlayer(alchemyMastery, player);
                }
            }
        }
    }

    /**
     * TODO FARMING
     *
     * @param harvestCheck
     *            --
     */
    @SubscribeEvent
    public void harvestCrop(PlayerEvent.HarvestCheck harvestCheck) {

    }

    /**
     * TODO FARMING
     *
     * @param bonemealEvent
     *            --
     */
    @SubscribeEvent
    public void boneEvent(BonemealEvent bonemealEvent) {

    }

    /**
     * TODO FARMING
     *
     * @param useHoeEvent
     *            --
     */
    @SubscribeEvent
    public void hoeing(UseHoeEvent useHoeEvent) {

    }

    /**
     * TODO HUSBANDRY
     *
     * @param tameEvent
     *            --
     */
    @SubscribeEvent
    public void animalTame(AnimalTameEvent tameEvent) {
    }

    /**
     * TODO HUSBANDRY
     *
     * @param interactEvent
     *            --
     */
    @SubscribeEvent
    public void feed(PlayerInteractEvent.EntityInteractSpecific interactEvent) {

    }

    /**
     * TODO CRAFTING
     *
     * @param itemCraftedEvent
     *            --
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

    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent jumpEvent) {
        if (!jumpEvent.getEntity().getEntityWorld().isRemote && jumpEvent.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) jumpEvent.getEntity();
            if (!player.isInWater() && !player.isInLava()) {
                AthleticsMastery athleticsMastery = MasteryUtils.getAthleticsMastery(player);
                athleticsMastery.increaseExperience(AthleticsUtils.EXP_JUMP);
                NetworkUtils.sendExpToPlayer(athleticsMastery, player);
            }
        }
    }

    @SubscribeEvent
    public void spawnPlayer(EntityJoinWorldEvent spawnEvent) {
        if (!spawnEvent.getEntity().getEntityWorld().isRemote && spawnEvent.getEntity() instanceof EntityPlayerMP) {
            this.lastPlayerPosition = spawnEvent.getEntity().getPosition();
        }
    }

    private BlockPos lastPlayerPosition = null;
    private float currentLength = 0;
    private float minimumLengthForExp = 15;

    @SubscribeEvent
    public void moveLivingEvent(LivingEvent.LivingUpdateEvent updateEvent) {
        if (!updateEvent.getEntity().getEntityWorld().isRemote && updateEvent.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) updateEvent.getEntity();
            if (this.lastPlayerPosition == null) {
                this.lastPlayerPosition = player.getPosition();
            } else {
                if (!player.getPosition().equals(this.lastPlayerPosition)) {
                    double movedDistance = this.calculateVector(this.lastPlayerPosition, player.getPosition());
                    if (player.isInWater()) {
                        this.currentLength += movedDistance * 8;
                    } else if (player.isInLava()) {
                        this.currentLength += movedDistance * 10;
                    } else if (player.isRiding()) {
                        this.currentLength += movedDistance * 0.2f;
                    } else if (player.isElytraFlying()) {
                        this.currentLength += movedDistance * 0.5f;
                    } else {
                        this.currentLength += movedDistance;
                    }
                    if (this.currentLength >= this.minimumLengthForExp) {
                        AthleticsMastery athleticsMastery = MasteryUtils.getAthleticsMastery(player);
                        athleticsMastery.increaseExperience(AthleticsUtils.EXP_OTHER);
                        NetworkUtils.sendExpToPlayer(athleticsMastery, player);
                        this.currentLength -= this.minimumLengthForExp;
                    }
                    this.lastPlayerPosition = player.getPosition();
                }
            }
        }
    }

    private double calculateVector(BlockPos first, BlockPos second) {
        return Math.sqrt(Math.pow(second.getX() - first.getX(), 2) + Math.pow(second.getY() - first.getY(), 2)
                + Math.pow(second.getZ() - first.getZ(), 2));
    }

    /*
     * TODO TNT MINING
     */
}