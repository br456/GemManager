package me.br456.Gem;

import java.util.List;

import org.bukkit.OfflinePlayer;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;

public class VaultConnector implements Economy{
	
	//SettingsManager settings = SettingsManager.getInstance();
	GemManagerAPI api = GemManagerAPI.getAPI();

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	@Override
	public boolean createPlayerAccount(String name) {
		api.setGems(name, 0);
		return true;
	}

	@Override
	public boolean createPlayerAccount(String name, String world) {
		return createPlayerAccount(name);
	}

	@Override
	public String currencyNamePlural() {
		return api.getCustomizer().getColorlessName()+"s";
	}

	@Override
	public String currencyNameSingular() {
		return api.getCustomizer().getColorlessName();
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse depositPlayer(String name, double amnt) {
		api.addGems(name, (int)amnt);
		return new EconomyResponse(amnt, api.getGems(name), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(String name, String world, double amnt) {
		return depositPlayer(name, amnt);
	}

	@Override
	public String format(double amnt) {
		return String.valueOf(amnt) + api.getCustomizer().getColoredCurrencyName()+"s";
	}

	@Override
	public int fractionalDigits() {
		return 0;
	}

	@Override
	public double getBalance(String name) {
		return api.getGems(name);
	}

	@Override
	public double getBalance(String name, String world) {
		return getBalance(name);
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public String getName() {
		return "GemManager";
	}

	@Override
	public boolean has(String name, double amnt) {
		return api.getGems(name) >= amnt;
	}

	@Override
	public boolean has(String name, String world, double amnt) {
		return has(name, amnt);
	}

	@Override
	public boolean hasAccount(String arg0) {
		return false;
	}

	@Override
	public boolean hasAccount(String arg0, String arg1) {
		return false;
	}

	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	@Override
	public boolean isEnabled() {
		Gem.getPlugin().isEnabled();
		return false;
	}

	@Override
	public EconomyResponse withdrawPlayer(String name, double amnt) {
		return new EconomyResponse(amnt, api.getGems(name) - amnt, api.subtractGems(name, (int)amnt) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse withdrawPlayer(String name, String world, double amnt) {
		return withdrawPlayer(name, amnt);
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer p) {
		api.setGems(p.getName(), 0);
		return true;
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer p, String world) {
		return createPlayerAccount(p);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer p, double amnt) {
		api.addGems(p.getName(), (int)amnt);
		return new EconomyResponse(amnt, api.getGems(p.getName()), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer p, String world,
			double amnt) {
		return depositPlayer(p,amnt);
	}

	@Override
	public double getBalance(OfflinePlayer p) {
		return api.getGems(p.getName());
	}

	@Override
	public double getBalance(OfflinePlayer p, String world) {
		return getBalance(p);
	}

	@Override
	public boolean has(OfflinePlayer p, double amnt) {
		return api.getGems(p.getName()) >= amnt;
	}

	@Override
	public boolean has(OfflinePlayer p, String world, double amnt) {
		return has(p,amnt);
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0) {
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer arg0, String arg1) {
		return false;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer p, double amnt) {
		String name = p.getName();
		return new EconomyResponse(amnt, api.getGems(name) - amnt, api.subtractGems(name, (int)amnt) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer p, String world,
			double amnt) {
		String name = p.getName();
		return new EconomyResponse(amnt, api.getGems(name) - amnt, api.subtractGems(name, (int)amnt) ? ResponseType.SUCCESS : ResponseType.FAILURE, "Insufficient funds.");
	}

}
