package de.ixsen.minecraft.mastery.ui.components;

import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.uilib.elements.basic.UILabel;
import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.layout.HorizontalLayout;
import de.ixsen.minecraft.uilib.layout.VerticalLayout;

public class ExperienceComponent extends UIContainer {

    private final UILabel level;
    private final UILabel mastery;

    public ExperienceComponent() {
        super();
        this.setLayout(new VerticalLayout());

        this.level = new UILabel("BLa", ReadableColor.BLACK, 1f, UILabel.UIAlignment.MIDDLE_CENTER);
        // this.level.setSize(20, 20);
        // this.level.setBackgroundColor(ReadableColor.GREY);

        this.mastery = new UILabel("Bku", ReadableColor.BLACK, 1f, UILabel.UIAlignment.MIDDLE_CENTER);
         this.mastery.setSize(20, 20);
        this.mastery.setBackgroundColor(ReadableColor.WHITE);

        UIContainer uiContainer = new UIContainer();
        uiContainer.setLayout(new HorizontalLayout());
        uiContainer.addElement(this.mastery);

        this.addElement(uiContainer);
    }

    public ExperienceComponent(MasteryClass mastery) {
        this();
        this.setMastery(mastery);
    }

    public void setMastery(MasteryClass mastery) {
        this.level.setText(String.valueOf(mastery.getLevel()));
        this.mastery.setText(mastery.getName());
    }
}
