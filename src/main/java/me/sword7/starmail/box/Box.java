package me.sword7.starmail.box;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import me.sword7.starmail.sys.config.ItemsConfig;
import me.sword7.starmail.util.Head;
import me.sword7.starmail.util.MailColor;
import me.sword7.starmail.util.MailUtil;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

import static me.sword7.starmail.sys.Language.LABEL_DYED_MAILBOX;
import static me.sword7.starmail.sys.Language.LORE_ID_GLOBAL_BOX;

public class Box {

    private static final Sound openSound = XSound.BLOCK_IRON_DOOR_OPEN.isSupported() ? XSound.BLOCK_IRON_DOOR_OPEN.parseSound() : XSound.BLOCK_WOODEN_DOOR_OPEN.parseSound();
    private static final Sound closeSound = XSound.BLOCK_IRON_DOOR_CLOSE.isSupported() ? XSound.BLOCK_IRON_DOOR_CLOSE.parseSound() : XSound.BLOCK_WOODEN_DOOR_CLOSE.parseSound();

    private static final Map<UUID, Box> iDToBox = new HashMap<>();
    private static final Map<String, Box> nameToBox = new HashMap<>();

    public static void init() {
        List<Box> boxes = new ArrayList<>();
        for (BoxType boxType : BoxType.values()) {
            if (boxType != BoxType.CUSTOM) {
                boxes.add(boxType.getBox());
            }
        }
        boxes.addAll(ItemsConfig.getCustomBoxes());
        for (Box box : boxes) {
            iDToBox.put(box.getProfileID(), box);
            nameToBox.put(box.getName(), box);
        }
        boxes.clear();
    }

    private final BoxType type;
    private final String displayName;
    private final String name;
    private final XMaterial glass;
    private final XMaterial highlight;
    private final XMaterial flag;
    private final ItemStack itemStack;
    private final UUID profileID;
    private final ChatColor color;
    private XMaterial xDye;
    private final String symbol;

    public Box(BoxType type, String name, String displayName, MailColor color, XMaterial highlight, XMaterial flag, UUID profileID, String data) {
        this.type = type;
        this.name = name;
        this.displayName = displayName;
        this.profileID = profileID;
        this.color = color.getChatColor();
        this.glass = color.getXGlass();
        this.symbol = color.getBoxSymbol();
        this.highlight = highlight;
        this.flag = flag;
        this.itemStack = Head.createHeadItem(data, profileID, displayName);
    }

    public Box(BoxType type, MailColor color, XMaterial highlight, XMaterial flag, UUID profileID, String data) {
        this.type = type;
        this.name = type.toString();
        this.displayName = LABEL_DYED_MAILBOX.fromColor(color.getLanguage());
        this.profileID = profileID;
        this.color = color.getChatColor();
        this.glass = color.getXGlass();
        this.symbol = color.getBoxSymbol();
        this.highlight = highlight;
        this.flag = flag;
        this.itemStack = Head.createHeadItem(data, profileID, displayName);
        this.xDye = color.getXDye();
    }

    public BoxType getType() {
        return type;
    }

    public XMaterial getGlass() {
        return glass;
    }

    public ItemStack getItemStack() {
        return itemStack.clone();
    }

    private static final String GLOBAL_LORE = ChatColor.GRAY.toString() + ChatColor.ITALIC + LORE_ID_GLOBAL_BOX;

    public ItemStack getGlobalItemStack() {
        ItemStack itemStack = this.itemStack.clone();
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(Collections.singletonList(GLOBAL_LORE));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static boolean isGlobal(ItemStack itemStack) {
        return itemStack != null && isGlobal(itemStack.getItemMeta());
    }

    public static boolean isGlobal(ItemMeta meta) {
        return GLOBAL_LORE.equals(MailUtil.getFirstLineLore(meta));
    }

    public UUID getProfileID() {
        return profileID;
    }

    public ChatColor getColor() {
        return color;
    }

    public XMaterial getXDye() {
        return xDye;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSymbol() {
        return symbol;
    }

    public static boolean isBox(ItemStack itemStack) {
        return getBox(itemStack) != null;
    }

    public static Box getBox(BlockState blockState) {
        if (!(blockState instanceof Skull)) {
            return null;
        }

        final Skull skull = (Skull) blockState;
        UUID playerID = Head.getPlayerID(skull);
        return iDToBox.get(playerID);
    }

    public static Box getBox(ItemStack itemStack) {
        return itemStack != null ? getBox(itemStack.getItemMeta()) : null;
    }

    public static Box getBox(ItemMeta meta) {
        if (meta instanceof SkullMeta) {
            final SkullMeta skull = (SkullMeta) meta;
            return iDToBox.get(Head.getPlayerID(skull));
        }
        return null;
    }

    public static Box getBox(String string) {
        return nameToBox.get(string);
    }

    public static Sound getOpenSound() {
        return openSound;
    }

    public static Sound getCloseSound() {
        return closeSound;
    }

    public static Collection<Box> getAllBoxes() {
        return nameToBox.values();
    }

    public XMaterial getHighlight() {
        return highlight;
    }

    public XMaterial getFlag() {
        return flag;
    }

}
