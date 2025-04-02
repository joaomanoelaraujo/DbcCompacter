package me.joaomanoel.d4rkk;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CompactarCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser usado por jogadores.");
            return true;
        }
        Player player = (Player) sender;
        Inventory inv = player.getInventory();

        ItemStack itemNaMao = player.getItemInHand();
        if (itemNaMao == null || itemNaMao.getType() == Material.AIR) {
            player.sendMessage(ChatColor.RED + "Você não está segurando nenhum item.");
            return true;
        }

        int totalItens = 0;
        totalItens += itemNaMao.getAmount();
        player.setItemInHand(null);

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack it = inv.getItem(i);
            if (it != null && it.getType() != Material.AIR && it.isSimilar(itemNaMao)) {
                totalItens += it.getAmount();
                inv.clear(i);
            }
        }

        ItemStack pacote = itemNaMao.clone();
        pacote.setAmount(1);

        ItemMeta meta = pacote.getItemMeta();
        List<String> lore = new ArrayList<>();
        if (meta.hasLore()) {
            lore.addAll(meta.getLore());
        }
        lore.add(ChatColor.GOLD + "Quantidade: " + totalItens);
        meta.setLore(lore);
        pacote.setItemMeta(meta);

        inv.addItem(pacote);

        player.sendMessage(ChatColor.GREEN + "Itens compactados em um pacote com " + totalItens + " unidades!");
        return true;
    }
}
