/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package griffon.plugins.scaffolding;

import griffon.plugins.validation.constraints.ConstrainedProperty;
import groovy.lang.MissingPropertyException;
import javafx.beans.property.Property;
import javafx.scene.Node;

import java.util.Map;

/**
 * @author Andres Almiray
 */
public final class JavafxScaffoldingContext extends ScaffoldingContext {
    public JavafxScaffoldingContext() {

    }

    public void bind(Property sourceProperty, Property targetProperty, ConstrainedProperty constrainedProperty, Class<?> sourcePropertyType) {
        addDisposable(PropertyBinding.create(sourceProperty, targetProperty, constrainedProperty, sourcePropertyType));
    }

    @Override
    public Map<String, Object> widgetAttributes(String widget, ConstrainedProperty constrainedProperty) {
        Map<String, Object> attributes = super.widgetAttributes(widget, constrainedProperty);
        attributes.put("disabled", !constrainedProperty.isEnabled());
        return attributes;
    }

    public void applyCssOnError(String... cssclasses) {
        if (cssclasses == null || cssclasses.length == 0) return;
        for (Map.Entry<String, ConstrainedProperty> property : getValidateable().constrainedProperties().entrySet()) {
            String propertyName = property.getKey();
            ConstrainedProperty constrainedProperty = property.getValue();
            if (!constrainedProperty.isDisplay()) continue;
            boolean hasErrors = getValidateable().getErrors().getFieldErrorCount(propertyName) != 0;
            try {
                Node labeler = (Node) getBinding().getVariable(propertyName + "_labeler");
                if (labeler != null) {
                    if (hasErrors) {
                        for (String cssclass : cssclasses) {
                            labeler.getStyleClass().add(cssclass);
                        }
                    } else {
                        for (String cssclass : cssclasses) {
                            labeler.getStyleClass().remove(cssclass);
                        }
                    }
                }
                Node editor = (Node) getBinding().getVariable(propertyName);
                if (editor != null) {
                    if (hasErrors) {
                        for (String cssclass : cssclasses) {
                            editor.getStyleClass().add(cssclass);
                        }
                    } else {
                        for (String cssclass : cssclasses) {
                            editor.getStyleClass().remove(cssclass);
                        }
                    }
                }
            } catch (MissingPropertyException mpe) {
                // ignore
            }
        }
    }
}