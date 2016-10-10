package net.tux22193.staffmode.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class VanishFeather implements Listener {

    private List<String> invisible;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item.getType().equals(Material.FEATHER)) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Vanish")) {
                    for(Player players : Bukkit.getOnlinePlayers()) {
                        if(players.hasPermission("staffmode.showvanish")) {
                            continue;
                        }
                        players.hidePlayer(player);
                        invisible.add(player.getName());
                    }
                    player.sendMessage(ChatColor.GREEN + "You have been vanished!");
                } else {
                    invisible.remove(player.getName());
                    for (Player players : Bukkit.getServer().getOnlinePlayers()) {
                        players.showPlayer(player);
                    }
                }
            }
        }
    }
}
