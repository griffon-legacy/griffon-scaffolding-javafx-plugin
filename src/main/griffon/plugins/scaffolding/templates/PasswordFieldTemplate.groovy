package griffon.plugins.scaffolding.templates

Map widgetAttributes = scaffoldingContext.widgetAttributes('passwordField', constrainedProperty)
widgetAttributes.editable = constrainedProperty.editable

passwordField(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).textProperty(),
    scaffoldingContext.validateable."${propertyName}Property"(),
    constrainedProperty, String)