package mastery.networking;

import io.netty.buffer.ByteBuf;
import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import mastery.experience.skillclasses.MASTERY_SPEC;
import mastery.ui.LevelOverlayUi;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MasteryMessage implements IMessage {
    private int level;
    private int experience;
    private int masteryID;

    public MasteryMessage() { // Don't delete, is necessary for message initialization
    }

    public MasteryMessage(int masteryID, int level, int experience) {
        this.masteryID = masteryID;
        this.level = level;
        this.experience = experience;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(masteryID);
        buf.writeInt(level);
        buf.writeInt(experience);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        masteryID = buf.readInt();
        level = buf.readInt();
        experience = buf.readInt();
    }

    public static class MasteryMessageHandler implements IMessageHandler<MasteryMessage, IMessage> {
        @Override
        public IMessage onMessage(MasteryMessage message, MessageContext context) {
            Minecraft.getMinecraft().addScheduledTask(() -> {
                IMastery mastery = Minecraft.getMinecraft().player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
                mastery.getMasteries().get(MASTERY_SPEC.getByOrder(message.masteryID)).setLevel(message.level);
                mastery.getMasteries().get(MASTERY_SPEC.getByOrder(message.masteryID)).calcNextLevelExp();
                mastery.getMasteries().get(MASTERY_SPEC.getByOrder(message.masteryID)).setExperience(message.experience);
                LevelOverlayUi.currentMastery = MASTERY_SPEC.getByOrder(message.masteryID);
                LevelOverlayUi.levelOverlayUi.show();
            });
            return null;
        }
    }
}
