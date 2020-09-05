package studio.fractures.yuhutils.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import studio.fractures.yuhutils.util.DiscordMessageHandler;


public class AsyncChatListener extends ListenerAdapter implements Listener {

    private final JDA jda;

    public AsyncChatListener(Plugin plugin, JDA jda) {
        this.jda = jda;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        String author = e.getPlayer().getDisplayName();
        DiscordMessageHandler.sendEmbedWithBody(jda, author, message);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        if (e.getChannel().getName().equals("minecraft")) {
            String author = e.getAuthor().getName() + "#" + e.getAuthor().getDiscriminator() + ": ";
            sendServerMessage(author, e.getMessage().getContentRaw());
        }
    }

    private void sendServerMessage(String author, String message) {
        Bukkit.getServer().broadcastMessage("${ChatColor.GOLD}${author}${ChatColor.AQUA}${message}");
    }

}