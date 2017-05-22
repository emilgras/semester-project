#!/bin/bash
../bin/neo4j-import --into books.db --id-type string \
                 --nodes:City city_nodes.csv
