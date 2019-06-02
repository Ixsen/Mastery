package de.ixsen.minecraft.mastery.common.annotations;

import java.util.ArrayList;
import java.util.List;

import com.impetus.annovention.listener.ClassAnnotationDiscoveryListener;

import de.ixsen.minecraft.mastery.common.HasLogger;

class SubscribeToEventBusAnnotationListener implements ClassAnnotationDiscoveryListener, HasLogger {

    private List<Class<?>> clientClasses = new ArrayList<>();
    private List<Class<?>> serverClasses = new ArrayList<>();
    private List<Class<?>> commonClasses = new ArrayList<>();

    @Override
    public void discovered(String clazz, String annotation) {
        try {
            if (SubscribeToClientEventBus.class.getName().equals(annotation)) {
                this.clientClasses.add(Class.forName(clazz));
            }
            if (SubscribeToCommonEventBus.class.getName().equals(annotation)) {
                this.commonClasses.add(Class.forName(clazz));
            }
            if (SubscribeToServerEventBus.class.getName().equals(annotation)) {
                this.serverClasses.add(Class.forName(clazz));
            }
        } catch (ClassNotFoundException e) {
            this.getLogger().error("Was not able to find annotated class, this may be a big problem!", e);
        }
    }

    @Override
    public String[] supportedAnnotations() {
        return new String[] { SubscribeToClientEventBus.class.getName(), SubscribeToServerEventBus.class.getName(),
                SubscribeToCommonEventBus.class.getName() };
    }

    List<Class<?>> getClientClasses() {
        return this.clientClasses;
    }

    List<Class<?>> getServerClasses() {
        return this.serverClasses;
    }

    List<Class<?>> getCommonClasses() {
        return this.commonClasses;
    }
}
