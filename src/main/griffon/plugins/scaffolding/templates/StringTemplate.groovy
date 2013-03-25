package griffon.plugins.scaffolding.templates

int sizeThreshold = 250

if (constrainedProperty.minSize >= sizeThreshold || constrainedProperty.maxSize >= sizeThreshold ||
    constrainedProperty.size?.from >= sizeThreshold || constrainedProperty.size?.to >= sizeThreshold) {
    Map widgetAttributes = scaffoldingContext.widgetAttributes('textArea', constrainedProperty)
    widgetAttributes.editable = constrainedProperty.editable
    Map scrollPaneAttributes = [:]
    scrollPaneAttributes.putAll(scaffoldingContext.widgetAttributes('scrollPane', constrainedProperty))
    scrollPaneAttributes.putAll(widgetAttributes.remove('scrollPane') ?: [:])
    scrollPaneAttributes.constraints = widgetAttributes.remove('constraints')

    scrollPane(scrollPaneAttributes) {
        textArea(widgetAttributes)
        scaffoldingContext.bind(getVariable(propertyName).textProperty(),
            scaffoldingContext.validateable."${propertyName}Property"(),
            constrainedProperty, String)
    }
} else {
    String widgetNode = constrainedProperty.password ? 'passwordField' : 'textField'
    Map widgetAttributes = scaffoldingContext.widgetAttributes(widgetNode, constrainedProperty)
    widgetAttributes.editable = constrainedProperty.editable

    "${widgetNode}"(widgetAttributes)
    scaffoldingContext.bind(getVariable(propertyName).textProperty(),
        scaffoldingContext.validateable."${propertyName}Property"(),
        constrainedProperty, String)
}