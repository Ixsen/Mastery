package mastery.resource;

import org.lwjgl.util.Point;

import de.johni0702.minecraft.gui.container.GuiPanel;
import mastery.capability.MasterySkills;
import mastery.ui.custom.elements.impl.UIImage;
import mastery.ui.custom.elements.impl.UILabel;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.layouts.FreeformLayout;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UISkillPreviewUtils {

    private static final ResourceLocation WIDGETS = new ResourceLocation("textures/gui/advancements/widgets.png");
    private static final int WIDGETS_TEXTURE_SIZE = 256;

    public static Point SKILL_SIZE = new Point(22, 26);
    public static Point SKILL_BADGE_YELLOW_UV = new Point(54, 128);
    public static Point SKILL_BADGE_SLIVER_UV = new Point(54, 154);

    public static UIImage getSkillBadgeYellow(int width, int height) {
        UIImage skill = new UIImage();
        skill.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        skill.setUV(SKILL_BADGE_YELLOW_UV);
        skill.setUVSize(SKILL_SIZE);
        skill.setSize(width, height);
        return skill;
    }

    public static UIImage getSkillBadgeSilver(int width, int height) {
        UIImage skill = new UIImage();
        skill.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        skill.setUV(SKILL_BADGE_SLIVER_UV);
        skill.setUVSize(SKILL_SIZE);
        skill.setSize(width, height);
        return skill;
    }

    public static UIImage getSkillBadgeYellowWithTooltip(MasterySkills skill, int width, int height) {
        UIImage badgeImage = new UIImage();
        badgeImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        badgeImage.setUV(SKILL_BADGE_YELLOW_UV);
        badgeImage.setUVSize(SKILL_SIZE);
        badgeImage.setSize(width, height);

        GuiPanel panel = new GuiPanel();
        panel.setLayout(new FreeformLayout());

        int textAreaHeight = 10;
        // Description for the mastery skill
        GuiPanel text = UIElementHelper.getMultiLabel(skill.getDescription(), 190);
        textAreaHeight += text.calcMinSize().getHeight();

        GuiPanel barDescription = getBlackBar(textAreaHeight + 8);
        panel.addElements(new FreeformLayout.Data(0, 20 - 8), barDescription);
        int y = 10;
        barDescription.addElements(new FreeformLayout.Data(5, y), text);
        y += text.getMinSize().getHeight() + 15;

        GuiPanel bar = getYellowBar(20);
        panel.addElements(new FreeformLayout.Data(0, 0), bar);

        UILabel skillName = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        skillName.setText(skill.getName());
        skillName.setSize(200, 20);
        panel.addElements(new FreeformLayout.Data(0, 0), skillName);

        badgeImage.setTooltip(panel);
        return badgeImage;
    }

    public static UIImage getSkillBadgeSilverWithTooltip(MasterySkills skill, int width, int height) {
        UIImage badgeImage = new UIImage();
        badgeImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        badgeImage.setUV(SKILL_BADGE_SLIVER_UV);
        badgeImage.setUVSize(SKILL_SIZE);
        badgeImage.setSize(width, height);

        GuiPanel panel = new GuiPanel();
        panel.setLayout(new FreeformLayout());

        int textAreaHeight = 10;
        // Description for the mastery skill
        GuiPanel text = UIElementHelper.getMultiLabel(skill.getDescription(), 190);
        textAreaHeight += text.calcMinSize().getHeight();

        // Label 'Effect:'
        UILabel effectTitle = new UILabel();
        effectTitle.setText("Effect:");
        textAreaHeight += text.calcMinSize().getHeight();

        // Description for the mastery skill effect
        GuiPanel textEffect = UIElementHelper.getMultiLabel(skill.getEffect(), 190);
        textAreaHeight += textEffect.calcMinSize().getHeight();

        GuiPanel barDescription = getBlackBar(textAreaHeight + 13);
        panel.addElements(new FreeformLayout.Data(0, 20 - 8), barDescription);
        int y = 10;
        barDescription.addElements(new FreeformLayout.Data(5, y), text);
        y += text.getMinSize().getHeight() + 15;
        barDescription.addElements(new FreeformLayout.Data(5, y), effectTitle);
        y += effectTitle.getMinSize().getHeight() + 3;
        barDescription.addElements(new FreeformLayout.Data(10, y), textEffect);

        GuiPanel bar = getBlueBar(20);
        panel.addElements(new FreeformLayout.Data(0, 0), bar);

        UILabel skillName = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        skillName.setText(skill.getName());
        skillName.setSize(200, 20);
        panel.addElements(new FreeformLayout.Data(0, 0), skillName);

        badgeImage.setTooltip(panel);
        return badgeImage;
    }

    public static GuiPanel getYellowBar(int height) {
        GuiPanel panel = new GuiPanel();
        panel.setLayout(new FreeformLayout());
        panel.setSize(200, height);

        // Top
        UIImage topImage = new UIImage();
        topImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        topImage.setUV(0, 3);
        topImage.setUVSize(200, 5);
        topImage.setSize(200, 6);
        // Top
        UIImage midImage = new UIImage();
        midImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        midImage.setUV(0, 8);
        midImage.setUVSize(200, 5);
        midImage.setSize(200, height - 10);
        // // Bot
        UIImage botImage = new UIImage();
        botImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        botImage.setUV(0, 19);
        botImage.setUVSize(200, 5);
        botImage.setSize(200, 6);

        panel.addElements(new FreeformLayout.Data(0, 0), topImage);
        panel.addElements(new FreeformLayout.Data(0, 5), midImage);
        panel.addElements(new FreeformLayout.Data(0, height - 6), botImage);

        return panel;
    }

    public static GuiPanel getBlackBar(int height) {
        GuiPanel panel = new GuiPanel();
        panel.setLayout(new FreeformLayout());
        panel.setSize(200, height);

        // Top
        UIImage topImage = new UIImage();
        topImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        topImage.setUV(0, 55);
        topImage.setUVSize(200, 5);
        topImage.setSize(200, 6);
        // Top
        UIImage midImage = new UIImage();
        midImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        midImage.setUV(0, 57);
        midImage.setUVSize(200, 5);
        midImage.setSize(200, height - 10);
        // // Bot
        UIImage botImage = new UIImage();
        botImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        botImage.setUV(0, 72);
        botImage.setUVSize(200, 5);
        botImage.setSize(200, 6);

        panel.addElements(new FreeformLayout.Data(0, 0), topImage);
        panel.addElements(new FreeformLayout.Data(0, 5), midImage);
        panel.addElements(new FreeformLayout.Data(0, height - 6), botImage);

        return panel;
    }

    public static GuiPanel getBlueBar(int height) {
        GuiPanel panel = new GuiPanel();
        panel.setLayout(new FreeformLayout());
        panel.setSize(200, height);

        // Top
        UIImage topImage = new UIImage();
        topImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        topImage.setUV(0, 29);
        topImage.setUVSize(200, 5);
        topImage.setSize(200, 6);
        // Top
        UIImage midImage = new UIImage();
        midImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        midImage.setUV(0, 34);
        midImage.setUVSize(200, 5);
        midImage.setSize(200, height - 10);
        // // Bot
        UIImage botImage = new UIImage();
        botImage.setTexture(WIDGETS, WIDGETS_TEXTURE_SIZE, WIDGETS_TEXTURE_SIZE);
        botImage.setUV(0, 44);
        botImage.setUVSize(200, 5);
        botImage.setSize(200, 6);

        panel.addElements(new FreeformLayout.Data(0, 0), topImage);
        panel.addElements(new FreeformLayout.Data(0, 5), midImage);
        panel.addElements(new FreeformLayout.Data(0, height - 6), botImage);

        return panel;
    }

}
