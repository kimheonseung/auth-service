# Authentication API

### Springboot Application

#### 1. MariaDB build

```shell 
cd docker
docker-compose up -d
```

```shell
# test data
# src/main/resources/application.yml
spring:
  sql:
    init:
      data-location: # uncomment this line.
```

#### 2. JAR build

```shell
./gradlew build
cd ./build/libs
java -jar hauth-1.0.jar
```

## USER API

### 1. get user list

```shell
curl -XGET "http://localhost:8001/api/v1/user/list"
{
    "timestamp": "2022-02-17T21:51:04.43041",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "createdAt": null,
            "updatedAt": null,
            "id": 1,
            "username": "root",
            "password": "$2a$10$7/OVVzZfA9wI1q4viJVmkefkzF7W7bgzwXoBJUXl2C4IVCVMz4tNi",
            "name": "김루트",
            "nickname": "루트킴",
            "email": null,
            "phone": null,
            "accessIp": "*.*.*.*",
            "loginFailIp": "",
            "loginFailCount": 0,
            "statusMessage": "",
            "backgroundImage": "",
            "profileImage": "",
            "loginAt": "2022-02-17T06:25:18",
            "loginFailedAt": "1970-01-01T09:00:00",
            "passwordChangedAt": "2022-02-17T06:25:18",
            "authorities": [
                {
                    "id": 2,
                    "name": "admin",
                    "description": "관리자"
                },
                {
                    "id": 1,
                    "name": "root",
                    "description": "루트"
                },
                {
                    "id": 4,
                    "name": "guest",
                    "description": "게스트"
                },
                {
                    "id": 3,
                    "name": "normal",
                    "description": "일반"
                }
            ],
            "departments": [
                {
                    "text": null,
                    "children": [],
                    "id": 2,
                    "name": "대표이사",
                    "description": "대표",
                    "parentId": 1,
                    "root": false
                }
            ]
        },
        {
            "createdAt": null,
            "updatedAt": null,
            "id": 2,
            "username": "admin",
            "password": "$2a$10$jGszCh15WEW3jxzoLKdNMuFt7ZV53cYPfPnrZH1w7RmZJnGnz3lDC",
            "name": "김관리",
            "nickname": "관리킴",
            "email": null,
            "phone": null,
            "accessIp": "*.*.*.*",
            "loginFailIp": "",
            "loginFailCount": 0,
            "statusMessage": "",
            "backgroundImage": "",
            "profileImage": "",
            "loginAt": "2022-02-17T06:25:18",
            "loginFailedAt": "1970-01-01T09:00:00",
            "passwordChangedAt": "2022-02-17T06:25:18",
            "authorities": [
                {
                    "id": 2,
                    "name": "admin",
                    "description": "관리자"
                },
                {
                    "id": 4,
                    "name": "guest",
                    "description": "게스트"
                },
                {
                    "id": 3,
                    "name": "normal",
                    "description": "일반"
                }
            ],
            "departments": []
        },
        {
            "createdAt": null,
            "updatedAt": null,
            "id": 3,
            "username": "normal",
            "password": "$2a$10$mvtG3BrlehPfFLDz5.XNveoRYK1LJhp5yudKuXKiwfiSyjb30kk1i",
            "name": "김일반",
            "nickname": "일반킴",
            "email": null,
            "phone": null,
            "accessIp": "*.*.*.*",
            "loginFailIp": "",
            "loginFailCount": 0,
            "statusMessage": "",
            "backgroundImage": "",
            "profileImage": "",
            "loginAt": "2022-02-17T06:25:18",
            "loginFailedAt": "1970-01-01T09:00:00",
            "passwordChangedAt": "2022-02-17T06:25:18",
            "authorities": [
                {
                    "id": 3,
                    "name": "normal",
                    "description": "일반"
                },
                {
                    "id": 4,
                    "name": "guest",
                    "description": "게스트"
                }
            ],
            "departments": []
        },
        {
            "createdAt": null,
            "updatedAt": null,
            "id": 4,
            "username": "guest",
            "password": "$2a$10$EWTFu2lWmFvzmP50VtXpPuAdOEESre0ta6FFkxaV/ED9VVcRh/0sW",
            "name": "김손님",
            "nickname": "손님킴",
            "email": null,
            "phone": null,
            "accessIp": "*.*.*.*",
            "loginFailIp": "",
            "loginFailCount": 0,
            "statusMessage": "",
            "backgroundImage": "",
            "profileImage": "",
            "loginAt": "2022-02-17T06:25:18",
            "loginFailedAt": "1970-01-01T09:00:00",
            "passwordChangedAt": "2022-02-17T06:25:18",
            "authorities": [
                {
                    "id": 4,
                    "name": "guest",
                    "description": "게스트"
                }
            ],
            "departments": []
        }
    ]
}   
```

### 2. get user by username

```shell
curl -XGET "http://localhost:8001/api/v1/user/root"
{
    "timestamp": "2022-02-18T00:16:14.355401",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "createdAt": null,
            "updatedAt": null,
            "id": 1,
            "username": "root",
            "password": "$2a$10$7/OVVzZfA9wI1q4viJVmkefkzF7W7bgzwXoBJUXl2C4IVCVMz4tNi",
            "name": "김루트",
            "nickname": "루트킴",
            "email": null,
            "phone": null,
            "accessIp": "*.*.*.*",
            "loginFailIp": "",
            "loginFailCount": 0,
            "statusMessage": "",
            "backgroundImage": "",
            "profileImage": "",
            "loginAt": "2022-02-17T06:25:18",
            "loginFailedAt": "1970-01-01T09:00:00",
            "passwordChangedAt": "2022-02-17T06:25:18",
            "authorities": [
                {
                    "id": 4,
                    "name": "guest",
                    "description": "게스트"
                },
                {
                    "id": 1,
                    "name": "root",
                    "description": "루트"
                },
                {
                    "id": 2,
                    "name": "admin",
                    "description": "관리자"
                },
                {
                    "id": 3,
                    "name": "normal",
                    "description": "일반"
                }
            ],
            "departments": [
                {
                    "text": null,
                    "children": [],
                    "id": 2,
                    "name": "대표이사",
                    "description": "대표",
                    "parentId": 1,
                    "root": false
                }
            ]
        }
    ]
}
```

## TOKEN API

### 1. generate token

```shell
# password: AES256 with password key (devh)
curl -XPOST "http://localhost:8001/api/v1/token/generate" \ 
  -H "Content-Type: application/json" \
  -d '{
    "username": "root",
    "passowrd": "bK5m/i6cms4hSHSronOsqKhqOUyqxTnjU8txYVbneWcPWVu1hOJV65BIAz6GPIIhNXrS+Q=="
  }'

{
    "timestamp": "2022-02-23T23:50:56.154025",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "tokenStatus": "LOGIN_SUCCESS",
            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTYyNzg1NiwiZXhwIjoxNjQ1NjMxNDU2fQ.2jCB6IvbI1Q-jFIxEWkmogfxTCwTa96yY0dV2SYVgfk",
            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU2Mjc4NTYsImV4cCI6MTY0NjIzMjY1Nn0.QsvIkLruKBQtXDgOlUWLvO_sJNXtMmgxMhGGiNVu8yI"
        }
    ]
}
```

### 2. get token information

```shell
curl -XGET "http://localhost:8001/api/v1/token/information" \
  -H "Content-Type: application/json" \
  -d '{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTExMDkyMiwiZXhwIjoxNjQ1MTE0NTIyfQ.SX1z22wHQw5gIwdKScUB8MToEHYl0N_3Z7ZKYjLJ4fE"
  }' 

{
    "timestamp": "2022-02-23T23:52:39.41098",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "validate": true,
            "issuer": "devh",
            "subject": "root",
            "audience": null,
            "expiration": "2022-02-24 00:50:56",
            "notBefore": "",
            "issuedAt": "2022-02-23 23:50:56",
            "id": null,
            "description": null
        }
    ]
}
```

### 3. Refresh token

```shell
curl -XPOST "http://localhost:8001/api/v1/token/refresh" \ 
  -H "Content-Type: application/json" \
  -d '{
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTYyNzg1NiwiZXhwIjoxNjQ1NjMxNDU2fQ.2jCB6IvbI1Q-jFIxEWkmogfxTCwTa96yY0dV2SYVgfk",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU2Mjc4NTYsImV4cCI6MTY0NjIzMjY1Nn0.QsvIkLruKBQtXDgOlUWLvO_sJNXtMmgxMhGGiNVu8yI"
  }'
  
# If access token is not expired...
{
    "timestamp": "2022-02-23T23:55:16.697727",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "tokenStatus": "ACCESS_TOKEN_NOT_EXPIRED",
            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTYyNzg1NiwiZXhwIjoxNjQ1NjMxNDU2fQ.2jCB6IvbI1Q-jFIxEWkmogfxTCwTa96yY0dV2SYVgfk",
            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU2Mjc4NTYsImV4cCI6MTY0NjIzMjY1Nn0.QsvIkLruKBQtXDgOlUWLvO_sJNXtMmgxMhGGiNVu8yI"
        }
    ]
}

# If access token is expired & refresh token is not expired...
{
    "timestamp": "2022-02-24T00:00:02.80272",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "tokenStatus": "REFRESH_SUCCESS",
            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTYyODQwMiwiZXhwIjoxNjQ1NjI4NDM0fQ.ao8UtqwV1EX0voa09bj1uVReYG3m1hXnLp0UYyYvjNw",
            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU2Mjg0MDIsImV4cCI6MTY0NjIzMzIwMn0.uh1QynBgDcJJKhE5zHxz5nYOYxSy5n8fSO2R16sdtfU"
        }
    ]
}

# If access token & refresh token are both expired...
{
    "timestamp": "2022-02-24T00:01:56.913204",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "tokenStatus": "LOGIN_REQUIRED",
            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJkZXZoIiwic3ViIjoicm9vdCIsImlhdCI6MTY0NTYyODQ5MSwiZXhwIjoxNjQ1NjI4NDkyfQ.smcsJlZ6zPKEYx5zZQ_HE6-kIcUzqaX--6VyEF0TpDg",
            "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NDU2Mjg0OTEsImV4cCI6MTY0NTYyODQ5Mn0.eXpTHPO3FzWBUbKBPnpBGL7pjHWZQMlp29VgLEg0aic"
        }
    ]
}

# If a

```

## DEPARTMENT API

### 1. get tree

```shell
curl -XGET "http://localhost:8001/api/v1/department/tree"
{
    "timestamp": "2022-02-17T19:40:16.653463",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "text": "DevH.Co",
            "children": [
                {
                    "text": "대표이사",
                    "children": [],
                    "id": 2,
                    "name": "대표이사",
                    "description": "대표",
                    "parentId": 1,
                    "root": false
                },
                {
                    "text": "기획",
                    "children": [],
                    "id": 3,
                    "name": "기획",
                    "description": "기획",
                    "parentId": 1,
                    "root": false
                },
                {
                    "text": "감사",
                    "children": [],
                    "id": 4,
                    "name": "감사",
                    "description": "감사",
                    "parentId": 1,
                    "root": false
                },
                {
                    "text": "경영",
                    "children": [
                        {
                            "text": "인사",
                            "children": [],
                            "id": 8,
                            "name": "인사",
                            "description": "인사",
                            "parentId": 5,
                            "root": false
                        },
                        {
                            "text": "재무",
                            "children": [],
                            "id": 9,
                            "name": "재무",
                            "description": "재무",
                            "parentId": 5,
                            "root": false
                        }
                    ],
                    "id": 5,
                    "name": "경영",
                    "description": "경영",
                    "parentId": 1,
                    "root": false
                },
                {
                    "text": "영업",
                    "children": [
                        {
                            "text": "국내영업",
                            "children": [],
                            "id": 10,
                            "name": "국내영업",
                            "description": "국내영업",
                            "parentId": 6,
                            "root": false
                        },
                        {
                            "text": "해외영업",
                            "children": [],
                            "id": 11,
                            "name": "해외영업",
                            "description": "해외영업",
                            "parentId": 6,
                            "root": false
                        }
                    ],
                    "id": 6,
                    "name": "영업",
                    "description": "영업",
                    "parentId": 1,
                    "root": false
                },
                {
                    "text": "연구소",
                    "children": [
                        {
                            "text": "1연구소",
                            "children": [
                                {
                                    "text": "C개발",
                                    "children": [],
                                    "id": 15,
                                    "name": "C개발",
                                    "description": "C개발",
                                    "parentId": 12,
                                    "root": false
                                },
                                {
                                    "text": "C설계",
                                    "children": [],
                                    "id": 16,
                                    "name": "C설계",
                                    "description": "C설계",
                                    "parentId": 12,
                                    "root": false
                                }
                            ],
                            "id": 12,
                            "name": "1연구소",
                            "description": "1연구소",
                            "parentId": 7,
                            "root": false
                        },
                        {
                            "text": "2연구소",
                            "children": [
                                {
                                    "text": "JAVA개발",
                                    "children": [],
                                    "id": 17,
                                    "name": "JAVA개발",
                                    "description": "JAVA개발",
                                    "parentId": 13,
                                    "root": false
                                },
                                {
                                    "text": "JAVA설계",
                                    "children": [],
                                    "id": 18,
                                    "name": "JAVA설계",
                                    "description": "JAVA설계",
                                    "parentId": 13,
                                    "root": false
                                }
                            ],
                            "id": 13,
                            "name": "2연구소",
                            "description": "2연구소",
                            "parentId": 7,
                            "root": false
                        },
                        {
                            "text": "3연구소",
                            "children": [
                                {
                                    "text": "QA",
                                    "children": [],
                                    "id": 19,
                                    "name": "QA",
                                    "description": "QA",
                                    "parentId": 14,
                                    "root": false
                                }
                            ],
                            "id": 14,
                            "name": "3연구소",
                            "description": "3연구소",
                            "parentId": 7,
                            "root": false
                        }
                    ],
                    "id": 7,
                    "name": "연구소",
                    "description": "연구소",
                    "parentId": 1,
                    "root": false
                }
            ],
            "id": 1,
            "name": "DevH.Co",
            "description": "회사명",
            "parentId": null,
            "root": true
        }
    ]
}
```

### 2. get department by id

```shell
curl -XGET "http://localhost:8001/api/v1/department/17"
{
    "timestamp": "2022-02-18T01:00:12.355179",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "text": null,
            "children": [],
            "id": 17,
            "name": "JAVA개발",
            "description": "JAVA개발",
            "parentId": 13,
            "root": false
        }
    ]
}
```

### 3. register department

```shell
curl -XPOST "http://localhost:8001/api/v1/department/register" \ 
  -H "Content-Type: application/json" \
  -d '{
    "name": "test dept",
    "description": "test"
  }'
{
    "timestamp": "2022-02-28T21:05:51.195119",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        true
    ]
}
```

### 4. update department

```shell
curl -XPOST "http://localhost:8001/api/v1/department/update" \ 
  -H "Content-Type: application/json" \
  -d '{
    "id": 26,
    "name": "test dept_up",
    "parentId": 2
  }'
{
    "timestamp": "2022-02-28T21:20:35.514127",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        true
    ]
}
```

### 5. delete department

```shell
curl -XPOST "http://localhost:8001/api/v1/department/delete" \ 
  -H "Content-Type: application/json" \
  -d '{
    "id": 12
  }'
{
    "timestamp": "2022-03-03T00:46:01.36401",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        true
    ]
}
```

## AUTHORITY API

### 1. get authority list

```shell
curl -XGET "http://localhost:8001/api/v1/authority/list"
{
    "timestamp": "2022-02-17T19:35:01.146856",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "id": 1,
            "name": "root",
            "description": "루트"
        },
        {
            "id": 2,
            "name": "admin",
            "description": "관리자"
        },
        {
            "id": 3,
            "name": "normal",
            "description": "일반"
        },
        {
            "id": 4,
            "name": "guest",
            "description": "게스트"
        }
    ]
}
```

### 2. get authority by id

```shell
curl -XGET "http://localhost:8001/api/v1/authority/1"
{
    "timestamp": "2022-02-17T19:35:27.921799",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        {
            "id": 1,
            "name": "root",
            "description": "루트"
        }
    ]
}
```

### 3. register authority

```shell
curl -XPOST "http://localhost:8001/api/v1/authority/register" \ 
  -H "Content-Type: application/json" \
  -d '{
    "name": "test",
    "description": "test_auth"
  }'
{
    "timestamp": "2022-03-03T00:49:22.253477",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        true
    ]
}
```

### 4. update authority

```shell
curl -XPOST "http://localhost:8001/api/v1/authority/update" \ 
  -H "Content-Type: application/json" \
  -d '{
    "id": 5,
    "name": "test_up",
    "description": "test_auth_up"
  }'
{
    "timestamp": "2022-03-03T00:51:03.989403",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        true
    ]
}
```

### 5. delete authority

```shell
curl -XPOST "http://localhost:8001/api/v1/authority/delete" \ 
  -H "Content-Type: application/json" \
  -d '{
    "id": 5
  }'
{
    "timestamp": "2022-03-03T00:52:40.725386",
    "status": 200,
    "message": "Ok",
    "description": "Standard response for successful HTTP requests.",
    "paging": null,
    "dataArray": [
        true
    ]
}
```