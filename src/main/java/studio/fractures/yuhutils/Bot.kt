package studio.fractures.yuhutils

import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.Bukkit
import studio.fractures.yuhutils.discordcommands.ListCommand
import studio.fractures.yuhutils.listeners.*
import studio.fractures.yuhutils.util.DiscordMessageHelper


class Bot(pluginInstance: Main) {
    private var jda: JDA
    private val plugin: Main = pluginInstance
    private val dotenv: Dotenv = Dotenv.load()

    init {
        dotenv.entries().forEach {e -> System.setProperty(e.key, e.value) }
        val token: String? = System.getProperty("BOT_TOKEN")
        jda = JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES).build().awaitReady();


        // commands on discord
        jda.addEventListener(ListCommand())

        // commands for server
        jda.addEventListener(AsyncChatListener(plugin, jda))

        // Util Listeners for announcing events on the chat
        PlayerJoinListener(jda, plugin)
        PlayerDeathListener(jda, plugin)
        PlayerAdvancementListener(jda, plugin)

        if (Bukkit.getPluginManager().isPluginEnabled("SuperVanish") || Bukkit.getPluginManager().isPluginEnabled("PremiumVanish")) {
            PlayerHideListener(jda, plugin)
        }

        DiscordMessageHelper.sendEmbedWithBody(jda, "yuhutils v" + pluginInstance.description.version + " is Enabled!", pluginInstance.description.description)
    }
}