package me.br456.Gem;

import me.br456.Gem.Gem.Customizer;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GemManagerAPI {
	
	private SettingsManager settings = SettingsManager.getInstance();
	
	public static GemManagerAPI getAPI() {
		return new GemManagerAPI();
	}
	
	public GemManagerAPI() {
		
	}
	
	/**
	 * Returns the amount of gems the player has
	 * @param name - Player to check gems from
	 * @return Amount of gems the player has
	 */
	public int getGems(String name) {
		return settings.getBalance(name);
	}
	
	/**
	 * Set gems of a player
	 * @param name - Player to set the gems of
	 * @param amnt - What to set the gems to
	 */
	public void setGems(String name, int amnt) {
		settings.setBalance(name, amnt);
	}
	
	/**
	 * Add gems to a player's balance
	 * @param name - Player to add gems to
	 * @param amnt - Amount of gems to add to player's balance
	 */
	public void addGems(String name, int amnt) {
		settings.addBalance(name, amnt);
	}
	
	/**
	 * Subtracts gems from a player's balance
	 * @param name - Player to subtract gems from
	 * @param amnt - Amount of gems to subtract from the player
	 * @return If transaction succesful
	 */
	public boolean subtractGems(String name, int amnt) {
		return settings.removeBalance(name, amnt);
	}
	
	/**
	 * Check if player has specified amount of gems or more
	 * @param name - Player to check for gems
	 * @param amnt - Amount of gems to check for
	 * @return If player has the amount of gems or more
	 */
	public boolean hasGems(String name, int amnt) {
		if(settings.getBalance(name) >= amnt) {
			return true;
		} else return false;
	}
	
	/**
	 * Refreshed the scoreboard of the specified player
	 * @param name - Player to refresh the scoreboard of
	 * @return If scoreboard updating was successful
	 */
	public boolean refreshScoreboard(String name) {
		Player p = Bukkit.getServer().getPlayerExact(name);
		if(p==null) return false;
		GemDisplay.updateScoreboard(p, getGems(name));
		return true;
	}
	
	/**
	 * Gives the customizer set in the main class
	 * @return The implementation of customizer
	 */
	public Customizer getCustomizer() {
		return Gem.getCustomizer();
	}
	
	/**
	 * Gives the Language file
	 * @return Instance of the language file
	 */
	public FileConfiguration getLang() {
		FileConfiguration lang = settings.getLang();
		return lang;
	}
	
}
