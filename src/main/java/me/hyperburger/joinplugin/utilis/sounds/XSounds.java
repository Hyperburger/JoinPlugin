package me.hyperburger.joinplugin.utilis.sounds;

import com.cryptomorin.xseries.XSound;

import java.util.ArrayList;
import java.util.List;

public class XSounds {

    public static List<XSound> listSound = new ArrayList<>();

    static {
        for(XSound sound : XSound.values()) listSound.add(sound);
    }

}
