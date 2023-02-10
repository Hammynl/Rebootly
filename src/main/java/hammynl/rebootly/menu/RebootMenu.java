package hammynl.rebootly.menu;

import hammynl.rebootly.Rebootly;
import hammynl.rebootly.enums.Menu;
import hammynl.rebootly.utils.ServerTimer;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RebootMenu extends BaseMenu {

    private final Rebootly plugin;
    private BukkitTask clockTask;

    public RebootMenu(Rebootly plugin) {
        super(5, Menu.MENU_MAIN.toString());
        this.plugin = plugin;
        setMenuItems();

    }

    private String[] ConvertSecondToHHMMString(int secondtTime)
    {
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
        date.setTimeZone(timeZone);
        String time = date.format(new Date(secondtTime*1000L));
        return time.split(":");

    }

    @Override
    protected void setMenuItems() {
        Inventory inv = getInventory();
        clockTask = new BukkitRunnable() {

            @Override
            public void run() {
                String[] timeLeft = ConvertSecondToHHMMString(ServerTimer.getInstance().getTime());

                inv.setItem(4, createItem(Material.CLOCK, 1, Menu.TIME_LEFT.toString(),
                        "&7Hours: " + timeLeft[0],
                        "&7Minutes: " + timeLeft[1],
                        "&7Seconds: " + timeLeft[2]
                ));
            }
        }.runTaskTimer(plugin, 0, 20);



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
        ServerTimer timer = ServerTimer.getInstance();

        if(event.getSlot() == 11) timer.addTime(30);
        if(event.getSlot() == 12) timer.addTime(60);
        if(event.getSlot() == 13) timer.addTime(300);
        if(event.getSlot() == 14) timer.addTime(900);
        if(event.getSlot() == 15) timer.addTime(1800);

        if(event.getSlot() == 22) timer.restartNotify(plugin);

        if(event.getSlot() == 29) timer.removeTime(30);
        if(event.getSlot() == 30) timer.removeTime(60);
        if(event.getSlot() == 31) timer.removeTime(300);
        if(event.getSlot() == 32) timer.removeTime(900);
        if(event.getSlot() == 33) timer.removeTime(1800);

        setMenuItems();
        clockTask.cancel();
    }
}
