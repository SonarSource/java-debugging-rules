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
package org.sonar.samples.java.checks;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.JavaFileScannerContext.Location;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public abstract class UnknownTypeAbstractCheck extends IssuableSubscriptionVisitor {
  static List<Location> getMissingArguments(Arguments arguments) {
    List<JavaFileScannerContext.Location> secondaries = new ArrayList<>();
    for (ExpressionTree argument : arguments) {
      if (argument.symbolType().isUnknown()) {
        secondaries.add(new JavaFileScannerContext.Location("", argument));
      }
    }
    return secondaries;
  }

  void reportUnknownType(Tree reportTree, String message, List<JavaFileScannerContext.Location> secondaries) {
    Tree target = reportTree;
    if (target.is(Tree.Kind.MEMBER_SELECT)) {
      target = ((MemberSelectExpressionTree) target).identifier();
    }
    reportIssue(target, message, secondaries, null);
  }

}
