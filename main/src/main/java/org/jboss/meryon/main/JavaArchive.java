/*
 * JBoss, Home of Professional Open Source.
 * Copyright (c) 2013, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.meryon.main;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author <a href="mailto:cdewolf@redhat.com">Carlo de Wolf</a>
 */
public class JavaArchive extends AbstractResource {
    final SortedMap<String, Pkg> packages = new TreeMap<>();

    public JavaArchive(final String name) {
        super(name);
    }

    void addClass(final String className) {
        final int i = className.lastIndexOf('.');
        final String pkgName;
        if (i >= 0)
            pkgName = className.substring(0, className.lastIndexOf('.'));
        else
            pkgName = "";
        Pkg pkg = packages.get(pkgName);
        if (pkg == null) {
            pkg = new Pkg(pkgName);
            packages.put(pkgName, pkg);
        }
        pkg.addClass(className);
    }

    public Map<String, Pkg> getPackages() {
        return Collections.unmodifiableMap(packages);
    }
}
