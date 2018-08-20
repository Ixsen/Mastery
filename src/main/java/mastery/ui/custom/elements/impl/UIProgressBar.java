package mastery.ui.custom.elements.impl;

import org.lwjgl.util.ReadableColor;
import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.element.advanced.AbstractGuiProgressBar;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.FontRenderer;

/**
 * @author Subaro
 */
public class UIProgressBar extends AbstractGuiProgressBar<UIProgressBar> {

    @Getter
    @Setter
    protected int borderSize = 2;
    @Getter
    @Setter
    protected ReadableColor borderColor = ReadableColor.BLACK;
    @Getter
    @Setter
    protected ReadableColor backgroundColor = ReadableColor.WHITE;
    @Getter
    @Setter
    protected ReadableColor progressColor = ReadableColor.GREY;
    @Getter
    @Setter
    protected ReadableColor textColor = ReadableColor.BLACK;

    public UIProgressBar() {
    }

    public UIProgressBar(GuiContainer container) {
        super(container);
    }

    @Override
    protected UIProgressBar getThis() {
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.johni0702.minecraft.gui.element.advanced.AbstractGuiProgressBar#setProgress(float)
     */
    @Override
    public UIProgressBar setProgress(float progress) {
        if (progress >= 1) {
            progress = 1;
        }
        if (progress <= 0) {
            progress = 0;
        }
        super.setProgress(progress);
        return getThis();
    }

    public void increaseBy(float value) {
        setProgress(getProgress() + value);
    }

    public void decreaseBy(float value) {
        setProgress(getProgress() - value);
    }

    public void setBorderSize(int value) {
        borderSize = value;
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        FontRenderer fontRenderer = getMinecraft().fontRenderer;
        int width = size.getWidth();
        int height = size.getHeight();
        int barTotalWidth = width - 2 * borderSize;
        int barDoneWidth = (int) (barTotalWidth * getProgress());

        renderer.drawRect(0, 0, width, height, borderColor); // Border
        renderer.drawRect(borderSize, borderSize, barTotalWidth, height - 2 * borderSize, backgroundColor); // Background
        renderer.drawRect(borderSize, borderSize, barDoneWidth, height - 2 * borderSize, progressColor); // Progress

        String text = String.format(getLabel(), (int) (getProgress() * 100));
        renderer.drawCenteredString(width / 2, size.getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2, textColor, text);
    }

}
