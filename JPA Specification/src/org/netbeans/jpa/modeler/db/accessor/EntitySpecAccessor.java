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
package org.netbeans.jpa.modeler.db.accessor;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
import org.eclipse.persistence.exceptions.ValidationException;
import org.eclipse.persistence.internal.helper.DatabaseTable;
import org.eclipse.persistence.internal.jpa.metadata.accessors.classes.EntityAccessor;
import org.eclipse.persistence.internal.jpa.metadata.columns.PrimaryKeyJoinColumnMetadata;
import org.eclipse.persistence.internal.jpa.metadata.tables.SecondaryTableMetadata;
import org.netbeans.jpa.modeler.spec.Entity;
import org.netbeans.jpa.modeler.spec.MappedSuperclass;
import org.netbeans.jpa.modeler.spec.PrimaryKeyJoinColumn;
import org.netbeans.jpa.modeler.spec.SecondaryTable;
import org.netbeans.jpa.modeler.spec.extend.JavaClass;
import org.netbeans.jpa.modeler.spec.validator.override.AssociationValidator;
import org.netbeans.jpa.modeler.spec.validator.override.AttributeValidator;
import org.netbeans.jpa.modeler.spec.validator.column.PrimaryKeyJoinColumnValidator;
import org.netbeans.db.modeler.exception.DBValidationException;
import org.netbeans.jpa.modeler.spec.Inheritance;

/**
 *
 * @author Gaurav Gupta
 */
public class EntitySpecAccessor extends EntityAccessor {

    private final Entity entity;

    private EntitySpecAccessor(Entity entity) {
        this.entity = entity;
    }

    public static EntitySpecAccessor getInstance(Entity entity) {
        EntitySpecAccessor accessor = new EntitySpecAccessor(entity);
        accessor.setName(entity.getName());
        accessor.setClassName(entity.getClazz());
        accessor.setAccess("VIRTUAL");
        accessor.setAttributes(entity.getAttributes().getAccessor());
        if (entity.getTable() != null) {
            accessor.setTable(entity.getTable().getAccessor());
        }
        if(!entity.getSecondaryTable().isEmpty()){
            List<SecondaryTableMetadata> secondaryTableMetadata = new ArrayList<>();
            for(SecondaryTable secondaryTable : entity.getSecondaryTable()){
               secondaryTableMetadata.add(secondaryTable.getAccessor());
            }
            accessor.setSecondaryTables(secondaryTableMetadata);
        }
        processSuperClass(entity, accessor);
        if (entity.getInheritance() != null) {
            accessor.setInheritance(entity.getInheritance().getAccessor());
        } else if(!entity.getSubclassList().isEmpty()) { //if Inheritance null and ROOT/BRANCH then set default
            accessor.setInheritance(Inheritance.getDefaultAccessor());
        }
        
        if (entity.getIdClass() != null) {
            accessor.setIdClassName(entity.getIdClass().getClazz());
        }
        
        if (entity.getDiscriminatorColumn() != null) {
            accessor.setDiscriminatorColumn(entity.getDiscriminatorColumn().getAccessor());
        }
        accessor.setDiscriminatorValue(entity.getDiscriminatorValue());

        AttributeValidator.filter(entity);
        accessor.setAttributeOverrides(entity.getAttributeOverride().stream().map(AttributeOverrideSpecMetadata::getInstance).collect(toList()));
        AssociationValidator.filter(entity);
        accessor.setAssociationOverrides(entity.getAssociationOverride().stream().map(AssociationOverrideSpecMetadata::getInstance).collect(toList()));

        PrimaryKeyJoinColumnValidator.filter(entity.getPrimaryKeyJoinColumn());
        accessor.setPrimaryKeyJoinColumns(entity.getPrimaryKeyJoinColumn().stream().map(PrimaryKeyJoinColumn::getAccessor).collect(toList()));

        return accessor;

    }

    private static void processSuperClass(JavaClass _class, EntityAccessor accessor) {
        if (_class.getSuperclass() != null) {
            if (_class.getSuperclass() instanceof MappedSuperclass) {
                MappedSuperclass superclass = (MappedSuperclass) _class.getSuperclass();
                superclass.getAttributes().updateAccessor(accessor.getAttributes(), true);
                processSuperClass(superclass, accessor);
            } else {
                accessor.setParentClassName(_class.getSuperclass().getClazz());
            }
        }
    }

    /**
     * @return the entity
     */
    public Entity getEntity() {
        return entity;
    }
    
    @Override
     protected void addMultipleTableKeyFields(List<PrimaryKeyJoinColumnMetadata> primaryKeyJoinColumns, DatabaseTable targetTable, String PK_CTX, String FK_CTX) {
        try {
            super.addMultipleTableKeyFields(primaryKeyJoinColumns, targetTable, PK_CTX, FK_CTX);
        } catch (ValidationException ex) {// to handle @PrimaryKeyJoinColumn exception
            DBValidationException exception = new DBValidationException(ex);
            exception.setJavaClass(entity);
            throw exception;
        }
    }

}
