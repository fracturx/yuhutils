package studio.fractures.yuhutils

import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import studio.fractures.yuhutils.servercommands.RequestCoords
import studio.fractures.yuhutils.servercommands.SaveCoords
import studio.fractures.yuhutils.servercommands.ShareCoords
import studio.fractures.yuhutils.servercommands.ViewCoords

class Main : JavaPlugin() {

    init {
        println("I think this should work.")
    }

    override fun onEnable() {
        this.logger.info("COMMAND IS UP AND RUNNINGGG!!")
        val audience = BukkitAudiences.create(this)
        val dataManager = DataManager(this)
        super.onEnable()
        getCommand("sharecoords")!!.setExecutor(ShareCoords())
        getCommand("requestcoords")!!.setExecutor(RequestCoords(audience))
        getCommand("savecoords")!!.setExecutor(SaveCoords(dataManager))
        getCommand("viewcoords")!!.setExecutor(ViewCoords(audience, dataManager))
        Bot(this)
    }

    override fun onDisable() {
        super.onDisable()
    }
}