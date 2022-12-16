package hammynl.rebootly.utils;

import hammynl.rebootly.Rebootly;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerTimer {

    private static ServerTimer instance;
    private int timeInSeconds;

    private ServerTimer() {}

    public static ServerTimer getInstance() {
        if(instance == null)
        {
            instance = new ServerTimer();
        }
        return instance;
    }

    public int getTime() {
        return timeInSeconds;
    }

    public void setTime(int seconds) {
        timeInSeconds = seconds;
    }

    public void addTime(int seconds) {
        timeInSeconds = timeInSeconds + seconds;
    }

    public void removeTime(int seconds) {
        timeInSeconds = timeInSeconds - seconds;
    }

    public void createTimer(int seconds, Rebootly plugin) {
        timeInSeconds = seconds;
        new BukkitRunnable() {
            @Override
            public void run() {
                timeInSeconds--;
                if(timeInSeconds < 0) {
                    plugin.restartNotify();
                }
            }
        }.runTaskTimer(plugin, 0, 20);
    }
}
