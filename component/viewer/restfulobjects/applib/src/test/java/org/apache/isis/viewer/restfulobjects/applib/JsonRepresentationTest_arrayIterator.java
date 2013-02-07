/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.isis.viewer.restfulobjects.applib;

import static org.apache.isis.viewer.restfulobjects.applib.JsonFixture.readJson;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

public class JsonRepresentationTest_arrayIterator {

    private JsonRepresentation jsonRepresentation;

    @Test
    public void forJsonRepresentation() throws JsonParseException, JsonMappingException, IOException {
        jsonRepresentation = new JsonRepresentation(readJson("list.json"));
        final Iterator<JsonRepresentation> arrayIterator = jsonRepresentation.arrayIterator(JsonRepresentation.class);
        assertThat(arrayIterator.hasNext(), is(true));
        assertThat(arrayIterator.next().getString("a"), is("a1"));
        assertThat(arrayIterator.hasNext(), is(true));
        assertThat(arrayIterator.next().getString("b"), is("b1"));
        assertThat(arrayIterator.hasNext(), is(false));
    }

    @Test
    public void forString() throws JsonParseException, JsonMappingException, IOException {
        jsonRepresentation = new JsonRepresentation(readJson("listOfStrings.json"));
        final Iterator<String> arrayIterator = jsonRepresentation.arrayIterator(String.class);
        assertThat(arrayIterator.hasNext(), is(true));
        assertThat(arrayIterator.next(), is("a"));
        assertThat(arrayIterator.hasNext(), is(true));
        assertThat(arrayIterator.next(), is("b"));
        assertThat(arrayIterator.hasNext(), is(true));
        assertThat(arrayIterator.next(), is("c"));
        assertThat(arrayIterator.hasNext(), is(false));
    }

}
