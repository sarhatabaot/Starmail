package me.sword7.starmail.gui;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.box.BoxType;
import me.sword7.starmail.post.Mail;
import me.sword7.starmail.util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static me.sword7.starmail.sys.Language.*;

public class Icons {

    public static final ItemStack AIR = new ItemStack(Material.AIR);
    public static final ItemStack BACKGROUND_ITEM = XMaterial.WHITE_STAINED_GLASS_PANE.parseItem();
    public static final ItemStack BACK_BUTTON = createBackButton();
    public static final ItemStack CLOSE = XMaterial.BARRIER.isSupported() ? createIcon(XMaterial.BARRIER.parseMaterial(), ChatColor.RED + ICON_CLOSE.toString()) :
            ItemUtil.displayName(XMaterial.RED_STAINED_GLASS_PANE.parseItem(), ChatColor.RED+ICON_CLOSE.toString());
    public static final ItemStack WAREHOUSE_ITEM = createIcon(XMaterial.BOOKSHELF.parseMaterial(), ChatColor.WHITE + LABEL_WAREHOUSE.toString());


    public static ItemStack createSeal(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + ICON_SEAL.toString());
        meta.setLore(Collections.singletonList(ChatColor.LIGHT_PURPLE + ICON_SEAL_LORE.toString()));
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static ItemStack createIcon(Material material, String name) {
        ItemStack icon = new ItemStack(material, 1);
        ItemMeta meta = icon.getItemMeta();
        meta.setDisplayName(name);
        icon.setItemMeta(meta);
        return icon;
    }

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat(MISC_DATE.toString());

    public static ItemStack createMail(Mail mail) {
        ItemStack icon = mail.getItemStack().clone();
        ItemMeta meta = icon.getItemMeta();
        List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
        lore.add("");
        lore.add(dataFormat.format(mail.getTimestamp()));
        lore.add(ChatColor.DARK_GRAY + LABEL_FROM.toString() + ": " + mail.getFrom());
        meta.setLore(lore);
        icon.setItemMeta(meta);
        return icon;
    }

    public static ItemStack createMenuGlass(XMaterial xGlass, ChatColor color, String symbol) {
        ItemStack itemStack = xGlass.parseItem();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(color + symbol);
        itemStack.setItemMeta(meta);
        return itemStack;
    }


    public static ItemStack createBackButton() {
        ItemStack itemStack = createIcon(Material.IRON_AXE, ICON_BACK.toString());
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createDisc(Material material, String title) {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName(ChatColor.WHITE + title);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static ItemStack createMailbox(String player) {
        ItemStack base = BoxType.DEFAULT.getBox().getItemStack();
        ItemMeta meta = base.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + ICON_PLAYER_MAILBOX.fromPlayer(player));
        base.setItemMeta(meta);
        return base;
    }

}
