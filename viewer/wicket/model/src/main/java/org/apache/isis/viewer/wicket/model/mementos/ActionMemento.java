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


package org.apache.isis.viewer.wicket.model.mementos;

import java.io.Serializable;

import org.apache.isis.metamodel.spec.feature.ObjectAction;
import org.apache.isis.metamodel.spec.feature.ObjectActionType;

/**
 * {@link Serializable} represention of a {@link ObjectAction}
 */
public class ActionMemento implements Serializable {

	private static final long serialVersionUID = 1L;

	private SpecMemento owningType;
	private ObjectActionType actionType;
	private String nameParmsId;

	private transient ObjectAction action;
	
	public ActionMemento(SpecMemento owningType,
			ObjectActionType actionType, String nameParmsId) {
		this.owningType = owningType;
		this.actionType = actionType;
		this.nameParmsId = nameParmsId;
	}

	public ActionMemento(ObjectAction action) {
		this(new SpecMemento(action.getOnType()), action.getType(), action.getIdentifier().toNameParmsIdentityString());
		this.action = action;
	}
	
	public SpecMemento getOwningType() {
		return owningType;
	}

	public ObjectActionType getActionType() {
		return actionType;
	}
	
	public String getNameParmsId() {
		return nameParmsId;
	}

	public ObjectAction getAction() {
		if (action == null) {
			action = owningType.getSpecification().getObjectAction(actionType, nameParmsId);
		}
		return action;
	}
	

}