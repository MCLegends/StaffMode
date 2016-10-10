package net.tux22193.staffmode.commands;

import net.tux22193.staffmode.StaffMode;
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
                player.setGameMode(GameMode.CREATIVE);
                player.getInventory().clear();

                // Adds items to player's inventories.
                player.getInventory().addItem(createVanish());
                player.getInventory().addItem(createFreeze());
                player.getInventory().addItem(createRod());
                player.getInventory().addItem(createRandomTP());
            }
        }
        return false;
    }

    public ItemStack createVanish() {
        ItemStack vanish = new ItemStack(Material.FEATHER, 1);
        ItemMeta feather = vanish.getItemMeta();
        feather.setDisplayName(ChatColor.GRAY + "Vanish");
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
}

