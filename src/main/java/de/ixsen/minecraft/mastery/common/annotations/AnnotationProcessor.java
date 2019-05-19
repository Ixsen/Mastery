package de.ixsen.minecraft.mastery.common.annotations;

import java.util.List;

import com.impetus.annovention.ClasspathDiscoverer;
import com.impetus.annovention.Discoverer;

public class AnnotationProcessor {

    private SubscribeToEventBusAnnotationListener subscribeToEventBusAnnotationListener;

    public void initialize() {
        Discoverer discoverer = new ClasspathDiscoverer();
        this.subscribeToEventBusAnnotationListener = new SubscribeToEventBusAnnotationListener();
        discoverer.addAnnotationListener(this.subscribeToEventBusAnnotationListener);
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