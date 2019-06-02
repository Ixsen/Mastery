package de.ixsen.minecraft.mastery.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ixsen.minecraft.mastery.MasteryMod;

public interface HasLogger {
    default Logger getLogger() {
        return LogManager.getLogger(MasteryMod.MOD_ID);
    }
}
