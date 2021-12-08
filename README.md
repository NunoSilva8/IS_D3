zookeeper-server-start.bat ../../config/zookeeper.properties

kafka-server-start.bat ../../config/server.properties

connect-standalone.bat ../../config/connect-standalone.properties ../../config/connect-jdbc-source-clients.properties ../../config/connect-jdbc-source-managers.properties ../../config/connect-jdbc-sink-payments.properties ../../config/connect-jdbc-sink-credits.properties

--------------------------------------------------------------

kafka-console-producer.bat --broker-list localhost:9092 --topic results

{"schema":{"type":"struct","fields":[{"type":"double","optional":false,"field":"revenue"},{"type":"double","optional":false,"field":"expenses"},{"type":"double","optional":false,"field":"profit"}],"optional":false,"name":"total data"},"payload":{"revenue":988500.0, "expenses":731430.0,"profit":257070.0}}