package io.quarkiverse.jef.java.embedded.framework.runtime.serial;

import io.quarkiverse.jef.java.embedded.framework.linux.serial.SerialBus;
import io.quarkiverse.jef.java.embedded.framework.linux.spi.SpiBus;
import io.quarkiverse.jef.java.embedded.framework.runtime.spi.SPI;
import io.quarkiverse.jef.java.embedded.framework.runtime.spi.SPIBusManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.lang.annotation.Annotation;

@ApplicationScoped
public class SerialBusProducer {
    @Inject
    SerialBusManager manager;

    @Produces
    //@SPI("") // The `value` attribute is @Nonbinding.
    SerialBus produce(InjectionPoint injectionPoint) {
        for (Annotation qualifier : injectionPoint.getQualifiers()) {
            if (qualifier instanceof Serial) {
                return manager.getBus(((Serial) qualifier).name());
            }
        }
        // This will never be returned.
        return null;
    }
}