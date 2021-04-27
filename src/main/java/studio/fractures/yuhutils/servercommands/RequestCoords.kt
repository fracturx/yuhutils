package studio.fractures.yuhutils.servercommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import studio.fractures.yuhutils.util.getPlayerNames


class RequestCoords : TabExecutor {

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.isNotEmpty()) {
                val target = Bukkit.getServer().getPlayer(args[0])
                if (target != null) {
                    val message = Component.text().content(sender.name).color(NamedTextColor.DARK_PURPLE)
                            .append(Component.text(" is requesting your coordinates. Would you like to share your coords with this player?").color(NamedTextColor.GREEN))
                            .decoration(TextDecoration.UNDERLINED, true)
                            .clickEvent(ClickEvent.runCommand("/sharecoords ${sender.name}"))
                            .hoverEvent(HoverEvent.showText(Component.text("Are you sure you want to share your coordinates with this player?").color(NamedTextColor.DARK_RED)))
                            .build()

                    target.sendMessage(message)
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
