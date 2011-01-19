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


package org.apache.isis.core.metamodel.specloader.specimpl;

import org.apache.isis.core.metamodel.adapter.MutableProposedHolder;
import org.apache.isis.core.metamodel.adapter.ObjectAdapter;
import org.apache.isis.core.metamodel.facetapi.FeatureType;
import org.apache.isis.core.metamodel.facets.TypedHolder;
import org.apache.isis.core.metamodel.spec.Instance;
import org.apache.isis.core.metamodel.spec.feature.OneToOneActionParameter;


public class OneToOneActionParameterImpl extends ObjectActionParameterAbstract implements OneToOneActionParameter {

    public OneToOneActionParameterImpl(final int index, final ObjectActionImpl actionImpl, final TypedHolder peer) {
        super(index, actionImpl, peer);
    }

    @Override
    public FeatureType getFeatureType() {
        return FeatureType.ACTION_PARAMETER;
    }


    @Override
    public boolean isObject() {
        return true;
    }

    /**
     * TODO: need to be able to validate parameters individually, eg if have <tt>RegEx</tt> annotation;
     * should delegate to the Check framework instead.
     */
    @Override
    public String isValid(final ObjectAdapter adapter, final Object proposedValue) {
        return null;
    }

    // /////////////////////////////////////////////////////////////
    // getInstance
    // /////////////////////////////////////////////////////////////
    
    @Override
    public Instance getInstance(ObjectAdapter adapter) {
        OneToOneActionParameter specification = this;
        return adapter.getInstance(specification);
    }


    // //////////////////////////////////////////////////////////////////////
    // get, set
    // //////////////////////////////////////////////////////////////////////

    /**
     * Gets the proposed value of the {@link Instance} (downcast as a
     * {@link MutableProposed}, wrapping the proposed value into a {@link ObjectAdapter}.
     */
    @Override
    public ObjectAdapter get(ObjectAdapter owner) {
        MutableProposedHolder proposedHolder = getProposedHolder(owner);
        Object proposed = proposedHolder.getProposed();
        return getAdapterMap().adapterFor(proposed);
    }

    /**
     * Sets the proposed value of the {@link Instance} (downcast as a
     * {@link MutableProposed}, unwrapped the proposed value from a {@link ObjectAdapter}.
     */
    public void set(ObjectAdapter owner, ObjectAdapter newValue) {
        MutableProposedHolder proposedHolder = getProposedHolder(owner);
        Object newValuePojo = newValue.getObject();
        proposedHolder.setProposed(newValuePojo);
    }

    private MutableProposedHolder getProposedHolder(ObjectAdapter owner) {
        Instance instance = getInstance(owner);
        if(!(instance instanceof MutableProposedHolder)) {
            throw new IllegalArgumentException("Instance should implement MutableProposedHolder");
        }
        MutableProposedHolder proposedHolder = (MutableProposedHolder) instance;
        return proposedHolder;
    }

}