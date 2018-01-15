# 分别以peer1和peeer2 配置信息启动eureka
java -jar batfish-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer1
java -jar batfish-eureka-server-0.0.1-SNAPSHOT.jar --spring.profiles.active=peer2