/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.palava.ipc;

import java.lang.annotation.Annotation;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Static factory class for {@link Predicate}s regarding {@link IpcCommand} 
 * matching.
 *
 * @author Willi Schoenborn
 */
public final class Commands {

    private static final Predicate<IpcCommand> ANY = new Predicate<IpcCommand>() {
        
        @Override
        public boolean apply(IpcCommand type) {
            return true;
        }
        
        @Override
        public String toString() {
            return "Commandy.any()";
        };
        
    };
    
    private Commands() {
        
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
    public static Predicate<IpcCommand> of(final Predicate<? super IpcCommand> predicate) {
        Preconditions.checkNotNull(predicate, "Predicate");
        return new Predicate<IpcCommand>() {
            
            @Override
            public boolean apply(IpcCommand input) {
                return predicate.apply(input);
            }
            
            @Override
            public String toString() {
                return String.format("Commands.of(%s)", predicate);
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
            
            @Override
            public String toString() {
                return String.format("Commands.annotatedWith(@%s)", annotation.getName());
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

            @Override
            public String toString() {
                return String.format("Commands.subClassesOf(%s)", superClass);
            }
            
        };
    }
    
    /**
     * Returns a {@link Predicate} which checks whether the given {@link IpcCommand}
     * is an instance of the  specified class literal.
     * 
     * @param type the required class type
     * @return a new {@link Predicate}
     * @throws NullPointerException if type is null
     */
    public static Predicate<IpcCommand> instanceOf(final Class<?> type) {
        Preconditions.checkNotNull(type, "Type");
        return new Predicate<IpcCommand>() {
            
            @Override
            public boolean apply(IpcCommand command) {
                return type.isInstance(command);
            }
            
            @Override
            public String toString() {
                return String.format("Commands.instanceOf(%s)", type);
            }
            
        };
    }
    
}
