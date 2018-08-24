package mastery.ui.custom.elements.impl;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.util.Point;
import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.layout.LayoutData;
import mastery.capability.skillclasses.MasterySpec;
import mastery.resource.UIBackgroundUtils;
import mastery.resource.UITabUtils;
import mastery.ui.custom.UIElementHelper;
import mastery.ui.custom.layouts.FreeformLayout;

/**
 * @author Subaro
 */
public class UISkillTree extends GuiPanel {
    private static int BACKGROUND_WIDTH = 252;
    private static int BACKGROUND_HEIGHT = 140;

    private static int SCROLL_PANEL_PADDING_TOP = 16;
    private static int SCROLL_PANEL_PADDING_LEFT = 8;
    private static int SCROLL_PANEL_PADDING_RIGHT = 8;
    private static int SCROLL_PANEL_PADDING_BOT = 5;

    private UIDraggableScrollContainer scrollPanel;
    GuiPanel scrollPanelLayer;
    List<UISlot> slotList;

    public UISkillTree() {
        this.setVisible(false);
        this.setLayout(new FreeformLayout());
        this.slotList = new ArrayList<>();

        // Add Placeholder for scrollPane
        this.scrollPanelLayer = new GuiPanel();
        this.scrollPanelLayer.setLayout(new FreeformLayout());
        this.addElements(null, this.scrollPanelLayer);

        // Init Background
        this.initBackground();

        // Init slots
        this.initSlots();
    }

    private void refreshSkillTable(UISlot actuatorSlot) {
        // Remove panel if already created
        if (this.scrollPanel != null) {
            this.scrollPanelLayer.removeElement(this.scrollPanel);
        }

        MasterySpec mastery = MasterySpec.valueOf(actuatorSlot.getId());

        // Init scrollable panel
        this.scrollPanel = new UIDraggableScrollContainer();
        // this.scrollPanel.setBackgroundColor(Colors.LIGHT_GRAY);
        this.scrollPanel.setLayout(new FreeformLayout());

        // Set Size (essential for scroll container)
        this.scrollPanel.setSize(BACKGROUND_WIDTH - SCROLL_PANEL_PADDING_RIGHT - SCROLL_PANEL_PADDING_LEFT,
                BACKGROUND_HEIGHT - SCROLL_PANEL_PADDING_TOP - SCROLL_PANEL_PADDING_BOT);

        // Add background
        UIRepeatableBackgroundImage repeatableImages = new UIRepeatableBackgroundImage(mastery);
        repeatableImages.setTextureSize(16, 16).setUV(0, 0).setUVSize(16, 16);
        this.scrollPanel.addElements(new FreeformLayout.Data(0, 0), repeatableImages);

        // Add a layout extender
        UILayoutExtender extender = new UILayoutExtender();
        this.scrollPanel.addElements(
                new FreeformLayout.Data(this.scrollPanel.getMinSize().getWidth() + 10,
                        this.scrollPanel.getMinSize().getHeight() + 10),
                extender);

        ReadableDimension dim = this.scrollPanel.getLayout().calcMinSize(this.scrollPanel);
        repeatableImages.setSize(dim.getWidth(), dim.getHeight());
        this.scrollPanelLayer.addElements(
                new FreeformLayout.Data(SCROLL_PANEL_PADDING_LEFT,
                        SCROLL_PANEL_PADDING_TOP + UITabUtils.TAB_SIZE.getY() - 4),
                this.scrollPanel);
    }

    private void initBackground() {
        GuiPanel containerAndTitle = UIBackgroundUtils
                .getAdvancementBackgroundWithTitle(new Point(BACKGROUND_WIDTH + 1, BACKGROUND_HEIGHT),
                        "Skill Progress");
        this.addElements(new FreeformLayout.Data(0, UITabUtils.TAB_SIZE.getY() - 4),
                containerAndTitle);
    }

    private void initSlots() {
        UISlotGroup group = new UISlotGroup();

        int y = BACKGROUND_HEIGHT + UITabUtils.TAB_SIZE.getX() - 4;
        int x = 0;

        // BottomLeft
        UISlot slotBottom = UIElementHelper.createMasterySlotsLeft(group, MasterySpec.ALCHEMY, UITabUtils.TAB_SIZE,
                false);
        this.addElements(new FreeformLayout.Data(x, y), slotBottom);
        this.slotList.add(slotBottom);

        // Top Left
        UISlot slotTop = UIElementHelper.createMasterySlotsLeft(group, MasterySpec.ALCHEMY, UITabUtils.TAB_SIZE, true);
        this.addElements(new FreeformLayout.Data(x, 0), slotTop);
        slotTop.setActive(true);
        this.slotList.add(slotTop);

        // Middle Slots
        for (int i = 0; i < 4; i++) {
            x += UITabUtils.TAB_SIZE.getX() + 17;
            UISlot slotiBottom = UIElementHelper.createMasterySlotsMiddle(group, MasterySpec.ALCHEMY,
                    UITabUtils.TAB_SIZE,
                    false);
            this.addElements(new FreeformLayout.Data(x, y), slotiBottom);
            UISlot slotiTop = UIElementHelper.createMasterySlotsMiddle(group, MasterySpec.ALCHEMY, UITabUtils.TAB_SIZE,
                    true);
            this.addElements(new FreeformLayout.Data(x, 0), slotiTop);
            this.slotList.add(slotiBottom);
            this.slotList.add(slotiTop);
        }

        x += UITabUtils.TAB_SIZE.getX() + 17;
        // Bottom Right
        UISlot slotBottomRight = UIElementHelper.createMasterySlotsRight(group, MasterySpec.ALCHEMY,
                UITabUtils.TAB_SIZE, false);
        this.addElements(new FreeformLayout.Data(x, y), slotBottomRight);
        this.slotList.add(slotBottomRight);

        // Top Left
        UISlot slotTopRight = UIElementHelper.createMasterySlotsRight(group, MasterySpec.ALCHEMY, UITabUtils.TAB_SIZE,
                true);
        this.addElements(new FreeformLayout.Data(x, 0), slotTopRight);
        this.slotList.add(slotTopRight);

        // Iterate all slots, create the images, add the click listeners
        for (int i = 0; i < this.slotList.size(); i++) {
            UISlot slot = this.slotList.get(i);
            slot.setId("" + MasterySpec.values()[i]);

            LayoutData data = this.getLayoutData(slot);
            if (data != null && data instanceof FreeformLayout.Data) {
                FreeformLayout.Data freeFormData = (FreeformLayout.Data) data;
                this.addElements(
                        new FreeformLayout.Data(freeFormData.getX() + UITabUtils.TAB_SIZE.getX() / 2 - 8,
                                freeFormData.getY() + +UITabUtils.TAB_SIZE.getY() / 2 - 8),
                        UIElementHelper.getMasteryIcon(MasterySpec.values()[i], 16, 16));
            }
            slot.onClick(() -> this.refreshSkillTable(slot));
        }

        this.refreshSkillTable(slotTop);
    }
}
