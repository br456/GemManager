package me.br456.Commands;

import me.br456.Gem.GemDisplay;
import me.br456.Gem.GemManagerAPI;
import me.br456.Gem.SettingsManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class GemCommand implements CommandExecutor{
	
	private GemManagerAPI api = GemManagerAPI.getAPI();
	private SettingsManager settings = SettingsManager.getInstance();
	private FileConfiguration lang = settings.getLang();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandlabel, String[] args) {
		
		if(sender instanceof Player) {			
			Player player = (Player) sender;
			
			if(cmd.getLabel().equalsIgnoreCase("gem")) {
				if(!api.getCustomizer().commandsEnabled()) return true;
				if(!player.hasPermission("gem.admin.command")) {
					player.sendMessage("§a§l"+lang.getString("b")+"§7»§r§a "+api.getGems(player.getName())+" "+
							api.getCustomizer().getColoredCurrencyName()+"s");
					return true;
				}
				if(args.length == 0) {
					player.sendMessage("§a§l"+lang.getString("c")+"§r§7»");
					player.sendMessage("  §2gem add §a<amnt> <player>");
					player.sendMessage("  §2gem sub §a<amnt> <player>");
					player.sendMessage("  §2gem set §a<amnt> <player>");
					player.sendMessage("  §2gem reset §a<player>");
					player.sendMessage("§a§l"+lang.getString("b")+"§7»§r§a "+api.getGems(player.getName())+" "+
							api.getCustomizer().getColoredCurrencyName()+"s");
				}
				
				if(args.length > 3) {
					player.sendMessage(ChatColor.RED + lang.getString("tma"));
					return true;
				}
				
				if(args.length == 1) {
					player.sendMessage(ChatColor.RED + lang.getString("aap"));
					return true;
				}
				
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("reset")) {
						String name = args[1];
						Player target = Bukkit.getPlayerExact(name);
						if(target == null) {
							player.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name);
							return true;
						}
						api.setGems(name, 0);

						GemDisplay.updateScoreboard(target, 0);
						
						return true;						
					}
					player.sendMessage(ChatColor.RED + lang.getString("p"));
					return true;
				}
				
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("add")) {
						String name = args[2];
						int current = api.getGems(name);
						Player target = Bukkit.getPlayer(name);
						int amnt;
						
						try {
							amnt = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED + args[1] + " "+lang.getString("nvn"));
							return true;
						}
						
						if(amnt < 0) {
							player.sendMessage(ChatColor.RED + lang.getString("ming"));
							return true;
						}
						
						if(amnt + current > 1000000) {
							player.sendMessage(ChatColor.RED + lang.getString("maxg"));
							return true;
						}
						
						if(target == null) {
							player.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name + "!");
							return true;
						} else {
							
							GemDisplay.updateScoreboard(target, amnt + current);
							
							api.addGems(name, amnt);
							return true;
						}
					}	
					if(args[0].equalsIgnoreCase("sub")) {
						String name = args[2];
						int current = api.getGems(name);
						Player target = Bukkit.getPlayer(name);
						int amnt;
						
						try {
							amnt = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED + args[1] + " "+lang.getString("nvn"));
							return true;
						}
						
						if(amnt < 0) {
							player.sendMessage(ChatColor.RED + lang.getString("nnn"));
							return true;
						}
						
						if(target == null) {
							player.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name + "!");
							return true;
						} else {
							if(amnt > current) {
								player.sendMessage(ChatColor.RED + name + " "+lang.getString("inf"));
								return true;
							} else {							
								GemDisplay.updateScoreboard(target, current - amnt);
								
								api.subtractGems(name, amnt);
								return true;
							}
						}						
					}	
					if(args[0].equalsIgnoreCase("set")) {
						String name = args[2];
						Player target = Bukkit.getPlayer(name);
						int amnt;
						
						try {
							amnt = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							player.sendMessage(ChatColor.RED + args[1] + " "+lang.getString("nvn"));
							return true;
						}
						
						if(amnt < 0) {
							player.sendMessage(ChatColor.RED + lang.getString("ming"));
							return true;
						}
						
						if(amnt > 1000000) {
							player.sendMessage(ChatColor.RED + lang.getString("maxg"));
							return true;
						}
						
						if(target == null) {
							player.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name + "!");
							return true;
						} else {							
							GemDisplay.updateScoreboard(target, amnt);
								
							api.setGems(name, amnt);
							return true;
						}
					}
				}
			}
		} else {
			if(cmd.getLabel().equalsIgnoreCase("gem")) {
				if(!api.getCustomizer().commandsEnabled()) return true;
				if(args.length == 0) {
					sender.sendMessage("§a§l"+lang.getString("c")+"§r§7»");
					sender.sendMessage("  §2gem add §a<amnt> <player>");
					sender.sendMessage("  §2gem sub §a<amnt> <player>");
					sender.sendMessage("  §2gem set §a<amnt> <player>");
					sender.sendMessage("  §2gem reset §a<player>");
				}
				
				if(args.length > 3) {
					sender.sendMessage(ChatColor.RED + lang.getString("tma"));
					return true;
				}
				
				if(args.length == 1) {
					sender.sendMessage(ChatColor.RED + lang.getString("aap"));
					return true;
				}
				
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("reset")) {
						String name = args[1];
						Player target = Bukkit.getPlayerExact(name);
						if(target == null) {
							sender.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name);
							return true;
						}
						api.setGems(name, 0);

						GemDisplay.updateScoreboard(target, 0);
						
						return true;						
					}
					sender.sendMessage(ChatColor.RED + lang.getString("p"));
					return true;
				}
				
				if(args.length == 3) {
					if(args[0].equalsIgnoreCase("add")) {
						String name = args[2];
						int current = api.getGems(name);
						Player target = Bukkit.getPlayer(name);
						int amnt;
						
						try {
							amnt = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							sender.sendMessage(ChatColor.RED + args[1] + " "+lang.getString("nvn"));
							return true;
						}
						
						if(amnt < 0) {
							sender.sendMessage(ChatColor.RED + lang.getString("ming"));
							return true;
						}
						
						if(amnt + current > 1000000) {
							sender.sendMessage(ChatColor.RED + lang.getString("maxg"));
							return true;
						}
						
						if(target == null) {
							sender.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name + "!");
							return true;
						} else {
							
							GemDisplay.updateScoreboard(target, amnt + current);
							
							api.addGems(name, amnt);
							return true;
						}
					}	
					if(args[0].equalsIgnoreCase("sub")) {
						String name = args[2];
						int current = api.getGems(name);
						Player target = Bukkit.getPlayer(name);
						int amnt;
						
						try {
							amnt = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							sender.sendMessage(ChatColor.RED + args[1] + " "+lang.getString("nvn"));
							return true;
						}
						
						if(amnt < 0) {
							sender.sendMessage(ChatColor.RED + lang.getString("nnn"));
							return true;
						}
						
						if(target == null) {
							sender.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name + "!");
							return true;
						} else {
							if(amnt > current) {
								sender.sendMessage(ChatColor.RED + name + " "+lang.getString("inf"));
								return true;
							} else {							
								GemDisplay.updateScoreboard(target, current - amnt);
								
								api.subtractGems(name, amnt);
								return true;
							}
						}						
					}	
					if(args[0].equalsIgnoreCase("set")) {
						String name = args[2];
						Player target = Bukkit.getPlayer(name);
						int amnt;
						
						try {
							amnt = Integer.parseInt(args[1]);
						} catch (NumberFormatException e) {
							sender.sendMessage(ChatColor.RED + args[1] + " "+lang.getString("nvn"));
							return true;
						}
						
						if(amnt < 0) {
							sender.sendMessage(ChatColor.RED + lang.getString("ming"));
							return true;
						}
						
						if(amnt > 1000000) {
							sender.sendMessage(ChatColor.RED + lang.getString("maxg"));
							return true;
						}
						
						if(target == null) {
							sender.sendMessage(ChatColor.RED + lang.getString("cnf")+" " + name + "!");
							return true;
						} else {							
							GemDisplay.updateScoreboard(target, amnt);
								
							api.setGems(name, amnt);
							return true;
						}
					}
				}
			}
		}
		return true;
	}
}
