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
package org.netbeans.jcode.mvc.viewer.dto;

import java.util.Collection;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import org.netbeans.api.java.classpath.ClassPath;
import org.netbeans.api.java.source.ClasspathInfo;
import org.netbeans.jcode.generator.internal.util.CustomJpaControllerUtil;
import static org.netbeans.jcode.jpa.JPAConstants.ID;
import org.openide.filesystems.FileObject;


public abstract class EntityClass {

    // Provide a method to determine if an entity field's value is autogenerated
    static boolean isAutoGenerated(ExecutableElement method, boolean isFieldAccess) {
        Element element = isFieldAccess ? CustomJpaControllerUtil.guessField(method) : method;
        if (element != null) {
            if (CustomJpaControllerUtil.isAnnotatedWith(element, "javax.persistence.GeneratedValue")) { // NOI18N
                return true;
            }
        }
        return false;
    }

    static boolean isId(ExecutableElement method, boolean isFieldAccess) {
        Element element = isFieldAccess ? CustomJpaControllerUtil.guessField(method) : method;
        if (element != null) {
            if (CustomJpaControllerUtil.isAnnotatedWith(element, ID) || CustomJpaControllerUtil.isAnnotatedWith(element, "javax.persistence.EmbeddedId")) { // NOI18N
                return true;
            }
        }
        return false;
    }

    static String getTemporal(ExecutableElement method, boolean isFieldAccess) {
        Element element = isFieldAccess ? CustomJpaControllerUtil.guessField(method) : method;
        if (element != null) {
            AnnotationMirror annotationMirror = CustomJpaControllerUtil.findAnnotation(element, "javax.persistence.Temporal"); // NOI18N
            if (annotationMirror != null) {
                Collection<? extends AnnotationValue> attributes = annotationMirror.getElementValues().values();
                if (attributes.iterator().hasNext()) {
                    AnnotationValue annotationValue = attributes.iterator().next();
                    if (annotationValue != null) {
                        return annotationValue.getValue().toString();
                    }
                }
            }
        }
        return null;
    }

    static ClasspathInfo createClasspathInfo(FileObject fileObject) {
        return ClasspathInfo.create(
                ClassPath.getClassPath(fileObject, ClassPath.BOOT),
                ClassPath.getClassPath(fileObject, ClassPath.COMPILE),
                ClassPath.getClassPath(fileObject, ClassPath.SOURCE)
        );
    }

    static String getDateTimeFormat(String temporal) {
        if ("DATE".equals(temporal)) {
            return "MM/dd/yyyy";
        } else if ("TIME".equals(temporal)) {
            return "HH:mm:ss";
        } else {
            return "MM/dd/yyyy HH:mm:ss";
        }
    }
}
