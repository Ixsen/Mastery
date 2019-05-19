package de.ixsen.minecraft.mastery.networking;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static SimpleNetworkWrapper INSTANCE;
    private static int packetId = 0;

    public static int nextID() {
        return packetId++;
    }

    public static void registerMessages(String channelName) {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
        registerMessages();
    }

    public static void registerMessages() {
        INSTANCE.registerMessage(MasteryMessage.MasteryMessageHandler.class, MasteryMessage.class, nextID(),
                Side.CLIENT);
    }

    public static void registerMasteryExpMessages() {
        registerMessages("mastery");
    }
}
