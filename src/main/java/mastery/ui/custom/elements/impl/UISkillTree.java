package mastery.ui.custom.elements.impl;

import org.lwjgl.util.ReadableDimension;

import de.johni0702.minecraft.gui.GuiRenderer;
import de.johni0702.minecraft.gui.RenderInfo;
import de.johni0702.minecraft.gui.container.GuiPanel;
import de.johni0702.minecraft.gui.element.GuiButton;
import de.johni0702.minecraft.gui.utils.Colors;
import mastery.ui.custom.elements.impl.UIRepeatableBackgroundImage.UIRepeatableBackgroundImageTypes;
import mastery.ui.custom.layouts.FreeformLayout;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;

/**
 * @author Subaro
 */
public class UISkillTree extends GuiPanel {
    private static final ResourceLocation WINDOW = new ResourceLocation("textures/gui/advancements/window.png");
    private static final ResourceLocation TABS = new ResourceLocation("textures/gui/advancements/tabs.png");

    private static int BACKGROUND_WIDTH = 252;
    private static int BACKGROUND_HEIGHT = 140;

    private static int SCROLL_PANEL_PADDING_TOP = 16;
    private static int SCROLL_PANEL_PADDING_LEFT = 9;
    private static int SCROLL_PANEL_PADDING_RIGHT = 9;
    private static int SCROLL_PANEL_PADDING_BOT = 5;

    private UIDraggableScrollContainer scrollPanel;

    public UISkillTree() {
        this.setVisible(false);
        this.setLayout(new FreeformLayout());

        this.setSize(BACKGROUND_WIDTH, BACKGROUND_HEIGHT);

        // Init Background
        this.initBackground();
    }

    private void initBackground() {
        // Init scrollable panel
        this.scrollPanel = new UIDraggableScrollContainer();
        // this.scrollPanel.setBackgroundColor(Colors.LIGHT_GRAY);
        this.scrollPanel.setLayout(new FreeformLayout());

        // Set Size
        this.scrollPanel.setSize(BACKGROUND_WIDTH - SCROLL_PANEL_PADDING_RIGHT - SCROLL_PANEL_PADDING_LEFT,
                BACKGROUND_HEIGHT - SCROLL_PANEL_PADDING_TOP - SCROLL_PANEL_PADDING_BOT);

        // Add background
        UIRepeatableBackgroundImage repeatableImages = new UIRepeatableBackgroundImage(
                UIRepeatableBackgroundImageTypes.HUSBANDRY);
        repeatableImages.setTextureSize(16, 16).setUV(0, 0).setUVSize(16, 16);
        this.scrollPanel.addElements(new FreeformLayout.Data(0, 0), repeatableImages);

        GuiButton button = new GuiButton(this.scrollPanel);
        button.setLabel("Hallo");
        button.onClick(() -> this.getMinecraft().player.sendChatMessage("LOOOOL"));

        // Add a layout extender
        UILayoutExtender extender = new UILayoutExtender();
        this.scrollPanel.addElements(
                new FreeformLayout.Data(BACKGROUND_WIDTH - SCROLL_PANEL_PADDING_RIGHT - SCROLL_PANEL_PADDING_LEFT,
                        BACKGROUND_HEIGHT - SCROLL_PANEL_PADDING_TOP - SCROLL_PANEL_PADDING_BOT),
                extender);

        ReadableDimension dim = this.scrollPanel.getLayout().calcMinSize(this.scrollPanel);
        repeatableImages.setSize(dim.getWidth(), dim.getHeight());

        this.addElements(new FreeformLayout.Data(SCROLL_PANEL_PADDING_LEFT, SCROLL_PANEL_PADDING_TOP),
                this.scrollPanel);
    }

    @Override
    public void draw(GuiRenderer renderer, ReadableDimension size, RenderInfo renderInfo) {
        super.draw(renderer, size, renderInfo);
        this.renderWindow(renderer);
    }

    public void renderWindow(GuiRenderer renderer) {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        RenderHelper.disableStandardItemLighting();
        renderer.bindTexture(WINDOW);
        renderer.drawTexturedRect(0, 0, 0, 0, BACKGROUND_WIDTH, BACKGROUND_HEIGHT, 252, 140, 256, 256);

        // if (this.tabs.size() > 1) {
        // this.mc.getTextureManager().bindTexture(TABS);
        //
        // for (GuiAdvancementTab guiadvancementtab : this.tabs.values()) {
        // if (guiadvancementtab.getPage() == tabPage) {
        // guiadvancementtab.drawTab(p_191934_1_, p_191934_2_, guiadvancementtab == this.selectedTab);
        // }
        // }
        //
        // GlStateManager.enableRescaleNormal();
        // GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
        // GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
        // GlStateManager.DestFactor.ZERO);
        // RenderHelper.enableGUIStandardItemLighting();
        //
        // for (GuiAdvancementTab guiadvancementtab1 : this.tabs.values()) {
        // if (guiadvancementtab1.getPage() == tabPage) {
        // guiadvancementtab1.drawIcon(p_191934_1_, p_191934_2_, this.itemRender);
        // }
        // }
        //
        // GlStateManager.disableBlend();
        // }
        renderer.drawString(8, 6, Colors.BLACK, "Skill Progress");
        GlStateManager.popMatrix();
    }
}
