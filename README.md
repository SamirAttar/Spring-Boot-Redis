# Spring-Boot-Redis
Spring boot+Redis


# Spring-Boot-Redis


What is Redis?
Redis is an in memory datastructure that is used for faster access the data. If we need to access data in frequnty then we have to go with redis.

1.Redis is a fast, in-memory data storage system.
2.It works like a server program.
3.Redis is great for quickly storing and retrieving data.
4.It's not ideal for keeping data safe if the system crashes, as it stores data in memory.
5.Multiple Redis instances can be used to handle more data.
6.Redis is often used as a temporary storage (cache) to speed up access to frequently used data.


Implimenattion Steps:

1. Download Redies in local system.
2. Extract that redies file.
3. Open redies server.exe file. From this file we can take port number.(Redis-x64-3.2.100\redis-server.exe /path/to/redis.conf) 
4. Create spring boot project for implimentation.While craeting make sure your    adding redies dependency as 
   
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency> 

   Also add third party dependency as jedies. For connecting our application to jedis server as follow:

        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
            <version>4.4.7</version>
        </dependency>

    Selct dependency version With respect to time.


6. For redies configarion create seprat pakage as config. Where we are going to conncet redies connection factory.
7. In this pakage create class as RedisConfig and annotaed as @Configration and   @EnableRedisRepositories.
8. In that same class create one method as jediesConnectionFActory. Where we have to define our hostname and port number.Also annoted this method with @Bean annotation.
9. In oredr to acces redies server. We have to create rediseTemplate bean method and need configure it.


#Redis Cache

1.When we want quick response and dont want to hit database in that case we are using Cache. 
2.For we have apply @EnableCaching annoattion over the services that is best practice. If we are directly intracting to dao layer then we can apply @EnableCaching annoation over the controller class.
3.Most of time we are using cache for getting response. For that we have to add @Cacheable(key="#id", value = "Product") over the get method. Where we have to supply id and reponse entity name as argument.

