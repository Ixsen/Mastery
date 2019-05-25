package de.ixsen.minecraft.mastery.common.annotations;

import java.util.List;

import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.Discoverer;

public class AnnotationProcessor {

    private SubscribeToEventBusAnnotationListener subscribeToEventBusAnnotationListener;

    public void initialize() {
        this.subscribeToEventBusAnnotationListener = new SubscribeToEventBusAnnotationListener();

        Discoverer discoverer = new ClasspathDiscoverer();
        discoverer.addAnnotationListener(this.subscribeToEventBusAnnotationListener);

        // TODO remove error output from annovention
        discoverer.discover(true, false, false, false, true, true);
    }

    public List<Class<?>> getClientClasses() {
        return this.subscribeToEventBusAnnotationListener.getClientClasses();
    }

    public List<Class<?>> getServerClasses() {
        return this.subscribeToEventBusAnnotationListener.getServerClasses();
    }

    public List<Class<?>> getCommonClasses() {
        return this.subscribeToEventBusAnnotationListener.getCommonClasses();
    }

}