package me.br456.Gem;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class SettingsManager {
	 
    private SettingsManager() { }
   
    static SettingsManager instance = new SettingsManager();
   
    public static SettingsManager getInstance() {
            return instance;
    }
   
    Plugin p;
    public Plugin getPlugin() {
    	return p;
    }
   
    FileConfiguration config;
    File cfile;
   
    FileConfiguration data;
    File dfile;
    
    FileConfiguration lang;
    File lfile;
    
    FileConfiguration uuids;
    File ufile;
   
    public void setup(Plugin p) {
    	cfile = new File(p.getDataFolder(), "config.yml");
        config = p.getConfig();
       
        if (!p.getDataFolder().exists()) {
        		p.saveDefaultConfig();
                p.getDataFolder().mkdir();
        }
           
            dfile = new File(p.getDataFolder(), "data.yml");
           
            if (!dfile.exists()) {
                    try {
                            dfile.createNewFile();
                    }
                    catch (IOException e) {
                            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create data.yml!");
                    }
            }
            
            lfile = new File(p.getDataFolder(), "lang.yml");
            if (!lfile.exists()) {
            	 try {
                     lfile.createNewFile();
            	 }
            	 catch (IOException e) {
                     Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create lang.yml!");
            	 }
            }
            
            ufile = new File(p.getDataFolder(), "uuids.yml");
            
            if (!ufile.exists()) {
                    try {
                            ufile.createNewFile();
                    }
                    catch (IOException e) {
                            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create uuids.yml!");
                    }
            }
            
            if (!cfile.exists()) {
                try {
                        cfile.createNewFile();
                }
                catch (IOException e) {
                        Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create config.yml!");
                }
            }
           
            data = YamlConfiguration.loadConfiguration(dfile);
            lang = YamlConfiguration.loadConfiguration(lfile);
            uuids = YamlConfiguration.loadConfiguration(ufile);
            
            lang.addDefault("tma", "Too many arguments!");
            lang.addDefault("c", "Commands");
            lang.addDefault("b", "Balance");
            lang.addDefault("aap", "Must specify amount and player!");
            lang.addDefault("cnf", "Could not find");
            lang.addDefault("p", "Must specify a player!");
            lang.addDefault("nvn", "is not a valid number!");
            lang.addDefault("ming", "The minimum gems is 0!");
            lang.addDefault("maxg", "The maximum gems is 1,000,000!");
            lang.addDefault("nnn", "Can not subtract negative numbers!");
            lang.addDefault("inf", "has insufficient funds");
            lang.addDefault("e", "Emerald");
            saveLang();
            
            lang.options().copyDefaults(true);
            saveLang();
            config = YamlConfiguration.loadConfiguration(cfile);
    }
   
    public FileConfiguration getData() {
            return data;
    }
   
    public void saveData() {
            try {
                    data.save(dfile);
            }
            catch (IOException e) {
                    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save data.yml!");
            }
    }
   
    public void reloadData() {
            data = YamlConfiguration.loadConfiguration(dfile);
    }
    
    public FileConfiguration getLang() {
        return lang;
    }

    public void saveLang() {
        try {
                lang.save(lfile);
        }
        catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save lang.yml!");
        }
    }

    public void reloadLang() {
        lang = YamlConfiguration.loadConfiguration(lfile);
    }
    
    //UUID
    public FileConfiguration getUUID() {
        return uuids;
    }

    public void saveUUID() {
        try {
                uuids.save(ufile);
        }
        catch (IOException e) {
                Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save uuids.yml!");
        }
    }

    public void reloadUUIDS() {
        uuids = YamlConfiguration.loadConfiguration(ufile);
    }
    //END UUID
   
    public FileConfiguration getConfig() {
            return config;
    }
   
    public void saveConfig() {
            try {
                    config.save(cfile);
            }
            catch (IOException e) {
                    Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save config.yml!");
            }
    }
   
    public void reloadConfig() {
            config = YamlConfiguration.loadConfiguration(cfile);
    }
   
    public PluginDescriptionFile getDesc() {
            return p.getDescription();
    }
    
    public int getBalance(String name) {
    	if(Gem.isSQLEnabled() == true) {
    		return Gem.getMySQL().getGems(getUUID(name));
    	} else {
        	return data.getInt(getUUID(name));
    	}
    }
    
    public void addBalance(String name, double amnt) {
    	if(amnt < 0) return;
    	if(amnt + getBalance(name) > 1000000) return;
    	setBalance(name, getBalance(name) + amnt);
    }
    
    public boolean removeBalance(String name, double amnt) {
    	if(getBalance(name) - amnt < 0) return false;
    	if(amnt < 0) return false;
    	setBalance(name, getBalance(name) - amnt);
    	return true;
    }
    
    public void setBalance(String name, double d) {
    	if(d>1000000||d<0) return;
    	Player player = Bukkit.getServer().getPlayerExact(name);
    	if(Gem.isSQLEnabled() == true) {
			Gem.getMySQL().setGems(getUUID(name), (int)Math.round(d));
    		if(player == null) {
    			return;
    		}
    		Bukkit.getServer().getPluginManager().callEvent(new BalanceChangeEvent(player));
    		GemDisplay.updateScoreboard(player, d);		
    	} else {
    		data.set(getUUID(name), d);
        	saveData();
    		if(player == null) {
    			return;
    		}
    		Bukkit.getServer().getPluginManager().callEvent(new BalanceChangeEvent(player));
        	GemDisplay.updateScoreboard(player, d);
    	}
    }
    
    public boolean doesExist(String name) {
    	if(Gem.isSQLEnabled() == true) {
    		return Gem.getMySQL().exists(getUUID(name));
    	} else {
    		return data.contains(getUUID(name));
    	}
    }
    
    private String getUUID(String name) {
    	return uuids.getString(name);
    }
}