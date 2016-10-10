package net.tux22193.staffmode.commands;

import net.tux22193.staffmode.StaffMode;
import net.tux22193.staffmode.listeners.VanishFeather;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

public class StaffCommand implements CommandExecutor, Listener {

    private StaffMode instance;
    private static HashMap<Player, ItemStack[]> inventory = new HashMap<Player, ItemStack[]>();
    public static HashMap<UUID, String> enabled = new HashMap<UUID, String>();
    private static VanishFeather vanishFeather;
    private static HashMap<Player, ItemStack[]> armor = new HashMap<Player, ItemStack[]>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("staff")) {
            if (!(sender instanceof Player)) {
                instance.getLogger().info("Console cannot perform this command.");
            }
            Player player = (Player) sender;
            if (!player.hasPermission("staffmode.use")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to perform this command.");
                return true;
            }
            if (enabled.containsKey(player.getUniqueId())) {
                enabled.remove(player.getUniqueId());
                player.getInventory().clear();
                player.sendMessage(ChatColor.GREEN + "You have left staff mode.");
                player.setGameMode(GameMode.SURVIVAL);
                player.getInventory().setContents(inventory.get(player));
                player.getInventory().setArmorContents(armor.get(player));
            } else {
                // If the player is enabling staff mode.
                enabled.put(player.getUniqueId(), player.getName());
                inventory.put(player, player.getInventory().getContents());
                armor.put(player, player.getInventory().getArmorContents());
                VanishFeather.invisible.put(player.getUniqueId(), player.getName());
                player.setGameMode(GameMode.CREATIVE);
                for (Player players : Bukkit.getOnlinePlayers()) {
                    if(players.hasPermission("staffmode.use")) {
                        continue;
                    }
                    players.hidePlayer(player);
                }
                player.sendMessage(ChatColor.RED + "StaffMode: " + ChatColor.GREEN + "You have been vanished.");
            }
                player.getInventory().clear();

                // Adds items to player's inventories.
                player.getInventory().setItem(4, createCarpet());
            }
        return false;
    }

    public ItemStack createVanish(Player player) {
        ItemStack vanish = new ItemStack(Material.FEATHER, 1);
        ItemMeta feather = vanish.getItemMeta();
        if(vanishFeather.invisible.containsKey(player.getUniqueId())) {
            feather.setDisplayName(ChatColor.GREEN + "Vanished");
        } else {
            feather.setDisplayName(ChatColor.RED + "Unvanished");
        }
        vanish.setItemMeta(feather);
        return vanish;
    }
    public ItemStack createFreeze() {
        ItemStack freeze = new ItemStack(Material.PACKED_ICE, 1);
        ItemMeta ice = freeze.getItemMeta();
        ice.setDisplayName(ChatColor.BLUE + "Freeze Player");
        freeze.setItemMeta(ice);
        return freeze;
    }
    public ItemStack createRod() {
        ItemStack alerts = new ItemStack(Material.BLAZE_ROD, 1);
        ItemMeta blaze = alerts.getItemMeta();
        blaze.setDisplayName(ChatColor.RED + "Toggle Alerts");
        alerts.setItemMeta(blaze);
        return alerts;
    }
    public ItemStack createRandomTP() {
        ItemStack teleportation = new ItemStack(Material.COMPASS, 1);
        ItemMeta compass = teleportation.getItemMeta();
        compass.setDisplayName(ChatColor.GOLD + "Random Teleport");
        teleportation.setItemMeta(compass);
        return teleportation;
    }
    public ItemStack createCarpet() {
        ItemStack carpet = new ItemStack(Material.CARPET, 1);
        ItemMeta carpetMeta = carpet.getItemMeta();
        carpetMeta.setDisplayName(ChatColor.RED + "Staff Mode");
        carpet.setItemMeta(carpetMeta);
        return carpet;
    }
}

