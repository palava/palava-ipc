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

/**
 * Will be used by the {@link IpcCommandExecutor} and will be given
 * to the IpcCommand caller.
 *
 * @author Tobias Sarnowski
 */
public class IpcCommandNotAvailableException extends IpcCommandExecutionException {

    private static final long serialVersionUID = -2465797117256681579L;

    public IpcCommandNotAvailableException(String requestedCommand) {
        super(new IllegalArgumentException("command '" + requestedCommand + "' is not available"));
    }

    public IpcCommandNotAvailableException(String requestedCommand, Throwable throwable) {
        super(new IllegalArgumentException("command '" + requestedCommand + "' is not available", throwable));
    }

}
