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

import griffon.core.resources.editors.PropertyEditorResolver;
import griffon.plugins.validation.constraints.ConstrainedProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.beans.PropertyEditor;

/**
 * @author Andres Almiray
 */
public class PropertyBinding extends AbstractPropertyBinding {
    private Property sourceProperty;
    private Property targetProperty;
    private final Class<?> sourcePropertyType;

    public static PropertyBinding create(Property sourceProperty, Property targetProperty, ConstrainedProperty constrainedProperty, Class<?> sourcePropertyType) {
        return new PropertyBinding(sourceProperty, targetProperty, constrainedProperty, sourcePropertyType);
    }

    protected PropertyBinding(Property sourceProperty, Property targetProperty, ConstrainedProperty constrainedProperty, Class<?> sourcePropertyType) {
        super(constrainedProperty);
        this.sourceProperty = sourceProperty;
        this.targetProperty = targetProperty;
        this.sourcePropertyType = sourcePropertyType;
        bind();
    }

    public void dispose() {
        sourceProperty = null;
        targetProperty = null;
        super.dispose();
    }

    @Override
    protected void bindSource() {
        sourceProperty.addListener(new ChangeListener() {
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                updateTarget();
            }
        });
    }

    @Override
    protected void bindTarget() {
        targetProperty.addListener(new ChangeListener() {
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                updateSource();
            }
        });
    }

    @Override
    protected Object getTargetPropertyValue() {
        return targetProperty.getValue();
    }

    @Override
    protected void setTargetPropertyValue(Object value) {
        targetProperty.setValue(value);
    }

    @Override
    protected void setSourcePropertyValue(Object value) {
        sourceProperty.setValue(value);
    }

    @Override
    protected Object getSourcePropertyValue() {
        return sourceProperty.getValue();
    }

    @Override
    protected PropertyEditor resolveSourcePropertyEditor() {
        return PropertyEditorResolver.findEditor(sourcePropertyType);
    }
    // private final ObservableValue<?> source;
    // private final ObservableValue<?> target;
    // private final ConstrainedProperty constrainedProperty;

    /*
    private final ChangeListener sourceListener = new ChangeListener() {
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            synchronized (LOCK) {
                if (firing) return;
                firing = true;
                if (newValue == null) {
                    writeValue(newValue);
                } else {
                    writeValue(convertValue(newValue));
                }
                firing = false;
            }
        }
    };

    private final ChangeListener targetListener = new ChangeListener() {
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            synchronized (LOCK) {
                if (firing) return;
                firing = true;
                if (newValue == null) {
                    writeValue(newValue);
                } else {
                    writeValue(convertValue(newValue));
                }
                firing = false;
            }
        }
    };

    public PropertyBinding(ObservableValue<?> source, ObservableValue<?> target, ConstrainedProperty constrainedProperty) {
        this.source = source;
        this.target = target;
        this.constrainedProperty = constrainedProperty;

        source.addListener(sourceListener);
        target.addListener(targetListener);
    }
    */
}
