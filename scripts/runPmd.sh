#!/usr/bin/env bash

# Run pmd
pmdRun.sh pmd -d src/ -f text -R scripts/pmd_rules.xml > pmd.out

# If pmd.out is empty it means there were no error so we'll exit with code 0
if [ -s pmd.out ]
then
  echo "Pmd failed. Look at pmd.out for details."
  code=1
else
  code=0
fi

exit $code
