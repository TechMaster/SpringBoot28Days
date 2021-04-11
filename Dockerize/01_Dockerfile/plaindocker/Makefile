containername = plaindocker
build:
	./mvnw package
	docker build -t plaindocker:latest .
start:
	docker rm $(containername)
	docker run -p 7000:8080 -d --name $(containername) plaindocker:latest
stop:
	docker stop $(containername)