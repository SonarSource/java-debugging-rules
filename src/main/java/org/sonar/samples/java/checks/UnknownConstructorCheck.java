package org.sonar.samples.java.checks;

import com.google.common.collect.ImmutableList;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

@Rule(key = "UnknownConstructorCall")
public class UnknownConstructorCheck extends UnknownTypeAbstractCheck {

  @Override
  public List<Tree.Kind> nodesToVisit() {
    return ImmutableList.of(Tree.Kind.NEW_CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    NewClassTree nct = (NewClassTree) tree;
    if (nct.constructorSymbol().isUnknown()) {
      reportUnknownType(nct.identifier(), "Unknown constructor", getMissingArguments(nct.arguments()));
    }
  }

}
