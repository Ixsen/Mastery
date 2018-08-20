package mastery.ui.custom.elements.impl;

import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import lombok.Getter;
import lombok.Setter;
import mastery.capability.skillclasses.MasteryClass;
import net.minecraft.client.gui.FontRenderer;

/**
 * Shows the progress of the given mastery class. The given alpha values determines the transparency of the overall progressbar.
 *
 * Label: <currentEXP> / <nextLevelEXP>
 *
 * Predefined colors are set in the constructor. Can be changed afterwards.
 *
 * @author Subaro
 */
public class UIMasteryProgressBar extends UIProgressBar {

    @Getter
    protected MasteryClass masteryClass;
    @Getter
    @Setter
    protected boolean textmode;

    public UIMasteryProgressBar(MasteryClass masteryClass, int alpha, boolean textmode) {
        super();
        this.masteryClass = masteryClass;
        this.textmode = textmode;
        this.setBackgroundColor(new Color(255, 255, 255, alpha));
        this.setBorderColor(new Color(15, 15, 15, alpha));
        this.setTextColor(new Color(0, 0, 0, alpha));
        this.setProgressColor(new Color(15, 15, 200, alpha));
    }

    @Override
    public UIProgressBar setProgress(float progress) {
        return this;
    }

    @Override
    public float getProgress() {
        return (float) this.masteryClass.getExperience() / (float) this.masteryClass.getNextLevelExp();
    }

    @Override
    public String getLabel() {
        if (this.isTextmode()) {
            return this.masteryClass.getExperience() + " / " + this.masteryClass.getNextLevelExp();
        } else {
            return "";
        }
    }

    @Override
    public void increaseBy(float value) {
    }

    @Override
    public void decreaseBy(float value) {
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        FontRenderer fontRenderer = this.getMinecraft().fontRenderer;
        int width = size.getWidth();
        int height = size.getHeight();
        int barTotalWidth = width - 2 * this.borderSize;
        int barDoneWidth = (int) (barTotalWidth * this.getProgress());

        if (this.getBorderSize() > 0) {
            renderer.drawRect(0, 0, width, height, this.borderColor); // Border
            renderer.drawRect(this.borderSize - 1, this.borderSize - 1, barTotalWidth, height - 2 * this.borderSize,
                    this.backgroundColor); // Background
            renderer.drawRect(this.borderSize, this.borderSize, barDoneWidth, height - 2 * this.borderSize,
                    this.progressColor); // Progress
        } else {
            renderer.drawRect(0, 0, width, height, this.backgroundColor); // Background
            renderer.drawRect(0, 0, barDoneWidth, height, this.progressColor); // Progress
        }

        if (this.isTextmode()) {
            renderer.drawCenteredString(width / 2, size.getHeight() / 2 - fontRenderer.FONT_HEIGHT / 2, this.textColor,
                    this.getLabel());
        }
    }
}
