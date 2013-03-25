package griffon.plugins.scaffolding.templates

import groovyx.javafx.beans.FXBindable
import javafx.stage.Modality
import javafx.stage.StageStyle

@FXBindable
class CommandObjectModel {
    String title = ''
    double width = 400d
    double height = 300d
    boolean resizable = false
    Modality modality = Modality.WINDOW_MODAL
    StageStyle stageStyle = StageStyle.UTILITY
}