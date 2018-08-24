package mastery.ui.custom.elements.impl;

import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.AbstractGuiContainer;
import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.function.Draggable;

/**
 * @author Subaro
 */
public class UIPanel extends AbstractGuiContainer<UIPanel> implements Draggable {

    private GuiPanel childPanel;

    public UIPanel() {
        this.childPanel = new GuiPanel();
        this.childPanel.setSize(500, 500);
    }

    /*
     * (non-Javadoc)
     *
     * @see de.johni0702.minecraft.gui.container.AbstractGuiContainer#draw(de.johni0702.minecraft.gui.GuiRenderer,
     * org.lwjgl.util.ReadableDimension, de.johni0702.minecraft.gui.RenderInfo)
     */
    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {

        super.draw(renderer, size, renderInfo);
    }

    @Override
    protected UIPanel getThis() {
        return this;
    }

    @Override
    public boolean mouseClick(ReadablePoint position, int button) {
        return false;
    }

    @Override
    public boolean mouseDrag(ReadablePoint position, int button, long timeSinceLastCall) {
        return false;
    }

    @Override
    public boolean mouseRelease(ReadablePoint position, int button) {
        return false;
    }

}
