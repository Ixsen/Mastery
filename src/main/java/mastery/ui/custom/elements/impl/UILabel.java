package mastery.ui.custom.elements.impl;

import java.util.List;

import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.element.GuiLabel;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;

public class UILabel extends GuiLabel {
    public enum UILabelAlignment {
        TOP_LEFT, TOP_CENTER, TOP_RIGHT, MIDDLE_LEFT, MIDDLE_CENTER, MIDDLE_RIGHT, BOT_LEFT, BOT_CENTER, BOT_RIGHT
    }

    @Getter
    @Setter
    private UILabelAlignment alignment;

    public UILabel() {
        this.alignment = UILabelAlignment.TOP_LEFT;
    }

    public UILabel(UILabelAlignment alignment) {
        this.alignment = alignment;
    }

    public UILabel(GuiContainer container, UILabelAlignment alignment) {
        super();
        this.alignment = alignment;
    }

    @Override
    protected UILabel getThis() {
        return this;
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        FontRenderer fontRenderer = this.getMinecraft().fontRenderer;
        List<String> lines = fontRenderer.listFormattedStringToWidth(this.getText(), size.getWidth());
        ReadableDimension calculatedSize = this.calcMinSize();
        int y, x;

        switch (this.alignment) {
        case BOT_RIGHT:
            x = this.getMaxSize().getWidth() - calculatedSize.getWidth();
            y = this.getMaxSize().getHeight() - fontRenderer.FONT_HEIGHT;
            break;
        case BOT_CENTER:
            x = this.getMaxSize().getWidth() / 2 - calculatedSize.getWidth() / 2;
            y = this.getMaxSize().getHeight() - fontRenderer.FONT_HEIGHT;
            break;
        case BOT_LEFT:
            x = 0;
            y = this.getMaxSize().getHeight() - fontRenderer.FONT_HEIGHT;
            break;
        case MIDDLE_RIGHT:
            x = this.getMaxSize().getWidth() - calculatedSize.getWidth();
            y = this.getMaxSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
            break;
        case MIDDLE_CENTER:
            x = this.getMaxSize().getWidth() / 2 - calculatedSize.getWidth() / 2;
            y = this.getMaxSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
            break;
        case MIDDLE_LEFT:
            x = 0;
            y = this.getMaxSize().getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2;
            break;
        case TOP_RIGHT:
            x = this.getMaxSize().getWidth() - calculatedSize.getWidth();
            y = 0;
            break;
        case TOP_CENTER:
            x = this.getMaxSize().getWidth() / 2 - calculatedSize.getWidth() / 2;
            y = 0;
            break;
        case TOP_LEFT:
        default:
            x = 0;
            y = 0;
            break;
        }

        for (String line : lines) {
            renderer.drawString(x, y, this.isEnabled() ? this.getColor() : this.getDisabledColor(), line);
            y += fontRenderer.FONT_HEIGHT;
        }
    }
}
