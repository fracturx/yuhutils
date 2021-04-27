package studio.fractures.yuhutils.servercommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import studio.fractures.yuhutils.util.DataManager;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SaveCoords implements TabExecutor {

    private DataManager data;

    public SaveCoords(DataManager dataManager) {
        this.data = dataManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1) {
                String identifier = args[0];

                if (this.data.getDataConfig().contains("players." + sender.getName() + ".coordinates." + identifier)) {
                    sender.sendMessage(ChatColor.DARK_BLUE + "A field already exists with the specified name.");
                    return true;
                }

                if (args.length == 2 && args[1].equals("@s")) {
                    Location coordinates = ((Player) sender).getLocation();
                    this.data.getDataConfig().set("players." + sender.getName() + ".coordinates." + identifier, coordinates.getBlock().getLocation());
                    this.data.saveDataConfig();
                    this.sendSuccessMessage((Player) sender);
                    return true;
                } else if (args.length == 5) {
                    for (int i = 2; i < 5; i++) {
                        try {
                            Double.parseDouble(args[i]);
                        } catch (NullPointerException | NumberFormatException e) {
                            sender.sendMessage(ChatColor.RED + "NOT A VALID COORDINATE!");
                            return false;
                        }
                    }
                    if (!args[1].matches("overworld|nether|end")) {
                        sender.sendMessage(ChatColor.RED + "NOT A VALID DIMENSION!");
                        return false;
                    }
                    if (args[1].equals("overworld")) args[1] = "world";
                    if (args[1].equals("nether")) args[1] = "world_nether";
                    if (args[1].equals("end")) args[1] = "world_the_end";
                    Location coordinates = new Location(Bukkit.getServer().getWorld(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]));
                    this.data.getDataConfig().set("players." + sender.getName() + ".coordinates." + identifier, coordinates);
                    this.data.saveDataConfig();
                    this.sendSuccessMessage((Player) sender);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 2) {
            return Arrays.asList("overworld", "nether", "end", "@s");
        }
        switch(args.length) {
            case 3:
                return Collections.singletonList(Double.toString(((Player) sender).getLocation().getX()));
            case 4:
                return Collections.singletonList(Double.toString(((Player) sender).getLocation().getY()));
            case 5:
                return Collections.singletonList(Double.toString(((Player) sender).getLocation().getZ()));
        }
        return null;
    }

    public void sendSuccessMessage(Player target) {
        target.sendMessage(ChatColor.GOLD + "Coordinates successfully saved.");
    }
}
