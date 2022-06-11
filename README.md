### Build
```./gradlew build```

### RUN
```./gradlew bootRun```


### Docker
```docker run shubham01/student:latest```

### Kubernetes
#### Apply
```kubectl apply -f k8s.yaml```

#### Install Ingress Controller
```kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.2.0/deploy/static/provider/cloud/deploy.yaml```



### Curl
#### [GET] - HealthZ
```
curl --location --request GET 'http://127.0.0.1:8080/healthz'
```
#### [PUT] - Student
```
curl --location -g --request PUT 'http://127.0.0.1:8080/StudentDetails' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "student1",
    "age": 33,
    "address": "Mumbai, India",
    "subjects": [
        {
            "maths": 98
        },
        {
            "physics": 66
        }
    ]
}'
```

#### [GET] - StudentByName
```
curl --location -g --request GET 'http://127.0.0.1:8080/StudentDetails/getData.htm?studentName=student1' \
--header 'Content-Type: application/json' \
--header 'Cookie: 9ccebc14eb9cddce7578730a7186ddc7=2be7f9038043918ffde20bd6f2d25367' \
--data-raw '{
    "name": "Shubham",
    "age": 27,
    "address": "Kormanagala, Bengaluru"
}'
```
#### [GET] - HealthZ 
```
curl --location --request GET 'http://127.0.0.1:8080/healthz'
```

### Postman Collection
Import file ```Student.postman_collection.json``` into Postman.


### ToDo
#### Fix 
- Logs
- PMD Code Analyze Report
- Swagger API Documentation or use Open API