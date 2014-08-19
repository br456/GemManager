package me.br456.Gem;

import me.br456.Commands.GemCommand;
import me.br456.Gem.Updater.UpdateResult;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Gem extends JavaPlugin{
	
	private SettingsManager settings = SettingsManager.getInstance();
	private static boolean updated = false;
	private static String latestVersion = "";
	private static boolean sqlEnabled = false;
	private static MySQL mysql;
	private static Plugin plugin;
	private static Customizer c = new DefaultCustomizer();
			
	private FileConfiguration config;
	
	public void onEnable() {
		settings.setup(this);
		settings.saveConfig();
		settings.saveData();
		config = settings.getConfig();
		
		registerEvents();
		registerEconomy();
		registerCommands();
		updateConfig();
		
		plugin = this;
		
		if(settings.getConfig().getBoolean("MySQL.Enabled") == true) {
			System.out.println("Enabling MySQL");
			mysql = new MySQL(config.getString("MySQL.Host"), config.getString("MySQL.Port"),
					config.getString("MySQL.Database"), config.getString("MySQL.Username"), config.getString("MySQL.Password"));
			sqlEnabled = true;
			if(mysql.error() == false) {
				System.out.println("Error enabling MySQL. Enabling FlatFile");
				sqlEnabled = false;
			} else {
				mysql.createStatement();
			}		
			convert();
		}
		
		if(settings.getConfig().getBoolean("Auto-Update") == false) {
			getServer().getLogger().info("Auto-Updates Disabled");
			return;
		} else {
			Updater updater = new Updater(this, 67890, this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
			if(updater.getLatestType() == "Beta" || updater.getLatestType() == "Alpha") {
				getServer().getLogger().info("Update available but not downloaded due to it being a Beta or an Alpha");
				getServer().getLogger().info("If you wish to use this, please go to: " + updater.getLatestFileLink() + " and download the file");
				return;
			}
			if(updater.getResult() == UpdateResult.FAIL_NOVERSION) {
				getServer().getLogger().warning("Latest version doesn't match with your server version!");
				return;
			} else if(updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
				Updater updater2 = new Updater(this, 67890, this.getFile(), Updater.UpdateType.NO_VERSION_CHECK, true);
				if(updater2.getResult() == UpdateResult.SUCCESS) {
					getServer().getLogger().info("Update success");
				} 
				return;
			}
			return;
		}
	}
	
	public void onDisable() {
		if(sqlEnabled == true) {
			mysql.closeConnection();
		}
	}
	
	protected static Plugin getPlugin() {
		return plugin;
	}
	
	private void registerEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") != null) {
            final ServicesManager sm = getServer().getServicesManager();
            sm.register(Economy.class, new VaultConnector(), this, ServicePriority.Highest);
            getServer().getLogger().info("Registered Vault interface");
        } else {
            getServer().getLogger().warning("Vault not found. Vault support disabled");
        }
    }
	
	private void registerEvents() {
		PluginManager manager = getServer().getPluginManager();
		
		manager.registerEvents(new GemDisplay(), this);
		manager.registerEvents(new EmeraldListeners(), this);
		manager.registerEvents(new MobDrops(), this);
		manager.registerEvents(new Events(), this);
	}
	
	private void registerCommands() {
		GemCommand gemCommand= new GemCommand();
		getCommand("gem").setExecutor(gemCommand);
	}
	
	private void updateConfig() {	
		if(!config.contains("MySQL")) {
			config.set("MySQL.Enabled", false);
			config.set("MySQL.Host", "1.1.1.1");
			config.set("MySQL.Port", "3306");
			config.set("MySQL.Database", "mydatabase");
			config.set("MySQL.Username", "admin");
			config.set("MySQL.Password", "password");
			settings.saveConfig();
		}
		
		if(!config.contains("Gems on Death")) {
			config.set("Gems on Death", true);
			settings.saveConfig();
		}
		
		if(!config.contains("Scoreboard")) {
			config.set("Scoreboard", true);
			settings.saveConfig();
		}
		
		if(!config.contains("Auto-Update")) {
			config.set("Auto-Update", true);
			settings.saveConfig();
		}
		
		if(!config.contains("Amount of gems per emerald")) {
			config.set("Amount of gems per emerald", 1);
			settings.saveConfig();
		}
		
		if(!config.contains("Custom Emerald")) {
			config.set("Custom Emerald", true);
		}
		
		if(!config.contains("Mob Drops")) {
			config.set("Mob Drops.Enabled", false);
			config.set("Mob Drops.Zombie.Min", 0);
			config.set("Mob Drops.Zombie.Max", 2);
			config.set("Mob Drops.Skeleton.Min", 0);
			config.set("Mob Drops.Skeleton.Max", 2);
			config.set("Mob Drops.Creeper.Min", 0);
			config.set("Mob Drops.Creeper.Max", 2);
			config.set("Mob Drops.Enderman.Min", 1);
			config.set("Mob Drops.Enderman.Max", 3);
			config.set("Mob Drops.Blaze.Min", 1);
			config.set("Mob Drops.Blaze.Max", 3);
			config.set("Mob Drops.Magma Cube.Min", 1);
			config.set("Mob Drops.Magma Cube.Max", 4);
			config.set("Mob Drops.Zombie Pigmen.Min", 1);
			config.set("Mob Drops.Zombie Pigmen.Max", 4);
			config.set("Mob Drops.Slime.Min", 1);
			config.set("Mob Drops.Slime.Max", 2);
			config.set("Mob Drops.Wither Skeleton.Min", 2);
			config.set("Mob Drops.Wither Skeleton.Max", 4);
			config.set("Mob Drops.Wither.Min", 15);
			config.set("Mob Drops.Wither.Max", 20);
			config.set("Mob Drops.Ender Dragon.Min", 20);
			config.set("Mob Drops.Ender Dragon.Max", 30);
			config.set("Mob Drops.Ghast.Min", 1);
			config.set("Mob Drops.Ghast.Max", 3);
			config.set("Mob Drops.Spider.Min", 0);
			config.set("Mob Drops.Spider.Max", 2);
			config.set("Mob Drops.Cave Spider.Min", 1);
			config.set("Mob Drops.Cave Spider.Max", 2);
			config.set("Mob Drops.Silverfish.Min", 0);
			config.set("Mob Drops.Silverfish.Max", 1);
			config.set("Mob Drops.Witch.Min", 5);
			config.set("Mob Drops.Witch.Max", 7);
			settings.saveConfig();
		}

	}
	
	private void convert() {
		for(String name : settings.getData().getKeys(false)) {
			if(!mysql.exists(name)) {
				mysql.addPlayer(name);
				mysql.setGems(name, settings.getData().getInt(name));
			}
		}
	}
	
	protected static MySQL getMySQL() {
		return mysql;
	}
	
	protected static boolean isSQLEnabled() {
		return sqlEnabled;
	}
	
	protected static boolean updated() {
		return updated;
	}
	
	protected static String getLatestVersion() {
		return latestVersion;
	}
	
	public static void setCustomizer(Customizer customizer) {
		Bukkit.getLogger().warning("-------------------------------");
		System.out.println("GemManager Warn: A plugin is modifying GemManager");
		Bukkit.getLogger().warning("-------------------------------");
		c=customizer;
	}
	
	protected static Customizer getCustomizer() {
		return c;
	}
	
	public interface Customizer {
		public String getColoredCurrencyName();
		public String getColorlessName();
		public ItemStack getCurrency();
		public boolean commandsEnabled();
	}

}

//Thanks to Sleaker for helping with Vault integration