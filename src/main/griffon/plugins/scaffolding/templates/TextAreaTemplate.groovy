package griffon.plugins.scaffolding.templates

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