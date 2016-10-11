/*
 * Copyright (C) 2009-2014 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package org.sonar.samples.java;

import org.sonar.api.Plugin;

/**
 * Entry point of plugin
 */
public class JavaDebuggingRulesPlugin implements Plugin {

  @Override
  public void define(Context context) {

    // server extensions -> objects are instantiated during server startup
    context.addExtension(JavaDebuggingRulesDefinition.class);

    // batch extensions -> objects are instantiated during code analysis
    context.addExtension(JavaDebuggingRulesCheckRegistrar.class);

  }
}
