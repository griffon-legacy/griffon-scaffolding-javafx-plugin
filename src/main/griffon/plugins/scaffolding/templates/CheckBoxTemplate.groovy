package griffon.plugins.scaffolding.templates

def valueHolder = scaffoldingContext.validateable."${propertyName}Property"()

Map widgetAttributes = scaffoldingContext.widgetAttributes('checkBox', constrainedProperty)
if (valueHolder.value != null) widgetAttributes.selected = valueHolder.value
if (constrainedProperty.propertyType.primitive || !constrainedProperty.nullable) {
    widgetAttributes.allowIndeterminate = false
    widgetAttributes.indeterminate = false
}

checkBox(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).selectedProperty(),
    valueHolder, constrainedProperty, Boolean)