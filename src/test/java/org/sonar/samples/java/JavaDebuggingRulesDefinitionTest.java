/*
 * Java Debugging Rules
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
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
