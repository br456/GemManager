package me.br456.Gem;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BalanceChangeEvent extends Event{
	
	private static final HandlerList handlers = new HandlerList();
	private int gems;
	private Player player;
	private SettingsManager settings = SettingsManager.getInstance();
	 
	public HandlerList getHandlers() {
	    return handlers;
	}
	 
	public static HandlerList getHandlerList() {
	    return handlers;
	}
	
	public BalanceChangeEvent(Player player) {
		gems = settings.getBalance(player.getName());
		this.player = player;
	}
	
	public int getGems() {
		return gems;
	}
	
	public Player getPlayer() {
		return player;
	}
}
