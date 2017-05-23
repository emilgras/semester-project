#!/bin/bash
DIR="$( cd "$( dirname "$0" )" && pwd)"
CSV_DIR="${DIR}/csvFiles"
sudo mkdir -p "${CSV_DIR}"
echo "Ready to work."

if [ -z "$(ls -A ${CSV_DIR})" ]; then
 wget https://github.com/hilleer/semester-project/raw/master/files/city.zip
 echo "Work, work."
 unzip city.zip -d csvFiles/
 rm city.zip
 echo "Okidoki."
 neo4j-admin import --into books.db --id-type string \ --nodes:City "${CSV_DIR}/city_nodes.csv"
 echo "Jobs done"
else
 echo "Hmmm?"
fi
