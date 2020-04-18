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

import java.io.File;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * EPUB publication resource.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
public class PublicationResource implements Resource {

    private final File epub;
    private final PathName location;

    /**
     * Creates new PublicationResource.
     *
     * @param epub the EPUB file.
     * @param path the {@link PathName} of publication resource
     */
    public PublicationResource(final File epub, final PathName path) {
        this.epub = epub;
        this.location = path;
    }

    @Override
    public final InputStream asStream() {
        try {
            final ZipFile zip = new ZipFile(this.epub);
            final ZipEntry entry = zip.getEntry(this.location.asString());

            if (entry == null) {
                zip.close();
                throw new IOException("Can't find Publication Resource!");
            }

            return new Wrapper(zip.getInputStream(entry), zip);
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * A wrapper class for closing a ZipFile object when the InputStream derived
     * from it is closed.
     */
    class Wrapper extends FilterInputStream {

        private final ZipFile zip;

        /**
         * Creates new Wrapper.
         *
         * @param item content within zip file.
         * @param zip zip archive file.
         */
        Wrapper(final InputStream item, final ZipFile zip) {
            super(item);
            this.zip = zip;
        }

        @Override
        public void close() throws IOException {
            super.close();
            this.zip.close();
        }
    }

}
