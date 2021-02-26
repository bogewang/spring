# springRedis
        Spring-data-redis是spring大家族的一部分，提供了在srping应用中通过简单的配置访问redis服务，对reids底层开发包(Jedis,  
        JRedis, and RJC)进行了高度封装，RedisTemplate提供了redis各种操作、异常处理及序列化，支持发布订阅，并对spring 3.1 cache进行了实现。
        官网：http://projects.spring.io/spring-data-redis/
        项目地址：https://github.com/spring-projects/spring-data-redis
        一、spring-data-redis功能介绍
        jedis客户端在编程实施方面存在如下不足:
        1)connection管理缺乏自动化，connection-pool的设计缺少必要的容器支持。
        2)数据操作需要关注“序列化”/“反序列化”，因为jedis的客户端API接受的数据类型为string和byte，对结构化数据(json
        ,xml,pojo等)操作需要额外的支持。
        3)事务操作纯粹为硬编码。
        4)pub/sub功能，缺乏必要的设计模式支持，对于开发者而言需要关注的太多。
        spring-data-redis针对jedis提供了如下功能：
        1.连接池自动管理，提供了一个高度封装的“RedisTemplate”类
        2.针对jedis客户端中大量api进行了归类封装,将同一类型操作封装为operation接口
        ValueOperations：简单K-V操作
        SetOperations：set类型数据操作
        ZSetOperations：zset类型数据操作
        HashOperations：针对map类型的数据操作
        ListOperations：针对list类型的数据操作
        3.提供了对key的“bound”(绑定)便捷化操作API，可以通过bound封装指定的key，然后进行一系列的操作而无须“显式”的再次指
        定Key，即BoundKeyOperations：
        BoundValueOperations
        BoundSetOperations
        BoundListOperations
        BoundSetOperations
        BoundHashOperations
        4.将事务操作封装，有容器控制。
        5.针对数据的“序列化/反序列化”，提供了多种可选择策略(RedisSerializer)
        JdkSerializationRedisSerializer：POJO对象的存取场景，使用JDK本身序列化机制，将pojo类通过ObjectInputStream/ObjectOutputStream
        进行序列化操作，最终redis-server中将存储字节序列。是目前最常用的序列化策略。
        StringRedisSerializer：Key或者value为字符串的场景，根据指定的charset对数据的字节序列编码成string，是“new String(bytes, charset)”
        和“string.getBytes(charset)”的直接封装。是最轻量级和高效的策略。
        JacksonJsonRedisSerializer：jackson-json工具提供了javabean与json之间的转换能力，可以将pojo实例序列化成json格式存储在redis中，
        也可以将json格式的数据转换成pojo实例。因为jackson工具在序列化和反序列化时，需要明确指定Class类型，因此此策略封装起来稍微复杂。
        【需要jackson-mapper-asl工具支持】
        OxmSerializer：提供了将javabean与xml之间的转换能力，目前可用的三方支持包括jaxb，apache-xmlbeans；redis存储的数据将是xml工具。
        不过使用此策略，编程将会有些难度，而且效率最低；不建议使用。【需要spring-oxm模块的支持】
        针对“序列化和发序列化”中JdkSerializationRedisSerializer和StringRedisSerializer是最基础的策略，原则上，
        我们可以将数据存储为任何格式以便应用程序存取和解析(其中应用包括app，hadoop等其他工具)，不过在设计时仍然不推荐直接使用
        “JacksonJsonRedisSerializer”和“OxmSerializer”，因为无论是json还是xml，他们本身仍然是String。如果你的数据需要被第三方工具解析，
        那么数据应该使用StringRedisSerializer而不是JdkSerializationRedisSerializer。如果你的数据格式必须为json或者xml，那么在编程级别，
        在redisTemplate配置中仍然使用StringRedisSerializer，在存储之前或者读取之后，使用“SerializationUtils”工具转换转换成json或者xml，
        请参见下文实例。
        6.基于设计模式，和JMS开发思路，将pub/sub的API设计进行了封装，使开发更加便捷。
        7.spring-data-redis中，并没有对sharding提供良好的封装，如果你的架构是基于sharding，那么你需要自己去实现，这也是sdr和jedis相比，
        唯一缺少的特性。
        二、serializer策略
        spring-data-redis提供了多种serializer策略，这对使用jedis的开发者而言，实在是非常便捷。sdr提供了4种内置的serializer：
        JdkSerializationRedisSerializer：使用JDK的序列化手段(serializable接口，ObjectInputStrean，ObjectOutputStream)，
        数据以字节流存储
        StringRedisSerializer：字符串编码，数据以string存储
        JacksonJsonRedisSerializer：json格式存储
        OxmSerializer：xml格式存储
        其中JdkSerializationRedisSerializer和StringRedisSerializer是最基础的序列化策略，其中“JacksonJsonRedisSerializer”与
        “OxmSerializer”都是基于stirng存储，因此它们是较为“高级”的序列化(最终还是使用string解析以及构建java对象)。
        RedisTemplate中需要声明4种serializer，默认为“JdkSerializationRedisSerializer”：
        1) keySerializer ：对于普通K-V操作时，key采取的序列化策略
        2) valueSerializer：value采取的序列化策略
        3) hashKeySerializer： 在hash数据结构中，hash-key的序列化策略
        4) hashValueSerializer：hash-value的序列化策略
        无论如何，建议key/hashKey采用StringRedisSerializer。