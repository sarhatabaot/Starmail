package me.sword7.starmail.gui.page;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.gui.LiveSessions;
import me.sword7.starmail.gui.MenuUtil;
import me.sword7.starmail.gui.data.SessionData;
import me.sword7.starmail.util.Scheduler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class IUpdater implements IInsertable {

    private static Map<UUID, UUID> sessionToPID = new HashMap<>();
    private static ItemStack normalStack = XMaterial.YELLOW_STAINED_GLASS_PANE.parseItem();

    public abstract List<Integer> getAnimationSlots();

    protected void doAccept(Player player, SessionData sessionData, Inventory menu) {
        doAnimation(player, sessionData, menu, XMaterial.LIME_STAINED_GLASS_PANE);
    }

    protected void doReject(Player player, SessionData sessionData, Inventory menu) {
        doAnimation(player, sessionData, menu, XMaterial.RED_STAINED_GLASS_PANE);
        MenuUtil.playErrorSound(player);
    }

    private void doAnimation(Player player, SessionData sessionData, Inventory menu, XMaterial glass) {
        PageType startPage = sessionData.getCurrent().getType();
        ItemStack itemStack = glass.parseItem();
        UUID pid = UUID.randomUUID();
        UUID sessionID = sessionData.getID();
        sessionToPID.put(sessionID, pid);
        for (int i : getAnimationSlots()) {
            menu.setItem(i, itemStack);
        }
        Scheduler.runLater(() -> {
            if (LiveSessions.hasSession(player) && sessionData.getCurrent().getType() == startPage) {
                UUID currentPid = sessionToPID.get(sessionID);
                if (currentPid != null && currentPid.equals(pid)) {
                    for (int i : getAnimationSlots()) {
                        menu.setItem(i, normalStack);
                        sessionToPID.remove(sessionID);
                    }
                }
            } else {
                sessionToPID.remove(sessionID);
            }
        }, 5);

    }

}
