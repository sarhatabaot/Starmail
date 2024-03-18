package me.sword7.starmail.gui.data;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.gui.LiveSessions;
import me.sword7.starmail.gui.page.PageType;
import me.sword7.starmail.warehouse.WarehouseEntry;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WarehouseData extends SessionData {

    private final String type;
    private final WarehouseEntry entry;

    public WarehouseData(Player player, String type, WarehouseEntry entry) {
        super(PageType.WAREHOUSE_HOME.getPage(), player);
        setTheme(XMaterial.YELLOW_STAINED_GLASS_PANE);
        this.type = type;
        this.entry = entry;
    }

    @Override
    public String getEffectiveTitle() {
        if (getCurrent().getType() == PageType.WAREHOUSE_HOME) {
            return super.getEffectiveTitle() + ChatColor.DARK_GRAY + ChatColor.ITALIC + " - " + type;
        } else {
            return super.getEffectiveTitle();
        }
    }

    public WarehouseEntry getEntry() {
        return entry;
    }

    public String getType() {
        return type;
    }

    @Override
    public void onEnd() {
        LiveSessions.removeEntry(type);
    }

}
