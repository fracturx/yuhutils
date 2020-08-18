package studio.yuhutils.servercommands

import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.md_5.bungee.api.chat.BaseComponent


class RequestCoords(private var audience: BukkitAudiences) : TabExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.isNotEmpty()) {
                val target = Bukkit.getServer().getPlayer(args[0])
                if (target != null) {
                    val message = TextComponent.builder().content(sender.name).color(NamedTextColor.DARK_PURPLE)
                            .append(TextComponent.builder(" is requesting your coordinates. Would you like to share your coords with this player?").color(NamedTextColor.GREEN).build())
                            .decoration(TextDecoration.UNDERLINED, true)
                            .clickEvent(ClickEvent.of(ClickEvent.Action.RUN_COMMAND, "/sharecoords ${target.name}"))
                            .hoverEvent(HoverEvent.showText(TextComponent.builder("Are you sure you want to share your coordinates with this player?").color(NamedTextColor.DARK_RED).build()))
                            .build()
                    audience.player(target).sendMessage(message)
                }
            } else {
                sender.sendMessage("No player specified to send the data to.")
                sender.sendMessage("/requestcoords [playerName]")
            }
        }
        return true
    }

    override fun onTabComplete(commandSender: CommandSender, command: Command, s: String, args: Array<String>): List<String>? {
        if (args.size == 1) {

            return getPlayerNames()
        }
        return null
    }
}
