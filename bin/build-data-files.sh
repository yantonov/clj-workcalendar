#!/bin/sh

SCRIPT_DIR=$(cd `dirname $0` && pwd)

cd $SCRIPT_DIR/../

# grab work-calendar and generate data files
lein run
