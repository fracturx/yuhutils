package studio.fractures.yuhutils

import net.dv8tion.jda.api.AccountType
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import studio.fractures.yuhutils.discordcommands.ListCommand
import studio.fractures.yuhutils.listeners.AsyncChatListener
import io.github.cdimascio.dotenv.dotenv
import studio.fractures.yuhutils.listeners.PlayerAdvancementListener
import studio.fractures.yuhutils.listeners.PlayerDeathListener
import studio.fractures.yuhutils.listeners.PlayerJoinListener


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

        // commands on discord
        jda.addEventListener(ListCommand())

        // commands for server
        jda.addEventListener(AsyncChatListener(plugin, jda))

        // Util Listeners for announcing events on the chat
        PlayerJoinListener(jda, plugin)
        PlayerDeathListener(jda, plugin)
        PlayerAdvancementListener(jda, plugin)
    }
}