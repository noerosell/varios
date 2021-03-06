# DDD Aproach for a simple users REST api

### To get information about a user

```sh
$>curl -H "Content-Type: application/json" -X GET --user admin:Admin1 127.0.0.1:8000/api/user/user1 -v
```

### To create a new User 

```sh
$> curl -H "Content-Type: application/json" -X POST -d '{"username":{"useridentity":"user4"},"password":"Password4","roles":{roles:[ROLE_1]}}' --user admin:Admin1 127.0.0.1:8000/api/user/create -v
```

### To Modify a User (also creates a new one if not exist)

```sh
$> curl -H "Content-Type: application/json" -X PUT -d '{"username":{"useridentity":"user4"},"password":"Password4","roles":{roles:[ROLE_1,ROLE_2]}}' --user admin:Admin1 127.0.0.1:8000/api/user/modify -v
```

### To Delete a user

```sh
$> curl -H "Content-Type: application/json" -X DELETE --user admin:Admin1 127.0.0.1:8000/api/user/delete/user4 -v
```

###

Existents users are:

* user1:Password1
* user2:Password2
* user3:Password3
* admin:Admin1

### Build

```sh
mvn clean install
```

### execute

```sh
java -jar ./target/my-test/my-test-0.1.jar
```





