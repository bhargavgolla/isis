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

package org.apache.isis.viewer.scimpi.dispatcher.view.field;

import org.apache.isis.viewer.scimpi.dispatcher.AbstractElementProcessor;
import org.apache.isis.viewer.scimpi.dispatcher.Dispatcher;
import org.apache.isis.viewer.scimpi.dispatcher.context.RequestContext;
import org.apache.isis.viewer.scimpi.dispatcher.context.RequestContext.Scope;
import org.apache.isis.viewer.scimpi.dispatcher.processor.Request;

public class LinkField extends AbstractElementProcessor {

    @Override
    public void process(final Request request) {
        final String field = request.getOptionalProperty(NAME);
        final String variable = request.getOptionalProperty(REFERENCE_NAME, RequestContext.RESULT);
        final String scope = request.getOptionalProperty(SCOPE, Scope.INTERACTION.toString());
        final String forwardView = request.getOptionalProperty(VIEW, "_generic." + Dispatcher.EXTENSION);
        final LinkedFieldsBlock tag = (LinkedFieldsBlock) request.getBlockContent();
        tag.link(field, variable, scope, forwardView);
    }

    @Override
    public String getName() {
        return "link";
    }

}
