package org.jedels.hookah;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Random;

public class Settings {
    private static Settings INSTANCE;

    public final int maxSmoked, smokeDuration, damage, positiveEffectDuration, negativeEffectDuration, messageRadius;
    public String limitMessage;
    public List<String> puffMessages, damageMessages, dieMessages;

    private Settings() {
        Controller.plugin.saveDefaultConfig();

        FileConfiguration config = Controller.plugin.getConfig();

        maxSmoked = config.getInt("max-smoked");
        smokeDuration = config.getInt("smoke-duration");
        damage = config.getInt("damage");
        positiveEffectDuration = config.getInt("positive-effect-duration");
        negativeEffectDuration = config.getInt("negative-effect-duration");
        messageRadius = config.getInt("message-radius");
        limitMessage = config.getString("limit-message");
        puffMessages = config.getStringList("puff-messages");
        damageMessages = config.getStringList("damage-messages");
        dieMessages = config.getStringList("die-messages");
    }

    public String getRandomPuffMessage() {
        return this.getRandomMessage(this.puffMessages);
    }

    public String getRandomDamageMessage() {
        return this.getRandomMessage(this.damageMessages);
    }

    public String getRandomDieMessage() {
        return this.getRandomMessage(this.dieMessages);
    }

    private String getRandomMessage(List<String> messageArray) {
        int index = new Random().nextInt(messageArray.size());
        return messageArray.get(index);
    }

    public static Settings getInstance() {
        if (INSTANCE == null) {
            synchronized (Settings.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Settings();
                }
            }
        }

        return INSTANCE;
    }
}
