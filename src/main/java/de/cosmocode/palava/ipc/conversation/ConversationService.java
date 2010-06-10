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

/**
 * A Service which allows creation and retrieval of {@link Conversation}s.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public interface ConversationService {

    /**
     * Retrieves a named Conversation. This may result in
     * the creation of a conversation if no conversation
     * with the specified name is currently in progress.
     * 
     * @since 1.4
     * @param name the name of the conversation
     * @return the conversation associated with the given name
     */
    Conversation get(String name);
    
}
