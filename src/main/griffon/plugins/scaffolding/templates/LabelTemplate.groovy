package griffon.plugins.scaffolding.templates

Map widgetAttributes = scaffoldingContext.widgetAttributes('label', constrainedProperty)

label(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).textProperty(),
    scaffoldingContext.validateable."${propertyName}Property"(),
    constrainedProperty, String)