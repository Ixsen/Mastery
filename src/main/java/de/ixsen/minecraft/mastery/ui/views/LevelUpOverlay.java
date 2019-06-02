package de.ixsen.minecraft.mastery.ui.views;

import de.ixsen.minecraft.mastery.ui.components.LevelUpComponent;
import de.ixsen.minecraft.uilib.elements.core.UiOverlay;

public class LevelUpOverlay extends UiOverlay<LevelUpComponent> {

    @Override
    protected LevelUpComponent createScreenContainer() {
        return new LevelUpComponent();
    }
}
