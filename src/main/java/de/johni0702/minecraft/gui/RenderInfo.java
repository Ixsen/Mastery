/*
 * This file is part of jGui API, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2016 johni0702 <https://github.com/johni0702>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.johni0702.minecraft.gui;

import lombok.Data;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;

@Data
public class RenderInfo {
    public final float partialTick;
    public final int mouseX;
    public final int mouseY;
    public final int layer;

    public RenderInfo offsetMouse(int minusX, int minusY) {
        return new RenderInfo(this.partialTick, this.mouseX - minusX, this.mouseY - minusY, this.layer);
    }

    public RenderInfo layer(int layer) {
        return this.layer == layer ? this : new RenderInfo(this.partialTick, this.mouseX, this.mouseY, layer);
    }

    public void addTo(CrashReport crashReport) {
        CrashReportCategory category = crashReport.makeCategory("Render info details");
        category.addCrashSection("Partial Tick", this.partialTick);
        category.addCrashSection("Mouse X", this.mouseX);
        category.addCrashSection("Mouse Y", this.mouseY);
        category.addCrashSection("Layer", this.layer);
    }
}
