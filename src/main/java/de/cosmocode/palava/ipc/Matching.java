/**
 * palava - a java-php-bridge
 * Copyright (C) 2007-2010  CosmoCode GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.cosmocode.palava.ipc;

import java.lang.annotation.Annotation;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Static factory class for {@link CommandMatcher}s.
 *
 * @author Willi Schoenborn
 */
public final class Matching {

    private static final Predicate<IpcCommand> ANY = new Predicate<IpcCommand>() {
        
        @Override
        public boolean apply(IpcCommand type) {
            return true;
        }
        
    };
    
    private Matching() {
        
    }
    
    /**
     * Provides a {@link Predicate<IpcCommand>} which matches every IpcCommand.
     * 
     * @return a {@link Predicate<IpcCommand>} which always return true
     */
    public static Predicate<IpcCommand> any() {
        return ANY;
    }
    
    /**
     * Provides a {@link Predicate<IpcCommand>} which uses the given predicate to decide
     * whether a given {@link IpcCommand} matches.
     * 
     * @param predicate the backing predicate
     * @return a {@link Predicate<IpcCommand>} backed by a {@link Predicate}
     * @throws NullPointerException if predicate is null
     */
    public static Predicate<IpcCommand> ofPredicate(final Predicate<? super IpcCommand> predicate) {
        Preconditions.checkNotNull(predicate, "Predicate");
        return new Predicate<IpcCommand>() {
            
            @Override
            public boolean apply(IpcCommand input) {
                return predicate.apply(input);
            }
            
        };
    }
    
    /**
     * Returns a {@link Predicate} which checks the given {@link IpcCommand} for the presence
     * of the specified {@link Annotation}.
     * 
     * @param annotation the required annotation
     * @return a new {@link Predicate}
     * @throws NullPointerException if annotation is null
     */
    public static Predicate<IpcCommand> annotatedWith(final Class<? extends Annotation> annotation) {
        Preconditions.checkNotNull(annotation, "Annotation");
        return new Predicate<IpcCommand>() {
            
            @Override
            public boolean apply(IpcCommand command) {
                return command.getClass().isAnnotationPresent(annotation);
            }
            
        };
    }
    
    /**
     * Returns a {@link Predicate} which checks whether the given {@link IpcCommand}
     * is a sub class or of the same class as specified class.
     * 
     * @param superClass the super class
     * @return a new {@link Predicate}
     * @throws NullPointerException if superClass is null
     */
    public static Predicate<IpcCommand> subClassesOf(final Class<?> superClass) {
        Preconditions.checkNotNull(superClass, "SuperClass");
        return new Predicate<IpcCommand>() {
            
            @Override
            public boolean apply(IpcCommand command) {
                return superClass.isAssignableFrom(command.getClass());
            }
            
        };
    }
    
}
