package mastery.items;

import mastery.MasteryItems;
import mastery.MasteryMod;
import mastery.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Is the superclass for all custom items of the mastery mod. Makes life easier
 * by providing a common naming structure for items and by implmenting the
 * {@linkplain IHasModel} interface.
 * 
 * @author Subaro
 */
public class ItemBase extends Item implements IHasModel {

	/**
	 * Creates an item in the {@linkplain CreativeTabs.MATERIAL} tab.
	 * 
	 * @param name
	 *            Name for the item
	 */
	public ItemBase(String name) {
		this(name, CreativeTabs.MATERIALS);
	}

	/**
	 * Creates an item in the given {@linkplain CreativeTabs} tab.
	 * 
	 * @param name
	 *            Name for the item
	 * @param tab
	 *            Tab for the item
	 */
	public ItemBase(String name, CreativeTabs tab) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);

		MasteryItems.ALL_ITEMS.add(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mastery.util.IHasModel#registerModel()
	 */
	@Override
	public void registerModel() {
		MasteryMod.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
