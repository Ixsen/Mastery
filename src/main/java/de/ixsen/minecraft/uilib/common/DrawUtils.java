package de.ixsen.minecraft.uilib.common;

import net.minecraft.client.gui.Gui;

public class DrawUtils {

    /**
     * Draws a Border around the given
     */
    public static void drawBorder(int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            int j = top;
            top = bottom;
            bottom = j;
        }

        Gui.drawRect(left, top, right, top, color);
        Gui.drawRect(left, bottom, right, bottom, color);
        Gui.drawRect(left, top, left, bottom, color);
        Gui.drawRect(right, top, right, bottom, color);
    }
}
