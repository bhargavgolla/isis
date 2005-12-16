package test.org.nakedobjects.objects.specification;

import org.nakedobjects.object.NakedObject;
import org.nakedobjects.object.NakedValue;
import org.nakedobjects.object.OneToOneAssociation;
import org.nakedobjects.object.control.Allow;
import org.nakedobjects.object.control.Consent;
import org.nakedobjects.utility.UnexpectedCallException;


public abstract class TestOneToOneAssociation implements OneToOneAssociation {
    public void clearValue(NakedObject inObject) {
        throw new UnexpectedCallException();
    }

    public void initValue(NakedObject inObject, Object value) {
        throw new UnexpectedCallException();
        }

    public void setValue(NakedObject inObject, Object value) {
        throw new UnexpectedCallException();
        }

    public Consent isValueValid(NakedObject inObject, NakedValue value) {
        throw new UnexpectedCallException();
        }

    public Class[] getExtensions() {
        return new Class[0];
    }

    public boolean isCollection() {
        return false;
    }

    public boolean isDerived() {
        return false;
    }

    public boolean isEmpty(NakedObject adapter) {
        return false;
    }

    public boolean isHidden() {
        return false;
    }

    public boolean isMandatory() {
        return false;
    }

    public boolean isObject() {
        return true;
    }

    public boolean isValue() {
        return false;
    }

    public String getDescription() {
        return "";
    }

    public Object getExtension(Class cls) {
        return null;
    }

    public boolean isAuthorised() {
        return true;
    }

    public Consent isUsable(NakedObject target) {
        return Allow.DEFAULT;
    }

    public Consent isVisible(NakedObject target) {
        return Allow.DEFAULT;
    }

}

/*
 * Naked Objects - a framework that exposes behaviourally complete business objects directly to the user.
 * Copyright (C) 2000 - 2005 Naked Objects Group Ltd
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to
 * the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * The authors can be contacted via www.nakedobjects.org (the registered address of Naked Objects Group is
 * Kingsway House, 123 Goldworth Road, Woking GU21 1NR, UK).
 */