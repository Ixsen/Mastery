package mastery.ui.views;

import org.lwjgl.util.ReadableColor;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.container.VanillaGuiScreen;
import de.johni0702.minecraft.gui.layout.VerticalLayout;
import de.johni0702.minecraft.gui.utils.Colors;
import mastery.capability.skillclasses.MasterySpec;
import mastery.ui.custom.UIElementHelper;
import mastery.ui.custom.elements.impl.UIImage;
import mastery.ui.custom.elements.impl.UILabel;
import mastery.ui.custom.elements.impl.UILabel.UILabelAlignment;
import mastery.ui.custom.elements.impl.UIMasteryPreview;
import mastery.ui.custom.elements.impl.UISkillTree;
import mastery.ui.custom.elements.impl.UISlot;
import mastery.ui.custom.elements.impl.UISlot.UIMainSlotTypes;
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
    private UISkillTree skillPanel;
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
        UIImage background = UIElementHelper.createBackground(140, 212);
        screen.addElements(new FreeformLayout.Data(5, 5), background);

        // Init title
        UILabel title = UIElementHelper.createUILabel(UILabelAlignment.MIDDLE_CENTER, "Masteries",
                background.getMinSize().getWidth(), 16, Colors.BLACK);
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

        // Position Slots
        int slotXPosition = slots.getMinSize().getWidth() + background.getMinSize().getWidth() + 20;
        screen.addElements(new FreeformLayout.Data(slotXPosition, 5), this.skillPanel);
        screen.addElements(new FreeformLayout.Data(slotXPosition, 5), this.infoPanel);
        screen.addElements(new FreeformLayout.Data(slotXPosition, 5), this.creditsPanel);
        screen.addElements(new FreeformLayout.Data(slotXPosition, 5), this.settingsPanel);
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

        // Create Skill SLots
        this.skillTreeSlot = UIElementHelper.createMenuSlots(slotGroup, UIMainSlotTypes.SKILL_TREE, SLOT_SIZE);
        this.infoSlot = UIElementHelper.createMenuSlots(slotGroup, UIMainSlotTypes.LORE, SLOT_SIZE);
        this.creditsSlot = UIElementHelper.createMenuSlots(slotGroup, UIMainSlotTypes.AUTHOR, SLOT_SIZE);
        this.settingsSlot = UIElementHelper.createMenuSlots(slotGroup, UIMainSlotTypes.SETTINGS, SLOT_SIZE);

        // Define order of the slots
        slots.addElements(null, this.skillTreeSlot);
        slots.addElements(null, this.infoSlot);
        slots.addElements(null, this.creditsSlot);
        slots.addElements(null, this.settingsSlot);

        return slots;
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
        UIImage background = UIElementHelper.createBackground(240, 212);
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
        UIImage background = UIElementHelper.createBackground(240, 212);
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
        UIImage background = UIElementHelper.createBackground(240, 212);
        panel.addElements(new FreeformLayout.Data(0, 0), background);

        // Init title
        UILabel title = new UILabel(UILabelAlignment.MIDDLE_CENTER);
        title.setText("Lore");
        title.setSize(background.getMinSize().getWidth(), 16);
        title.setColor(ReadableColor.BLACK);
        panel.addElements(new FreeformLayout.Data(5, 0), title);

        return panel;
    }

    private UISkillTree initSkillTree() {
        UISkillTree panel = new UISkillTree();
        return panel;
    }
}
