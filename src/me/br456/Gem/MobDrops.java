package me.br456.Gem;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class MobDrops implements Listener{
	
	private SettingsManager settings = SettingsManager.getInstance();
	private Material drop;

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if(settings.getConfig().getBoolean("Mob Drops.Enabled") == false) return;
		drop=GemManagerAPI.getAPI().getCustomizer().getCurrency().getType();
		Entity entity = event.getEntity();
		if(event.getEntity().getType() == EntityType.ZOMBIE) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Zombie.Min"), settings.getConfig().getInt("Mob Drops.Zombie.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.SKELETON) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Skeleton.Min"), settings.getConfig().getInt("Mob Drops.Skeleton.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.CREEPER) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Creeper.Min"), settings.getConfig().getInt("Mob Drops.Creeper.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.ENDERMAN) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Enderman.Min"), settings.getConfig().getInt("Mob Drops.Enderman.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.BLAZE) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Blaze.Min"), settings.getConfig().getInt("Mob Drops.Blaze.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.MAGMA_CUBE) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Magma Cube.Min"), settings.getConfig().getInt("Mob Drops.Magma Cube.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.PIG_ZOMBIE) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Zombie Pigmen.Min"), settings.getConfig().getInt("Mob Drops.Zombie Pigmen.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.SLIME) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Slime.Min"), settings.getConfig().getInt("Mob Drops.Slime.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.WITHER_SKULL) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Wither Skeleton.Min"), settings.getConfig().getInt("Mob Drops.Wither Skeleton.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.WITHER) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Wither.Min"), settings.getConfig().getInt("Mob Drops.Wither.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.ENDER_DRAGON) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Ender Dragon.Min"), settings.getConfig().getInt("Mob Drops.Ender Dragon.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.GHAST) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Ghast.Min"), settings.getConfig().getInt("Mob Drops.Ghast.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.SPIDER) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Spider.Min"), settings.getConfig().getInt("Mob Drops.Spider.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.CAVE_SPIDER) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Cave Spider.Min"), settings.getConfig().getInt("Mob Drops.Cave Spider.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.SILVERFISH) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Silverfish.Min"), settings.getConfig().getInt("Mob Drops.Silverfish.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
		if(event.getEntity().getType() == EntityType.WITCH) {
			int dropnum = Utils.randInt(settings.getConfig().getInt("Mob Drops.Witch.Min"), settings.getConfig().getInt("Mob Drops.Witch.Max"));
			ItemStack drops = new ItemStack(drop, dropnum);
			entity.getWorld().dropItem(entity.getLocation(), drops);
		}
	}
}
