package hammynl.rebootly.utils;

import hammynl.rebootly.Rebootly;
import hammynl.rebootly.enums.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

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

    public void createCronTimer(String crontab, Rebootly plugin) {
        try {

            CronExpression cron = new CronExpression(crontab);

            Date nextOccurrence = cron.getNextValidTimeAfter(new Date());
            long cronUnixTimestamp = nextOccurrence.getTime() / 1000;

            timeInSeconds = (int) (cronUnixTimestamp - (System.currentTimeMillis() / 1000));

            new BukkitRunnable() {
                @Override
                public void run() {
                    timeInSeconds--;
                    if(timeInSeconds < 0) {
                        plugin.restartNotify();
                    }
                }
            }.runTaskTimer(plugin, 0, 20);

        } catch (ParseException ex) {
            ex.printStackTrace();
            Bukkit.getConsoleSender().sendMessage(Messages.INCORRECT_CRON.toStringPrefix());
        }

    }
}
