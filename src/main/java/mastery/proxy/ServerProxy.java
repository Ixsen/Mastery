package mastery.proxy;

import mastery.MasteryMod;
import mastery.common.annotations.AnnotationProcessor;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ServerProxy extends CommonProxy {

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        AnnotationProcessor annotationProcessor = MasteryMod.getAnnotationProcessor();
        annotationProcessor.getServerClasses().forEach(this::registerToEventBus);

    }
}
