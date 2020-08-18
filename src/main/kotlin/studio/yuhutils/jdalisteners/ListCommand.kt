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
                Bukkit.getServer().onlinePlayers.forEach { p: Player ->
                    if (!isVanished(p)) playerList.add(p.name)
                }
                if (playerList.size == 0 || numPlayers == 0) {
                    e.channel.sendMessage("No players currently online.").queue()
                    return
                }
                val descriptionList = playerList.joinToString("\n")
                val embedInfo = EmbedBuilder()
                        .setTitle("Player Info")
                        .addField("Number of Online Players: ", playerList.size.toString(), true)
                        .setDescription(descriptionList)
                e.channel.sendMessage(embedInfo.build()).queue()
                return
            }
        }
    }

    private fun isVanished(player: Player): Boolean {
        for (meta in player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true
        }
        return false
    }

}