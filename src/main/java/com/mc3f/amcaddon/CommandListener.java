package com.mc3f.amcaddon;

import it.unimi.dsi.fastutil.io.FastBufferedOutputStream;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("SpellCheckingInspection")
class CommandListener implements Listener {
    private void writefile(final String command, final String sender) {
        if (AMCAddon.getAmcCommands().stream().noneMatch(command::startsWith)) return;
        final File file = new File("plugins/AdvancedMonthlyCrates", "log.txt");
        try (final OutputStream out = new FastBufferedOutputStream(new FileOutputStream("plugins/AdvancedMonthlyCrates/log.txt", true))) {
            if (!file.exists())
                file.createNewFile();
            out.write((new SimpleDateFormat("dd/MM/yyyy ss:mm:HH").format(new Date()) + " " + sender + ": " + command + "\n").getBytes());
            out.flush();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onPlayerCommand(final PlayerCommandPreprocessEvent e) {
        if (!e.getPlayer().isOp() || e.getMessage().length() == 1) return;
        writefile(e.getMessage().substring(1).trim(), e.getPlayer().getName());
    }

    @EventHandler
    public void onConsoleCommand(final ServerCommandEvent e) {
        writefile(e.getCommand().trim(), "Console");
    }
}