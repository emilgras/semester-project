USING PERIODIC COMMIT
LOAD CSV WITH HEADERS FROM "file:///city_nodes.csv" AS row
MERGE (:City {id: row.city_id, name: row.city});

bin/neo4j-import --into books.db --id-type string \
                 --nodes:City city_nodes.csv
