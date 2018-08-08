package mastery.networking;

import io.netty.buffer.ByteBuf;
import mastery.MasteryMod;
import mastery.capability.IMastery;
import mastery.capability.skillclasses.MasterySpec;
import mastery.util.MasteryUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        public IMessage onMessage(MasteryMessage message, MessageContext context) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                IMastery mastery = MasteryUtils.getMasteries(Minecraft.getMinecraft().player);
                mastery.getMasteries().get(MasterySpec.getByOrder(message.masteryID)).setLevel(message.level);
                mastery.getMasteries().get(MasterySpec.getByOrder(message.masteryID)).calcNextLevelExp();
                mastery.getMasteries().get(MasterySpec.getByOrder(message.masteryID)).setExperience(message.experience);
                MasteryMod.fireExpEvent(MasterySpec.getByOrder(message.masteryID), message.notifyUI);
            });
            return null;
        }
    }
}
