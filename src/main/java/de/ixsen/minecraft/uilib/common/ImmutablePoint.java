package de.ixsen.minecraft.uilib.common;

import org.lwjgl.util.Point;

public class ImmutablePoint {
    private int x;
    private int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ImmutablePoint(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
