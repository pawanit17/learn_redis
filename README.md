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
