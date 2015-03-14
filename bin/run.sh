#!/bin/sh

SCRIPT_DIR=$(cd `dirname $0` && pwd)

cd $SCRIPT_DIR/../

# collect work calendar and build js data
lein run
# generate js api to access
lein cljsbuild once
