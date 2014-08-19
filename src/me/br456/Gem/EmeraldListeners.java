package me.br456.Gem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class EmeraldListeners implements Listener{
	
	private SettingsManager settings = SettingsManager.getInstance();
	private GemManagerAPI api = GemManagerAPI.getAPI();

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		int aogpe = settings.getConfig().getInt("Amount of gems per emerald");
		if(!player.hasPermission("gem.emerald.deposit")) return;
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(player.getItemInHand()==null) return;
			if(player.getItemInHand().getItemMeta()==null) return;
			String hand=player.getItemInHand().getItemMeta().getDisplayName();
			if(hand==null) return;
			String currency=api.getCustomizer().getCurrency().getItemMeta().getDisplayName();
			if(hand==currency) {
				player.sendMessage("§a§lEconomy Balance§7»§r§a +"+aogpe+" "+api.getCustomizer().getColoredCurrencyName());
				player.getInventory().removeItem(api.getCustomizer().getCurrency());
				player.updateInventory();
				
				int current = api.getGems(name);
				int newbal = current + aogpe;
				
				GemDisplay.updateScoreboard(player, newbal);
				api.setGems(name, newbal);
				event.setCancelled(true);
			} else if(player.getItemInHand().getType().equals(api.getCustomizer().getCurrency().getType()) && 
					!settings.getConfig().getBoolean("Custom Emerald")) {
				player.sendMessage("§a§lEconomy Balance§7§l»§r§a +"+aogpe+" "+api.getCustomizer().getColoredCurrencyName());
				player.getInventory().removeItem(new ItemStack(api.getCustomizer().getCurrency().getType(), 1));
				player.updateInventory();
				
				int current = api.getGems(name);
				int newbal = current + aogpe;
				
				GemDisplay.updateScoreboard(player, newbal);
				api.setGems(name, newbal);
				event.setCancelled(true);
			}
		}
	}

}
