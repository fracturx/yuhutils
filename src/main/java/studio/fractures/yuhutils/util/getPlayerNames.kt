package studio.fractures.yuhutils.util

import org.bukkit.Bukkit
import java.util.ArrayList

fun getPlayerNames(): List<String>? {
    val playerNames: MutableList<String> = ArrayList()
    val players = Bukkit.getServer().onlinePlayers
    for (player in players) {
        playerNames.add(player!!.name)
    }
    return playerNames
}
