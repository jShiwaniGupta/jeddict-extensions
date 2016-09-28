//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.internal.jpa.metadata.columns.PrimaryKeyJoinColumnMetadata;
import org.netbeans.jpa.modeler.spec.extend.IJoinColumn;
import org.netbeans.jpa.modeler.spec.validator.column.ForeignKeyValidator;
import org.netbeans.jpa.source.JavaSourceParserUtil;
import org.netbeans.jpa.modeler.spec.validator.column.PrimaryKeyJoinColumnValidator;
/**
 *
 *
 * @Target({TYPE, METHOD, FIELD}) @Retention(RUNTIME) public @interface
 * PrimaryKeyJoinColumn { String name() default ""; String
 * referencedColumnName() default ""; String columnDefinition() default ""; }
 *
 *
 *
 * <p>
 * Java class for primary-key-join-column complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="primary-key-join-column">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="referenced-column-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="column-definition" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pk-jc")
@XmlJavaTypeAdapter(value = PrimaryKeyJoinColumnValidator.class)
public class PrimaryKeyJoinColumn implements IJoinColumn {

    @XmlAttribute(name = "name")
    protected String name;
    @XmlTransient
    private String implicitName;//automatically assigned by persistence provider
    @XmlAttribute(name = "rc")
    protected String referencedColumnName;
    @XmlAttribute(name = "cd")
    protected String columnDefinition;
    @XmlElement(name = "fk")
    private ForeignKey foreignKey;
    
    private static PrimaryKeyJoinColumn loadAttribute(Element element, AnnotationMirror annotationMirror) {
        PrimaryKeyJoinColumn primaryKeyJoinColumn = null;
        if (annotationMirror != null) {
            primaryKeyJoinColumn = new PrimaryKeyJoinColumn();
             primaryKeyJoinColumn.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
            primaryKeyJoinColumn.referencedColumnName = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "referencedColumnName");
            primaryKeyJoinColumn.columnDefinition = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "columnDefinition");

            AnnotationMirror foreignKeyValue = (AnnotationMirror) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "foreignKey");
            if (foreignKeyValue != null) {
                primaryKeyJoinColumn.foreignKey = ForeignKey.load(element, foreignKeyValue);
            }
        }
        return primaryKeyJoinColumn;
    }
    
    public static List<PrimaryKeyJoinColumn> load(Element element) {
        List<PrimaryKeyJoinColumn> primaryKeyJoinColumns = new ArrayList<>();

        AnnotationMirror attributeOverridesMirror = JavaSourceParserUtil.findAnnotation(element, "javax.persistence.PrimaryKeyJoinColumns");
        if (attributeOverridesMirror != null) {
            List attributeOverridesMirrorList = (List) JavaSourceParserUtil.findAnnotationValue(attributeOverridesMirror, "value");
            if (attributeOverridesMirrorList != null) {
                for (Object attributeOverrideObj : attributeOverridesMirrorList) {
                    primaryKeyJoinColumns.add(PrimaryKeyJoinColumn.loadAttribute(element, (AnnotationMirror) attributeOverrideObj));
                }
            }
        } else {
            attributeOverridesMirror = JavaSourceParserUtil.findAnnotation(element, "javax.persistence.PrimaryKeyJoinColumn");
            if (attributeOverridesMirror != null) {
                primaryKeyJoinColumns.add(PrimaryKeyJoinColumn.loadAttribute(element, attributeOverridesMirror));
            }
        }

        return primaryKeyJoinColumns;
    }

    
    
    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        if (value != null) {
            value = value.toUpperCase();
        }
        this.name = value;
    }

    /**
     * Gets the value of the referencedColumnName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getReferencedColumnName() {
        return referencedColumnName;
    }

    /**
     * Sets the value of the referencedColumnName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setReferencedColumnName(String value) {
        if (value != null) {
            value = value.toUpperCase();
        }
        this.referencedColumnName = value;
    }

    /**
     * Gets the value of the columnDefinition property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getColumnDefinition() {
        return columnDefinition;
    }

    /**
     * Sets the value of the columnDefinition property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setColumnDefinition(String value) {
        this.columnDefinition = value;
    }

    /**
     * @return the foreignKey
     */
    public ForeignKey getForeignKey() {
        if (foreignKey == null) {
            foreignKey = new ForeignKey();
        }
        return foreignKey;
    }

    /**
     * @param foreignKey the foreignKey to set
     */
    public void setForeignKey(ForeignKey foreignKey) {
        this.foreignKey = foreignKey;
    }

    /**
     * @return the implicitName
     */
    public String getImplicitName() {
        return implicitName;
    }

    /**
     * @param implicitName the implicitName to set
     */
    public void setImplicitName(String implicitName) {
        this.implicitName = implicitName;
    }

    public PrimaryKeyJoinColumnMetadata getAccessor() {
        PrimaryKeyJoinColumnMetadata accessor = new PrimaryKeyJoinColumnMetadata();
        accessor.setColumnDefinition(columnDefinition);
        accessor.setName(name);
        accessor.setReferencedColumnName(getReferencedColumnName());
        if (ForeignKeyValidator.isNotEmpty(foreignKey)) {
            accessor.setForeignKey(foreignKey.getAccessor());
        }
        return accessor;
    }
}
