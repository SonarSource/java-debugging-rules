package org.sonar.samples.java;

import com.google.common.collect.ImmutableList;

import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.samples.java.checks.UnknownConstructorCheck;
import org.sonar.samples.java.checks.UnknownMethodCheck;

import java.util.List;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .add(UnknownMethodCheck.class)
      .add(UnknownConstructorCheck.class)
      .build();
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
