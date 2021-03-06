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

package org.apache.isis.core.progmodel.facets.object.value.annotation;

import org.apache.isis.applib.annotation.Value;
import org.apache.isis.core.commons.config.IsisConfiguration;
import org.apache.isis.core.commons.lang.StringUtils;
import org.apache.isis.core.metamodel.facetapi.FacetHolder;
import org.apache.isis.core.progmodel.facets.object.value.ValueFacetAbstract;
import org.apache.isis.core.progmodel.facets.object.value.ValueSemanticsProviderContext;
import org.apache.isis.core.progmodel.facets.object.value.ValueSemanticsProviderUtil;

public class ValueFacetAnnotation extends ValueFacetAbstract {

    private static String semanticsProviderName(final Class<?> annotatedClass, final IsisConfiguration configuration) {
        final Value annotation = annotatedClass.getAnnotation(Value.class);
        final String semanticsProviderName = annotation.semanticsProviderName();
        if (!StringUtils.isNullOrEmpty(semanticsProviderName)) {
            return semanticsProviderName;
        }
        return ValueSemanticsProviderUtil.semanticsProviderNameFromConfiguration(annotatedClass, configuration);
    }

    private static Class<?> semanticsProviderClass(final Class<?> annotatedClass) {
        final Value annotation = annotatedClass.getAnnotation(Value.class);
        return annotation.semanticsProviderClass();
    }

    public ValueFacetAnnotation(final Class<?> annotatedClass, final FacetHolder holder, final IsisConfiguration configuration, final ValueSemanticsProviderContext context) {
        this(semanticsProviderName(annotatedClass, configuration), semanticsProviderClass(annotatedClass), holder, configuration, context);
    }

    private ValueFacetAnnotation(final String candidateSemanticsProviderName, final Class<?> candidateSemanticsProviderClass, final FacetHolder holder, final IsisConfiguration configuration, final ValueSemanticsProviderContext context) {
        super(ValueSemanticsProviderUtil.valueSemanticsProviderOrNull(candidateSemanticsProviderClass, candidateSemanticsProviderName), AddFacetsIfInvalidStrategy.DO_ADD, holder, configuration, context);
    }

    /**
     * Always valid, even if the specified semanticsProviderName might have been
     * wrong.
     */
    @Override
    public boolean isValid() {
        return true;
    }

}
