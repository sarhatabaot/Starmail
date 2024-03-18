package me.sword7.starmail.util;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.sys.Language;
import org.bukkit.ChatColor;

public enum MailColor {

    BLACK(Language.COLOR_BLACK, XMaterial.BLACK_DYE, XMaterial.BLACK_STAINED_GLASS_PANE, ChatColor.DARK_GRAY, "\uE100"),
    RED(Language.COLOR_RED, XMaterial.RED_DYE, XMaterial.RED_STAINED_GLASS_PANE, ChatColor.RED, "\uE101"),
    GREEN(Language.COLOR_GREEN, XMaterial.GREEN_DYE, XMaterial.GREEN_STAINED_GLASS_PANE, ChatColor.DARK_GREEN, "\uE102"),
    BROWN(Language.COLOR_BROWN, XMaterial.BROWN_DYE, XMaterial.BROWN_STAINED_GLASS_PANE, ChatColor.DARK_GRAY, "\uE103"),
    BLUE(Language.COLOR_BLUE, XMaterial.BLUE_DYE, XMaterial.BLUE_STAINED_GLASS_PANE, ChatColor.BLUE, "\uE104"),
    PURPLE(Language.COLOR_PURPLE, XMaterial.PURPLE_DYE, XMaterial.PURPLE_STAINED_GLASS_PANE, ChatColor.DARK_PURPLE, "\uE105"),
    CYAN(Language.COLOR_CYAN, XMaterial.CYAN_DYE, XMaterial.CYAN_STAINED_GLASS_PANE, ChatColor.DARK_AQUA, "\uE106"),
    LIGHT_GRAY(Language.COLOR_LIGHT_GRAY, XMaterial.LIGHT_GRAY_DYE, XMaterial.LIGHT_GRAY_STAINED_GLASS_PANE, ChatColor.GRAY, "\uE107"),
    GRAY(Language.COLOR_GRAY, XMaterial.GRAY_DYE, XMaterial.GRAY_STAINED_GLASS_PANE, ChatColor.DARK_GRAY, "\uE108"),
    PINK(Language.COLOR_PINK, XMaterial.PINK_DYE, XMaterial.PINK_STAINED_GLASS_PANE, ChatColor.RED, "\uE109"),
    LIME(Language.COLOR_LIME, XMaterial.LIME_DYE, XMaterial.LIME_STAINED_GLASS_PANE, ChatColor.GREEN, "\uE10A"),
    YELLOW(Language.COLOR_YELLOW, XMaterial.YELLOW_DYE, XMaterial.YELLOW_STAINED_GLASS_PANE, ChatColor.YELLOW, "\uE10B"),
    LIGHT_BLUE(Language.COLOR_LIGHT_BLUE, XMaterial.LIGHT_BLUE_DYE, XMaterial.LIGHT_BLUE_STAINED_GLASS_PANE, ChatColor.AQUA, "\uE10C"),
    MAGENTA(Language.COLOR_MAGENTA, XMaterial.MAGENTA_DYE, XMaterial.MAGENTA_STAINED_GLASS_PANE, ChatColor.LIGHT_PURPLE, "\uE10D"),
    ORANGE(Language.COLOR_ORANGE, XMaterial.ORANGE_DYE, XMaterial.ORANGE_STAINED_GLASS_PANE, ChatColor.GOLD, "\uE10E"),
    WHITE(Language.COLOR_WHITE, XMaterial.WHITE_DYE, XMaterial.WHITE_STAINED_GLASS_PANE, ChatColor.WHITE, "\uE10F"),

    ;
    private final Language language;
    private final XMaterial xDye;
    private final XMaterial xGlass;
    private final ChatColor chatColor;
    private final String boxSymbol;

    MailColor(Language language, XMaterial xDye, XMaterial xGlass, ChatColor chatColor, String boxSymbol) {
        this.language = language;
        this.xDye = xDye;
        this.xGlass = xGlass;
        this.chatColor = chatColor;
        this.boxSymbol = boxSymbol;
    }

    public Language getLanguage() {
        return language;
    }

    public XMaterial getXDye() {
        return xDye;
    }

    public XMaterial getXGlass() {
        return xGlass;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getBoxSymbol() {
        return boxSymbol;
    }

    public static MailColor fromString(String s) {
        try {
            return valueOf(s);
        } catch (Exception e) {
            return null;
        }
    }

}
