package net.tux22193.staffmode;

import net.tux22193.staffmode.commands.StaffCommand;
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
    }
}
