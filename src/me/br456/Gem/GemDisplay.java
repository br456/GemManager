package me.br456.Gem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class GemDisplay implements Listener{
	private static SettingsManager settings = SettingsManager.getInstance();
	private static GemManagerAPI api = GemManagerAPI.getAPI();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String name = player.getName();
		
		Scoreboard board= Bukkit.getServer().getScoreboardManager().getNewScoreboard();
		Objective obj = board.registerNewObjective("economyBoard", "dummy");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		obj.setDisplayName(name);
		obj.getScore(Bukkit.getOfflinePlayer(api.getCustomizer().getColoredCurrencyName()+"s")).setScore(api.getGems(name));
				
		if(settings.getConfig().getBoolean("Scoreboard") == true) {
			player.setScoreboard(board);
		}

	}
	
	public static void updateScoreboard(Player player, double d) {
		if(settings.getConfig().getBoolean("Scoreboard") == false) {
			return;
		}
		int b = (int)Math.round(d);
		Scoreboard board = player.getScoreboard();
		Objective gemsObj = board.getObjective("economyBoard");
		gemsObj.getScore(Bukkit.getOfflinePlayer(api.getCustomizer().getColoredCurrencyName()+"s")).setScore(b);
	}	
}
