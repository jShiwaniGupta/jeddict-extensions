//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.eclipse.persistence.internal.jpa.metadata.tables.TableMetadata;
import org.netbeans.jpa.modeler.spec.validator.table.TableValidator;
import org.netbeans.jpa.source.JavaSourceParserUtil;

/**
 *
 *
 * @Target({TYPE}) @Retention(RUNTIME) public @interface Table { String name()
 * default ""; String catalog() default ""; String schema() default "";
 * UniqueConstraint[] uniqueConstraints() default {}; Index[] indexes() default
 * {}; }
 *
 *
 *
 * <p>
 * Java class for table complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="table">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unique-constraint" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}unique-constraint" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="index" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}index" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="catalog" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="schema" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "table", propOrder = {
    "uniqueConstraint",
    "index"
})
@XmlJavaTypeAdapter(value = TableValidator.class)

public class Table {

    @XmlElement(name = "unique-constraint")
    protected Set<UniqueConstraint> uniqueConstraint;
    protected List<Index> index;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "catalog")
    protected String catalog;
    @XmlAttribute(name = "schema")
    protected String schema;

    public static Table load(Element element) {
        AnnotationMirror annotationMirror = JavaSourceParserUtil.findAnnotation(element, "javax.persistence.Table");
        Table table = null;
        if (annotationMirror != null) {
            table = new Table();
            List uniqueConstraintsAnnot = (List) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "uniqueConstraints");
            if (uniqueConstraintsAnnot != null) {
                for (Object uniqueConstraintsObj : uniqueConstraintsAnnot) {
                    table.getUniqueConstraint().add(UniqueConstraint.load(element, (AnnotationMirror) uniqueConstraintsObj));
                }
            }
            
            List indexesAnnot = (List) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "indexes");
            if (indexesAnnot != null) {
                for (Object indexObj : indexesAnnot) {
                    table.getIndex().add(Index.load(element, (AnnotationMirror) indexObj));
                }
            }

            table.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
            table.catalog = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "catalog");
            table.schema = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "schema");
        }
        return table;

    }

    /**
     * Gets the value of the uniqueConstraint property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the uniqueConstraint property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUniqueConstraint().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UniqueConstraint }
     *
     *
     */
    public Set<UniqueConstraint> getUniqueConstraint() {
        if (uniqueConstraint == null) {
            uniqueConstraint = new LinkedHashSet<>();
        }
        return this.uniqueConstraint;
    }

    /**
     * Gets the value of the index property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the index property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIndex().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Index }
     *
     *
     */
    public List<Index> getIndex() {
        if (index == null) {
            index = new ArrayList<Index>();
        }
        return this.index;
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
        this.name = value;
    }

    /**
     * Gets the value of the catalog property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * Sets the value of the catalog property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setCatalog(String value) {
        this.catalog = value;
    }

    /**
     * Gets the value of the schema property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSchema() {
        return schema;
    }

    /**
     * Sets the value of the schema property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setSchema(String value) {
        this.schema = value;
    }

    public TableMetadata getAccessor() {
        TableMetadata accessor = new TableMetadata();
        accessor.setName(name);
        accessor.setCatalog(catalog);
        accessor.setSchema(schema);

        return accessor;
    }

    public void clear(){
        this.name = null;
        this.schema = null;
        this.catalog = null;
        this.uniqueConstraint = null;
        this.index = null;
    }
}
