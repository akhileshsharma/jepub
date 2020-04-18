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

import java.io.InputStream;

/**
 * EPUB Content Document.
 * <p>
 * A Publication Resource with an XHTML or SVG media type that contains all
 * or part of the content of an EPUB Publication (i.e., the textual, visual
 * and/or audio content). These resources have to conform to their respective
 * XHTML or SVG definitions to be used in the spine or be referenced from
 * another EPUB Content Document.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
public interface Resource {

    /**
     * Resource as {@code InputStream} object.
     *
     * @return the publication resource
     */
    InputStream asStream();

}
