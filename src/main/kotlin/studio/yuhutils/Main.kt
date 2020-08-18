package studio.yuhutils


import org.bukkit.plugin.java.JavaPlugin
import studio.yuhutils.servercommands.RequestCoords
import studio.yuhutils.servercommands.ShareCoords

class Main(): JavaPlugin() {

    init {
        println("I think this should work.")
    }

    override fun onEnable() {
        logger.info("COMMAND IS UP AND RUNNINGGG!!")
        super.onEnable()
        getCommand("sharecoords")!!.setExecutor(ShareCoords())
        getCommand("requestcoords")!!.setExecutor(RequestCoords())
        Bot(this)
    }

    override fun onDisable() {
        super.onDisable()
    }
}