package me.sword7.starmail.pack;

import com.cryptomorin.xseries.XMaterial;
import com.cryptomorin.xseries.XSound;
import me.sword7.starmail.gui.page.PageType;
import me.sword7.starmail.sys.Language;
import me.sword7.starmail.sys.Version;
import me.sword7.starmail.util.MailWood;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Chest extends Pack {

    private static final Sound openSound = XSound.BLOCK_CHEST_OPEN.parseSound();
    private static final Sound closeSound = XSound.BLOCK_CHEST_CLOSE.parseSound();

    private XMaterial xPlanks;
    private final XMaterial wood;

    public Chest(PackType type, MailWood mailWood, String profileID, String data) {
        super(type, type.toString(), Language.LABEL_WOODEN_CHEST.fromWood(mailWood.getLanguage()), profileID, data);
        this.xPlanks = mailWood.getXPlanks();
        this.wood = mailWood.getXGlass();
    }

    public Chest(PackType type, String name, String displayName, XMaterial wood, String profileID, String data) {
        super(type, name, displayName, profileID, data);
        this.wood = wood;
    }

    @Override
    public XMaterial getBorder() {
        return wood;
    }

    @Override
    public void playOpenSound(Player player) {
        player.playSound(player.getLocation(), openSound, 0.5f, 1.2f);
    }

    @Override
    public void playCloseSound(Player player) {
        player.playSound(player.getLocation(), closeSound, 0.5f, 1.2f);
    }

    @Override
    public PageType getOpener() {
        return PageType.CHEST_SEAL;
    }

    @Override
    public boolean isSupported() {
        return xPlanks == null || xPlanks.isSupported();
    }


    @Override
    public ItemStack getSealBaseStack() {
        return new ItemStack(Material.TRIPWIRE_HOOK);
    }

    private static final Sound seal = Version.current.value >= 109 ? XSound.BLOCK_IRON_TRAPDOOR_OPEN.parseSound() : XSound.BLOCK_PISTON_EXTEND.parseSound();
    private static final Sound unseal = Version.current.value >= 109 ? XSound.BLOCK_IRON_TRAPDOOR_OPEN.parseSound() : XSound.BLOCK_PISTON_CONTRACT.parseSound();

    @Override
    public void playSealSound(Player player) {
        if(Version.current.value >= 109){
            player.playSound(player.getLocation(), seal, 2f, 0.7f);
        }else{
            player.playSound(player.getLocation(), seal, 1f, 0.9f);
        }
    }

    @Override
    public void playUnsealSound(Player player) {
        if(Version.current.value >= 109){
            player.playSound(player.getLocation(), unseal, 2f, 0.7f);
        }else{
            player.playSound(player.getLocation(), unseal, 1f, 0.9f);
        }
    }

    public XMaterial getXPlanks() {
        return xPlanks;
    }

    public XMaterial getWood() {
        return wood;
    }

}
