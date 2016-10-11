#!/bin/bash

set -euo pipefail

function configureTravis { 
  mkdir ~/.local curl -sSL https://github.com/SonarSource/travis-utils/tarball/latest | tar zx --strip-components 1 -C ~/.local source ~/.local/bin/install
}

configureTravis
. installJDK8

mvn clean install