package studio.yuhutils.jdalisteners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import org.bukkit.entity.Player


class ListCommand() : ListenerAdapter() {
    override fun onGuildMessageReceived(e: GuildMessageReceivedEvent) {
        if (e.author.isBot) return
        if (e.channel.name == "minecraft") {
            if (e.message.contentRaw.equals("list", ignoreCase = true)) {
                val numPlayers = Bukkit.getServer().onlinePlayers.size
                val playerList = mutableListOf<String>()
                if (numPlayers == 0) {
                    e.channel.sendMessage("No players currently online.").queue()
                    return
                }
                Bukkit.getServer().onlinePlayers.forEach { p: Player ->
                    playerList.add(p.name)
                }
                val descriptionList = java.lang.String.join("\n", playerList)
                val embedInfo = EmbedBuilder()
                        .setTitle("Player Info")
                        .addField("Number of Online Players: ", numPlayers.toString(), true)
                        .setDescription(descriptionList)
                e.channel.sendMessage(embedInfo.build()).queue()
                return
            }
        }
    }

}