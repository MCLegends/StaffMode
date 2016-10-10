package net.tux22193.staffmode.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FreezeIce implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item.getType().equals(Material.PACKED_ICE)) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
                    if(!(entity instanceof Player)) {
                        player.sendMessage(ChatColor.RED + "This entity is not freezable.");
                    }
                    Player target = (Player)entity;
                    player.performCommand("freeze " + target.getName());
                }
            }
        }
    }
}
