/* UnitedShops - A Bukkit 1.8 plugin for shop menus.
    Copyright (C) 2015 Adrian Schollmeyer

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.github.nexadn.unitedshops.config;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.nexadn.unitedshops.UnitedShops;
import io.github.nexadn.unitedshops.shop.ShopInventory;
import io.github.nexadn.unitedshops.shop.ShopObject;

/** Container for the shop config file
 * @author NexAdn
 */
public class ConfigShopMain extends ConfigBase {
	private HashMap<String, ShopInventory> menus;		// Menu container
	
	public ConfigShopMain(UnitedShops plugin) {
		super(plugin, "shops");
		menus = new HashMap<String, ShopInventory>();
	}
	
	/** Parse the config file and save all data in a HashMap */ 
	public void parseConfig()
	{
		Set<String> kies = super.getSubKeys();
		for( String s:kies )
		{
			String title = super.getMainSection().getString(s + ".title"); // shops.[key].title
			if( title.equals("exampleshop")) { // Don't use the example configuration
				continue;
			}
			Material icon = Material.getMaterial(super.getMainSection().getString(s + ".iconitem")); // shops.[key].iconitem
			this.menus.put(s, new ShopInventory(title, new ItemStack(icon, 1)) );
			String sect = super.getWorkKey() + "." + s + "." + "items";
			Set<String> subkies = super.getConf().getConfigurationSection(sect).getKeys(false);
			for( String sub:subkies ) // shops.[key].items.[key2]
			{
				String path = super.getWorkKey() + "." + s + "." + "items" + "." + sub; // replacement for sec
				//ConfigurationSection sec = super.getConf().getConfigurationSection(sub);
				//Material mat = Material.getMaterial(sec.getString("item")); // shops.[key].items.[key2].item // ERROR ??? //
				Material mat = Material.getMaterial(sub);
				ShopObject cont = new ShopObject(mat, super.getConf().getDouble(path + ".buy"), super.getConf().getDouble("sell")); // Shop Contents
				this.menus.get(s).addContent(cont);
			}
		}
	}
	
	public List<ShopInventory> getMenus() 
	{
		List<ShopInventory> temp = new Vector<ShopInventory>();
		Collection<ShopInventory> inv = this.menus.values();
		temp.addAll(inv);
		return temp;
	}
}
