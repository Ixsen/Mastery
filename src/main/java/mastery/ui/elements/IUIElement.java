package mastery.ui.elements;

import net.minecraft.client.Minecraft;

/**
 * @author Subaro
 */
public interface IUIElement {

    void drawElement(Minecraft mc, int mouseX, int mouseY);

    int getHeight();

    int getWidth();
}
