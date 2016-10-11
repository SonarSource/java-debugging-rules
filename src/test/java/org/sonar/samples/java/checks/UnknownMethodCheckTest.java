package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class UnknownMethodCheckTest {

  @Test
  public void test() {
    JavaCheckVerifier.verify("src/test/files/UnknownMethodCheck.java", new UnknownMethodCheck());
  }

}
