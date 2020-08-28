package studio.fractures.yuhutils.spigotlisteners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.Plugin
import java.awt.Color


class AsyncChatListener(private var plugin: Plugin, private var jda: JDA) : ListenerAdapter(), Listener {
    @EventHandler
    fun onAsyncPlayerChatEvent(e: AsyncPlayerChatEvent) {
        val message = e.message
        val author = e.player.displayName
        sendDiscordMessage(author, message)
    }

    override fun onGuildMessageReceived(e: GuildMessageReceivedEvent) {
        if (e.author.isBot) return
        if (e.channel.name == "minecraft") {
            val author = e.author.name + "#" + e.author.discriminator + ": "
            sendServerMessage(author, e.message.contentRaw)
        }
    }

    private fun sendServerMessage(author: String, message: String) {
        Bukkit.getServer().broadcastMessage("${ChatColor.GOLD}${author}${ChatColor.AQUA}${message}")
    }

    private fun sendDiscordMessage(author: String, message: String) {
        val mcChannel = jda.getTextChannelById("737075555085582437")!!
        val embed = EmbedBuilder()
        embed.setTitle(author)
        embed.setColor(Color(0x03FCCF))
        embed.setDescription(message)
        mcChannel.sendMessage(embed.build()).queue()
    }

    init {
        Bukkit.getPluginManager().registerEvents(this, this.plugin)
    }

}