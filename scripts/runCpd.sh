#!/usr/bin/env bash

# Run cpd
pmdRun.sh cpd --minimum-tokens 50 --files src/ --language java > cpd.out

# If cpd.out is empty it means there were no error so we'll exit with code 0
if [ -s cpd.out ]
then
  echo "Cpd failed. Look at cpd.out for details."
  code=1
else
  code=0
fi

exit $code
