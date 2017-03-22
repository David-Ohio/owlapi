/* This file is part of the OWL API.
 * The contents of this file are subject to the LGPL License, Version 3.0.
 * Copyright 2014, The University of Manchester
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0 in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License. */
package org.semanticweb.owlapi.io;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLRuntimeException;
import org.semanticweb.owlapi.model.OWLStorerParameters;

/**
 * @author Matthew Horridge, The University Of Manchester, Bio-Health Informatics Group
 * @since 2.0.0
 */
@FunctionalInterface
public interface OWLRenderer {

    /**
     * Renders the specified ontology to a concrete representation which should be written to the
     * specified output stream.
     *
     * @param ontology The ontology
     * @param os The OutputStream
     * @param storerParameters storer parameters
     * @throws OWLRendererException for any exception raised
     */
    default void render(OWLOntology ontology, OutputStream os, OWLStorerParameters storerParameters)
        throws OWLRendererException {
        try {
            PrintWriter writer = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(os, storerParameters.getEncoding())));
            render(ontology, writer, storerParameters);
            writer.flush();
        } catch (OWLRuntimeException e) {
            throw new OWLRendererIOException(e);
        }
    }

    /**
     * Renders the specified ontology using the specified writer.
     *
     * @param ontology the ontology to render
     * @param writer The writer that should be used to write the ontology. Note that this writer
     *        need not be wrapped with a {@code BufferedWriter} because this is taken care of by
     *        this abstract implementation.
     * @param storerParameters storer parameters
     * @throws OWLRendererException if exceptions arise
     */
    void render(OWLOntology ontology, PrintWriter writer, OWLStorerParameters storerParameters)
        throws OWLRendererException;
}
