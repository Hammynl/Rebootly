package hammynl.rebootly.enums;

import hammynl.rebootly.Rebootly;
import org.bukkit.ChatColor;

public enum Messages {

    PREFIX(Rebootly.getMain().getConfig().getString("prefix")),
    RELOAD("&aSuccesfully reloaded!"),
    TIME("&aThere is currently [time] left before the server restarts!"),
    INCORRECT_USAGE("&cIncorrect usage! use '&a/rebootly&c' to find out the correct syntax!"),
    NO_PERMISSION("&cYou do not have permission to perform this command!"),
    NOT_PLAYER("&cYou must be a player to perform this command!");

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    public String toStringPrefix() {
        return PREFIX + toString();
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
