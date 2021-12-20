# User Management System

A Basic user management system for demonstrate cache implementation.

Full guide - Link to the tutorial : [Please wait! :) ]

# API Endpoints

## GET /api/users

Get all the users

```bash
curl http://localhost:7000/api/users
```

## GET /api/users/{id}

Get a particular user for given id

```bash
curl http://localhost:7000/api/users/1
```
## POST /api/register

Register a new user

```bash
curl -d '{"name":"Aruna", "age":34,"address":"Galle"}' \
	-H "Content-Type: application/json" \
	-X POST http://localhost:7000/api/register
```

## DELETE /api/users/{id}

Remove a user with given id

```bash
curl -X DELETE http://localhost:7000/api/users/1
```
