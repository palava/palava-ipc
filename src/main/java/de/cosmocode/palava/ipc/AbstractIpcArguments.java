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

import java.util.Date;

import com.google.common.base.Preconditions;

import de.cosmocode.collections.utility.AbstractUtilityMap;
import de.cosmocode.collections.utility.Convert;
import de.cosmocode.commons.DateMode;

/**
 * Abstract skeleton implementation of the {@link IpcArguments} interface.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcArguments extends AbstractUtilityMap<String, Object> implements IpcArguments {

    @Override
    public void require(String... keys) throws IpcArgumentsMissingException {
        Preconditions.checkNotNull(keys, "Keys");
        for (String key : keys) {
            if (containsKey(key)) {
                continue;
            } else {
                throw new IpcArgumentsMissingException(key);
            }
        }
    }

    @Override
    public Date getDate(String key) throws IllegalArgumentException {
        Preconditions.checkArgument(containsKey(key), "No key named '%s' present for expected date", key);
        final Object value = get(key);
        Preconditions.checkNotNull(value, "Required value for key '%s' is null, but should be a UNIX timestamp", key);
        return Convert.intoDate(value, DateMode.UNIXTIME);
    }

    @Override
    public Date getDate(String key, Date defaultValue) {
        return Convert.intoDate(get(key), DateMode.UNIXTIME, defaultValue);
    }

}
