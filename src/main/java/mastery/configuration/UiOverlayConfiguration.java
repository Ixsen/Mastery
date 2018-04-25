package mastery.configuration;

import net.minecraftforge.common.config.Config;

public class UiOverlayConfiguration {
	
	@Config.Name("X Position Offset")
	@Config.Comment("Determines the gap between the left side of the monitor and the overlay")
	public int uiOffsetX = 3;
	
	@Config.Name("Y Position Offset")
	@Config.Comment("Determines the gap between the top of the monitor and the overlay")
	public int uiOffsetY = 3;

	@Config.Name("Overlay Time")
	@Config.Comment("Determines how long the overlay is shown after getting exp.\n 0 means no overlay\n 1000 = 1 second\n 5000 = 5 seconds usw...")
	public int overlayTime = 5000;

	@Config.Name("Transparency")
	@Config.RangeDouble(min = 0, max = 1)
	@Config.Comment("Determines the transparency of the overlay.\n 0 = completely transparent\n 1 = no transparency")
	public double uiAlphaValue = 0.6;
	
	@Config.Name("Show Mastery Name")
	@Config.Comment("If activated the name of the current category is shown besides the level bar")
	public boolean uiShowTitle = true;
	
	@Config.Name("Show Current Exp Text")
	@Config.Comment("If activated the the current exp and the exp required for the next level are displayed inside the exp bar as text")
	public boolean uiShowCurrentExp = true;
	
	@Config.Name("Active")
	@Config.Comment("Activates the overlay")
	public boolean isActive = true;
	
}
