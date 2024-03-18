package me.sword7.starmail.postbox;

import com.cryptomorin.xseries.XMaterial;

import java.util.UUID;

public class HatBox extends Postbox {

    private XMaterial hatBase;
    private XMaterial hatHighlight;

    public HatBox(PostboxType type, String name, XMaterial xGlass, XMaterial hatBase, XMaterial hatHighlight, UUID profileID, String data) {
        super(type, name, xGlass, profileID, data);
        this.hatBase = hatBase;
        this.hatHighlight = hatHighlight;
    }

    public XMaterial getHatBase() {
        return hatBase;
    }

    public XMaterial getHatHighlight() {
        return hatHighlight;
    }
}
