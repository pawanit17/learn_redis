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

127.0.0.1:6379> mget student docker
1) "pavan kumar"
2) "greater"

## List
Lists of strings sorted by insertion order.

127.0.0.1:6379> lpush tutorials tut1 tut2 tut3
(integer) 3
127.0.0.1:6379> lrange tutorials 0 2
1) "tut3"
2) "tut2"
3) "tut1"
127.0.0.1:6379> lrange tutorials 0 29
1) "tut3"
2) "tut2"
3) "tut1"
127.0.0.1:6379> rpush tutorials tut4
(integer) 4
127.0.0.1:6379> lrange tutorials
(error) ERR wrong number of arguments for 'lrange' command
127.0.0.1:6379> lrange tutorials 0 10
1) "tut3"
2) "tut2"
3) "tut1"
4) "tut4"
127.0.0.1:6379> lpop tutorials
"tut3"
127.0.0.1:6379> rpop tutorials
"tut4"
127.0.0.1:6379>


## Sets
No repetition.

127.0.0.1:6379> sadd emails email1 email2 email3
(integer) 3
127.0.0.1:6379> smembers emails
1) "email1"
2) "email3"
3) "email2"
127.0.0.1:6379> scard emails
(integer) 3
127.0.0.1:6379> sadd newemails email3 email4 email5
(integer) 3
127.0.0.1:6379> sdiff emails newemails
1) "email1"
2) "email2"
127.0.0.1:6379> sdiff newemails emails
1) "email4"
2) "email5"
127.0.0.1:6379> sunion emails newemails
1) "email5"
2) "email2"
3) "email3"
4) "email1"
5) "email4"
127.0.0.1:6379> sinter emails newemails
1) "email3"
127.0.0.1:6379> spop emails
"email2"
127.0.0.1:6379> srem newemails email5 email4
(integer) 2
127.0.0.1:6379>


## Sorted Sets
127.0.0.1:6379> zadd names 1- bassem 20 ali 30 chuck
(error) ERR value is not a valid float
127.0.0.1:6379> zadd names 10 bassem 20 ali 30 chuck
(integer) 3
127.0.0.1:6379> zcard names
(integer) 3
127.0.0.1:6379> zrange names
(error) ERR wrong number of arguments for 'zrange' command
127.0.0.1:6379> zrange names 0 10
1) "bassem"
2) "ali"
3) "chuck"
127.0.0.1:6379> rank names bassem
(error) ERR unknown command `rank`, with args beginning with: `names`, `bassem`,
127.0.0.1:6379> zrank names bassem
(integer) 0
127.0.0.1:6379> zrank names ali
(integer) 1
127.0.0.1:6379> zscore names bassem
"10"

## Hashes


# Redist Persistency Options
- No persistence
- Redis DataBase File
  - Periodic snapshot.
  - Good option for Production.
  - Enabled by default.
  - Good for disaster recovery.
- Append Only File
  - Log each WRITE operation so that the dataset can be recovered.
  - Prevents data corruption.
  - Slow because of the number of WRITEs involved.
  - Has to be explicitly enabled in the conf file.
- Hybrid Approach
  - Recommended approach

# Sentinel
This is used for increasing the availability of Redis to the Redis clients.
Redis Clients ask Sentinels what the address of the current Redis master is.
If the master is considered down by the quorum number of Sentinels, then the Sentinels promote one of the Redis slaves as the new master.
![image](https://user-images.githubusercontent.com/42272776/110230625-41934a00-7f38-11eb-8c1a-baed0aa3d4d9.png)

Redis config files are used to determine settings like whether the service is Master, which port to run at, what its slaves are etc.

# Cluster
Similar to Sentinel, it provides high availability.
But because the Redis is sharded, more data can be fit in.
Unlike Sentinels, there is no dedicated monitoring services.
![image](https://user-images.githubusercontent.com/42272776/110230923-596bcd80-7f3a-11eb-8fbe-24994642fcdd.png)

In the above example, the master cluster is formed of 3 server and the slave cluster too has 3 nodes. The clients connect to it by the default 6386 port and the masters and slaves are all connected via another port 16386. This connection allows configuration files exchange, availability updates etc.
Sharding is implicitly done in this mode using a CRC hash function to distribute the data across multiple servers.
Ex: ```create-cluster start```

# Cluster vs Sentinel

Sentinel - Smaller implementations with the need for high availability.

Cluster - Scalable implementations with the need for high availability. This is also faster as there are no sentinels and offers replication.





