package me.br456.Gem;

import me.br456.Gem.Gem.Customizer;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DefaultCustomizer implements Customizer{
	
	protected DefaultCustomizer() {
		
	}
	
	@Override
	public String getColoredCurrencyName() {
		return ChatColor.GREEN+"Gem";
	}

	@Override
	public ItemStack getCurrency() {
		ItemStack is = new ItemStack(Material.EMERALD);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(SettingsManager.getInstance().getLang().getString("e"));
		is.setItemMeta(im);
		return is;
	}

	@Override
	public String getColorlessName() {
		return "Gem";
	}
	
	@Override
	public boolean commandsEnabled() {
		return true;
	}
	
}
