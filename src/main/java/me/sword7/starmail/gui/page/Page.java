package me.sword7.starmail.gui.page;

import me.sword7.starmail.gui.MenuUtil;
import me.sword7.starmail.gui.data.SessionData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public class Page {

    private static final String titleBase = ChatColor.DARK_GRAY.toString() + ChatColor.BOLD;

    private String title;
    private final PageType type;
    private final int rows;
    private final IPageContents contents;
    private final PageType previousPage;

    public Page(PageType type, int rows, String title, IPageContents contents, PageType previousPage) {
        this.type = type;
        this.rows = rows;
        this.title = title;
        this.contents = contents;
        this.previousPage = previousPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRows() {
        return rows;
    }

    public Inventory getInventory(SessionData sessionData) {
        Inventory inventory = MenuUtil.createMenu(sessionData.getEffectiveTitle(), this, sessionData.getTheme());
        return contents.populate(inventory, sessionData);
    }

    public void processClick(Player player, Inventory menu, SessionData sessionData, int slot, ClickType clickType) {
        if (previousPage != null && slot == 0) {
            sessionData.transition(previousPage);
        } else {
            contents.processClick(player, menu, sessionData, slot, clickType);
        }
    }

    public boolean hasPrevious() {
        return previousPage != null;
    }

    public IPageContents getContents() {
        return contents;
    }

    public PageType getType() {
        return type;
    }

}
