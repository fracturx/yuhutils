package studio.fractures.yuhutils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {

    private JavaPlugin plugin;
    private FileConfiguration dataConfig;
    private File configFile;

    public DataManager(Main plugin) {
        this.plugin = plugin;
        this.saveDefaultDataConfig();
    }

    public void reloadConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "playerdata.yml");
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("playerdata.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getDataConfig() {
        if (this.dataConfig == null) reloadConfig();
        return dataConfig;
    }

    public void saveDataConfig() {
        if (this.dataConfig == null || this.configFile == null) return;
        try {
            this.getDataConfig().save(configFile);
        } catch(IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save Configuration file!");
        }
    }

    public void saveDefaultDataConfig() {
        if (this.configFile == null) {
            this.configFile = new File(this.plugin.getDataFolder(), "playerdata.yml");
        }
        if (!this.configFile.exists()) {
            this.plugin.saveResource("playerdata.yml", false);
        }
    }


}
