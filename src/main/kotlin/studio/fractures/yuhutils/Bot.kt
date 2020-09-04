package studio.fractures.yuhutils

import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import studio.fractures.yuhutils.jdalisteners.ListCommand
import studio.fractures.yuhutils.spigotlisteners.AsyncChatListener
import io.github.cdimascio.dotenv.dotenv
import studio.fractures.yuhutils.spigotlisteners.PlayerDeathListener
import studio.fractures.yuhutils.spigotlisteners.PlayerJoinListener


class Bot(pluginInstance: Main) {
    private var jda: JDA
    private val plugin: Main = pluginInstance
    private val dotenv = dotenv()
    private val token: String? = dotenv["BOT_TOKEN"]

    init {
        jda = JDABuilder(AccountType.BOT)
                .setToken(token)
                .build()
        jda.awaitReady()
        jda.addEventListener(ListCommand())
        jda.addEventListener(AsyncChatListener(plugin, jda))
        PlayerJoinListener(jda, plugin)
        PlayerDeathListener(jda, plugin)
    }
}