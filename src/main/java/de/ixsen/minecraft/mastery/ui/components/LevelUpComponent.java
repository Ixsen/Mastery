package de.ixsen.minecraft.mastery.ui.components;

import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.elements.Label;
import de.ixsen.minecraft.uilib.elements.container.VerticalContainer;

public class LevelUpComponent extends VerticalContainer {

    private final Label masteryLabel;
    private final Label bonusLabel;
    private final Label levelGainLabel;

    public LevelUpComponent() {
        this.masteryLabel = new Label("Mastery", ReadableColor.WHITE, 1f, Alignment.TOP_RIGHT);
        this.levelGainLabel = new Label("Mastery", ReadableColor.WHITE, 1f, Alignment.TOP_RIGHT);
        this.bonusLabel = new Label("Mastery", ReadableColor.WHITE, 1f, Alignment.TOP_RIGHT);

        this.addElements(this.masteryLabel, this.levelGainLabel, this.bonusLabel);
    }

    public void setMasteryInfo(MasteryClass mastery) {
        this.masteryLabel.setText(mastery.getName());
        this.levelGainLabel.setText(String.format("%d -> %d", mastery.getLevel() - 1, mastery.getLevel()));
        this.bonusLabel.setText(mastery.getBonusDescription());
    }
}
