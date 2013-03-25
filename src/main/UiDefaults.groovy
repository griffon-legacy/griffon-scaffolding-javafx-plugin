/*
 * Copyright 2009-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author Andres Almiray
 */

labeler {
    constraints = 'top, left'
}

widget {
    constraints = 'top, grow'
}

textField {
    prefColumnCount = 20
}

passwordField {
    prefColumnCount = 20
}

textArea {
    prefColumnCount = 20
    prefRowCount = 4
    wrapText = true
}

scrollPane {
    fitToWidth = true
    fitToHeight = true
    pannable = true
}

checkBox {
    allowIndeterminate = true
    indeterminate = true
}

slider {
    majorTickUnit = 10
    minorTickCount = 5
    blockIncrement = 1
    showTickLabels = true
    showTickMarks = true
}