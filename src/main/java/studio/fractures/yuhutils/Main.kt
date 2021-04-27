package studio.fractures.yuhutils

import org.bukkit.plugin.java.JavaPlugin
import studio.fractures.yuhutils.servercommands.RequestCoords
import studio.fractures.yuhutils.servercommands.SaveCoords
import studio.fractures.yuhutils.servercommands.ShareCoords
import studio.fractures.yuhutils.servercommands.ViewCoords
import studio.fractures.yuhutils.util.DataManager

class Main : JavaPlugin() {


    override fun onEnable() {
        this.logger.info("yuhutils has started")
        val dataManager = DataManager(this)
        super.onEnable()
        getCommand("sharecoords")!!.setExecutor(ShareCoords())
        getCommand("requestcoords")!!.setExecutor(RequestCoords())
        getCommand("savecoords")!!.setExecutor(SaveCoords(dataManager))
        getCommand("viewcoords")!!.setExecutor(ViewCoords(dataManager))
        Bot(this)
    }

}