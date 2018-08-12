package mastery.eventhandlers.athletics;

import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_DISTANCE;
import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_FLYING;
import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_JUMP;
import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_LAVA;
import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_RIDING;
import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_WALKING;
import static mastery.eventhandlers.ExperienceDictionary.ATHLETICS_WATER;

import mastery.capability.skillclasses.MasterySpec;
import mastery.eventhandlers.AbstractExperienceHandler;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AthleticsExperience extends AbstractExperienceHandler {

    private BlockPos lastPlayerPosition;
    private float currentLength;
    private final int minimumLengthForExp;

    public AthleticsExperience() {
        this.lastPlayerPosition = null;
        this.currentLength = 0;
        this.minimumLengthForExp = ATHLETICS_DISTANCE.getValue();
        this.spec = MasterySpec.ATHLETICS;
    }

    @SubscribeEvent
    public void jump(LivingEvent.LivingJumpEvent jumpEvent) {
        if (!jumpEvent.getEntity().getEntityWorld().isRemote && jumpEvent.getEntity() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) jumpEvent.getEntity();
            if (!player.isInWater() && !player.isInLava()) {
                this.addExperience(player, ATHLETICS_JUMP);
            }
        }
    }

    @SubscribeEvent
    public void spawnPlayer(EntityJoinWorldEvent spawnEvent) {
        if (!spawnEvent.getEntity().getEntityWorld().isRemote && spawnEvent.getEntity() instanceof EntityPlayerMP) {
            this.lastPlayerPosition = spawnEvent.getEntity().getPosition();
        }
    }

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
                        this.currentLength += movedDistance * ATHLETICS_WATER.getValue();
                    } else if (player.isInLava()) {
                        this.currentLength += movedDistance * ATHLETICS_LAVA.getValue();
                    } else if (player.isRiding()) {
                        this.currentLength += movedDistance * ATHLETICS_RIDING.getValue();
                    } else if (player.isElytraFlying()) {
                        this.currentLength += movedDistance * ATHLETICS_FLYING.getValue();
                    } else {
                        this.currentLength += movedDistance * ATHLETICS_WALKING.getValue();
                    }
                    if (this.currentLength >= this.minimumLengthForExp) {
                        this.addExperience(player, 1);
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

}
