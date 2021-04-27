package studio.fractures.yuhutils.util;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.Bukkit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DiscordMessageHelper {

    public static final TimeZone EST = TimeZone.getTimeZone("America/New_York");

    public static final String CHANNEL_ID = System.getProperty("CHANNEL_ID");

    public static void sendSimpleEmbed(JDA jda, String titleMessage) {
        DateFormat format = new SimpleDateFormat("M/dd/yy hh:mm a z");
        format.setTimeZone(EST);
        TextChannel mcChannel = jda.getTextChannelById(CHANNEL_ID);
        if (mcChannel == null) {
            Bukkit.getLogger().info("Minecraft Channel Not Found!");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(titleMessage)
                .setColor(0x00A2FF)
                .setFooter(format.format(new Date()));
        mcChannel.sendMessage(embed.build()).queue();
    }

    public static void sendSimpleEmbed(JDA jda, String titleMessage, int color) {
        DateFormat format = new SimpleDateFormat("M/dd/yy hh:mm a z");
        format.setTimeZone(EST);
        TextChannel mcChannel = jda.getTextChannelById(CHANNEL_ID);
        if (mcChannel == null) {
            Bukkit.getLogger().info("Minecraft Channel Not Found!");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(titleMessage)
                .setColor(color)
                .setFooter(format.format(new Date()));
        mcChannel.sendMessage(embed.build()).queue();
    }

    public static void sendEmbedWithBody(JDA jda, String titleMessage, String body) {
        DateFormat format = new SimpleDateFormat("M/dd/yy hh:mm a z");
        format.setTimeZone(EST);
        TextChannel mcChannel = jda.getTextChannelById(CHANNEL_ID);
        if (mcChannel == null) {
            Bukkit.getLogger().info("Minecraft Channel Not Found!");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setTitle(titleMessage)
                .setColor(0x00A2FF)
                .setDescription(body)
                .setFooter(format.format(new Date()));
        mcChannel.sendMessage(embed.build()).queue();
    }

    public static void sendEmbedWithBody(JDA jda, String titleMessage, String body, int color) {
        DateFormat format = new SimpleDateFormat("M/dd/yy hh:mm a z");
        format.setTimeZone(EST);
        TextChannel mcChannel = jda.getTextChannelById(CHANNEL_ID);
        if (mcChannel == null) {
            Bukkit.getLogger().info("Minecraft Channel Not Found!");
            return;
        }
        EmbedBuilder embed = new EmbedBuilder().setTitle(titleMessage).setColor(color).setDescription(body).setFooter(format.format(new Date()));
        mcChannel.sendMessage(embed.build()).queue();
    }

}
