package studio.fractures.yuhutils.listeners;

import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import studio.fractures.yuhutils.Main;
import studio.fractures.yuhutils.util.DiscordMessageHelper;

public class PlayerDeathListener implements Listener {

    private final JDA jda;

    public PlayerDeathListener(JDA jda, JavaPlugin plugin) {
        this.jda = jda;
        Main plugin1 = (Main) plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin1);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
        String deathMessage = e.getDeathMessage();
        DiscordMessageHelper.sendSimpleEmbed(this.jda, deathMessage);
    }
}
