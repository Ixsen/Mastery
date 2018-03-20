package mastery.networking;

import com.google.common.primitives.Ints;
import io.netty.buffer.ByteBuf;
import mastery.experience.IMastery;
import mastery.experience.MasteryProvider;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.ArrayList;
import java.util.List;

public class MasteryMessage implements IMessage {
    List<Integer> experienceLevels = new ArrayList<>();

    public MasteryMessage() {
    }

    public MasteryMessage(int[] experienceLevels) {
        this.experienceLevels = Ints.asList(experienceLevels);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (int i : experienceLevels) {
            buf.writeInt(i);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        while (buf.isReadable()) {
            experienceLevels.add(buf.readInt());
        }
    }

    public static class MasteryMessageHandler implements IMessageHandler<MasteryMessage, IMessage> {
        @Override
        public IMessage onMessage(MasteryMessage message, MessageContext context) {
            Minecraft.getMinecraft().addScheduledTask(() ->{
                IMastery mastery = Minecraft.getMinecraft().player.getCapability(MasteryProvider.MASTERY_CAPABILITY, null);
                mastery.readIntArray(message.experienceLevels.stream().mapToInt(it -> it.intValue()).toArray());
                    });
            return null;
        }
    }
}
