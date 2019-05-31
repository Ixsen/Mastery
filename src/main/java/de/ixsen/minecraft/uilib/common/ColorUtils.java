package de.ixsen.minecraft.uilib.common;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

/**
 * Class providing some colors and also providing methods to convert bidirectional.
 *
 * @author Subaro
 */
public class ColorUtils {

    public static int toInt(ReadableColor color) {
        return color.getAlpha() << 24
                | color.getRed() << 16
                | color.getGreen() << 8
                | color.getBlue();
    }

    public static ReadableColor toColor(int color) {
        return new Color(color >> 16 & 0xff, color >> 8 & 0xff, color & 0xff, color >> 24 & 0xff);
    }
}
