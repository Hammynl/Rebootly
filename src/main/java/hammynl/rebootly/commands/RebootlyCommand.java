package hammynl.rebootly.commands;

import hammynl.rebootly.enums.Messages;
import hammynl.rebootly.menu.RebootMenu;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RebootlyCommand extends BaseCommand {

    public RebootlyCommand() {
        super("rebootly", "rebootly.*", 0, 1, true);
    }

    @Override
    protected void handleCommand(CommandSender sender, String[] args) {
        new RebootMenu().openInventory((Player) sender);
    }
}
