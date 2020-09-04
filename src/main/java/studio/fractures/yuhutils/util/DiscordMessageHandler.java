package studio.fractures.yuhutils.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;

public class DiscordMessageHandler {
    public static void sendSimpleEmbed(JDA jda, String titleMessage) {
        TextChannel mcChannel = jda.getTextChannelById("737075555085582437");
        if (mcChannel == null) {
            Bukkit.getLogger().info("Minecraft Channel Not Found!");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder().setTitle(titleMessage).setColor(0x00A2FF);
        mcChannel.sendMessage(embed.build()).queue();
    }

    public static void sendSimpleEmbed(JDA jda, String titleMessage, int color) {
        TextChannel mcChannel = jda.getTextChannelById("737075555085582437");
        if (mcChannel == null) {
            Bukkit.getLogger().info("Minecraft Channel Not Found!");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder().setTitle(titleMessage).setColor(color);
        mcChannel.sendMessage(embed.build()).queue();
    }
}
