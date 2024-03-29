package me.sword7.starmail.pack;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import me.sword7.starmail.gui.page.PageType;
import me.sword7.starmail.sys.Language;
import me.sword7.starmail.sys.Version;
import me.sword7.starmail.util.MailColor;
import me.sword7.starmail.util.MailUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Gift extends Pack {

    private static final Sound openSound = XSound.BLOCK_BARREL_OPEN.isSupported() ? XSound.BLOCK_BARREL_OPEN.parseSound() : XSound.BLOCK_CHEST_OPEN.parseSound();
    private static final Sound closeSound = XSound.BLOCK_BARREL_CLOSE.isSupported() ? XSound.BLOCK_BARREL_CLOSE.parseSound() : XSound.BLOCK_CHEST_CLOSE.parseSound();

    private XMaterial xDye;
    private final XMaterial paper;
    private final XMaterial ribbon;
    private final ItemStack sealBaseStack;

    public Gift(PackType type, MailColor mailColor, XMaterial ribbon, String profileID, String data) {
        super(type, type.toString(), Language.LABEL_DYED_GIFT.fromColor(mailColor.getLanguage()), profileID, data);
        this.xDye = mailColor.getXDye();
        this.paper = mailColor.getXGlass();
        this.ribbon = ribbon;
        this.sealBaseStack = parseCarpetItemStack();
    }

    public Gift(PackType type, String name, String displayName, XMaterial paper, XMaterial ribbon, String profileID, String data) {
        super(type, name, displayName, profileID, data);
        this.paper = paper;
        this.ribbon = ribbon;
        this.sealBaseStack = parseCarpetItemStack();
    }

    @Override
    public XMaterial getBorder() {
        return paper;
    }


    @Override
    public void playOpenSound(Player player) {
        if (XSound.BLOCK_BARREL_OPEN.isSupported()) {
            player.playSound(player.getLocation(), openSound, 1.5f, 1.2f);
        } else {
            player.playSound(player.getLocation(), openSound, 0.5f, 1.2f);
        }
    }

    @Override
    public void playCloseSound(Player player) {
        if (XSound.BLOCK_BARREL_CLOSE.isSupported()) {
            player.playSound(player.getLocation(), closeSound, 1.5f, 1.2f);
        } else {
            player.playSound(player.getLocation(), closeSound, 0.5f, 1.2f);
        }
    }

    @Override
    public PageType getOpener() {
        return PageType.GIFT_SEAL;
    }

    @Override
    public boolean isSupported() {
        return true;
    }

    @Override
    public ItemStack getSealBaseStack() {
        return sealBaseStack;
    }

    @Override
    public void playSealSound(Player player) {
        if (seal != null) {
            player.playSound(player.getLocation(), seal, 2f, 1.7f);
        }
    }

    private static final Sound seal = XSound.ITEM_BOOK_PAGE_TURN.isSupported() ? XSound.ITEM_BOOK_PAGE_TURN.parseSound() : null;

    private static final Sound unseal = XSound.ENTITY_SHEEP_SHEAR.parseSound();

    @Override
    public void playUnsealSound(Player player) {
        player.playSound(player.getLocation(), unseal, 2f, 0.7f);
    }

    private ItemStack parseCarpetItemStack() {
        Material material = parseCarpetMaterial();
        if (Version.current.hasExtendedEnums()) {
            return new ItemStack(material);
        } else {
            return new ItemStack(material, 1, ribbon.getData());
        }
    }

    private Material parseCarpetMaterial() {
        Material material = MailUtil.materialFrom(ribbon.toString() + "_CARPET");
        return material != null ? material : XMaterial.WHITE_CARPET.parseMaterial();
    }


    public XMaterial getXDye() {
        return xDye;
    }

    public XMaterial getPaper() {
        return paper;
    }

    public XMaterial getRibbon() {
        return ribbon;
    }
}
