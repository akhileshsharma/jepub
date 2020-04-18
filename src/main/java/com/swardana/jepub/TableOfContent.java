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

import java.util.List;

/**
 * Primary navigational hierarchy.
 * <p>
 * Conceptually corresponds to a table of contents in a printed work.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
public interface TableOfContent {

    /**
     * Ordered list of content item.
     *
     * @return ordered content item
     */
    List<Content> contents();

    /**
     * Add content item.
     *
     * @param ctn the content item
     */
    default void addContent(Content ctn) {
        this.contents().add(ctn);
    }

    /**
     * Content item.
     */
    interface Content {

        /**
         * Content title.
         *
         * @return the title
         */
        String title();

        /**
         * Content location within OCF Abstract Container.
         *
         * @return the path location
         */
        PathName path();

        /**
         * Sub level of Content Item.
         *
         * @return the sub content item within
         */
        List<Content> subContent();

        /**
         * Add sub level content item.
         *
         * @param ctn the sub content
         */
        default void addSubContent(Content ctn) {
            this.subContent().add(ctn);
        }

    }

}
