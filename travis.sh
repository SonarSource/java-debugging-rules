#!/bin/bash
set -euo pipefail

function configureTravis { 
  mkdir -p ~/.local
  curl -sSL https://github.com/SonarSource/travis-utils/tarball/v36 | tar zx --strip-components 1 -C ~/.local
  source ~/.local/bin/install
}
configureTravis

. installJDK8

# CI: build (incl. tests), deploy artifacts and triggers NEXT analysis (incl. JaCoCo)
regular_mvn_build_deploy_analyze