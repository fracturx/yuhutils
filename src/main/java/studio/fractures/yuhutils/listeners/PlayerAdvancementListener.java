package studio.fractures.yuhutils.listeners;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.plugin.java.JavaPlugin;
import studio.fractures.yuhutils.Main;
import studio.fractures.yuhutils.util.DiscordMessageHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;

public class PlayerAdvancementListener implements Listener {

    private final JDA jda;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private final JavaPlugin plugin;

    public PlayerAdvancementListener(JDA jda, JavaPlugin plugin) {
        this.jda = jda;
        Main plugin1 = (Main) plugin;
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin1);
    }

    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent e) {
        String namespacedAdvancement = e.getAdvancement().getKey().getKey();
        if (!namespacedAdvancement.matches("(story|husbandry|nether|adventure|end).*")) return;
        InputStream inputStream = this.plugin.getResource("advancements_us.json");
        if (inputStream == null) return;
        InputStreamReader isReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(isReader);
        StringBuilder sb = new StringBuilder();
        String str;
        try {
            while ((str = reader.readLine()) != null) {
                sb.append(str);
            }
        } catch(IOException exception) {
            exception.printStackTrace();
        }
        Type mapType = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> json = gson.fromJson(sb.toString(), mapType);
        String englishAdvancementTitle = json.get(namespacedAdvancement.substring(namespacedAdvancement.indexOf("/") + 1) + ".title");
        String englishAdvancementDescription = json.get(namespacedAdvancement.substring(namespacedAdvancement.indexOf("/") + 1) + ".description");
        if (englishAdvancementTitle == null) {
            this.plugin.getLogger().info("Error appeared with the advancement " + e.getAdvancement().getKey().getKey());
        }
        String advancementCompleteMessage = e.getPlayer().getName() + " has made the advancement [" + englishAdvancementTitle + "]";
        DiscordMessageHelper.sendEmbedWithBody(this.jda, advancementCompleteMessage, englishAdvancementDescription);
    }
}
