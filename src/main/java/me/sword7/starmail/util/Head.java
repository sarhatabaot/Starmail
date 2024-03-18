package me.sword7.starmail.util;

import com.cryptomorin.xseries.XMaterial;
import de.tr7zw.changeme.nbtapi.NBT;
import de.tr7zw.changeme.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.changeme.nbtapi.iface.ReadableNBT;
import me.sword7.starmail.sys.Version;
import me.sword7.starmail.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Head {

    private static int currentValue = Version.current.value;

    public static ItemStack createHeadItem(String data, UUID profileID, String name) {
        ItemStack head = Head.getHead(data, "Head", profileID);
        ItemMeta meta = head.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + name);
        head.setItemMeta(meta);
        return head;
    }

    private static Map<UUID, ItemStack> playerToHead = new HashMap<>();

    public static ItemStack getPlayerHead(User user) {
        UUID userID = user.getID();
        if (playerToHead.containsKey(userID)) {
            return playerToHead.get(userID).clone();
        }

        ItemStack head = Head.getHead(userID, user.getName());
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName(ChatColor.WHITE + user.getName());
        head.setItemMeta(meta);
        playerToHead.put(userID, head);
        return head.clone();
    }

    public static ItemStack getHead(String url, String profileName, UUID profileID) {
        ItemStack head = currentValue >= 113 ? new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial()) : new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3);
        if (!url.isEmpty()) {
            assignTexture(head, url, profileName, profileID);
        }
        return head;
    }

    public static void assignTexture(ItemStack head, String url, String profileName, UUID profileID) {
        NBT.modify(head, nbt -> {
            final ReadWriteNBT skullOwnerCompound = nbt.getOrCreateCompound("SkullOwner");

            // The owner UUID. Note that skulls with the same UUID but different textures will misbehave and only one texture will load.
            // They will share the texture. To avoid this limitation, it is recommended to use a random UUID.
            skullOwnerCompound.setUUID("Id", UUID.randomUUID());

            skullOwnerCompound.getOrCreateCompound("Properties")
                    .getCompoundList("textures")
                    .addCompound()
                    .setString("Value", url);
        });

    }

    public static ItemStack getSteeveHead(String name) {
        ItemStack head = currentValue >= 113 ? new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial()) : new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3);
        ItemMeta meta = head.getItemMeta();
        meta.setDisplayName(name);
        head.setItemMeta(meta);
        return head;
    }

    public static ItemStack getHead(UUID playerID, String playerName) {
        ItemStack head = currentValue >= 113 ? new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial()) : new ItemStack(XMaterial.PLAYER_HEAD.parseMaterial(), 1, (short) 3);


        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();
        //        GameProfile profile = new GameProfile(playerID, playerName);
        if (currentValue >= 117) {
            skullMeta.setOwningPlayer(Bukkit.getServer().getOfflinePlayer(playerID));
        }
        //        else {
        //            try {
        //                Field profileField = skullMeta.getClass().getDeclaredField("profile");
        //                profileField.setAccessible(true);
        //                profileField.set(skullMeta, profile);
        //            } catch (IllegalArgumentException | SecurityException | NoSuchFieldException | IllegalAccessException e) {
        //                e.printStackTrace();
        //            }
        //        }

        head.setItemMeta(skullMeta);
        return head;
    }

    public static UUID getPlayerID(SkullMeta skullMeta) {
        try {
            return skullMeta.getOwningPlayer().getUniqueId();
        } catch (Exception e) {
            return null;
        }
    }

    public static UUID getPlayerID(Skull skull) {
        try {
            if (currentValue >= 113) {
                return skull.getOwningPlayer().getUniqueId();
            }

            return NBT.get(skull, nbt -> {
                final ReadableNBT skullOwnerCompound = nbt.getCompound("SkullOwner");
                return skullOwnerCompound.getUUID("profile");
            });
        } catch (Exception e) {
            return null;
        }
    }
}
