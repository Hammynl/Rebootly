package hammynl.rebootly.commands;

import hammynl.rebootly.Rebootly;
import hammynl.rebootly.enums.Messages;
import hammynl.rebootly.menu.RebootMenu;
import hammynl.rebootly.utils.ServerTimer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RebootlyCommand extends BaseCommand {

    private final Rebootly plugin;

    public RebootlyCommand(Rebootly plugin) {
        super("rebootly", "rebootly.admin", 0, 1, true);
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
            "&c&l>>"
    };

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
    }
}
