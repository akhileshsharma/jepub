/**
 * Copyright (c) 2020 Sukma Wardana
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.swardana.jepub;

/**
 * Absolute directory of {@link FileName}.
 * <p>
 * For a given directory within the OCF Abstract Container, the string holding
 * all directory File Name in the full path concatenated together
 * with a / ({@code "\u002F"}) character separating the directory File Names.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
public interface PathName {

    /**
     * Path Name as {@code String} object.
     *
     * @return the file name
     */
    String asString();

    /**
     * The name of any type of file within an OCF Abstract Container.
     * <p>
     * The name could be a directory or a file within a directory.
     */
    interface FileName {
        /**
         * File Name as {@code String} object.
         *
         * @return the file name
         */
        String asString();
    }

}
