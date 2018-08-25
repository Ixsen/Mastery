package mastery.ui.custom.elements.impl;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.AbstractGuiScrollable;
import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.function.Draggable;

/**
 * @author Subaro
 */
public class UIDraggableScrollContainer extends AbstractGuiScrollable<UIDraggableScrollContainer> implements Draggable {

    private ReadablePoint lastPoint;

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

    protected boolean isMouseHovering(ReadablePoint pos) {
        return pos.getX() > 0 && pos.getY() > 0
                && pos.getX() < this.getLastSize().getWidth() && pos.getY() < this.getLastSize().getHeight();
    }

    @Override
    public boolean mouseClick(ReadablePoint position, int button) {
        Point mouse = new Point(position);
        if (this.getContainer() != null) {
            this.getContainer().convertFor(this, mouse);
        }
        if (this.isMouseHovering(mouse) && this.isEnabled()) {
            try {
                this.lastPoint = position;
            } catch (Exception e) {
                // TODO Quickfix ignore event
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean mouseDrag(ReadablePoint position, int button, long timeSinceLastCall) {
        if (this.lastPoint == null) {
            return false;
        }
        try {
            Point newPos = new Point(position.getX() - this.lastPoint.getX(),
                    position.getY() - this.lastPoint.getY());
            this.scrollBoth(newPos);
            this.lastPoint = position;

        } catch (Exception e) {
            // TODO Quickfix ignore event
        }
        return false;
    }

    @Override
    public boolean mouseRelease(ReadablePoint position, int button) {
        try {
            this.lastPoint = null;
        } catch (Exception e) {
            // TODO Quickfix ignore event
        }
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
