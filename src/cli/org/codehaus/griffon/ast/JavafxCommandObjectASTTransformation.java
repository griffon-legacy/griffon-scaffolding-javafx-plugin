/*
 * Copyright 2013 the original author or authors.
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

package org.codehaus.griffon.ast;

import groovyx.javafx.beans.FXBindable;
import groovyx.javafx.beans.FXBindableASTTransformation;
import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.ast.AnnotatedNode;
import org.codehaus.groovy.ast.AnnotationNode;
import org.codehaus.groovy.ast.ClassNode;
import org.codehaus.groovy.control.SourceUnit;

/**
 * Handles generation of code for the {@code @CommandObject} annotation.
 * <p/>
 *
 * @author Andres Almiray
 */
public class JavafxCommandObjectASTTransformation extends CommandObjectASTTransformation {
    private static final ClassNode FXBINDABLE_TYPE = makeClassSafe(FXBindable.class);

    /**
     * Handles the bulk of the processing, mostly delegating to other methods.
     *
     * @param nodes  the ast nodes
     * @param source the source unit for the nodes
     */
    public void visit(ASTNode[] nodes, SourceUnit source) {
        FXBindableASTTransformation astTransformation = new FXBindableASTTransformation();

        AnnotationNode fxBindable = new AnnotationNode(FXBINDABLE_TYPE);
        AnnotatedNode classNode = (AnnotatedNode) nodes[1];
        classNode.addAnnotation(fxBindable);

        astTransformation.visit(nodes, source);
    }
}
