package griffon.plugins.scaffolding.templates

import javafx.collections.FXCollections

Map widgetAttributes = scaffoldingContext.widgetAttributes('choiceBox', constrainedProperty)
def valueHolder = scaffoldingContext.validateable."${propertyName}Property"()

Map modelAttributes = [:]
if (valueHolder.value != null) modelAttributes.value = valueHolder.value

if (constrainedProperty.inList) {
    widgetAttributes.items = FXCollections.observableList(constrainedProperty.inList)
} else if (Enum.isAssignableFrom(constrainedProperty.propertyType)) {
    List values = constrainedProperty.nullable ? [null] : []
    values.addAll(EnumSet.allOf(constrainedProperty.propertyType))
    widgetAttributes.items = FXCollections.observableList(values)
} else {
    widgetAttributes.items = FXCollections.observableList([])
}

choiceBox(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).valueProperty(),
    scaffoldingContext.validateable."${propertyName}Property"(),
    constrainedProperty, Object)