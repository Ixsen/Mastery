package mastery.ui.custom.elements.impl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.VanillaGuiScreen;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author Subaro
 */
public class UIScreen extends VanillaGuiScreen {

    /**
     * @param mcScreen
     */
    public UIScreen(GuiScreen mcScreen) {
        super(mcScreen);
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        super.draw(renderer, size, renderInfo);
        GL11.glPopMatrix();
    }

    public static UIScreen setup(net.minecraft.client.gui.GuiScreen originalGuiScreen) {
        UIScreen gui = new UIScreen(originalGuiScreen);
        gui.register();
        return gui;
    }
}
