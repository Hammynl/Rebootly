package hammynl.rebootly;

import hammynl.rebootly.commands.RebootlyCommand;
import hammynl.rebootly.listeners.MenuListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;

public final class Rebootly extends JavaPlugin {


    private static Rebootly main;
    private int timer = 5644779;

    public int getTime() {
        return timer;
    }


    public static Rebootly getMain() {
        return main;
    }
    @Override
    public void onEnable() {
        main = this;
        saveDefaultConfig();
        // Plugin startup logic
        registerCommands();
        registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void registerCommands() {
        new RebootlyCommand();
    }
}
