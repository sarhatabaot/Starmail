package me.sword7.starmail.util;


import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtil {

    public static ItemStack displayName(final ItemStack itemStack, final String displayName) {
        final ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(displayName);

        final ItemStack newStack = itemStack.clone();
        newStack.setItemMeta(meta);
        return newStack;
    }
}
