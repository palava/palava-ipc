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

import com.google.common.base.Preconditions;

import de.cosmocode.palava.ipc.IpcSession;

/**
 * Default {@link Conversation} implementation. This implementation removes the given key
 * from the specified session when {@link Conversation#abort()} is called.
 * 
 * @author Tobias Sarnowski
 */
public final class DefaultConversation extends AbstractConversation implements Serializable {

    private static final long serialVersionUID = -6810686546862281083L;
    
    private IpcSession session;
    private String key;

    public DefaultConversation(IpcSession session, String key) {
        this.session = Preconditions.checkNotNull(session, "Session");
        this.key = Preconditions.checkNotNull(key, "Key");
    }

    @Override
    public void abort() {
        try {
            clear();
        } finally {
            session.remove(key);
        }
    }
    
}
