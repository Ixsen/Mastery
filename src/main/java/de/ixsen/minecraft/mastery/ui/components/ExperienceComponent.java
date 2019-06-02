package de.ixsen.minecraft.mastery.ui.components;

import org.lwjgl.util.ReadableColor;

import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasteryClass;
import de.ixsen.minecraft.mastery.capability.player.skillclasses.MasterySpec;
import de.ixsen.minecraft.mastery.resource.MasteryImageLoader;
import de.ixsen.minecraft.uilib.common.Alignment;
import de.ixsen.minecraft.uilib.common.Orientation;
import de.ixsen.minecraft.uilib.elements.Image;
import de.ixsen.minecraft.uilib.elements.Label;
import de.ixsen.minecraft.uilib.elements.ProgressBar;
import de.ixsen.minecraft.uilib.elements.TiledImage;
import de.ixsen.minecraft.uilib.elements.container.HorizontalContainer;
import de.ixsen.minecraft.uilib.elements.container.VerticalContainer;
import de.ixsen.minecraft.uilib.elements.core.data.ImageData;

public class ExperienceComponent extends VerticalContainer {

    private final Label levelLabel;
    private final Label masteryLabel;
    private final Image masteryIcon;
    private ProgressBar progressBar;

    public ExperienceComponent() {
        ImageData imageData = new ImageData(MasteryImageLoader.getImage(MasterySpec.COMBAT), 0, 0, 8, 8, 8, 8);
        this.masteryIcon = new TiledImage(imageData);
        this.masteryIcon.setSize(8, 8);
        this.masteryIcon.setBorderColor(ReadableColor.BLACK);

        this.levelLabel = new Label("42", ReadableColor.WHITE, 1f, Alignment.TOP_RIGHT);
        this.levelLabel.setScale(0.5f);

        this.masteryLabel = new Label("Mastery", ReadableColor.WHITE, 1f, Alignment.TOP_RIGHT);
        this.masteryLabel.setScale(0.5f);

        HorizontalContainer levelMasteryContainer = new HorizontalContainer(2, 2, 2, 1, 3);
        levelMasteryContainer.addElements(this.levelLabel, this.masteryLabel);

        HorizontalContainer topBar = new HorizontalContainer();
        topBar.setBackgroundColor(ReadableColor.GREY);
        topBar.addElements(this.masteryIcon, levelMasteryContainer);

        this.progressBar = new ProgressBar(Orientation.HORIZONTAL, ReadableColor.BLUE);
        this.progressBar.setSize(100, 5);
        this.progressBar.setBackgroundColor(ReadableColor.LTGREY);
        this.progressBar.setBorderColor(ReadableColor.BLACK);

        this.containedElements.clear();
        this.addElements(topBar, this.progressBar);
    }

    public void setMasteryInfo(MasteryClass mastery) {
        this.levelLabel.setText(String.valueOf(mastery.getLevel()));
        this.masteryLabel.setText(mastery.getName());
        this.masteryIcon.setResourceLocation(MasteryImageLoader.getImage(mastery.getSkillClass()));
        this.progressBar.setProgressData(0, mastery.getExperience(), mastery.getNextLevelExp());
    }

}
