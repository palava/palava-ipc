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

package de.cosmocode.palava.ipc.conversation;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.cosmocode.palava.ipc.IpcSession;

/**
 * @author Tobias Sarnowski
 */
public class DefaultConversation extends AbstractConversation implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultConversation.class);

    private IpcSession ipcSession;
    private String key;

    public DefaultConversation(IpcSession ipcSession, String key) {
        this.ipcSession = ipcSession;
        this.key = key;
    }

    @Override
    public void abort() {
        try {
            clear();
        } finally {
            ipcSession.remove(key);
        }
    }
}