package com.sample.jersey.web;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.sample.jersey.service.ex04.ImageService;
import com.sample.jersey.service.ex04.ImageServiceFactory;

public class Application extends ResourceConfig {

    public Application() {
    	// for ex02, ex03
        register(JacksonFeature.class);
        // for ex04
        register(MultiPartFeature.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
            	bindFactory(ImageServiceFactory.class).to(ImageService.class);
            }
        });
    }
}
