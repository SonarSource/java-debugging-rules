package org.foo;

class A {
  void foo() {
    foo(); // Compliant
    gul("hello", 0); // Compliant

    bar(); // Noncompliant [[sc=5;ec=8]] {{Unknown method}}
    Unknown.bar(); // Noncompliant [[sc=13;ec=16]]
    this.bar(); // Noncompliant [[sc=10;ec=13]]
    gul( // Compliant - ECJ is able to resolve it!
      unknownVar
      , 1);
  }

  void gul(String s, int i) {}
}