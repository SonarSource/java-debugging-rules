package org.foo;

class A {
  A(String s, int i) {}

  void foo() {
    new A("hello", 0); // Compliant
    new A( // Compliant - ECJ is able to resolve it!
      unknownVar
      , 1);
    new org.foo.A(); // Noncompliant [[sc=17;ec=18]] {{Unknown constructor}}

    new unknownClass("hello"); // Noncompliant
  }
}
