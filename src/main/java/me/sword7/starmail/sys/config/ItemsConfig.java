package me.sword7.starmail.sys.config;

import com.cryptomorin.xseries.XMaterial;
import me.sword7.starmail.box.Box;
import me.sword7.starmail.box.BoxType;
import me.sword7.starmail.letter.Letter;
import me.sword7.starmail.letter.LetterType;
import me.sword7.starmail.pack.*;
import me.sword7.starmail.util.MailColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemsConfig {

    private static final File file = new File("plugins/StarMail", "items.yml");
    private static final FileConfiguration config = YamlConfiguration.loadConfiguration(file);

    private static final List<Pack> customPacks = new ArrayList<>();
    private static final List<Box> customBoxes = new ArrayList<>();
    private static final List<Letter> customLetters = new ArrayList<>();

    private static final String cratesKey = "Crates";
    private static final String chestsKey = "Chests";
    private static final String giftsKey = "Gifts";
    private static final String lettersKey = "Letters";
    private static final String boxesKey = "Boxes";

    public ItemsConfig() {
        load();
    }

    private void load() {
        if (file.exists()) {
            if (config.contains(cratesKey)) {
                for (String crateName : config.getConfigurationSection(cratesKey).getKeys(false)) {
                    try {
                        String key = cratesKey + "." + crateName;
                        String name = crateName.toUpperCase().replaceAll(" ", "_");
                        String displayName = config.getString(key + ".displayName");
                        XMaterial glass = XMaterial.valueOf(config.getString(key + ".glass"));
                        XMaterial glassBorder = config.contains(key + ".borderGlass") ? XMaterial.valueOf(config.getString(key + ".borderGlass")) : null;
                        UUID profile = UUID.fromString(config.getString(key + ".profile"));
                        String texture = config.getString(key + ".texture");
                        if (config.contains(key + ".profileSeal") && config.contains(key + ".textureSeal")) {
                            UUID profileSeal = UUID.fromString(config.getString(key + ".profileSeal"));
                            String textureSeal = config.getString(key + ".textureSeal");
                            customPacks.add(new Crate(PackType.CUSTOM, name, displayName, glass, glassBorder, profile.toString(), texture, profileSeal.toString(), textureSeal));
                        } else {
                            customPacks.add(new Crate(PackType.CUSTOM, name, displayName, glass, glassBorder, profile.toString(), texture));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            if (config.contains(chestsKey)) {
                for (String chestName : config.getConfigurationSection(chestsKey).getKeys(false)) {
                    try {
                        String key = chestsKey + "." + chestName;
                        String name = chestName.toUpperCase().replaceAll(" ", "_");
                        String displayName = config.getString(key + ".displayName");
                        XMaterial glass = XMaterial.valueOf(config.getString(key + ".glass"));
                        UUID profile = UUID.fromString(config.getString(key + ".profile"));
                        String texture = config.getString(key + ".texture");
                        customPacks.add(new Chest(PackType.CUSTOM, name, displayName, glass, profile.toString(), texture));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            if (config.contains(giftsKey)) {
                for (String giftName : config.getConfigurationSection(giftsKey).getKeys(false)) {
                    try {
                        String key = giftsKey + "." + giftName;
                        String name = giftName.toUpperCase().replaceAll(" ", "_");
                        String displayName = config.getString(key + ".displayName");
                        XMaterial paper = XMaterial.valueOf(config.getString(key + ".paperGlass"));
                        XMaterial ribbon = XMaterial.valueOf(config.getString(key + ".ribbonGlass"));
                        UUID profile = UUID.fromString(config.getString(key + ".profile"));
                        String texture = config.getString(key + ".texture");
                        customPacks.add(new Gift(PackType.CUSTOM, name, displayName, paper, ribbon, profile.toString(), texture));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
            if (config.contains(lettersKey)) {
                for (String letterName : config.getConfigurationSection(lettersKey).getKeys(false)) {
                    try {
                        String key = lettersKey + "." + letterName;
                        String name = letterName.toUpperCase().replaceAll(" ", "_");
                        String displayName = config.getString(key + ".displayName");
                        int model = config.getInt(key + ".model");
                        customLetters.add(new Letter(LetterType.CUSTOM, name, displayName, model));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (config.contains(boxesKey)) {
                for (String boxName : config.getConfigurationSection(boxesKey).getKeys(false)) {
                    try {
                        String key = boxesKey + "." + boxName;
                        String name = boxName.toUpperCase().replaceAll(" ", "_");
                        String displayName = config.getString(key + ".displayName");
                        MailColor color = MailColor.valueOf(config.getString(key + ".color"));
                        MailColor colorHighlight = MailColor.fromString(config.getString(key + ".colorHighlight", ""));
                        if(colorHighlight == null) colorHighlight = color;
                        MailColor colorFlag = MailColor.fromString(config.getString(key + ".colorFlag", ""));
                        if(colorFlag == null) colorFlag = color;
                        UUID profile = UUID.fromString(config.getString(key + ".profile"));
                        String texture = config.getString(key + ".texture");
                        customBoxes.add(new Box(BoxType.CUSTOM, name, displayName, color, colorHighlight.getXGlass(), colorFlag.getXGlass(), profile, texture));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }

    public static List<Pack> getCustomPacks() {
        return customPacks;
    }

    public static List<Box> getCustomBoxes() {
        return customBoxes;
    }

    public static List<Letter> getCustomLetters() {
        return customLetters;
    }


}