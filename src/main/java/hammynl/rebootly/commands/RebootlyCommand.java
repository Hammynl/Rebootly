package hammynl.rebootly.commands;

import hammynl.rebootly.Rebootly;
import hammynl.rebootly.enums.Messages;
import hammynl.rebootly.menu.RebootMenu;
import hammynl.rebootly.utils.ServerTimer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RebootlyCommand extends BaseCommand {

    private final Rebootly plugin;

    public RebootlyCommand(Rebootly plugin) {
        super("rebootly", "rebootly.command", 0, 1, true);
        this.plugin = plugin;
    }

    String[] helpPage = {
            "&c&l>>",
            "&c&l>> &a&lReboot&c&lly &7| &aMade by &cHammynl",
            "&c&l>>",
            "&c&l>> &c&lCommands",
            "&c&l>> &a/rebootly &7| &aShows this help page",
            "&c&l>> &a/rebootly reload &7| &aReloads this plugin",
            "&c&l>> &a/rebootly menu &7| &aOpens the built-in menu",
            "&c&l>> &a/rebootly time &7| &aShows the time left before restart",
            "&c&l>>"
    };

    private String[] ConvertSecondToHHMMString(int secondTime)
    {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        date.setTimeZone(timeZone);
        String time = date.format(new Date(secondTime*1000L));
        return time.split(":");

    }


    @Override
    protected void handleCommand(CommandSender sender, String[] args) {
        if(args.length == 0) {
            for (String line : helpPage) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', line));
            }
        }

        else if(args.length == 1 && args[0].equalsIgnoreCase("menu") && sender.hasPermission("rebootly.admin.menu")) {
            new RebootMenu(plugin).openInventory((Player) sender);
        }

        else if(args.length == 1 && args[0].equalsIgnoreCase("reload") && sender.hasPermission("rebootly.admin.reload")) {
            sender.sendMessage(Messages.RELOAD.toStringPrefix());
            plugin.reloadConfig();
        }

        else if(args.length == 1 && args[0].equalsIgnoreCase("time") && sender.hasPermission("rebootly.admin.time")) {
            String[] time = ConvertSecondToHHMMString(ServerTimer.getInstance().getTime());

            String timeFormatted = time[0]+"h "+time[1]+"m "+time[2]+"s";

            sender.sendMessage(Messages.TIME.toStringPrefix().replace("[time]", timeFormatted));
            plugin.reloadConfig();
        }

    }

    @Override
    protected List<String> handleTabComplete(CommandSender sender, String[] args) {
        List<String> list = new ArrayList<>();
        list.add("menu");
        list.add("reload");
        list.add("time");
        return list;
    }
}
