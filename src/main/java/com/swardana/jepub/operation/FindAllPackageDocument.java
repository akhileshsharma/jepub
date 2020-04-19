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

import com.swardana.jepub.FixedPathName;
import com.swardana.jepub.PathName;
import com.swardana.jepub.PublicationResource;
import com.swardana.jepub.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;

/**
 * Find all package document on EPUB File.
 *
 * @author Sukma Wardana
 * @since 1.0.0
 */
public class FindAllPackageDocument implements Operation {

    private final List<PathName> packages;
    private final Resource container;

    /**
     * Creates new FindAllPackageDocument.
     * <p>
     * Finding all of the EPUB Packages.
     *
     * @param epub the EPUB file.
     * @param paths the ordered list of {@link PathName} EPUB Packages document
     */
    public FindAllPackageDocument(final File epub, final List<PathName> paths) {
        this.packages = paths;
        this.container = new PublicationResource(
            epub,
            new FixedPathName("META-INF/container.xml")
        );
    }

    @Override
    public final void execute() {
        try (var stream = this.container.asStream()) {
            var reader = XMLInputFactory
                .newInstance()
                .createXMLEventReader(stream);
            while (reader.hasNext()) {
                var event = reader.nextEvent();

                if (event.isStartElement()) {
                    if ("rootfile".equals(
                        event.asStartElement().getName().getLocalPart()
                    )) {
                        var attributes = event.asStartElement().getAttributes();
                        while (attributes.hasNext()) {
                            var attribute = attributes.next();
                            if ("full-path".equals(
                                attribute.getName().getLocalPart())
                            ) {
                                this.packages.add(
                                    new FixedPathName(attribute.getValue())
                                );
                            }
                        }
                    }
                }

                if (event.isEndElement()) {
                    if ("rootfiles".equals(
                        event.asEndElement().getName().getLocalPart()
                    )) {
                        break;
                    }
                }

            }
        } catch (final IOException ex) {
            throw new RuntimeException(
                "Failed to open container.xml file", ex
            );
        } catch (final XMLStreamException ex) {
            throw new RuntimeException(
                "Failed to stream the XML file", ex
            );
        }
    }

}
