package me.sword7.starmail.gui.page;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.gui.Icons;
import me.sword7.starmail.gui.data.PostData;
import me.sword7.starmail.gui.data.SessionData;
import me.sword7.starmail.postbox.HatBox;
import me.sword7.starmail.postbox.Postbox;
import me.sword7.starmail.util.ItemUtil;
import me.sword7.starmail.util.Scheduler;
import me.sword7.starmail.util.Symbol;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class PostboxMail extends PostboxMenu implements IInsertable {

    @Override
    public void populatePostbox(Inventory menu, SessionData sessionData) {
        PostData postData = (PostData) sessionData;
        Postbox postbox = postData.getPostbox();

        ItemStack background = postbox.getXGlass().parseItem();
        for (int i = 10; i < 17; i++) {
            menu.setItem(i, background);
        }

        if (postbox instanceof HatBox) {
            HatBox hatBox = (HatBox) postbox;
            ItemStack hatBase = hatBox.getHatBase().parseItem();
            ItemStack hatHighlight = hatBox.getHatHighlight().parseItem();
            menu.setItem(9, hatHighlight);
            menu.setItem(10, hatBase);
            menu.setItem(12, hatHighlight);
            menu.setItem(15, hatBase);
            menu.setItem(17, hatHighlight);
            menu.setItem(18, hatBase);
            menu.setItem(26, hatBase);

        }

        ItemStack doorTop = ItemUtil.displayName(XMaterial.BLACK_STAINED_GLASS_PANE.parseItem(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Symbol.DOWN);
        ItemStack doorBottom = ItemUtil.displayName(XMaterial.WHITE_STAINED_GLASS_PANE.parseItem(), ChatColor.YELLOW.toString() + ChatColor.BOLD + Symbol.UP);
        ItemStack doorLeft = (postbox instanceof HatBox ? XMaterial.BLACK_STAINED_GLASS.parseItem() : ItemUtil.displayName(
                XMaterial.WHITE_STAINED_GLASS_PANE.parseItem(),
                ChatColor.YELLOW.toString() + ChatColor.BOLD + Symbol.RIGHT
        ));
        ItemStack doorRight = (postbox instanceof HatBox ? XMaterial.BLACK_STAINED_GLASS.parseItem() : ItemUtil.displayName(
                XMaterial.WHITE_STAINED_GLASS.parseItem(),
                ChatColor.YELLOW.toString() + ChatColor.BOLD + Symbol.LEFT
        ));

        for (int i = 20; i < 25; i++) {
            menu.setItem(i, doorTop);
        }
        for (int i = 38; i < 43; i++) {
            menu.setItem(i, doorBottom);
        }

        menu.setItem(19, doorLeft);
        menu.setItem(25, doorRight);
        menu.setItem(28, doorLeft);
        menu.setItem(29, Icons.createIcon(XMaterial.IRON_BARS.parseMaterial(), ChatColor.YELLOW.toString() + ChatColor.BOLD + "→"));

        ItemStack[] mails = postData.getMails();
        for (int i = 0; i < 3; i++) {
            menu.setItem(30 + i, mails[i] != null ? mails[i] : Icons.AIR);
        }

        menu.setItem(33, Icons.createIcon(XMaterial.IRON_BARS.parseMaterial(), ChatColor.YELLOW.toString() + ChatColor.BOLD + "←"));
        menu.setItem(34, doorRight);
        menu.setItem(37, doorLeft);
        menu.setItem(43, doorRight);

    }

    @Override
    public void processPostboxClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        PostData postData = (PostData) sessionData;
        //run later because slot items not updated yet
        Scheduler.runLater(() -> {
            boolean validMailPrev = postData.isValidMails();
            postData.updateContents(menu);
            boolean validMailCurrent = postData.isValidMails();
            if (validMailCurrent != validMailPrev) {
                populate(menu, sessionData);
            }
        }, 1);
    }

    final Set<Integer> insertableSet = new ImmutableSet.Builder<Integer>()
            .add(30, 31, 32).build();

    final List<Integer> insertableList = new ImmutableList.Builder<Integer>()
            .add(30, 31, 32).build();

    @Override
    public boolean isInsertable(int slot) {
        return insertableSet.contains(slot);
    }

    @Override
    public List<Integer> getOrderedSlots() {
        return insertableList;
    }

}
