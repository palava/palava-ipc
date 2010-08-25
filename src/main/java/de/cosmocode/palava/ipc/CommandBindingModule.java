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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Binder;
import com.google.inject.Module;

import de.cosmocode.commons.reflect.Classpath;
import de.cosmocode.commons.reflect.Packages;
import de.cosmocode.commons.reflect.Reflection;

/**
 * A Module which scans a set of packages for
 * {@link IpcCommand} and binds them using {@link Binder#bind(Class)}.
 *
 * @since 1.10
 * @author Willi Schoenborn
 */
public final class CommandBindingModule implements Module {

    private static final Logger LOG = LoggerFactory.getLogger(CommandBindingModule.class);
    
    private final String[] packageNames;
    
    private CommandBindingModule(String[] packageNames) {
        this.packageNames = packageNames;
    }
    
    @Override
    public void configure(Binder binder) {
        final Classpath classpath = Reflection.defaultClasspath();
        final Packages packages = classpath.restrictTo(packageNames);
        
        for (Class<? extends IpcCommand> type : packages.subclassesOf(IpcCommand.class)) {
            LOG.info("Binding command {}", type);
            binder.bind(type);
        }
    }
    
    /**
     * Creates new instance of this class using the given
     * packages.
     * 
     * @since 1.10
     * @param packages the packages to look into
     * @return a new instance
     * @throws NullPointerException if packages is null
     */
    public static Module in(String... packages) {
        Preconditions.checkNotNull(packages, "Packages");
        return new CommandBindingModule(packages);
    }

}
