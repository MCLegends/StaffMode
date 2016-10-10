package net.tux22193.staffmode;

import net.tux22193.staffmode.commands.StaffCommand;
import net.tux22193.staffmode.listeners.RandomTeleport;
import net.tux22193.staffmode.listeners.RodAlerts;
import net.tux22193.staffmode.listeners.VanishFeather;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;

public class StaffMode extends JavaPlugin {

    public static List<String> enabled;
    public static List<String> getEnabled() {
        return enabled;
    }

    @Override
    public void onEnable() {
        this.getCommand("staff").setExecutor(new StaffCommand());
        Bukkit.getServer().getPluginManager().registerEvents(new RandomTeleport(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RodAlerts(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new VanishFeather(), this);
    }
}
