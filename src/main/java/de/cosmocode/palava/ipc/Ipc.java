package de.cosmocode.palava.ipc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Binding annotation and utility class for ipc.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.METHOD,
    ElementType.PARAMETER
})
@BindingAnnotation
public @interface Ipc {

    String PREFIX = "ipc.";
    
}
