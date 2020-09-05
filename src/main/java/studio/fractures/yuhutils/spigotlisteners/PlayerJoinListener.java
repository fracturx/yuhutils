package studio.fractures.yuhutils.spigotlisteners;

import de.myzelyam.api.vanish.PlayerHideEvent;
import de.myzelyam.api.vanish.PlayerShowEvent;
import de.myzelyam.api.vanish.VanishAPI;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import studio.fractures.yuhutils.Main;
import studio.fractures.yuhutils.util.DiscordMessageHandler;

public class PlayerJoinListener implements Listener {

    private final JDA jda;

    public PlayerJoinListener(JDA jda, JavaPlugin plugin) {
        this.jda = jda;
        Main plugin1 = (Main) plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin1);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish") || Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
            if (VanishAPI.isInvisible(e.getPlayer())) return;
        }
        String joinMessage = e.getPlayer().getName() + " joined the game";
        DiscordMessageHandler.sendSimpleEmbed(this.jda, joinMessage);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish") || Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
            if (VanishAPI.isInvisible(e.getPlayer())) return;
        }
        String quitMessage = e.getPlayer().getName() + " left the game";
        DiscordMessageHandler.sendSimpleEmbed(this.jda, quitMessage);
    }

    @EventHandler
    public void onPlayerHide(PlayerHideEvent e) {
        String hideMessage = e.getPlayer().getName() + " left the game";
        DiscordMessageHandler.sendSimpleEmbed(this.jda, hideMessage);
    }

    @EventHandler
    public void onPlayerShow(PlayerShowEvent e) {
        String showMessage = e.getPlayer().getName() + " joined the game";
        DiscordMessageHandler.sendSimpleEmbed(this.jda, showMessage);
    }

}
