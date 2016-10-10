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

import java.util.HashMap;
import java.util.UUID;

public class VanishFeather implements Listener {

    public static HashMap<UUID, String> invisible = new HashMap<UUID, String>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item.getType().equals(Material.FEATHER)) {
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if(!player.hasPermission("staffmode.use")) {
                    return;
                }
                if(invisible.containsKey(player.getUniqueId())) {
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        players.showPlayer(player);
                    }
                    player.sendMessage(ChatColor.RED + "StaffMode: " + ChatColor.GREEN + "You have unvanished.");
                } else {
                    invisible.put(player.getUniqueId(), player.getName());
                    for (Player players : Bukkit.getOnlinePlayers()) {
                        if(players.hasPermission("staffmode.use")) {
                            continue;
                        }
                        players.hidePlayer(player);
                    }
                    player.sendMessage(ChatColor.RED + "StaffMode: " + ChatColor.GREEN + "You have vanished.");
                }
            }
        }
    }
}
