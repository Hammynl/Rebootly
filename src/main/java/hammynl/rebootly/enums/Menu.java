package hammynl.rebootly.enums;

import net.md_5.bungee.api.ChatColor;

public enum Menu {

    MENU_MAIN("&eMain menu"),
    TIME_LEFT("&e&lRemaining time"),
    ADD_OPTION_1("&a+30 seconds"),
    ADD_OPTION_2("&a+60 seconds"),
    ADD_OPTION_3("&a+5 minutes"),
    ADD_OPTION_4("&a+15 minutes"),
    ADD_OPTION_5("&a+30 minutes"),
    RESTART_OPTION_1("&4&lRestart now"),
    REMOVE_OPTION_1("&c-30 seconds"),
    REMOVE_OPTION_2("&c-60 seconds"),
    REMOVE_OPTION_3("&c-5 minutes"),
    REMOVE_OPTION_4("&c-10 minutes"),
    REMOVE_OPTION_5("&c-30 minutes");


    private String menuText;

    Menu(String menuText) {
        this.menuText = menuText;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', menuText);
    }

}
