package hammynl.rebootly;

import hammynl.rebootly.commands.RebootlyCommand;
import hammynl.rebootly.listeners.MenuListener;
import hammynl.rebootly.utils.ServerTimer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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

        if(getConfigBoolean("phone.enabled")) sendPhoneNotification(getConfigString("phone.shutdown.message"),getConfigString("phone.phone-number"), getConfigString("phone.key"));

        ServerTimer.getInstance().createTimer(getConfigInt("time"), this);

    }

    @Override
    public void onDisable() {
        if(getConfig().getBoolean("phone.enabled")) sendPhoneNotification(getConfigString("phone.shutdown.message"),getConfigString("phone.phone-number"), getConfigString("phone.key"));
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

    // Other methods
    private void sendPhoneNotification(String message, String phoneNumber, String apiKey) {
        try {
            String link = "https://api.callmebot.com/whatsapp.php?phone=%s&text=%s&apikey=%s";

            URL url = new URL(String.format(link, phoneNumber, message, apiKey).replace(" ", "+"));
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            new InputStreamReader(con.getInputStream());
            con.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restartNotify() {
        StringBuilder kickMessageBuilder = new StringBuilder();
        for (String line : getStringList("kick-message")) {
            kickMessageBuilder.append(line).append("\n");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            String kickMessage = ChatColor.translateAlternateColorCodes('&', kickMessageBuilder.toString().replace("\\n", "\n"));
            player.kickPlayer(kickMessage);
        }
        Bukkit.getServer().dispatchCommand(getServer().getConsoleSender(), "restart");
    }
}
