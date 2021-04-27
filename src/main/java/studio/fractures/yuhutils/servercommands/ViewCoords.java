package studio.fractures.yuhutils.servercommands;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.fractures.yuhutils.util.DataManager;

import java.util.List;

public class ViewCoords implements TabExecutor {

    private final DataManager data;

    public ViewCoords(DataManager dataManager) {
        this.data = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (this.data.getDataConfig().contains("players." + sender.getName() + ".coordinates")) {
                ConfigurationSection playerCoordinates = this.data.getDataConfig().getConfigurationSection("players." + sender.getName() + ".coordinates");
                assert playerCoordinates != null;
                TextComponent.Builder message = Component.text().content("Coordinates\n").color(NamedTextColor.LIGHT_PURPLE);
                for (String key: playerCoordinates.getKeys(true)) {
                    Location coordinates = this.data.getDataConfig().getLocation("players." + sender.getName() + ".coordinates." + key);
                    assert coordinates != null;
                    message.append(Component.text(key + " - " + coordinates.getX() + ", " + coordinates.getY() + ", " + coordinates.getZ() + "\n").color(NamedTextColor.GREEN));
                }
                sender.sendMessage(message.build());
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
