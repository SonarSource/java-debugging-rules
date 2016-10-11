package org.foo;

class A {
  A(String s, int i) {}

  void foo() {
    new A("hello", 0); // Compliant
    new A( // Noncompliant [[sc=9;ec=10;secondary=9]] {{Unknown constructor}}
      unknownVar
      , 1);
    new org.foo.A(); // noncompliant [[sc=17;ec=18]] {{Unknown constructor}}
  }
}