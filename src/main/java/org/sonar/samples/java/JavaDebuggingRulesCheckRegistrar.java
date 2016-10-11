package org.sonar.samples.java;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonarsource.api.sonarlint.SonarLintSide;

/**
 * Provide the "checks" (implementations of rules) classes that are gonna be executed during
 * source code analysis.
 *
 * This class is a batch extension by implementing the {@link org.sonar.plugins.java.api.CheckRegistrar} interface.
 */
@SonarLintSide
public class JavaDebuggingRulesCheckRegistrar implements CheckRegistrar {

  /**
   * Register the classes that will be used to instantiate checks during analysis.
   */
  @Override
  public void register(RegistrarContext registrarContext) {
    // Call to registerClassesForRepository to associate the classes with the correct repository key
    registrarContext.registerClassesForRepository(JavaDebuggingRulesDefinition.REPOSITORY_KEY, RulesList.getJavaChecks(), RulesList.getJavaTestChecks());
  }
}
