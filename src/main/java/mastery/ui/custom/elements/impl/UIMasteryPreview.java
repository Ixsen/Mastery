package mastery.ui.custom.elements.impl;

import org.lwjgl.util.Color;
import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableColor;

import de.johni0702.minecraft.gui.container.GuiContainer;
import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.element.GuiImage;
import de.johni0702.minecraft.gui.element.GuiTexturedButton;
import de.johni0702.minecraft.gui.utils.Colors;
import lombok.Getter;
import mastery.capability.skillclasses.MasteryClass;
import mastery.capability.skillclasses.MasterySpec;
import mastery.resource.MasteryImageLoader;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.layouts.FreeformLayout;
import mastery.util.MasteryUtils;

/**
 * @author Subaro
 */
public class UIMasteryPreview extends GuiPanel {

    @Getter
    private MasterySpec masterySpec;
    @Getter
    private MasteryClass masteryClass;

    private static final int PREVIEW_WIDTH = 140;
    private static final int PREVIEW_HEIGHT = 14;
    private static final int INFO_HEIGHT = 11; // Info regarding Level and MasteryName
    private static final int LEVEL_WIDTH = 20;
    private static final int LEVEL_NAME_SPACING = 2;

    // Visual Components
    private UIMasteryProgressBar componentBar;

    public UIMasteryPreview(MasterySpec spec) {
        this.masterySpec = spec;
        this.masteryClass = MasteryUtils.getMastery(this.getMinecraft().player, spec);
        this.init();
    }

    @SuppressWarnings("rawtypes")
    public UIMasteryPreview(GuiContainer container, MasterySpec spec) {
        super(container);
        this.masterySpec = spec;
        this.masteryClass = MasteryUtils.getMastery(this.getMinecraft().player, spec);
        this.init();
    }

    @Override
    protected UIMasteryPreview getThis() {
        return this;
    }

    private void init() {
        this.setLayout(new FreeformLayout());
        this.setBackgroundColor(ReadableColor.LTGREY);

        int x = 0;
        // Add Mastery Image
        GuiImage image = this.initImage();
        this.addElements(new FreeformLayout.Data(x, 0), image);
        x += image.getMinSize().getWidth();

        // GuiPanel for expbar, name and level
        GuiPanel infoGroup = this.initMasteryInfoGroup();
        this.addElements(new FreeformLayout.Data(x, 0), infoGroup);
        x += infoGroup.getMinSize().getWidth();

        // Add Button
        GuiTexturedButton button = this.initButton();
        this.addElements(new FreeformLayout.Data(x, 0), button);

        // Add all elements to the panel
        this.setSize(this.calcMinSize());
    }

    private GuiImage initImage() {
        GuiImage imagePlaceHolder = new GuiImage();
        Point masteryUV = MasteryImageLoader.getMasteryUV(this.masterySpec);
        imagePlaceHolder.setTexture(MasteryImageLoader.masteryClassIconPlain, masteryUV.getX(), masteryUV.getY(),
                MasteryImageLoader.MASTERY_CLASS_ICON_SIZE, MasteryImageLoader.MASTERY_CLASS_ICON_SIZE);
        imagePlaceHolder.setSize(PREVIEW_HEIGHT, PREVIEW_HEIGHT);
        return imagePlaceHolder;
    }

    private GuiTexturedButton initButton() {
        GuiTexturedButton button = new GuiTexturedButton();
        button.onClick(() -> this.getMinecraft().player
                .sendChatMessage("Open " + UIMasteryPreview.this.masteryClass.getName() + " skills!"));
        Point masteryUV = MasteryImageLoader.getMasteryUV(this.masterySpec);
        button.setTexture(MasteryImageLoader.masteryClassIconBadge, MasteryImageLoader.MASTERY_CLASS_ATLAS_SIZE);
        button.setTexturePos(masteryUV.getX(), masteryUV.getY(), masteryUV.getX() + 64, masteryUV.getY() + 64,
                masteryUV.getX() + 64 + 64, masteryUV.getY() + 64 + 64);
        button.setTextureSize(MasteryImageLoader.MASTERY_CLASS_ICON_SIZE, MasteryImageLoader.MASTERY_CLASS_ICON_SIZE);
        button.setSize(PREVIEW_HEIGHT, PREVIEW_HEIGHT);
        return button;
    }

    private GuiPanel initMasteryInfoGroup() {
        GuiPanel infoGroup = new GuiPanel();
        infoGroup.setLayout(new FreeformLayout());

        GuiPanel nameLevelPanel = this.initNameAndExpPanel();
        infoGroup.addElements(new FreeformLayout.Data(0, 0), nameLevelPanel);

        // Add progressbar
        this.componentBar = new UIMasteryProgressBar(this.masteryClass, 255, false);
        this.componentBar.setProgressColor(Colors.CYAN);
        this.componentBar.setBackgroundColor(new Color(150, 150, 150, 255));
        this.componentBar.setSize(PREVIEW_WIDTH - 2 * PREVIEW_HEIGHT, PREVIEW_HEIGHT - INFO_HEIGHT).setBorderSize(0);
        infoGroup.addElements(new FreeformLayout.Data(0, INFO_HEIGHT), this.componentBar);

        infoGroup.setSize(infoGroup.calcMinSize());
        return infoGroup;
    }

    private GuiPanel initNameAndExpPanel() {
        // Add panel for name and level
        GuiPanel nameLevelPanel = new GuiPanel();
        nameLevelPanel.setLayout(new FreeformLayout());

        GuiPanel levelInfo = this.initLevelPanel();
        nameLevelPanel.addElements(new FreeformLayout.Data(0, 0), levelInfo);

        UILabel nameInfo = new UILabel(UILabelAlignment.MIDDLE_LEFT);
        nameInfo.setColor(ReadableColor.BLACK);
        nameInfo.setSize(PREVIEW_WIDTH - 2 * PREVIEW_HEIGHT - LEVEL_WIDTH - LEVEL_NAME_SPACING, INFO_HEIGHT)
                .setText("" + this.masteryClass.getName());
        nameLevelPanel.addElements(new FreeformLayout.Data(LEVEL_WIDTH + LEVEL_NAME_SPACING, 0), nameInfo);

        nameLevelPanel.setSize(nameLevelPanel.calcMinSize());
        return nameLevelPanel;
    }

    private GuiPanel initLevelPanel() {
        GuiPanel levelInfo = new GuiPanel();
        levelInfo.setLayout(new FreeformLayout());
        levelInfo.setBackgroundColor(new Color(150, 150, 150, 255));

        UILabel level = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        level.setText("" + this.masteryClass.getLevel());
        level.setSize(LEVEL_WIDTH, INFO_HEIGHT);
        level.setColor(ReadableColor.BLACK);
        levelInfo.addElements(new FreeformLayout.Data(0, 0), level);

        levelInfo.setSize(levelInfo.calcMinSize());
        return levelInfo;
    }
}
