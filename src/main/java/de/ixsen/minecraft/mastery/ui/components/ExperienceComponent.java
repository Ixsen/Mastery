package de.ixsen.minecraft.mastery.ui.components;

import de.ixsen.minecraft.uilib.elements.basic.UIButton;
import de.ixsen.minecraft.uilib.elements.core.UIContainer;
import de.ixsen.minecraft.uilib.layout.VerticalLayout;

public class ExperienceComponent extends UIContainer {

    public ExperienceComponent() {
        super();

        this.setLayout(new VerticalLayout());

        UIButton whatup = new UIButton("Whatup");

        UIButton whatup2 = new UIButton("Whatup2");
        this.addElement(whatup, whatup2);
    }
}
