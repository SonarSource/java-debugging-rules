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
