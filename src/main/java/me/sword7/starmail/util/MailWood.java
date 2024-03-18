package me.sword7.starmail.util;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.sys.Language;

public enum MailWood {

    OAK(Language.WOOD_OAK, XMaterial.OAK_PLANKS, XMaterial.BROWN_STAINED_GLASS_PANE),
    SPRUCE(Language.WOOD_SPRUCE, XMaterial.SPRUCE_PLANKS, XMaterial.BROWN_STAINED_GLASS_PANE),
    BIRCH(Language.WOOD_BIRCH, XMaterial.BIRCH_PLANKS, XMaterial.BROWN_STAINED_GLASS_PANE),
    JUNGLE(Language.WOOD_JUNGLE, XMaterial.JUNGLE_PLANKS, XMaterial.BROWN_STAINED_GLASS_PANE),
    ACACIA(Language.WOOD_ACACIA, XMaterial.ACACIA_PLANKS, XMaterial.BROWN_STAINED_GLASS_PANE),
    DARK_OAK(Language.WOOD_DARK_OAK, XMaterial.DARK_OAK_PLANKS, XMaterial.BROWN_STAINED_GLASS_PANE),
    CRIMSON(Language.WOOD_CRIMSON, XMaterial.CRIMSON_PLANKS, XMaterial.RED_STAINED_GLASS_PANE),
    WARPED(Language.WOOD_WARPED, XMaterial.WARPED_PLANKS, XMaterial.CYAN_STAINED_GLASS_PANE),

    ;

    private final Language language;
    private final XMaterial xPlanks;
    private final XMaterial xGlass;

    MailWood(Language language, XMaterial xPlanks, XMaterial xGlass) {
        this.language = language;
        this.xPlanks = xPlanks;
        this.xGlass = xGlass;
    }

    public Language getLanguage() {
        return language;
    }

    public XMaterial getXPlanks() {
        return xPlanks;
    }

    public XMaterial getXGlass() {
        return xGlass;
    }
}
