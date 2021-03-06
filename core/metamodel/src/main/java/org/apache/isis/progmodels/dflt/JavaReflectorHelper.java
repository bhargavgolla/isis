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

package org.apache.isis.progmodels.dflt;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.isis.core.commons.config.IsisConfiguration;
import org.apache.isis.core.metamodel.facetapi.ClassSubstitutorFactory;
import org.apache.isis.core.metamodel.facetapi.MetaModelRefiner;
import org.apache.isis.core.metamodel.facetdecorator.FacetDecorator;
import org.apache.isis.core.metamodel.layout.MemberLayoutArranger;
import org.apache.isis.core.metamodel.progmodel.ProgrammingModel;
import org.apache.isis.core.metamodel.spec.SpecificationLoaderSpi;
import org.apache.isis.core.metamodel.specloader.ObjectReflectorDefault;
import org.apache.isis.core.metamodel.specloader.classsubstitutor.ClassSubstitutor;
import org.apache.isis.core.metamodel.specloader.collectiontyperegistry.CollectionTypeRegistry;
import org.apache.isis.core.metamodel.specloader.collectiontyperegistry.CollectionTypeRegistryDefault;
import org.apache.isis.core.metamodel.specloader.traverser.SpecificationTraverser;
import org.apache.isis.core.metamodel.specloader.traverser.SpecificationTraverserDefault;
import org.apache.isis.core.metamodel.specloader.validator.MetaModelValidator;
import org.apache.isis.core.metamodel.specloader.validator.MetaModelValidatorComposite;
import org.apache.isis.core.progmodel.layout.dflt.MemberLayoutArrangerDefault;

public final class JavaReflectorHelper  {
    
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(JavaReflectorHelper.class);

    private JavaReflectorHelper(){}
    
    public static SpecificationLoaderSpi createObjectReflector(
                                                           final ProgrammingModel programmingModel,
                                                           final ClassSubstitutorFactory classSubstitutorFactory,
                                                           final Collection<MetaModelRefiner> metaModelRefiners,
                                                           final Set<FacetDecorator> facetDecorators,
                                                           final MetaModelValidator mmv, 
                                                           final IsisConfiguration configuration) {
        final MemberLayoutArranger memberLayoutArranger = new MemberLayoutArrangerDefault();
        final SpecificationTraverser specificationTraverser = new SpecificationTraverserDefault();
        final CollectionTypeRegistry collectionTypeRegistry = new CollectionTypeRegistryDefault();
        final ClassSubstitutor classSubstitutor = classSubstitutorFactory.createClassSubstitutor(configuration);
        
        MetaModelValidatorComposite metaModelValidator = MetaModelValidatorComposite.asComposite(mmv);
        for (MetaModelRefiner metaModelRefiner : metaModelRefiners) {
            metaModelRefiner.refineProgrammingModel(programmingModel, configuration);
            metaModelRefiner.refineMetaModelValidator(metaModelValidator, configuration);
        }
        
        // the programming model is itself also a MetaModelValidatorRefiner
        if(!metaModelRefiners.contains(programmingModel)) {
            programmingModel.refineMetaModelValidator(metaModelValidator, configuration);
        }
        
        return new ObjectReflectorDefault(configuration, classSubstitutor, collectionTypeRegistry, specificationTraverser, memberLayoutArranger, programmingModel, facetDecorators, metaModelValidator);
    }

}
