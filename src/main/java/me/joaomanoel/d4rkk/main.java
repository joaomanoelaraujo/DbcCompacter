package me.joaomanoel.d4rkk;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage("§a[DbcCompacter] Plugin iniciado com sucesso!");
        Bukkit.getConsoleSender().sendMessage("§a[DbcCompacter] Muito obrigado por baixar :)");
        getCommand("compactar").setExecutor(new CompactarCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage("§c[DbcCompacter] Plugin desligado com sucesso!");

    }
}
