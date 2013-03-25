package griffon.plugins.scaffolding.templates

Map widgetAttributes = scaffoldingContext.widgetAttributes('textField', constrainedProperty)
widgetAttributes.editable = constrainedProperty.editable

textField(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).textProperty(),
    scaffoldingContext.validateable."${propertyName}Property"(),
    constrainedProperty, String)