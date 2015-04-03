all: run

bower:
	cd ./website/src/main/webapp/ && bower install

java: bower
	mvn clean package -Dmaven.test.skip=true

containers: java
	docker-compose build

run: containers
	docker-compose up


