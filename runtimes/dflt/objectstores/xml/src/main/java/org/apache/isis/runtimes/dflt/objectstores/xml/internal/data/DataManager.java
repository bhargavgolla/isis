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

package org.apache.isis.runtimes.dflt.objectstores.xml.internal.data;

import org.apache.isis.runtimes.dflt.runtime.persistence.ObjectNotFoundException;
import org.apache.isis.runtimes.dflt.runtime.persistence.oidgenerator.simple.SerialOid;
import org.apache.isis.runtimes.dflt.runtime.transaction.ObjectPersistenceException;

public interface DataManager {

    void shutdown();

    /**
     * Return data for all instances that match the pattern.
     */
    public ObjectDataVector getInstances(final ObjectData pattern);

    /**
     * Return the number of instances that match the specified data
     */
    public int numberOfInstances(final ObjectData pattern);

    public Data loadData(final SerialOid oid);

    /**
     * Save the data for an object and adds the reference to a list of instances
     */
    void insertObject(ObjectData data);

    void remove(SerialOid oid) throws ObjectNotFoundException, ObjectPersistenceException;

    /**
     * Save the data for latter retrieval.
     */
    void save(Data data);

    String getDebugData();

    boolean isFixturesInstalled();
}