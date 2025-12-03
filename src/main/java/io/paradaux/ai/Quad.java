/*
 * Copyright (c) 2020, Rían Errity. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 3 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 3 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Rían Errity <rian@paradaux.io> or visit https://paradaux.io
 * if you need additional information or have any questions.
 * See LICENSE.md for more details.
 */

package io.paradaux.ai;

import java.io.Serializable;

/**
 * Modifications Copyright Rían Errity, 2021, https://paradaux.io
 * Copyright Paul James Mutton, 2001-2004, http://www.jibble.org/
 *
 * @author pjm2
 * @author Rían Errity
 * <p>
 * This software is dual-licensed, allowing you to choose between the GNU
 * General Public License (GPL) and the www.jibble.org Commercial License.
 * Since the GPL may be too restrictive for use in a proprietary application,
 * a commercial license is also provided. Full license information can be found at http://www.jibble.org/licenses/
 *
 *
 */
public class Quad implements Serializable {

    private final String[] tokens;

    private boolean canStart;

    private boolean canEnd;

    public Quad(String paramString1, String paramString2, String paramString3, String paramString4) {
        this.canStart = false;
        this.canEnd = false;
        this.tokens = new String[]{paramString1, paramString2, paramString3, paramString4};
    }

    public String getToken(int paramInt) {
        return this.tokens[paramInt];
    }

    public void setCanStart(boolean paramBoolean) {
        this.canStart = paramBoolean;
    }

    public void setCanEnd(boolean paramBoolean) {
        this.canEnd = paramBoolean;
    }

    public boolean canStart() {
        return this.canStart;
    }

    public boolean canEnd() {
        return this.canEnd;
    }

    public int hashCode() {
        return this.tokens[0].hashCode() + this.tokens[1].hashCode() + this.tokens[2].hashCode() + this.tokens[3].hashCode();
    }

    @Override
    public boolean equals(Object paramObject) {
        if (!(paramObject instanceof Quad)) {
            return false;
        }

        Quad quad = (Quad) paramObject;
        return (quad.tokens[0].equals(this.tokens[0]) && quad.tokens[1].equals(this.tokens[1]) && quad.tokens[2].equals(this.tokens[2])
                && quad.tokens[3].equals(this.tokens[3]));
    }
}
