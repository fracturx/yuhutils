package studio.fractures.yuhutils.servercommands;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.fractures.yuhutils.DataManager;

import java.util.List;

public class ViewCoords implements TabExecutor {

    private final DataManager data;
    private final BukkitAudiences audience;

    public ViewCoords(BukkitAudiences audience, DataManager dataManager) {
        this.data = dataManager;
        this.audience = audience;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (this.data.getDataConfig().contains("players." + sender.getName() + ".coordinates")) {
                ConfigurationSection playerCoordinates = this.data.getDataConfig().getConfigurationSection("players." + sender.getName() + ".coordinates");
                assert playerCoordinates != null;
                TextComponent.Builder message = TextComponent.builder().content("Coordinates\n").color(NamedTextColor.LIGHT_PURPLE);
                for (String key: playerCoordinates.getKeys(true)) {
                    Location coordinates = this.data.getDataConfig().getLocation("players." + sender.getName() + ".coordinates." + key);
                    assert coordinates != null;
                    message.append(TextComponent.builder(key + " - " + coordinates.getX() + ", " + coordinates.getY() + ", " + coordinates.getZ() + "\n").color(NamedTextColor.GREEN).build());
                }
                audience.player((Player) sender).sendMessage(message.build());
                return true;
            }
            sender.sendMessage(ChatColor.RED + "NO SAVED COORDINATES FOUND!");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
