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
 * Static constant holder class for context specific key names.
 *
 * @author Willi Schoenborn
 */
public final class Context {
    
    public static final String PREFIX = "context.";

    public static final String LOCALE = PREFIX + "locale";
    
    public static final String REQUEST_URI = PREFIX + "requestUri";
    
    public static final String REFERER = PREFIX + "referer";
    
    public static final String REMOTE_ADDRESS = PREFIX + "remoteAddress";
    
    public static final String USER_AGENT = PREFIX + "userAgent";

    private Context() {
        
    }
    
}
