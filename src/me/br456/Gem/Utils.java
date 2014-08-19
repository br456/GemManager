package me.br456.Gem;

import java.util.Random;

import org.bukkit.Bukkit;

public class Utils {
	
	public static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static void sendError(int code) {
		System.out.println("GemManager Error: Code "+code);
		System.out.println("Details: ");
		System.out.println("  GemManager Version: 1.7");
		System.out.println("  GemManager Modded: "+!Gem.getCustomizer().commandsEnabled());
		System.out.println("  Bukkit Version: "+Bukkit.getServer().getBukkitVersion());
		System.out.println("  Java Version: "+System.getProperty("java.version"));
		System.out.println("  JVM Version: "+System.getProperty("java.vm.specification.version"));
		System.out.println("  OS Name: "+System.getProperty("os.name"));
		System.out.println("  OS Architecture: "+System.getProperty("os.arch"));
		System.out.println("  OS Version: "+System.getProperty("os.version"));
	}
}
