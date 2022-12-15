package hammynl.rebootly.menu;

import hammynl.rebootly.Rebootly;
import hammynl.rebootly.enums.Menu;
import hammynl.rebootly.enums.Messages;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.concurrent.TimeUnit;

public class RebootMenu extends BaseMenu {

    public RebootMenu() {
        super(5, Menu.MENU_MAIN.toString());
        setMenuItems();
    }

    @Override
    protected void setMenuItems() {
        Inventory inv = getInventory();
        int time = Rebootly.getMain().getTime();

        inv.setItem(4, createItem(Material.CLOCK, 1, Menu.TIME_LEFT.toString(),
                "&7Hours: " + TimeUnit.SECONDS.toHours(time),
                "&7Minutes: " + TimeUnit.SECONDS.toMinutes(time),
                "&7Seconds: " + time
        ));

        inv.setItem(11, createItem(Material.GREEN_TERRACOTTA, 1, Menu.ADD_OPTION_1.toString(), "&7Click to add this amount of time"));
        inv.setItem(12, createItem(Material.GREEN_TERRACOTTA, 1, Menu.ADD_OPTION_2.toString(), "&7Click to add this amount of time"));
        inv.setItem(13, createItem(Material.GREEN_TERRACOTTA, 1, Menu.ADD_OPTION_3.toString(), "&7Click to add this amount of time"));
        inv.setItem(14, createItem(Material.GREEN_TERRACOTTA, 1, Menu.ADD_OPTION_4.toString(), "&7Click to add this amount of time"));
        inv.setItem(15, createItem(Material.GREEN_TERRACOTTA, 1, Menu.ADD_OPTION_5.toString(), "&7Click to add this amount of time"));

        inv.setItem(22, createItem(Material.BARRIER, 1, Menu.RESTART_OPTION_1.toString()));

        inv.setItem(29, createItem(Material.RED_TERRACOTTA, 1, Menu.REMOVE_OPTION_1.toString(), "&7Click to remove this amount of time"));
        inv.setItem(30, createItem(Material.RED_TERRACOTTA, 1, Menu.REMOVE_OPTION_2.toString(), "&7Click to remove this amount of time"));
        inv.setItem(31, createItem(Material.RED_TERRACOTTA, 1, Menu.REMOVE_OPTION_3.toString(), "&7Click to remove this amount of time"));
        inv.setItem(32, createItem(Material.RED_TERRACOTTA, 1, Menu.REMOVE_OPTION_4.toString(), "&7Click to remove this amount of time"));
        inv.setItem(33, createItem(Material.RED_TERRACOTTA, 1, Menu.REMOVE_OPTION_5.toString(), "&7Click to remove this amount of time"));

    }

    @Override
    public void handleClicks(InventoryClickEvent event) {

    }
}
