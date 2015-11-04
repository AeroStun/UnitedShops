package io.github.nexadn.unitedshops.tradeapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class MoneyTrade {
	public static boolean tradeItemForMoney( Player player, ItemStack offer, double want )
	{
		Economy eco = null;
		EconomyResponse eReturn = null;
		double bal = eco.getBalance(player);
		if(want > bal) {
			// Weniger Geld als verlangt
			return false;
		} else {
			// Genug Geld, Trade ausführen
			eReturn = eco.withdrawPlayer(player, want);
			if( eReturn.transactionSuccess() ) {
				player.getInventory().addItem(offer);
				return true;
			}
		}
		return false;
	}
	
	public static boolean tradeMoneyForItem( Player player, double offer, ItemStack want )
	{
		Economy eco = null;
		EconomyResponse eReturn = null;
		Inventory playerinv = player.getInventory();
		if ( playerinv.contains(want)) {
			// Spieler hat das Zeugs
			eReturn = eco.depositPlayer(player, offer);
			if( eReturn.transactionSuccess() ) {
				playerinv.remove(want);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}
}
