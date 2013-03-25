package griffon.plugins.scaffolding.templates

String widgetNode = constrainedProperty.password ? 'passwordField' : 'textField'
Map widgetAttributes = scaffoldingContext.widgetAttributes(widgetNode, constrainedProperty)
widgetAttributes.editable = constrainedProperty.editable

"${widgetNode}"(widgetAttributes)
scaffoldingContext.bind(getVariable(propertyName).textProperty(),
    scaffoldingContext.validateable."${propertyName}Property"(),
    constrainedProperty, String)