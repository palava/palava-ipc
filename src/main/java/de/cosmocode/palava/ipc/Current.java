/**
 * (c) 2010 CosmoCode GmbH
 * All rights reserved
 */

/**
 * All rights reserved
 */

package de.cosmocode.palava.ipc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.BindingAnnotation;

/**
 * Binding annotation for accessing the current version of something.
 * This annotation can be used with everything that makes sense.
 *
 * @author Willi Schoenborn
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.PARAMETER,
    ElementType.METHOD
})
@BindingAnnotation
public @interface Current {

}
