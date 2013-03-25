package griffon.plugins.scaffolding.templates

def valueHolder = scaffoldingContext.validateable."${propertyName}Property"()

Map widgetAttributes = scaffoldingContext.widgetAttributes('slider', constrainedProperty)

if (constrainedProperty.min != null && constrainedProperty.max != null) {
    widgetAttributes.min = constrainedProperty.min
    widgetAttributes.max = constrainedProperty.max
} else if (constrainedProperty.range != null) {
    widgetAttributes.min = constrainedProperty.range.from
    widgetAttributes.max = constrainedProperty.range.to
}

widgetAttributes.value = valueHolder.value != null ? valueHolder.value : (widgetAttributes.min ?: 0)

slider(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).valueProperty(),
    scaffoldingContext.validateable."${propertyName}Property"(),
    constrainedProperty, Double)