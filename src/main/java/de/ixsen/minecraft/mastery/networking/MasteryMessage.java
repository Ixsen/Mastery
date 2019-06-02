package de.ixsen.minecraft.mastery.networking;

import io.netty.buffer.ByteBuf;
import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.capability.player.IMastery;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.common.util.MasteryUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MasteryMessage implements IMessage {
    private int level;
    private int experience;
    private int masteryID;
    private boolean notifyUI;

    /**
     * Default constructor Don't delete, is necessary for message initialization
     */
    public MasteryMessage() {
    }

    public MasteryMessage(int masteryID, int level, int experience, boolean notifyUI) {
        this.masteryID = masteryID;
        this.level = level;
        this.experience = experience;
        this.notifyUI = notifyUI;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.notifyUI);
        buf.writeInt(this.masteryID);
        buf.writeInt(this.level);
        buf.writeInt(this.experience);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.notifyUI = buf.readBoolean();
        this.masteryID = buf.readInt();
        this.level = buf.readInt();
        this.experience = buf.readInt();
    }

    public static class MasteryMessageHandler implements IMessageHandler<MasteryMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(MasteryMessage message, MessageContext context) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                IMastery mastery = MasteryUtils.getMasteries(Minecraft.getMinecraft().player);
                MasteryClass masteryClass = mastery.getMasteries().get(MasterySpec.getByOrder(message.masteryID));
                if (masteryClass.getPlayer() == null) {
                    masteryClass.setPlayer(Minecraft.getMinecraft().player);
                }
                masteryClass.setLevel(message.level);
                masteryClass.calcNextLevelExp();
                masteryClass.setExperience(message.experience);
                MasteryMod.fireExpEvent(MasterySpec.getByOrder(message.masteryID), message.notifyUI);
            });
            return null;
        }
    }
}
