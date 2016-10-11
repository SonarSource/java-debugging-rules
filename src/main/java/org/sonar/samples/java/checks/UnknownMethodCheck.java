package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

@Rule(key = "UnknownMethodInvocation")
public class UnknownMethodCheck extends UnknownTypeAbstractCheck {

  @Override
  public List<Tree.Kind> nodesToVisit() {
    return ImmutableList.of(Tree.Kind.METHOD_INVOCATION);
  }

  @Override
  public void visitNode(Tree tree) {
    MethodInvocationTree mit = (MethodInvocationTree) tree;
    if (mit.symbol().isUnknown()) {
      reportUnknownType(mit.methodSelect(), "Unknown method", getMissingArguments(mit.arguments()));
    }
  }

}
