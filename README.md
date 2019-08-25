# Using Spring with redis 

Basic samples using both `@Cacheable` and `RedisTemplate`. 

To start redis server using docker: `docker run -it --rm redis redis-cli -h 127.0.0.1`

Accessing redis with redis-cli: `docker exec -it redis1 sh`

### Useful commands

- `redis-cli monitor`
- `redis-cli info stats`
- `redis-cli --latency -h 127.0.0.1 -p 6379`

