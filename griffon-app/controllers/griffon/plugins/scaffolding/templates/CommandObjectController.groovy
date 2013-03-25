package griffon.plugins.scaffolding.templates

import griffon.javafx.JavaFXApplication
import griffon.javafx.WindowManager
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.stage.Window

import griffon.transform.Threading
import griffon.plugins.scaffolding.ScaffoldingContext
import griffon.plugins.scaffolding.ScaffoldingUtils

class CommandObjectController {
    def model
    def view
    def builder
    ScaffoldingContext scaffoldingContext
    private static final String CONTENT = 'content'

    protected Stage dialog

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void show(Window owner) {
        Window window = owner
        if (!window) {
            window = app.windowManager.windows.find { it.focused }
        }
        if (!dialog || dialog.owner != window) {
            dialog = _createDialog(window, model, builder)
        }
        model.setTitle(scaffoldingContext.resolveMessage('title',
                       ScaffoldingUtils.getNaturalName(scaffoldingContext.validateable)))
        dialog.showAndWait()
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void cancel(evt = null) {
        app.windowManager.hide(dialog)
        scaffoldingContext.validateable.validate()
        dialog = null
    }

    @Threading(Threading.Policy.INSIDE_UITHREAD_SYNC)
    void ok(evt = null) {
        scaffoldingContext.validateable.errors.clearAllErrors()
        scaffoldingContext.validateable.validate()
        if (!scaffoldingContext.validateable.errors.hasErrors()) {
            app.windowManager.hide(dialog)
            dialog = null
        } else {
            scaffoldingContext.applyCssOnError('error')
            for (errorMessage in scaffoldingContext.resolveErrorMessages()) {
                println errorMessage
            }
        }
    }

    private static Stage _createDialog(Window owner, model, builder) {
        Scene scene = (Scene) builder.getVariable(CONTENT)
        scene.stylesheets.add('/validation.css')
        Stage dialog = new Stage()
        if (null != owner) dialog.initOwner(owner)
        dialog.initModality(model.getModality())
        dialog.initStyle(model.getStageStyle())
        dialog.setWidth(model.getWidth())
        dialog.setHeight(model.getHeight())
        dialog.titleProperty().bind(model.titleProperty())
        dialog.resizableProperty().bind(model.resizableProperty())
        dialog.setScene(scene)
        dialog.sizeToScene()
        return dialog
    }
}