package com.mc3f.amcaddon;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
public class CommandListener implements Listener {
    private static void writefile(final String command, final String sender) {
        final File file = new File("plugins/AdvancedMonthlyCrates", "log.txt");
        file.getParentFile().mkdirs();
        for (final String amc : AmcAddon.getAmcCommands()) {
            if (command.startsWith(amc)) {
                try {
                    if (!file.exists())
                        file.createNewFile();
                    final PrintWriter printWriter = new PrintWriter(new FileWriter("plugins/AdvancedMonthlyCrates/log.txt", true));
                    printWriter.println(new SimpleDateFormat("dd/MM/yyyy ss:mm:HH").format(new Date()) + " " + sender + ": " + command);
                    printWriter.close();
                }
                catch (final IOException ignored) { }
                return;
            }
        }
    }

    @EventHandler
    public static void onPlayerCommand(final PlayerCommandPreprocessEvent e) {
        if (e.getMessage().length() < 1 || e.getPlayer().isOp()) return;
        writefile(e.getMessage().substring(1).trim(), e.getPlayer().getName());
    }

    @EventHandler
    public static void onConsoleCommand(final ServerCommandEvent e) {
        final String command = e.getCommand().trim();
        if (!command.startsWith("amc")) return;
        writefile(command, "Console");
    }
}