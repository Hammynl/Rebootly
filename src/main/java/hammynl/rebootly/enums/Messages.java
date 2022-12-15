package hammynl.rebootly.enums;

import org.bukkit.ChatColor;

public enum Messages {

    PREFIX("&8[&a&lReboot&c&lly&8] "),
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
