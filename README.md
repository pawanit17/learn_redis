# Overview
- The name Redis means REmote DIctionary Server.
- Most popular in-memory database.
- Stores data in key-value pairs.
- NoSQL database.
  - Key-Value stores - Redis, Voldemort and Dynamo
  - Document Databases - MongoDB and CouchDB
  - Wide column databases - Cassandra and HBASE
  - Graph Databases - Neo4J and Infinite Graph

# Usecases
- User session management
- Caching
- Pub/Sub ( Queues and Notifications )
- Leaderboards for realtime gaming applications
- Geospatial searches

# Datatypes
The value in Redis can be one from the below list:
- Strings
- Lists
- Sets
- Sorted Sets
- Hashes

# Working with Redis

Spin a REDIS container: ```docker run --name redis_container -d redis```
Run commands on REDIS cli: ```docker exec -it redis_container sh```

# Commands
- set student pavan
- get student
- set student "pavan dittakavi"
- keys *
- keys st*
- set student pavard ex 10 // Expires automatically after 10 seconds.
- ttl student 7
- ttl student -2 
- get student
  - (nil)
-- XX works only if the key exists.
-- NX works only if the key doesnt exist.

127.0.0.1:6379> set student pavan
OK
127.0.0.1:6379> append student " kumar"
(integer) 11
127.0.0.1:6379> get student
"pavan kumar"
