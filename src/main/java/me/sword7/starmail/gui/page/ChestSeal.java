package me.sword7.starmail.gui.page;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.gui.Icons;
import me.sword7.starmail.pack.Chest;
import me.sword7.starmail.pack.Pack;
import com.google.common.collect.ImmutableSet;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class ChestSeal extends Seal {

    @Override
    public Inventory populate(Inventory menu, Pack pack) {
        Chest chest = (Chest) pack;
        ItemStack dot = chest.getWood().parseItem();
        ItemStack seal = Icons.createSeal(XMaterial.WHITE_STAINED_GLASS_PANE.parseItem());
        ItemStack seem = XMaterial.BLACK_STAINED_GLASS_PANE.parseItem();

        for (int i = 0; i < 9; i++) {
            menu.setItem(i, dot);
        }

        for (int i = 9; i < 18; i++) {
            menu.setItem(i, seem);
        }

        for (int i = 18; i < 45; i++) {
            menu.setItem(i, dot);
        }

        for (int i : sealSlots) {
            menu.setItem(i, seal);
        }


        return menu;
    }

    private final Set<Integer> sealSlots = new ImmutableSet.Builder<Integer>()
            .add(13)
            .add(22)
            .build();

    @Override
    public Set<Integer> getSealedSlots() {
        return sealSlots;
    }

}
