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
package uk.ac.manchester.cs.owl.owlapi;

import static org.semanticweb.owlapi.util.OWLAPIPreconditions.checkNotNull;

import java.io.Serializable;
import java.util.Set;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.util.CollectionFactory;

/**
 * @author Matthew Horridge, The University Of Manchester, Bio-Health Informatics Group
 * @since 2.0.0
 */
public class OWLClassImpl extends OWLClassExpressionImpl implements OWLClass, Serializable {

    private final IRI iri;
    private final boolean isThing;
    private final boolean isNothing;

    /**
     * @param iri class iri
     */
    public OWLClassImpl(IRI iri) {
        this.iri = checkNotNull(iri, "iri cannot be null");
        isThing = getIRI().isThing();
        isNothing = getIRI().isNothing();
    }

    @Override
    public OWLClassExpression getObjectComplementOf() {
        return new OWLObjectComplementOfImpl(this);
    }

    @Override
    public String toStringID() {
        return iri.toString();
    }

    @Override
    public IRI getIRI() {
        return iri;
    }

    @Override
    public boolean isBuiltIn() {
        return isOWLThing() || isOWLNothing();
    }

    @Override
    public boolean isOWLThing() {
        return isThing;
    }

    @Override
    public boolean isOWLNothing() {
        return isNothing;
    }

    @Override
    public OWLClassExpression getNNF() {
        return this;
    }

    @Override
    public Set<OWLClassExpression> asConjunctSet() {
        return CollectionFactory.createSet((OWLClassExpression) this);
    }

    @Override
    public boolean containsConjunct(OWLClassExpression ce) {
        return ce.equals(this);
    }

    @Override
    public Set<OWLClassExpression> asDisjunctSet() {
        return CollectionFactory.createSet((OWLClassExpression) this);
    }

    @Override
    public OWLClassExpression getComplementNNF() {
        return new OWLObjectComplementOfImpl(this);
    }
}
