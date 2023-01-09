package smp.jello.jelloblock;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.HashMap;
import java.util.List;

public class Listeners implements Listener {
    JelloBlock plugin = JelloBlock.getInstance();
    FileConfiguration config = plugin.getConfig();

    final ItemStack BOOK;

    private final HashMap<Player, Integer> annoyingBookTaskHash = new HashMap<>();

    public Listeners() {
        BOOK = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) BOOK.getItemMeta();
        bookMeta.setTitle("blank");
        bookMeta.setAuthor("server");
        bookMeta.addPage(
                ChatColor.DARK_GREEN.toString() +
                        ChatColor.BOLD +
                        "Welcome!\n" +
                        ChatColor.RESET +
                        "This server is not public yet! It will be released early next month." +
                        " The server will be" +
                        ChatColor.BOLD +
                        " Whitelist Only!" +
                        ChatColor.RESET +
                        " See the" +
                        ChatColor.BLUE +
                        ChatColor.BOLD +
                        " Discord" +
                        ChatColor.RESET +
                        " to join.\n\n" +
                        "https://dsc.gg/jelsmp"
        );
        BOOK.setItemMeta(bookMeta);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (isExempt(player)) return;
        openInventory(player);

        int taskID = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            if (player.isOnline())
                openInventory(player);
        }, 0, 100);

        annoyingBookTaskHash.put(player, taskID);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (!annoyingBookTaskHash.containsKey(player)) return;

        plugin.getServer().getScheduler().cancelTask(annoyingBookTaskHash.remove(player));
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (isExempt(event.getPlayer())) return;
        event.getPlayer().kickPlayer("The server is currently not open!");
        event.setCancelled(true);
        event.setDropItems(false);
        event.setExpToDrop(0);
    }

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent event) {
        if (isExempt(event.getPlayer())) return;
        event.setCancelled(true);
    }

    private boolean isExempt(Player player) {
        List<String> exemptPlayers = config.getStringList("exempt");
        return exemptPlayers.contains(player.getName());
    }

    private void openInventory(Player player) {
        player.openBook(BOOK);
    }
}
