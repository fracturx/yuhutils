package studio.yuhutils.servercommands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player


class ShareCoords : TabExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            if (args.isNotEmpty()) {
                val target = Bukkit.getServer().getPlayer(args[0])
                if (target != null) {
                    val pLoc = sender.location.blockX.toString() + ", " + sender.location.blockY + ", " + sender.location.blockZ
                    target.sendMessage(ChatColor.RED.toString() + sender.name + ChatColor.RESET + "'s location is: " + ChatColor.GREEN + pLoc)
                    sender.sendMessage(ChatColor.GOLD.toString() + "Successfully sent coordinates to player " + target.name)
                }
            } else {
                sender.sendMessage("No player specified to send the data to.")
                sender.sendMessage("/sharecoords [playerName]")
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

