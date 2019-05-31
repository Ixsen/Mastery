package de.ixsen.minecraft.mastery.ui.components;

import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.common.Orientation;
import de.ixsen.minecraft.uilib.elements.Label;
import de.ixsen.minecraft.uilib.elements.ProgressBar;
import de.ixsen.minecraft.uilib.elements.container.GuiContainer;
import de.ixsen.minecraft.uilib.layout.HorizontalLayout;
import de.ixsen.minecraft.uilib.layout.VerticalLayout;

public class ExperienceComponent extends GuiContainer {

    private final Label levelLabel;
    private final Label masteryLabel;

    public ExperienceComponent() {
        super();
        // this.setLayout(new VerticalLayout());

        this.levelLabel = new Label("BLa", ReadableColor.WHITE, 1f, Alignment.MIDDLE_CENTER);
        this.levelLabel.setBackgroundColor(ReadableColor.GREY);

        this.masteryLabel = new Label("Bku", ReadableColor.WHITE, 1f, Alignment.MIDDLE_CENTER);
        this.masteryLabel.setBackgroundColor(ReadableColor.GREY);

        GuiContainer container = new GuiContainer();
        container.setLayout(new HorizontalLayout());
        container.addElement(this.levelLabel, this.masteryLabel);

        ProgressBar progressBar = new ProgressBar(Orientation.HORIZONTAL, ReadableColor.BLUE);
        progressBar.setSize(50, 10);
        progressBar.setBackgroundColor(ReadableColor.BLACK);

        GuiContainer guiContainer = new GuiContainer();
        guiContainer.setLayout(new VerticalLayout());
        guiContainer.addElement(container, this.levelLabel, this.masteryLabel);

        this.addElement(progressBar, guiContainer);
    }

    public void setMasteryInfo(MasteryClass masteryLabel) {
        this.levelLabel.setText(String.valueOf(masteryLabel.getLevel()));
        this.masteryLabel.setText(masteryLabel.getName());
    }

    @Override
    public void draw(int parentX, int parentY, int mouseX, int mouseY, float partialTicks) {
        super.draw(parentX, parentY, mouseX, mouseY, partialTicks);
    }
}
