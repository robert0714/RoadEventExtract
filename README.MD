# Introduction
windows要先安裝Docker-toolbox <br/>
https://download.docker.com/win/stable/DockerToolbox.exe<br/>
org.jolokia是io.fabric8的其中一個分支，已經沒有在維護，使用方法大致相同
mvn docker:help <br/>
docker-machine rm default <br/>
docker-machine create  --virtualbox-memory 4096 default <br/>
eval $( docker-machine env  default ) <br/>
mvn clean package docker:build <br/>
mvn docker:start <br/>

docker run -d  -p 8161:8080     -e "MONGO_URL=192.168.57.111"     localhost:5000/robert0714/road-event-extract:2017-12-23 <br/>

 java -jar road-event-extract-1.0-SNAPSHOT.jar  --server.port=8161 --management.port=8162  --spring.data.mongodb.host=172.21.2.135
 <br/>
 
 
docker run -d --rm  -p 8161:8161  -e server.port=8161  -e TZ=Asia/Taipei  -e spring.data.mongodb.host=172.21.2.135  --name road   localhost:5000/robert0714/road-event-extract:2017-12-23  <br/>

docker run -d --rm  -p 8161:8161   -e server.port=8161 -e TZ=Asia/Taipei   -e spring.data.mongodb.host=172.21.2.135  --name road   localhost:5000/robert0714/road-event-extract:2017-12-23  <br/>


docker run -d --rm  -p 8161:8161  -e server.port=8161  -e TZ=Asia/Taipei  -e spring.data.mongodb.host=172.21.2.135  --name road   localhost:5000/robert0714/road-event-extract:2017-12-23  <br/>


 mvn  clean package docker:build -P staging -Dmaven.test.skip=true <br/>
 
 docker run -d --rm  -p 8161:8161    -e server.port=816  -e spring.data.mongodb.host=172.21.2.135   -e http.cors.enabled=true  -e http.cors.allow-origin=http://localhost:* -e http.cors.allow-headers=Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested   --name road   localhost:5000/robert0714/road-event-extract:2017-12-23  <br/>
 
 
 
 
 docker run -d --rm  -p 8161:8161     -e server.port=8161   -e spring.data.mongodb.host=172.21.2.135 -e TZ=Asia/Taipei   -e http.cors.enabled=true  -e http.cors.allow-origin=http://localhost:* -e http.cors.allow-headers=Content-Type,Access-Control-Allow-Headers,Authorization,X-Requested  -e http.cors.allow-methods=OPTIONS,HEAD,GET,POST,PUT,DELETE  --name road   localhost:5000/robert0714/road-event-extract:2017-12-26  <br/>

  # 使用spring cloud ereka client
docker run -d --net=host   -e server.port=8161  -e eureka.client.enabled=true   --name road   -e TZ=Asia/Taipei -e  eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/      localhost:5000/robert0714/road-event-extract:2018-01-27
<br/>


  # 使用mongo replica-set
docker run -d --net=host      --name road   -e TZ=Asia/Taipei -e    spring.data.mongodb.uri=mongodb://192.168.57.115:27017,192.168.57.116:27017  --add-host=db5:192.168.57.115  --add-host=db6:192.168.57.116    localhost:5000/robert0714/road-event-extract:2018-01-27
<br/>