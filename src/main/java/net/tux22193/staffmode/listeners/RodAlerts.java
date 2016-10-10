package net.tux22193.staffmode.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RodAlerts implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item.getType().equals(Material.BLAZE_ROD)) {
            if(item.getItemMeta().getDisplayName().equals(ChatColor.RED + "Toggle Alerts")) {
                player.performCommand("alerts");
            }
        }
    }
}
