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
package org.netbeans.jpa.modeler.spec.extend;

public enum AttributeSnippetLocationType implements SnippetLocation {

    BEFORE_FIELD("Before Field"), AFTER_FIELD("After Field"), 
    BEFORE_METHOD("Before Method"), AFTER_METHOD("After Method"),
    PRE_GETTER("Pre Getter"), GETTER("Getter"),
    PRE_SETTER("Pre Setter"), SETTER("Setter"), POST_SETTER("Post Setter"),
    IMPORT("Import");
    
    private final String title;

    
    private AttributeSnippetLocationType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    
}