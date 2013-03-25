package griffon.plugins.scaffolding.templates

import static griffon.util.GriffonNameUtils.getNaturalName

scene(id: 'content', fill: WHITE) {
    migLayoutPane(layoutConstraints: 'wrap 2', columnConstraints: '[left][left, grow]') {
        scaffoldingContext.validateable.constrainedProperties().each { propertyName, constrainedProperty ->
            if (propertyName.endsWith('Property')) return
            if (!constrainedProperty.display) return
            label(scaffoldingContext.resolveMessage(propertyName + '.label', getNaturalName(propertyName) + ':'),
                constraints: 'top, left', id: propertyName + '_labeler')
            noparent {
                if (constrainedProperty.nullable || !constrainedProperty.blank) {
                    getProperty(propertyName + '_labeler').styleClass.add('required')
                }
            }
            Class widgetTemplate = scaffoldingContext.resolveWidget(propertyName)
            setVariable('propertyName', propertyName)
            setVariable('constrainedProperty', constrainedProperty)
            build(widgetTemplate)
        }
        button(cancelAction, constraints: 'skip, split 2, tag cancel')
        button(okAction, constraints: 'tag ok')
    }
}
