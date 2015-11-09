package io.github.nexadn.unitedshops;

import java.util.logging.Level;

import org.bukkit.Server;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.nexadn.unitedshops.command.ShopGUIHandler;
import io.github.nexadn.unitedshops.command.UShopDebug;
import io.github.nexadn.unitedshops.tradeapi.EcoManager;

public class UnitedShops extends JavaPlugin {
	
	public static Server server;
	
	@Override
	public void onEnable()
	{
		UnitedShops.server = getServer();
		
		// Hook into Vault
		if ( !EcoManager.initEco() ) {
			// Economy nicht eingestellt...
			UnitedShops.server.getLogger().log(Level.SEVERE, "The Economy hook couldn't be initialized. Is Vault missing?");
		}
		
		// Commande executors
		UnitedShops.server.getPluginCommand("ushopdebug").setExecutor(new UShopDebug());										// /ushopdebug
		//UnitedShops.server.getPluginCommand("ushop").setExecutor(new io.github.nexadn.unitedshops.command.ShopGUIHandler());	// /ushop
		
	}
	
	@Override
	public void onDisable()
	{
		
	}
}

/*
	TODO: 
	- [DONE] Testbefehl hinzufügen
	- Testbefehl Executor registrieren
	- GUI vervollständigen
	- EventHandler einstellen
	- CommandExecutor setzen
	- Permissions
	- UnitedShops.class: Variablen hinzufügen
*/