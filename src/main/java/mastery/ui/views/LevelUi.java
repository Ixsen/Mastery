package mastery.ui.views;

import org.lwjgl.util.ReadableColor;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.container.VanillaGuiScreen;
import de.johni0702.minecraft.gui.layout.VerticalLayout;
import mastery.capability.skillclasses.MasterySpec;
import mastery.resource.MasteryImageLoader;
import mastery.ui.custom.elements.impl.UIImage;
import mastery.ui.custom.elements.impl.UILabel;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.elements.impl.UIMasteryPreview;
import mastery.ui.custom.elements.impl.UISlot;
import mastery.ui.custom.elements.impl.UISlotGroup;
import mastery.ui.custom.layouts.FreeformLayout;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LevelUi extends GuiScreen {

    private static final int SLOT_SIZE = 20;

    private UISlot skillTreeSlot;
    private UISlot infoSlot;
    private UISlot creditsSlot;
    private UISlot settingsSlot;
    private GuiPanel skillPanel;
    private GuiPanel infoPanel;
    private GuiPanel creditsPanel;
    private GuiPanel settingsPanel;

    public LevelUi() {
        super();
    }

    @Override
    public void initGui() {
        VanillaGuiScreen screen = VanillaGuiScreen.setup(this);
        screen.setLayout(new FreeformLayout());

        // Init background
        UIImage background = this.initBackgroundImage(140, 212);
        screen.addElements(new FreeformLayout.Data(5, 5), background);

        // Init title
        UILabel title = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        title.setText("Masteries");
        title.setSize(background.getMinSize().getWidth(), 16);
        title.setColor(ReadableColor.BLACK);
        screen.addElements(new FreeformLayout.Data(5, 5), title);

        // Init the previews
        GuiPanel panel = this.initMasteryPreviews();
        screen.addElements(new FreeformLayout.Data(12, 20), panel);

        // Init the Slots
        GuiPanel slots = this.initSlots();
        screen.addElements(new FreeformLayout.Data(background.getMinSize().getWidth() + 5, 20), slots);

        // Init the dofferent panels
        this.skillPanel = this.initSkillTree();
        this.infoPanel = this.initLorePanel();
        this.settingsPanel = this.initSettingsPanel();
        this.creditsPanel = this.initAuthorPanel();

        // Connect slots to panels
        this.creditsSlot.onActiveChanged(this::clickSlotCredits);
        this.settingsSlot.onActiveChanged(this::clickSlotSettings);
        this.infoSlot.onActiveChanged(this::clickSlotLore);
        this.skillTreeSlot.onActiveChanged(this::clickSlotTree);

        screen.addElements(
                new FreeformLayout.Data(slots.getMinSize().getWidth() + background.getMinSize().getWidth() + 20, 5),
                this.skillPanel);
        screen.addElements(
                new FreeformLayout.Data(slots.getMinSize().getWidth() + background.getMinSize().getWidth() + 20, 5),
                this.infoPanel);
        screen.addElements(
                new FreeformLayout.Data(slots.getMinSize().getWidth() + background.getMinSize().getWidth() + 20, 5),
                this.creditsPanel);
        screen.addElements(
                new FreeformLayout.Data(slots.getMinSize().getWidth() + background.getMinSize().getWidth() + 20, 5),
                this.settingsPanel);

    }

    private void clickSlotCredits(boolean value) {
        this.creditsPanel.setVisible(value);
        if (value) {
            this.skillPanel.setVisible(false);
            this.infoPanel.setVisible(false);
            this.settingsPanel.setVisible(false);
        }
    }

    private void clickSlotTree(boolean value) {
        this.skillPanel.setVisible(value);
        if (value) {
            this.infoPanel.setVisible(false);
            this.creditsPanel.setVisible(false);
            this.settingsPanel.setVisible(false);
        }
    }

    private void clickSlotSettings(boolean value) {
        this.settingsPanel.setVisible(value);
        if (value) {
            this.skillPanel.setVisible(false);
            this.infoPanel.setVisible(false);
            this.creditsPanel.setVisible(false);
        }
    }

    private void clickSlotLore(boolean value) {
        this.infoPanel.setVisible(value);
        if (value) {
            this.skillPanel.setVisible(false);
            this.creditsPanel.setVisible(false);
            this.settingsPanel.setVisible(false);
        }
    }

    private GuiPanel initSlots() {
        GuiPanel slots = new GuiPanel();
        slots.setLayout(new VerticalLayout());

        // Create slot group
        UISlotGroup slotGroup = new UISlotGroup();

        // Skill Tree Slot
        this.skillTreeSlot = new UISlot(false, slotGroup);
        this.skillTreeSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
        this.skillTreeSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
        this.skillTreeSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_DEACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_DEACTIVE_UV.getY());
        this.skillTreeSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_ACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_TREE_ACTIVE_UV.getY());
        this.skillTreeSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
        this.skillTreeSlot.setSize(SLOT_SIZE, SLOT_SIZE);

        // Mastery Info Slot
        this.infoSlot = new UISlot(false, slotGroup);
        this.infoSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
        this.infoSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
        this.infoSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_DEACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_DEACTIVE_UV.getY());
        this.infoSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_ACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_BOOK_ACTIVE_UV.getY());
        this.infoSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
        this.infoSlot.setSize(SLOT_SIZE, SLOT_SIZE);

        // Author Slot
        this.creditsSlot = new UISlot(false, slotGroup);
        this.creditsSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
        this.creditsSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
        this.creditsSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_DEACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_DEACTIVE_UV.getY());
        this.creditsSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_ACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_ID_ACTIVE_UV.getY());
        this.creditsSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
        this.creditsSlot.setSize(SLOT_SIZE, SLOT_SIZE);

        // Settings Slot
        this.settingsSlot = new UISlot(false, slotGroup);
        this.settingsSlot.setTexture(MasteryImageLoader.masteryOverviewBackgroundSlots);
        this.settingsSlot.setTextureSize(MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SLOTS_TEXTURE_SIZE);
        this.settingsSlot.setUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_DEACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_DEACTIVE_UV.getY());
        this.settingsSlot.setActiveUV(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_ACTIVE_UV.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SETTINGS_ACTIVE_UV.getY());
        this.settingsSlot.setUVSize(MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_SLOT_SIZE.getY());
        this.settingsSlot.setSize(SLOT_SIZE, SLOT_SIZE);

        // Define order of the slots
        slots.addElements(null, this.skillTreeSlot);
        slots.addElements(null, this.infoSlot);
        slots.addElements(null, this.creditsSlot);
        slots.addElements(null, this.settingsSlot);

        return slots;
    }

    private UIImage initBackgroundImage(int width, int height) {
        UIImage background = new UIImage();
        background.setTexture(MasteryImageLoader.masteryOverviewBackground,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_TEXTURE_SIZE,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_TEXTURE_SIZE, 0, 0,
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SIZE.getX(),
                MasteryImageLoader.MASTERY_OVERVIEW_BACKGROUND_SIZE.getY());
        background.setSize(width, height);
        return background;
    }

    private GuiPanel initMasteryPreviews() {
        GuiPanel panel = new GuiPanel();
        panel.setLayout(new VerticalLayout().setSpacing(2));

        for (MasterySpec mastery : MasterySpec.values()) {
            UIMasteryPreview view = this.getPreview(mastery);
            panel.addElements(null, view);
        }

        return panel;
    }

    private UIMasteryPreview getPreview(MasterySpec mastery) {
        UIMasteryPreview preview = new UIMasteryPreview(mastery);
        return preview;
    }

    private GuiPanel initAuthorPanel() {
        GuiPanel panel = new GuiPanel();
        panel.setVisible(false);
        panel.setLayout(new FreeformLayout());

        // Init background
        UIImage background = this.initBackgroundImage(240, 200);
        panel.addElements(new FreeformLayout.Data(0, 0), background);

        // Init title
        UILabel title = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        title.setText("Credits");
        title.setSize(background.getMinSize().getWidth(), 16);
        title.setColor(ReadableColor.BLACK);
        panel.addElements(new FreeformLayout.Data(5, 0), title);

        return panel;
    }

    private GuiPanel initSettingsPanel() {
        GuiPanel panel = new GuiPanel();
        panel.setVisible(false);
        panel.setLayout(new FreeformLayout());

        // Init background
        UIImage background = this.initBackgroundImage(240, 200);
        panel.addElements(new FreeformLayout.Data(0, 0), background);

        // Init title
        UILabel title = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        title.setText("Settings");
        title.setSize(background.getMinSize().getWidth(), 16);
        title.setColor(ReadableColor.BLACK);
        panel.addElements(new FreeformLayout.Data(5, 0), title);

        return panel;
    }

    private GuiPanel initLorePanel() {
        GuiPanel panel = new GuiPanel();
        panel.setVisible(false);
        panel.setLayout(new FreeformLayout());

        // Init background
        UIImage background = this.initBackgroundImage(240, 200);
        panel.addElements(new FreeformLayout.Data(0, 0), background);

        // Init title
        UILabel title = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        title.setText("Lore");
        title.setSize(background.getMinSize().getWidth(), 16);
        title.setColor(ReadableColor.BLACK);
        panel.addElements(new FreeformLayout.Data(5, 0), title);

        return panel;
    }

    private GuiPanel initSkillTree() {
        GuiPanel panel = new GuiPanel();
        panel.setVisible(false);
        panel.setLayout(new FreeformLayout());

        // Init background
        UIImage background = this.initBackgroundImage(240, 200);
        panel.addElements(new FreeformLayout.Data(0, 0), background);

        // Init title
        UILabel title = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        title.setText("Skill");
        title.setSize(background.getMinSize().getWidth(), 16);
        title.setColor(ReadableColor.BLACK);
        panel.addElements(new FreeformLayout.Data(5, 0), title);

        return panel;
    }
}
