package me.br456.Gem;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener{
	
	private SettingsManager settings = SettingsManager.getInstance();
	private GemManagerAPI api = GemManagerAPI.getAPI();
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if(settings.getConfig().getBoolean("Gems on Death")) {
			Player player = event.getEntity();
			ItemStack drop = api.getCustomizer().getCurrency();
			if(api.getGems(player.getName())==0) return;
			drop.setAmount(api.getGems(player.getName()));
			event.getDrops().add(drop);
			api.setGems(player.getName(), 0);
		}
	}
	
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent event) {
		//Connect to SQL
		if(Gem.isSQLEnabled() == true) {
			Gem.getMySQL().establishConnection();
		}
		//Declare variables
		Player player = event.getPlayer();
		String name = player.getName();
		FileConfiguration uuids = settings.getUUID();
		//Check if player is already in database
		if(uuids.contains(name)) return;
		//Get UUID
		System.out.println("Getting UUID...");
		String uuid = player.getUniqueId().toString();
		System.out.println(uuid);
		//Check if all is well
		if(uuid==null) {
			event.disallow(Result.KICK_OTHER, "An error has occured, please contact the server administrator");
		}
		//Convert to uuid
		if(Gem.isSQLEnabled()) {
			MySQL sql = Gem.getMySQL();
			if(sql.exists(name)) {
				System.out.println("Converting " + name + " to UUID");
				System.out.println("Setting " + uuid + " to "+sql.getGems(name));
				sql.addPlayer(uuid);
				sql.setGems(uuid, sql.getGems(name));
				System.out.println("Removing old "+name+ " from database");
				sql.setGems(name, 0);
			} else {
				sql.addPlayer(uuid);
			}
		} else {
			if(settings.getData().contains(name)) {
				System.out.println("Converting " + name + " to UUID");
				settings.getData().set(uuid, settings.getData().getInt(name));
				System.out.println("setting " + uuid + " to "+settings.getData().getInt(name));
				settings.getData().set(name, null);
				settings.saveData();
			} else {
				settings.getData().set(uuid, 0);
				settings.saveData();
			}
		}
		//Save UUID to config for quick access
		if(!uuids.contains(name)) {
			for(String key : uuids.getKeys(false)) {
				if(uuids.getString(key)==uuid) {
					uuids.set(key, null);
					settings.saveUUID();
				}
			}
			uuids.set(name, uuid);
			settings.saveUUID();
		}
	}
}
