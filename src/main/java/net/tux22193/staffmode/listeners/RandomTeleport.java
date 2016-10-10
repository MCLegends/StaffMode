package net.tux22193.staffmode.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class RandomTeleport implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if(item.getType().equals(Material.COMPASS)) {
            if(!player.hasPermission("staffmode.use")) {
                return;
            }
            Random random = new Random();
            ArrayList<Player> players = new ArrayList<Player>();
            for (Player online : Bukkit.getOnlinePlayers()) {
                if(online == player) {
                    return;
                } else {
                    players.add(online);
                }
                int index = random.nextInt(players.size());
                Player target = (Player)players.get(index);
                player.teleport(target);
                player.sendMessage(ChatColor.GREEN + "You have been teleported to " + target.getName());
            }
        }
    }
}
