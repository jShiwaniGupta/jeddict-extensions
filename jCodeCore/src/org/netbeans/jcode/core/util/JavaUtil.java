/**
 * Copyright [2016] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.jcode.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Gaurav Gupta
 */
public class JavaUtil {

    public static Class<?> getPrimitiveType(String typeName) {
        return Lazy.primitiveTypes.get(typeName);
    }

    private static class Lazy {

        private static final Map<String, Class<?>> primitiveTypes = new HashMap<String, Class<?>>();

        static {
            primitiveTypes.put("int", Integer.class);
            primitiveTypes.put("int[]", int[].class);
            primitiveTypes.put("java.lang.Integer[]", Integer[].class);
            primitiveTypes.put("boolean", Boolean.class);
            primitiveTypes.put("boolean[]", boolean[].class);
            primitiveTypes.put("java.lang.Boolean[]", Boolean[].class);
            primitiveTypes.put("byte", Byte.class);
            primitiveTypes.put("byte[]", byte[].class);
            primitiveTypes.put("java.lang.Byte[]", Byte[].class);
            primitiveTypes.put("char", Character.class);
            primitiveTypes.put("char[]", char[].class);
            primitiveTypes.put("java.lang.Character[]", Character[].class);
            primitiveTypes.put("double", Double.class);
            primitiveTypes.put("double[]", double[].class);
            primitiveTypes.put("java.lang.Double[]", Double[].class);
            primitiveTypes.put("float", Float.class);
            primitiveTypes.put("float[]", float[].class);
            primitiveTypes.put("java.lang.Float[]", Float[].class);
            primitiveTypes.put("long", Long.class);
            primitiveTypes.put("long[]", long[].class);
            primitiveTypes.put("java.lang.Long[]", Long[].class);
            primitiveTypes.put("short", Short.class);
            primitiveTypes.put("short[]", short[].class);
            primitiveTypes.put("java.lang.Short[]", Short[].class);
        }
    }
    
    public static boolean isMap(String _className) {
        Class _class = null;
        try {
            _class = Class.forName(_className);
        } catch (ClassNotFoundException ex) {
        }
        if (_class == null) {
            return false;
        }
        return Map.class.isAssignableFrom(_class);
    }

}
