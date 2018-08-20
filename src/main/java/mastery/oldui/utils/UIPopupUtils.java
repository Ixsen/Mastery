package mastery.oldui.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;

/**
 * @author Subaro
 */
public class UIPopupUtils {

    public static void notifyPopup(String message) {
        Minecraft.getMinecraft().player.sendStatusMessage(new TextComponentString(message), true);
    }

    public static void notifyPopup(EntityPlayer player, String message) {
        player.sendStatusMessage(new TextComponentString(message), true);
    }

}
