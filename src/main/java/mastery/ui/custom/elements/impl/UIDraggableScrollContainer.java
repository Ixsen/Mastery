package mastery.ui.custom.elements.impl;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.AbstractGuiScrollable;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.function.Draggable;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UIDraggableScrollContainer extends AbstractGuiScrollable<UIDraggableScrollContainer> implements Draggable {

    private static final ResourceLocation BACKGROUND_ADV = new ResourceLocation(
            "textures/gui/advancements/backgrounds/adventure.png");

    private ReadablePoint lastPoint;
    private boolean renderBackgroundTexture = true;

    public UIDraggableScrollContainer() {
    }

    public UIDraggableScrollContainer(GuiContainer container) {
        super(container);
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
    }

    @Override
    protected UIDraggableScrollContainer getThis() {
        return this;
    }

    @Override
    public boolean mouseClick(ReadablePoint position, int button) {
        this.lastPoint = position;
        return false;
    }

    @Override
    public boolean mouseDrag(ReadablePoint position, int button, long timeSinceLastCall) {
        if (this.lastPoint == null) {
            this.lastPoint = position;
            return false;
        } else {
            Point newPos = new Point(position.getX() - this.lastPoint.getX(),
                    position.getY() - this.lastPoint.getY());
            this.scrollBoth(newPos);
            this.lastPoint = position;
        }
        return false;
    }

    @Override
    public boolean mouseRelease(ReadablePoint position, int button) {
        this.lastPoint = null;
        return false;
    }

    public boolean scrollBoth(ReadablePoint mousePosition) {
        Point mouse = new Point(mousePosition);
        if (this.getContainer() != null) {
            this.getContainer().convertFor(this, mouse);
        }
        this.scrollX(mousePosition.getX());
        this.scrollY(mousePosition.getY());
        return false;
    }

}
