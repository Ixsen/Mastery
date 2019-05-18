package mastery.proxy;

import static mastery.ui.views.UserInterfaceId.LEVEL_UI;

import javax.annotation.Nullable;

import mastery.common.exceptions.UnexpectedUiIdException;
import mastery.ui.views.ExampleUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
        case LEVEL_UI:
            return new ExampleUI();
        case 2:
        default:
            throw new UnexpectedUiIdException(ID);
        }
    }
}
