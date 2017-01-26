//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package org.netbeans.jpa.modeler.spec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang.StringUtils;
import org.netbeans.jpa.modeler.spec.extend.AssociationOverrideHandler;
import org.netbeans.jpa.modeler.spec.extend.Attribute;
import static org.netbeans.jcode.core.util.AttributeType.STRING;
import org.netbeans.jcode.core.util.JavaSourceHelper;
import static org.netbeans.jcode.jpa.JPAConstants.MAP_KEY_COLUMN_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.MAP_KEY_ENUMERATED_FQN;
import static org.netbeans.jcode.jpa.JPAConstants.MAP_KEY_TEMPORAL_FQN;
import org.netbeans.jpa.modeler.spec.extend.CollectionTypeHandler;
import org.netbeans.jpa.modeler.spec.extend.ColumnHandler;
import org.netbeans.jpa.modeler.spec.extend.CompositionAttribute;
import org.netbeans.jpa.modeler.spec.extend.EnumTypeHandler;
import org.netbeans.jpa.modeler.spec.extend.FetchTypeHandler;
import org.netbeans.jpa.modeler.spec.extend.MapKeyHandler;
import org.netbeans.jpa.modeler.spec.extend.MapKeyType;
import org.netbeans.jpa.modeler.spec.extend.SortableAttribute;
import org.netbeans.jpa.modeler.spec.extend.TemporalTypeHandler;
import org.netbeans.jpa.modeler.spec.jaxb.JaxbVariableType;
import org.netbeans.jpa.modeler.spec.validator.override.AssociationValidator;
import org.netbeans.jpa.modeler.spec.validator.override.AttributeValidator;
import org.netbeans.jpa.source.JavaSourceParserUtil;
import static org.netbeans.jpa.source.JavaSourceParserUtil.isEmbeddableClass;
import static org.netbeans.jpa.source.JavaSourceParserUtil.isEntityClass;
import static org.netbeans.jpa.source.JavaSourceParserUtil.loadEmbeddableClass;
import static org.netbeans.jpa.source.JavaSourceParserUtil.loadEntityClass;

/**
 *
 *
 * @Target({METHOD, FIELD}) @Retention(RUNTIME) public @interface
 * ElementCollection { Class targetClass() default void.class; FetchType fetch()
 * default LAZY; }
 *
 *
 *
 * <p>
 * Java class for element-collection complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="element-collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="order-by" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}order-by" minOccurs="0"/>
 *           &lt;element name="order-column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}order-column" minOccurs="0"/>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;element name="map-key" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}map-key" minOccurs="0"/>
 *           &lt;sequence>
 *             &lt;element name="map-key-class" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}map-key-class" minOccurs="0"/>
 *             &lt;choice>
 *               &lt;element name="map-key-temporal" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}temporal" minOccurs="0"/>
 *               &lt;element name="map-key-enumerated" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}enumerated" minOccurs="0"/>
 *               &lt;sequence>
 *                 &lt;element name="map-key-attribute-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}attribute-override" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;element name="map-key-convert" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}convert" maxOccurs="unbounded" minOccurs="0"/>
 *               &lt;/sequence>
 *             &lt;/choice>
 *             &lt;choice>
 *               &lt;element name="map-key-column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}map-key-column" minOccurs="0"/>
 *               &lt;sequence>
 *                 &lt;element name="map-key-join-column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}map-key-join-column" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;element name="map-key-foreign-key" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}foreign-key" minOccurs="0"/>
 *               &lt;/sequence>
 *             &lt;/choice>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;choice>
 *           &lt;sequence>
 *             &lt;element name="column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}column" minOccurs="0"/>
 *             &lt;choice>
 *               &lt;element name="temporal" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}temporal" minOccurs="0"/>
 *               &lt;element name="enumerated" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}enumerated" minOccurs="0"/>
 *               &lt;element name="lob" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}lob" minOccurs="0"/>
 *             &lt;/choice>
 *           &lt;/sequence>
 *           &lt;sequence>
 *             &lt;element name="attribute-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}attribute-override" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="association-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}association-override" maxOccurs="unbounded" minOccurs="0"/>
 *             &lt;element name="convert" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}convert" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;/sequence>
 *         &lt;/choice>
 *         &lt;element name="collection-table" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}collection-table" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="target-class" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="fetch" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}fetch-type" />
 *       &lt;attribute name="access" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}access-type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "element-collection", propOrder = {
    "orderBy",
    "orderColumn",
    "mapKeyColumn",
    "mapKeyTemporal",
    "mapKeyEnumerated",
    "mapKeyAttributeOverride",
    "mapKeyConvert",
    "mapKeyJoinColumn",
    "mapKeyForeignKey",
    "column",
    "temporal",
    "enumerated",
    "lob",
    "attributeOverride",
    "associationOverride",
    "convert",
    "collectionTable"
})
/**
 * For Basic ElementCollection -> TargetClass<String>
 * For Embeddable ElementCollection -> ConnectedClass<Embeddable>
 */
public class ElementCollection extends CompositionAttribute<Embeddable> implements SortableAttribute, FetchTypeHandler, ColumnHandler, AssociationOverrideHandler, CollectionTypeHandler, MapKeyHandler, TemporalTypeHandler, EnumTypeHandler { //CompositionAttribute/BaseAttributes

    @XmlElement(name = "ob")
    protected OrderBy orderBy;
    @XmlElement(name = "oc")
    protected OrderColumn orderColumn;
    protected Column column;
    protected TemporalType temporal;
    protected EnumType enumerated;
    protected Lob lob;
    @XmlElement(name = "attribute-override")
    protected Set<AttributeOverride> attributeOverride;
    @XmlElement(name = "association-override")
    protected Set<AssociationOverride> associationOverride;
    protected List<Convert> convert;//REVENG PENDING
    @XmlElement(name = "collection-table")
    protected CollectionTable collectionTable;

    @XmlAttribute(name = "target-class")
    protected String targetClass;
    @XmlAttribute(name = "fetch")
    protected FetchType fetch;
    @XmlAttribute(name = "access")
    protected AccessType access;

    @XmlAttribute(name = "collection-type")
    private String collectionType;

    @XmlElement(name = "map-key-convert")
    protected List<Convert> mapKeyConvert;//REVENG PENDING

    @XmlAttribute(name = "mkt")
    private MapKeyType mapKeyType;

    //Existing MapKeyType
    @XmlAttribute(name = "mkat-ref")//attribute-ref
    @XmlIDREF
    private Attribute mapKeyAttribute;
    @XmlTransient//@XmlElement(name = "map-key")//Not required
    protected MapKey mapKey;//only required in rev-eng and stored to mapKeyAttribute

    //New MapKeyType - Basic
    @XmlAttribute(name = "mkat")
    private String mapKeyAttributeType; //e.g String, int, Enum, Date    applicable for basic,enumerated,temporal
    @XmlElement(name = "mkc")
    protected Column mapKeyColumn;
    @XmlElement(name = "mktemp")
    protected TemporalType mapKeyTemporal;
    @XmlElement(name = "mkenum")
    protected EnumType mapKeyEnumerated;
    //@XmlElement(name = "map-key-class")//Not required
    //protected MapKeyClass mapKeyClass; //rev-eng done and stored to mapKeyAttributeType

    //New MapKeyType - Entity
    @XmlAttribute(name = "mken-ref")//entity-ref
    @XmlIDREF
    private Entity mapKeyEntity;
    @XmlElement(name = "mkjc")
    protected List<JoinColumn> mapKeyJoinColumn;
    @XmlElement(name = "mkfk")
    protected ForeignKey mapKeyForeignKey;

    //New MapKeyType - Embeddable
    @XmlAttribute(name = "mkem-ref")
    @XmlIDREF
    private Embeddable mapKeyEmbeddable;
    @XmlElement(name = "mkao")
    protected Set<AttributeOverride> mapKeyAttributeOverride;

    public static ElementCollection load(EntityMappings entityMappings, Element element, VariableElement variableElement, ExecutableElement getterElement) {
        AnnotationMirror annotationMirror = JavaSourceParserUtil.getAnnotation(element, "javax.persistence.ElementCollection");
        ElementCollection elementCollection = new ElementCollection();
        elementCollection.loadAttribute(element, variableElement, getterElement);
        elementCollection.column = new Column().load(element, null);
        elementCollection.temporal = TemporalType.load(element, null);
        elementCollection.enumerated = EnumType.load(element, null);
        elementCollection.lob = Lob.load(element, variableElement);

        elementCollection.getAttributeOverride().addAll(AttributeOverride.load(element));
        elementCollection.getAssociationOverride().addAll(AssociationOverride.load(element));

        elementCollection.collectionTable = CollectionTable.load(element, variableElement);

        elementCollection.orderBy = OrderBy.load(element, variableElement);
        elementCollection.orderColumn = OrderColumn.load(element, variableElement);
        elementCollection.fetch = FetchType.load(element, annotationMirror);
        elementCollection.access = AccessType.load(element);

        elementCollection.collectionType = ((DeclaredType) variableElement.asType()).asElement().toString();
        Class collectionTypeClass = null;
        try {
            collectionTypeClass = Class.forName(elementCollection.collectionType);
        } catch (ClassNotFoundException ex) {
        }
        boolean mapKeyExist = collectionTypeClass != null && Map.class.isAssignableFrom(collectionTypeClass);

        DeclaredType declaredType = (DeclaredType) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "targetClass");
        if (declaredType == null) {
            declaredType = (DeclaredType) ((DeclaredType) variableElement.asType()).getTypeArguments().get(mapKeyExist ? 1 : 0);
        }
        if (declaredType != null) {
            if (isEmbeddableClass(declaredType.asElement())) {
                Embeddable embeddableClassSpec = loadEmbeddableClass(entityMappings, element, variableElement, declaredType);
                elementCollection.setConnectedClass(embeddableClassSpec);
            } else {
                elementCollection.setTargetClass(declaredType.toString());
            }
        } else {
            elementCollection.setTargetClass(STRING);
        }
        JavaSourceParserUtil.getBeanValidation(elementCollection, element);

        if (mapKeyExist) {
            elementCollection.mapKey = new MapKey().load(element, null);
            elementCollection.mapKeyType = elementCollection.mapKey != null ? MapKeyType.EXT : MapKeyType.NEW;

            DeclaredType keyDeclaredType = MapKeyClass.getDeclaredType(element);
            if (keyDeclaredType == null) {
                keyDeclaredType = (DeclaredType) ((DeclaredType) variableElement.asType()).getTypeArguments().get(0);
            }
            if (isEmbeddableClass(keyDeclaredType.asElement())) {
                loadEmbeddableClass(entityMappings, element, variableElement, keyDeclaredType);
                elementCollection.mapKeyAttributeType = JavaSourceHelper.getSimpleClassName(keyDeclaredType.toString());
            } else if (isEntityClass(keyDeclaredType.asElement())) {
                loadEntityClass(entityMappings, element, variableElement, keyDeclaredType);
                elementCollection.mapKeyAttributeType = JavaSourceHelper.getSimpleClassName(keyDeclaredType.toString());
            } else {
                elementCollection.mapKeyAttributeType = keyDeclaredType.toString();
            }

            elementCollection.mapKeyColumn = new Column().load(element, JavaSourceParserUtil.findAnnotation(element, MAP_KEY_COLUMN_FQN));
            elementCollection.mapKeyTemporal = TemporalType.load(element, JavaSourceParserUtil.findAnnotation(element, MAP_KEY_TEMPORAL_FQN));
            elementCollection.mapKeyEnumerated = EnumType.load(element, JavaSourceParserUtil.findAnnotation(element, MAP_KEY_ENUMERATED_FQN));

            AnnotationMirror joinColumnsAnnotationMirror = JavaSourceParserUtil.findAnnotation(element, "javax.persistence.MapKeyJoinColumns");
            if (joinColumnsAnnotationMirror != null) {
                List joinColumnsAnnot = (List) JavaSourceParserUtil.findAnnotationValue(joinColumnsAnnotationMirror, "value");
                if (joinColumnsAnnot != null) {
                    for (Object joinColumnObj : joinColumnsAnnot) {
                        elementCollection.getMapKeyJoinColumn().add(new JoinColumn().load(element, (AnnotationMirror) joinColumnObj));
                    }
                }
            } else {
                AnnotationMirror joinColumnAnnotationMirror = JavaSourceParserUtil.findAnnotation(element, "javax.persistence.MapKeyJoinColumn");
                if (joinColumnAnnotationMirror != null) {
                    elementCollection.getMapKeyJoinColumn().add(new JoinColumn().load(element, joinColumnAnnotationMirror));
                }
            }

            elementCollection.mapKeyForeignKey = ForeignKey.load(element, null);
            elementCollection.getMapKeyAttributeOverride().addAll(AttributeOverride.load(element));
            // TODO if both side are Embeddable then how to diffrentiat MapKeyAttributeOverride and AttributeOverride 
            // with single @AttributeOverride means there is no @MapKeyAttributeOverride  ?
            // currently AttributeValidator will remove the invalid
        }
        return elementCollection;
    }

    void beforeMarshal(Marshaller marshaller) {
        AttributeValidator.filter(this);
        AssociationValidator.filter(this);
    }

    /**
     * Gets the value of the orderBy property.
     *
     * @return possible object is {@link String }
     *
     */
    public OrderBy getOrderBy() {
        return orderBy;
    }

    /**
     * Sets the value of the orderBy property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setOrderBy(OrderBy value) {
        this.orderBy = value;
    }

    /**
     * Gets the value of the orderColumn property.
     *
     * @return possible object is {@link OrderColumn }
     *
     */
    public OrderColumn getOrderColumn() {
        return orderColumn;
    }

    /**
     * Sets the value of the orderColumn property.
     *
     * @param value allowed object is {@link OrderColumn }
     *
     */
    public void setOrderColumn(OrderColumn value) {
        this.orderColumn = value;
    }

    /**
     * Gets the value of the mapKey property.
     *
     * @return possible object is {@link MapKey }
     *
     */
    public MapKey getMapKey() {
        return mapKey;
    }

    /**
     * Sets the value of the mapKey property.
     *
     * @param value allowed object is {@link MapKey }
     *
     */
    public void setMapKey(MapKey value) {
        this.mapKey = value;
    }

//    /**
//     * Gets the value of the mapKeyClass property.
//     *
//     * @return possible object is {@link MapKeyClass }
//     *
//     */
//    public MapKeyClass getMapKeyClass() {
//        return mapKeyClass;
//    }
//
//    /**
//     * Sets the value of the mapKeyClass property.
//     *
//     * @param value allowed object is {@link MapKeyClass }
//     *
//     */
//    public void setMapKeyClass(MapKeyClass value) {
//        this.mapKeyClass = value;
//    }
    /**
     * Gets the value of the mapKeyTemporal property.
     *
     * @return possible object is {@link TemporalType }
     *
     */
    public TemporalType getMapKeyTemporal() {
        return mapKeyTemporal;
    }

    /**
     * Sets the value of the mapKeyTemporal property.
     *
     * @param value allowed object is {@link TemporalType }
     *
     */
    public void setMapKeyTemporal(TemporalType value) {
        this.mapKeyTemporal = value;
    }

    /**
     * Gets the value of the mapKeyEnumerated property.
     *
     * @return possible object is {@link EnumType }
     *
     */
    public EnumType getMapKeyEnumerated() {
        return mapKeyEnumerated;
    }

    /**
     * Sets the value of the mapKeyEnumerated property.
     *
     * @param value allowed object is {@link EnumType }
     *
     */
    public void setMapKeyEnumerated(EnumType value) {
        this.mapKeyEnumerated = value;
    }

    /**
     * Gets the value of the mapKeyAttributeOverride property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the mapKeyAttributeOverride property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMapKeyAttributeOverride().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeOverride }
     *
     *
     * @return
     */
    public Set<AttributeOverride> getMapKeyAttributeOverride() {
        if (mapKeyAttributeOverride == null) {
            mapKeyAttributeOverride = new TreeSet<>();
        }
        return this.mapKeyAttributeOverride;
    }

    /**
     * Gets the value of the mapKeyConvert property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the mapKeyConvert property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMapKeyConvert().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Convert }
     *
     *
     */
    public List<Convert> getMapKeyConvert() {
        if (mapKeyConvert == null) {
            mapKeyConvert = new ArrayList<Convert>();
        }
        return this.mapKeyConvert;
    }

    /**
     * Gets the value of the mapKeyColumn property.
     *
     * @return possible object is {@link MapKeyColumn }
     *
     */
    public Column getMapKeyColumn() {
        if (mapKeyColumn == null) {
            mapKeyColumn = new Column();
        }
        return mapKeyColumn;
    }

    /**
     * Sets the value of the =property.
     *
     * @param value allowed object is {@link MapKeyColumn }
     *
     */
    public void setMapKeyColumn(Column value) {
        this.mapKeyColumn = value;
    }

    /**
     * Gets the value of the mapKeyJoinColumn property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the mapKeyJoinColumn property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMapKeyJoinColumn().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MapKeyJoinColumn }
     *
     *
     */
    public List<JoinColumn> getMapKeyJoinColumn() {
        if (mapKeyJoinColumn == null) {
            mapKeyJoinColumn = new ArrayList<>();
        }
        return this.mapKeyJoinColumn;
    }

    /**
     * Gets the value of the mapKeyForeignKey property.
     *
     * @return possible object is {@link ForeignKey }
     *
     */
    public ForeignKey getMapKeyForeignKey() {
        return mapKeyForeignKey;
    }

    /**
     * Sets the value of the mapKeyForeignKey property.
     *
     * @param value allowed object is {@link ForeignKey }
     *
     */
    public void setMapKeyForeignKey(ForeignKey value) {
        this.mapKeyForeignKey = value;
    }

    /**
     * Gets the value of the column property.
     *
     * @return possible object is {@link Column }
     *
     */
    public Column getColumn() {
        if (column == null) {
            column = new Column();
        }
        return column;
    }

    /**
     * Sets the value of the column property.
     *
     * @param value allowed object is {@link Column }
     *
     */
    public void setColumn(Column value) {
        this.column = value;
    }

    /**
     * Gets the value of the temporal property.
     *
     * @return possible object is {@link TemporalType }
     *
     */
    public TemporalType getTemporal() {
        return temporal;
    }

    /**
     * Sets the value of the temporal property.
     *
     * @param value allowed object is {@link TemporalType }
     *
     */
    public void setTemporal(TemporalType value) {
        this.temporal = value;
    }

    /**
     * Gets the value of the enumerated property.
     *
     * @return possible object is {@link EnumType }
     *
     */
    public EnumType getEnumerated() {
        return enumerated;
    }

    /**
     * Sets the value of the enumerated property.
     *
     * @param value allowed object is {@link EnumType }
     *
     */
    public void setEnumerated(EnumType value) {
        this.enumerated = value;
    }

    /**
     * Gets the value of the lob property.
     *
     * @return possible object is {@link Lob }
     *
     */
    public Lob getLob() {
        return lob;
    }

    /**
     * Sets the value of the lob property.
     *
     * @param value allowed object is {@link Lob }
     *
     */
    public void setLob(Lob value) {
        this.lob = value;
    }

    /**
     * Gets the value of the attributeOverride property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the attributeOverride property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOverride().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeOverride }
     *
     *
     */
    @Override
    public Set<AttributeOverride> getAttributeOverride() {
        if (attributeOverride == null) {
            attributeOverride = new TreeSet<AttributeOverride>();
        }
        return this.attributeOverride;
    }

    public AttributeOverride findAttributeOverride(String name) {
        for (AttributeOverride attributeOverride : getAttributeOverride()) {
            if (StringUtils.equals(name, attributeOverride.getName())) {
                return attributeOverride;
            }
        }
        return null;
    }

    public boolean addAttributeOverride(AttributeOverride attributeOverride) {
        return getAttributeOverride().add(attributeOverride);
    }

    public boolean removeAttributeOverride(AttributeOverride attributeOverride) {
        return getAttributeOverride().remove(attributeOverride);
    }

    /**
     * Gets the value of the associationOverride property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the associationOverride property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociationOverride().add(newItem);
     * {@link AssociationOverride }
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     *
     *
     */
    @Override
    public Set<AssociationOverride> getAssociationOverride() {
        if (associationOverride == null) {
            associationOverride = new TreeSet<>();
        }
        return this.associationOverride;
    }

    public AssociationOverride findAssociationOverride(String name) {
        for (AssociationOverride associationOverride : getAssociationOverride()) {
            if (StringUtils.equals(name, associationOverride.getName())) {
                return associationOverride;
            }
        }
        return null;
    }

    public boolean addAssociationOverride(AssociationOverride associationOverride) {
        return getAssociationOverride().add(associationOverride);
    }

    public boolean removeAssociationOverride(AssociationOverride associationOverride) {
        return getAssociationOverride().remove(associationOverride);
    }

    /**
     * Gets the value of the convert property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the convert property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConvert().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Convert }
     *
     *
     */
    public List<Convert> getConvert() {
        if (convert == null) {
            convert = new ArrayList<Convert>();
        }
        return this.convert;
    }

    /**
     * Gets the value of the collectionTable property.
     *
     * @return possible object is {@link CollectionTable }
     *
     */
    public CollectionTable getCollectionTable() {
        if (collectionTable == null) {
            collectionTable = new CollectionTable();
        }
        return collectionTable;
    }

    /**
     * Sets the value of the collectionTable property.
     *
     * @param value allowed object is {@link CollectionTable }
     *
     */
    public void setCollectionTable(CollectionTable value) {
        this.collectionTable = value;
    }

    /**
     * Gets the value of the targetClass property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTargetClass() {
        return targetClass;
    }

    /**
     * Sets the value of the targetClass property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setTargetClass(String value) {
        this.targetClass = value;
    }

    /**
     * Gets the value of the fetch property.
     *
     * @return possible object is {@link FetchType }
     *
     */
    @Override
    public FetchType getFetch() {
        return fetch;
    }

    /**
     * Sets the value of the fetch property.
     *
     * @param value allowed object is {@link FetchType }
     *
     */
    @Override
    public void setFetch(FetchType value) {
        this.fetch = value;
    }

    /**
     * Gets the value of the access property.
     *
     * @return possible object is {@link AccessType }
     *
     */
    @Override
    public AccessType getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     *
     * @param value allowed object is {@link AccessType }
     *
     */
    @Override
    public void setAccess(AccessType value) {
        this.access = value;
    }

    /**
     * @return the collectionType
     */
    public String getCollectionType() {
        if (collectionType == null) {
            collectionType = "java.util.List";
        }
        return collectionType;
    }

    /**
     * @param collectionType the collectionType to set
     */
    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    @Override
    public AttributeOverride getAttributeOverride(String attributePath) {
        Set<AttributeOverride> attributeOverrides = getAttributeOverride();
        for (AttributeOverride attributeOverride_TMP : attributeOverrides) {
            if (attributeOverride_TMP.getName().equals(attributePath)) {
                return attributeOverride_TMP;
            }
        }
        AttributeOverride attributeOverride_TMP = new AttributeOverride();
        attributeOverride_TMP.setName(attributePath);
        attributeOverrides.add(attributeOverride_TMP);
        return attributeOverride_TMP;
    }

    @Override
    public AssociationOverride getAssociationOverride(String attributePath) {
        Set<AssociationOverride> associationOverrides = getAssociationOverride();
        for (AssociationOverride associationOverride_TMP : associationOverrides) {
            if (associationOverride_TMP.getName().equals(attributePath)) {
                return associationOverride_TMP;
            }
        }
        AssociationOverride attributeOverride_TMP = new AssociationOverride();
        attributeOverride_TMP.setName(attributePath);
        associationOverrides.add(attributeOverride_TMP);
        return attributeOverride_TMP;
    }

    @Override
    public List<JaxbVariableType> getJaxbVariableList() {
        if (this.getConnectedClass() != null) { //Embedded //Complex
            return super.getJaxbVariableList();
        } else { //Basic //Simple
            return Arrays.asList(JaxbVariableType.values());
        }
    }

    @Override
    public String getAttributeType() {
        return this.getConnectedClass() != null ? super.getAttributeType() : targetClass;
    }

    @Override
    public boolean isTextAttributeType() {
        if (isMapType(getCollectionType())) {
            return isTextAttributeType(getMapKeyAttributeType());
        } else {
            return isTextAttributeType(getAttributeType());
        }
    }

    @Override
    public boolean isPrecisionAttributeType() {
        if (isMapType(getCollectionType())) {
            return isPrecisionAttributeType(getMapKeyAttributeType());
        } else {
            return isPrecisionAttributeType(getAttributeType());
        }
    }

    @Override
    public boolean isScaleAttributeType() {
        if (isMapType(getCollectionType())) {
            return isScaleAttributeType(getMapKeyAttributeType());
        } else {
            return isScaleAttributeType(getAttributeType());
        }
    }

    public String getDefaultColumnName() {
        return this.getName().toUpperCase();
    }

//    public String getDefaultMapKeyColumnName() {
//        if(getValidatedMapKeyType()==MapKeyType.NEW){
//            if(mapKeyAttributeType != null){
//                
//            } else if(mapKeyEmbeddable != null){
//                
//            } else if(mapKeyEntity != null){
//                
//            }
//        }
//        return this.getName().toUpperCase();
//    }
    public String getColumnName() {
        if (this.getColumn() != null && StringUtils.isNotBlank(this.getColumn().getName())) {
            return getColumn().getName();
        } else {
            return getDefaultColumnName();
        }
    }

    @Override
    public String getDataTypeLabel() {
        if (getValidatedMapKeyType() == null) {
            return String.format("%s<%s>", getCollectionType(), getAttributeType());
        } else {
            return String.format("%s<%s, %s>", getCollectionType(), getMapKeyDataTypeLabel(), getAttributeType());
        }
    }

    /**
     * @return the mapKeyAttribute
     */
    public Attribute getMapKeyAttribute() {
        return mapKeyAttribute;
    }

    /**
     * @param mapKeyAttribute the mapKeyAttribute to set
     */
    public void setMapKeyAttribute(Attribute mapKeyAttribute) {
        resetMapAttributeExceptExisting();
        this.mapKeyAttribute = mapKeyAttribute;
    }

    public MapKeyType getValidatedMapKeyType() {
        if (mapKeyAttribute != null) {
            return MapKeyType.EXT;
        } else if (mapKeyAttributeType != null || mapKeyEmbeddable != null || mapKeyEntity != null) {
            return MapKeyType.NEW;
        }
        return null;
    }

    /**
     * @return the mapKeyType
     */
    public MapKeyType getMapKeyType() {
        if (mapKeyType == null) {
            return MapKeyType.EXT;
        }
        return mapKeyType;
    }

    @Override
    public void setMapKeyType(MapKeyType mapKeyType) {
        this.mapKeyType = mapKeyType;
    }

    /**
     * @return the mapKeyAttributeType
     */
    public String getMapKeyAttributeType() {
        return mapKeyAttributeType;
    }

    /**
     * @param mapKeyAttributeType the mapKeyAttributeType to set
     */
    public void setMapKeyAttributeType(String mapKeyAttributeType) {
        this.mapKeyAttributeType = mapKeyAttributeType;
    }

    /**
     * @return the mapKeyEntity
     */
    public Entity getMapKeyEntity() {
        return mapKeyEntity;
    }

    /**
     * @param mapKeyEntity the mapKeyEntity to set
     */
    public void setMapKeyEntity(Entity mapKeyEntity) {
        resetMapAttributeExceptEntity();
        this.mapKeyEntity = mapKeyEntity;
    }

    /**
     * @return the mapKeyEmbeddable
     */
    public Embeddable getMapKeyEmbeddable() {
        return mapKeyEmbeddable;
    }

    /**
     * @param mapKeyEmbeddable the mapKeyEmbeddable to set
     */
    public void setMapKeyEmbeddable(Embeddable mapKeyEmbeddable) {
        resetMapAttributeExceptEmbeddable();
        this.mapKeyEmbeddable = mapKeyEmbeddable;
    }

    @Override
    public void resetMapAttribute() {
        this.mapKeyAttribute = null;

        this.mapKeyEntity = null;
        this.mapKeyEmbeddable = null;
        this.mapKeyEnumerated = null;
        this.mapKeyTemporal = null;
        this.mapKeyAttributeType = null;

        this.mapKeyColumn = null;
        this.mapKeyJoinColumn = null;
        this.mapKeyForeignKey = null;
        this.mapKeyAttributeOverride = null;
    }

    public void resetMapAttributeExceptExisting() {
        this.mapKeyEntity = null;
        this.mapKeyEmbeddable = null;
        this.mapKeyEnumerated = null;
        this.mapKeyTemporal = null;
        this.mapKeyAttributeType = null;

        this.mapKeyColumn = null;
        this.mapKeyJoinColumn = null;
        this.mapKeyForeignKey = null;
        this.mapKeyAttributeOverride = null;
    }

    public void resetMapAttributeExceptBasic() {
        this.mapKeyEnumerated = null;
        this.mapKeyTemporal = null;
        this.mapKeyAttributeType = null;
        this.mapKeyColumn = null;
    }

    public void resetMapAttributeExceptEmbeddable() {
        this.mapKeyAttribute = null;

        this.mapKeyEntity = null;
        this.mapKeyEnumerated = null;
        this.mapKeyTemporal = null;
        this.mapKeyAttributeType = null;

        this.mapKeyColumn = null;
        this.mapKeyJoinColumn = null;
        this.mapKeyForeignKey = null;
    }

    public void resetMapAttributeExceptEntity() {
        this.mapKeyAttribute = null;

        this.mapKeyEmbeddable = null;
        this.mapKeyEnumerated = null;
        this.mapKeyTemporal = null;
        this.mapKeyAttributeType = null;

        this.mapKeyColumn = null;
        this.mapKeyAttributeOverride = null;
    }

    @Override
    public String getMapKeyDataTypeLabel() {
        if (mapKeyType == MapKeyType.EXT && mapKeyAttribute != null) {
            return mapKeyAttribute.getDataTypeLabel();
        } else if (mapKeyEntity != null) {
            return mapKeyEntity.getClazz();
        } else if (mapKeyEmbeddable != null) {
            return mapKeyEmbeddable.getClazz();
        } else if (mapKeyAttributeType != null) {
            return mapKeyAttributeType;
        }
        return null;
    }

}
