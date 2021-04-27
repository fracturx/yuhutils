package studio.fractures.yuhutils.servercommands

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import studio.fractures.yuhutils.util.getPlayerNames


class ShareCoords : TabExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.isNotEmpty()) {
                val target = Bukkit.getServer().getPlayer(args[0])
                val confirmation = args.getOrNull(1)
                if (target != null) {
                    if (confirmation == null || confirmation != "confirm") {
                        val message = Component.text().content("Are you sure you want to share your coords with ").color(NamedTextColor.GREEN)
                            .append(
                                Component.text(target.name)
                                    .color(
                                        NamedTextColor.DARK_PURPLE
                                    )
                            )
                            .decoration(TextDecoration.UNDERLINED, true)
                            .clickEvent(ClickEvent.runCommand("/sharecoords ${target.name} confirm"))
                            .hoverEvent(
                                HoverEvent.showText(
                                    Component.text("Are you sure you want to share your coordinates with this player?")
                                        .color(
                                            NamedTextColor.DARK_RED
                                        )
                                )
                            )
                            .build()
                        sender.sendMessage(message)
                        return true;

                    } else if (confirmation == "confirm") {
                        val pLoc =
                            sender.location.blockX.toString() + ", " + sender.location.blockY + ", " + sender.location.blockZ
                        target.sendMessage(ChatColor.RED.toString() + sender.name + ChatColor.RESET + "'s location is: " + ChatColor.GREEN + pLoc)
                        sender.sendMessage(ChatColor.GOLD.toString() + "Successfully sent coordinates to player " + target.name)
                        return true;
                    }
                }
            } else {
                sender.sendMessage("No player specified to send the data to.")
                sender.sendMessage("/sharecoords [playerName]")
            }
        }
        return true
    }

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        s: String,
        args: Array<String>
    ): List<String>? {
        if (args.size == 1) {

            return getPlayerNames()
        }
        return null
    }
}

