package hammynl.rebootly.commands;

import hammynl.rebootly.Rebootly;
import hammynl.rebootly.enums.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseCommand implements CommandExecutor, TabCompleter {

    private Rebootly main;

    // Parameters
    private final String command;
    private final String permission;
    private final int minArgs;
    private final int maxArgs;
    private final boolean playerOnly;

    protected BaseCommand(String command, String permission, int minArgs, int maxArgs, boolean playerOnly) {

        this.main = Rebootly.getMain();
        main.getCommand(command).setExecutor(this);

        this.command = command;
        this.permission = permission;
        this.minArgs = minArgs;
        this.maxArgs = maxArgs;
        this.playerOnly = playerOnly;

    }

    protected abstract void handleCommand(CommandSender sender, String[] args);
    protected abstract List<String> handleTabComplete(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(playerOnly && !(sender instanceof Player))
        {
            sender.sendMessage(Messages.NOT_PLAYER.toStringPrefix());
            return true;
        }

        if(!sender.hasPermission(permission))
        {
            sender.sendMessage(Messages.NO_PERMISSION.toStringPrefix());
            return true;
        }

        if(args.length > maxArgs || args.length < minArgs)
        {
            sender.sendMessage(Messages.INCORRECT_USAGE.toStringPrefix());
            return true;
        }

        handleCommand(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return handleTabComplete(sender, args);
    }


}
