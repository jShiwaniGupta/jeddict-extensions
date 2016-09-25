/**
 * Copyright [2014] Gaurav Gupta
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
package org.netbeans.jpa.modeler.spec.extend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import org.apache.commons.lang.StringUtils;
import org.netbeans.jpa.modeler.spec.EntityMappings;
import org.netbeans.jpa.modeler.spec.IdentifiableClass;
import org.netbeans.jpa.modeler.spec.extend.annotation.Annotation;
import org.netbeans.jpa.source.JavaSourceParserUtil;
import org.netbeans.modeler.core.NBModelerUtil;
import org.netbeans.jpa.source.JCRELoader;
import org.openide.filesystems.FileObject;

/**
 *
 * @author Gaurav Gupta
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class JavaClass extends FlowNode implements JCRELoader {

    @XmlElement(name = "ts")
    private ClassMembers toStringMethod;

    @XmlElement(name = "hc")
    private ClassMembers hashCodeMethod;

    @XmlElement(name = "eq")
    private ClassMembers equalsMethod;

    @XmlElement(name = "con")
    private List<Constructor> constructors;

    @XmlAttribute(name = "abs")
    protected Boolean _abstract = false;

    @XmlAttribute(name = "class", required = true)
    protected String clazz;

    @XmlAttribute
    private String superclassId;

//  @XmlElementWrapper(name = "interface-list")
    @XmlElement(name = "inf")
    private List<String> interfaces;

    @XmlTransient
    private JavaClass superclass;

    @XmlTransient
    private Set<JavaClass> subclassList;

    @XmlAttribute
    private boolean visibile = true;

    private List<Annotation> annotation;
    
    @XmlElement(name = "snp")
    private List<Snippet> snippets;
    
    @XmlTransient
    private FileObject fileObject;
    
    
    @XmlAttribute(name = "gen")
    private Boolean generatesourceCode;
    
    @XmlAttribute(name = "pkg")
    private String _package;

    @Override
    public void load(EntityMappings entityMappings, TypeElement element, boolean fieldAccess) {
        this.setId(NBModelerUtil.getAutoGeneratedStringId());

        this.clazz = element.getSimpleName().toString();
        if (element.getModifiers().contains(Modifier.ABSTRACT)) {
            this.setAbstract(true);
        }
        for (TypeMirror mirror : element.getInterfaces()) {
            if (Serializable.class.getName().equals(mirror.toString())) {
                continue;
            }
            this.addInterface(mirror.toString());
        }
        this.setAnnotation(JavaSourceParserUtil.getNonEEAnnotation(element));
    }

    /**
     * @return the annotation
     */
    public List<Annotation> getAnnotation() {
        if (annotation == null) {
            annotation = new ArrayList<>();
        }
        return annotation;
    }

    /**
     * @param annotation the annotation to set
     */
    public void setAnnotation(List<Annotation> annotation) {
        this.annotation = annotation;
    }

    public void addAnnotation(Annotation annotation_In) {
        if (annotation == null) {
            annotation = new ArrayList<>();
        }
        this.annotation.add(annotation_In);
    }

    public void removeAnnotation(Annotation annotation_In) {
        if (annotation == null) {
            annotation = new ArrayList<>();
        }
        this.annotation.remove(annotation_In);
    }

    /**
     * @return the visibile
     */
    public boolean isVisibile() {
        return visibile;
    }

    /**
     * @param visibile the visibile to set
     */
    public void setVisibile(boolean visibile) {
        this.visibile = visibile;
    }

    /**
     * @return the superclassRef
     */
    public JavaClass getSuperclass() {
        return superclass;
    }
    
    public List<Attribute> getSuperclassAttributes() {
         List<Attribute> attributes = Collections.EMPTY_LIST;
        if(superclass !=null && superclass instanceof IdentifiableClass){
            attributes = ((IdentifiableClass)superclass).getAttributes().getAllAttribute();
            attributes.addAll(superclass.getSuperclassAttributes());
        }
        return attributes;
    }

    /**
     * @param superclassRef the superclassRef to set
     */
    public void addSuperclass(JavaClass superclassRef) {
        if (this.superclass == superclassRef) {
            return;
        }
        if (this.superclass != null) {
            throw new RuntimeException("JavaClass.addSuperclass > superclass is already exist [remove it first to add the new one]");
        }
        this.superclass = superclassRef;
        if (this.superclass != null) {
            this.superclassId = this.superclass.getId();
            this.superclass.addSubclass(this);
        } else {
            throw new RuntimeException("JavaClass.addSuperclass > superclassRef is null");
        }
    }

    public void removeSuperclass(JavaClass superclassRef) {

        if (superclassRef != null) {
            superclassRef.removeSubclass(this);
        } else {
            throw new RuntimeException("JavaClass.removeSuperclass > superclassRef is null");
        }
        this.superclassId = null;
        this.superclass = null;
    }

    /**
     * @return the subclassList
     */
    public Set<JavaClass> getSubclassList() {
        if (this.subclassList == null) {
            this.subclassList = new HashSet<>();
        }
        return subclassList;
    }

    /**
     * @param subclassList the subclassList to set
     */
    public void setSubclassList(Set<JavaClass> subclassList) {
        if (this.subclassList == null) {
            this.subclassList = new HashSet<JavaClass>();
        }
        this.subclassList = subclassList;
    }

    public void addSubclass(JavaClass subclass) {
        if (this.subclassList == null) {
            this.subclassList = new HashSet<JavaClass>();
        }
        this.subclassList.add(subclass);
    }

    public void removeSubclass(JavaClass subclass) {
        if (this.subclassList == null) {
            this.subclassList = new HashSet<JavaClass>();
        }
        this.subclassList.remove(subclass);
    }

    /**
     * @return the superclassId
     */
    public String getSuperclassId() {
        return superclassId;
    }

    /**
     * @return the _abstract
     */
    public Boolean getAbstract() {
        return _abstract;
    }

    /**
     * @param _abstract the _abstract to set
     */
    public void setAbstract(Boolean _abstract) {
        this._abstract = _abstract;
    }

    /**
     * @return the interfaces
     */
    public List<String> getInterfaces() {
        return interfaces;
    }

    /**
     * @param interfaces the interfaces to set
     */
    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    public void addInterface(String _interface) {
        if (this.interfaces == null) {
            this.interfaces = new ArrayList<String>();
        }
        this.interfaces.add(_interface);
    }

    public void removeInterface(String _interface) {
        if (this.interfaces == null) {
            this.interfaces = new ArrayList<String>();
        }
        this.interfaces.remove(_interface);
    }

    /**
     * Gets the value of the clazz property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClazz() {
        return clazz;
    }

    /**
     * Sets the value of the clazz property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setClazz(String value) {
        this.clazz = value;
    }

    /**
     * @return the fileObject
     */
    public FileObject getFileObject() {
        return fileObject;
    }

    /**
     * @param fileObject the fileObject to set
     */
    public void setFileObject(FileObject fileObject) {
        this.fileObject = fileObject;
    }

    /**
     * @return the toStringMethod
     */
    public ClassMembers getToStringMethod() {
        if(toStringMethod==null){
            toStringMethod = new ClassMembers();
        }
        return toStringMethod;
    }

    /**
     * @param toStringMethod the toStringMethod to set
     */
    public void setToStringMethod(ClassMembers toStringMethod) {
        this.toStringMethod = toStringMethod;
    }

    /**
     * @return the hashCodeMethod
     */
    public ClassMembers getHashCodeMethod() {
        if(hashCodeMethod==null){
            hashCodeMethod = new ClassMembers();
        }
        return hashCodeMethod;
    }

    /**
     * @param hashCodeMethod the hashCodeMethod to set
     */
    public void setHashCodeMethod(ClassMembers hashCodeMethod) {
        this.hashCodeMethod = hashCodeMethod;
    }

    /**
     * @return the equalsMethod
     */
    public ClassMembers getEqualsMethod() {
        if(equalsMethod==null){
            equalsMethod = new ClassMembers();
        }
        return equalsMethod;
    }

    /**
     * @param equalsMethod the equalsMethod to set
     */
    public void setEqualsMethod(ClassMembers equalsMethod) {
        this.equalsMethod = equalsMethod;
    }

    /**
     * @return the constructors
     */
    public List<Constructor> getConstructors() {
        if(constructors==null){
            constructors = new ArrayList<>();
        }
        return constructors;
    }

    /**
     * @param constructors the constructors to set
     */
    public void setConstructors(List<Constructor> constructors) {
        this.constructors = constructors;
    }

    /**
     * @return the generatesourceCode
     */
    public Boolean getGeneratesourceCode() {
        if(generatesourceCode==null){
            return true;
        }
        return generatesourceCode;
    }

    /**
     * @param generatesourceCode the generatesourceCode to set
     */
    public void setGeneratesourceCode(Boolean generatesourceCode) {
        if(generatesourceCode!=false){
            this.generatesourceCode = null;
        } else {
            this.generatesourceCode = false;
        }
        // default value will be true, store only for false
    }

    /**
     * @return the snippets
     */
    public List<Snippet> getSnippets() {
        if(snippets==null){
            snippets = new ArrayList<>();
        }
        return snippets;
    }

    /**
     * @param snippets the snippets to set
     */
    public void setSnippets(List<Snippet> snippets) {
        this.snippets = snippets;
    }

     public boolean addSnippet(Snippet snippet) {
        return getSnippets().add(snippet);
    }

    public boolean removeSnippet(Snippet snippet) {
        return getSnippets().remove(snippet);
    }

    /**
     * @return the _package
     */
    public String getPackage() {
        return _package;
    }
    
    /**
     * @param rootPackage
     * @return the complete _package
     */
    public String getPackage(String rootPackage) {
        return StringUtils.isBlank(_package) ? rootPackage : rootPackage + '.' + _package;
    }
    
    public String getFQN(String rootPackage) {
        return getPackage(rootPackage) + '.' + getClazz();
    }

    /**
     * @param _package the _package to set
     */
    public void setPackage(String _package) {
        this._package = _package;
    }
}
