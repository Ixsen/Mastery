package de.ixsen.minecraft.mastery.ui.components;

import de.ixsen.minecraft.uilib.elements.basic.UIAlignment;
import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.uilib.elements.basic.UILabel;
import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.layout.HorizontalLayout;
import de.ixsen.minecraft.uilib.layout.VerticalLayout;

public class ExperienceComponent extends UIContainer {

    private final UILabel levelLabel;
    private final UILabel masteryLabel;

    public ExperienceComponent() {
        super();
        this.setLayout(new VerticalLayout());

        this.levelLabel = new UILabel("BLa", ReadableColor.WHITE, 1f, UIAlignment.MIDDLE_CENTER);
         this.levelLabel.setSize(20, 20);
         this.levelLabel.setBackgroundColor(ReadableColor.GREY);

        this.masteryLabel = new UILabel("Bku", ReadableColor.WHITE, 1f, UIAlignment.MIDDLE_CENTER);
        this.masteryLabel.setSize(20, 20);
        this.masteryLabel.setBackgroundColor(ReadableColor.GREY);

        UIContainer uiContainer = new UIContainer();
        uiContainer.setLayout(new HorizontalLayout());
        uiContainer.addElement(this.levelLabel, this.masteryLabel);

        this.addElement(this.levelLabel, this.masteryLabel);
    }

    public void setMasteryInfo(MasteryClass masteryLabel) {
        this.levelLabel.setText(String.valueOf(masteryLabel.getLevel()));
        this.masteryLabel.setText(masteryLabel.getName());
    }
}
