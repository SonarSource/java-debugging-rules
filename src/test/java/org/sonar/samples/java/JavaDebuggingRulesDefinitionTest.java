package org.sonar.samples.java;

import org.junit.Test;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Repository;
import org.sonar.api.server.rule.RulesDefinition.Rule;

import static org.fest.assertions.Assertions.assertThat;

public class JavaDebuggingRulesDefinitionTest {

  @Test
  public void test() {
    JavaDebuggingRulesDefinition rulesDefinition = new JavaDebuggingRulesDefinition();
    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);
    RulesDefinition.Repository repository = context.repository(JavaDebuggingRulesDefinition.REPOSITORY_KEY);

    assertThat(repository.language()).isEqualTo("java");
    assertThat(repository.rules()).hasSize(RulesList.getChecks().size());

    assertRuleProperties(repository);
  }

  private void assertRuleProperties(Repository repository) {
    Rule rule = repository.rule("UnknownMethodInvocation");
    assertThat(rule).isNotNull();
    assertThat(rule.name()).isEqualTo("Unknown Method invocation");
    assertThat(rule.debtRemediationFunction()).isNull();
    assertThat(rule.type()).isEqualTo(RuleType.CODE_SMELL);
  }

}
