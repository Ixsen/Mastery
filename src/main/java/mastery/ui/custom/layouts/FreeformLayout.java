package mastery.ui.custom.layouts;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.util.Dimension;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;
import org.lwjgl.util.ReadablePoint;

import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.element.GuiElement;
import de.johni0702.minecraft.gui.layout.Layout;
import de.johni0702.minecraft.gui.layout.LayoutData;
import lombok.AllArgsConstructor;

public class FreeformLayout implements Layout {
    private static final Data DEFAULT_DATA = new Data();

    public FreeformLayout() {
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Map<GuiElement, Pair<ReadablePoint, ReadableDimension>> layOut(GuiContainer<?> container,
            ReadableDimension size) {
        Map<GuiElement, Pair<ReadablePoint, ReadableDimension>> map = new LinkedHashMap<>();
        for (Map.Entry<GuiElement, LayoutData> entry : container.getElements().entrySet()) {
            GuiElement element = entry.getKey();
            Data data = entry.getValue() instanceof Data ? (Data) entry.getValue() : DEFAULT_DATA;
            Dimension elementSize = new Dimension(element.getMinSize());
            map.put(element, Pair.<ReadablePoint, ReadableDimension>of(new Point(data.x, data.y), elementSize));
        }
        return map;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ReadableDimension calcMinSize(GuiContainer<?> container) {
        int maxHeight = 0;
        int maxWidth = 0;
        for (Map.Entry<GuiElement, LayoutData> entry : container.getElements().entrySet()) {
            GuiElement element = entry.getKey();
            ReadableDimension minSize = element.getMinSize();
            LayoutData layoutData = entry.getValue();

            if (layoutData instanceof Data) {
                Data lData = (Data) layoutData;
                if (lData.x + minSize.getWidth() > maxWidth) {
                    maxWidth = lData.x + minSize.getWidth();
                }
                if (lData.y + minSize.getHeight() > maxHeight) {
                    maxHeight = lData.y + minSize.getHeight();
                }
            }
        }
        return new Dimension(maxWidth, maxHeight);
    }

    @lombok.Data
    @AllArgsConstructor
    public static class Data implements LayoutData {
        private int x;
        private int y;

        public Data() {
            this.x = 0;
            this.y = 0;
        }
    }
}
