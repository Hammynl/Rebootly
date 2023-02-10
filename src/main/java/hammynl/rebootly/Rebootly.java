package hammynl.rebootly;

import hammynl.rebootly.commands.RebootlyCommand;
import hammynl.rebootly.listeners.MenuListener;
import hammynl.rebootly.utils.PhoneNotifier;
import hammynl.rebootly.utils.ServerTimer;
import org.bstats.bukkit.Metrics;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Rebootly extends JavaPlugin {


    private static Rebootly main;

    public static Rebootly getMain() {
        return main;
    }

    @Override
    public void onEnable() {
        main = this;

        saveDefaultConfig();
        registerCommands();
        registerEvents();

        if(getConfigBoolean("phone.enabled")) PhoneNotifier.getInstance().sendPhoneNotification(getConfigString("phone.shutdown.message"),getConfigString("phone.phone-number"), getConfigString("phone.key"));
        if(getConfigBoolean("metrics")) new Metrics(this, 17126);
        if(getConfigBoolean("restart.cron.enabled"))
        {
            ServerTimer.getInstance().createCronTimer(getConfigString("restart.cron.crontab"), this);
        }
        else
        {
            ServerTimer.getInstance().createTimer(getConfigInt("restart.seconds.value"), this);
        }
    }

    @Override
    public void onDisable() {
        if(getConfig().getBoolean("phone.enabled")) PhoneNotifier.getInstance().sendPhoneNotification(getConfigString("phone.shutdown.message"),getConfigString("phone.phone-number"), getConfigString("phone.key"));
    }

    // Registration of commands and events
    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new MenuListener(), this);
    }

    private void registerCommands() {
        new RebootlyCommand(this);
    }

    private int getConfigInt(String s) {
        return getConfig().getInt(s);
    }

    private Boolean getConfigBoolean(String s) {
        return getConfig().getBoolean(s);
    }

    // Config getters
    private String getConfigString(String s) {
        return getConfig().getString(s);
    }

    public List<String> getStringList(String s) {
        return getConfig().getStringList(s);
    }

}
