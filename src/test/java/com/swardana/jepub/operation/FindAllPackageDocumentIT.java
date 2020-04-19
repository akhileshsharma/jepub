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

package com.swardana.jepub.operation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.swardana.jepub.PathName;
import java.io.File;
import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Integration test for
 * {@link com.swardana.jepub.operation.FindAllPackageDocument}.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
public class FindAllPackageDocumentIT {

    @Test
    @DisplayName("Find all EPUB Package document")
    public void shouldFindAllPackageDocument() {
        var epubFile = getClass().getClassLoader()
            .getResource("epub/childrens-literature.epub")
            .getFile();
        var paths = new ArrayList<PathName>();
        new FindAllPackageDocument(new File(epubFile), paths).execute();
        assertTrue(paths.size() >= 1);
    }

}
