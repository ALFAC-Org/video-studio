#!/bin/sh

OWASPDC_DIRECTORY=$HOME/OWASP-Dependency-Check-PUBLIC
DATA_DIRECTORY="$OWASPDC_DIRECTORY/data"
REPORT_DIRECTORY="$OWASPDC_DIRECTORY/reports"
FAIL_ON_CVSS=$1

if [ ! -d "$DATA_DIRECTORY" ]; then
    echo "Initially creating persistent directories"
    mkdir -p "$DATA_DIRECTORY"
    chmod -R 777 "$DATA_DIRECTORY"

    mkdir -p "$REPORT_DIRECTORY"
    chmod -R 777 "$REPORT_DIRECTORY"
fi

# Make sure we are using the latest version
docker pull izhar0407/dependency-check

docker run --rm \
    --volume $(pwd):/src \
    --volume "$DATA_DIRECTORY":/usr/share/dependency-check/data \
    --volume "$REPORT_DIRECTORY":/report \
    izhar0407/dependency-check \
    --scan ./src \
    --disableNodeAuditCache \
    --nodeAuditSkipDevDependencies \
    --nodePackageSkipDevDependencies \
    --format "ALL" \
    --project "video-studio" \
    --failOnCVSS $FAIL_ON_CVSS \
    --out /report