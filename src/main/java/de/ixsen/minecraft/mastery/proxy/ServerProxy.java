package de.ixsen.minecraft.mastery.proxy;

import de.ixsen.minecraft.mastery.MasteryMod;
import de.ixsen.minecraft.mastery.common.annotations.AnnotationProcessor;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        AnnotationProcessor annotationProcessor = MasteryMod.getAnnotationProcessor();
        annotationProcessor.getServerClasses().forEach(this::registerToEventBus);

    }
}
