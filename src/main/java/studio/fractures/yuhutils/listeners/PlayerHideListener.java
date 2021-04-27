package studio.fractures.yuhutils.listeners;

import de.myzelyam.api.vanish.PlayerHideEvent;
import de.myzelyam.api.vanish.PlayerShowEvent;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import studio.fractures.yuhutils.util.DiscordMessageHelper;

public class PlayerHideListener implements Listener {
    private final JDA jda;

    public PlayerHideListener(JDA jda, JavaPlugin plugin) {
        this.jda = jda;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerHide(PlayerHideEvent e) {
        String hideMessage = e.getPlayer().getName() + " left the game";
        DiscordMessageHelper.sendSimpleEmbed(this.jda, hideMessage);
    }

    @EventHandler
    public void onPlayerShow(PlayerShowEvent e) {
        String showMessage = e.getPlayer().getName() + " joined the game";
        DiscordMessageHelper.sendSimpleEmbed(this.jda, showMessage);
    }
}
