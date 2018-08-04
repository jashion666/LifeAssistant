package com.assistant.jedis;

import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis的接口.
 *
 * @author 会写代码的厨师.
 *         * @date : 2018-08-04
 */
public interface JedisClient {

    /**
     * hash
     * 通过key给field设置指定的值,如果key不存在,则先创建 ,存在会覆盖原来的值
     *
     * @param key   key
     * @param field field 字段
     * @param value value
     * @return result  如果不存在，新建的返回1，存在返回0, 异常返回null
     */
    Long hset(String key, String field, String value);

    /**
     * Hash
     * 为哈希表 key 中的域 field 的值加上增量 value
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return result
     */
    Long hincrBy(String key, String field, long value);

    /**
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在，操作无效
     *
     * @param key   key
     * @param field field
     * @param value value
     * @return result  不存在新建返回1，存在返回0
     */
    Long hsetnx(String key, String field, String value);

    /**
     * 通过key同时设置 hash的多个field
     *
     * @param key  key
     * @param hash hash
     * @return result  返回OK 异常返回null
     */
    String hmset(String key, Map<String, String> hash);

    /**
     * 通过key 和 field 获取指定的 value
     *
     * @param key   key
     * @param field field
     * @return result  没有返回null
     */
    String hget(String key, String field);

    /**
     * 通过key 和 fields 获取指定的value 如果没有对应的value则返回null
     *
     * @param key    key
     * @param fields 可以使 一个String 也可以是 String数组
     * @return result
     */
    List<String> hmget(String key, String... fields);

    /**
     * 通过key获取所有的field和value
     *
     * @param key key
     * @return result
     */
    Map<String, String> hgetAll(String key);

    /**
     * 通过key删除field的value
     *
     * @param key key
     * @return result
     */
    Long hdel(String key, String field);

    /**
     * 返回key为键中存放的field值的个数
     *
     * @param key key
     * @return result
     */
    Long hlen(String key);

    /**
     * 查看key是否存在指定的field
     *
     * @param key key
     * @return result
     */
    Boolean hexists(String key, String field);

    /**
     * 返回key存储的map对象中的所有key
     *
     * @param key key
     * @return result
     */
    Set<String> hkeys(String key);

    /**
     * 返回key存储的map对象中的所有键的values值
     *
     * @param key key
     * @return result
     */
    List<String> hvals(String key);

    /**
     * 判断key是否存在
     *
     * @param key key
     * @return result  true OR false
     */
    boolean exists(String key);

    /**
     * 删除指定的key,也可以传入一个包含key的数组
     *
     * @param keys keys
     * @return result  返回删除成功的个数
     */
    Long del(String... keys);

    /**
     * 对key的对应value值排序
     *
     * @return result
     */
    List<String> sort(String key);

    /**
     * 将当前数据库的 ke移动到给定的数据库 db 当中
     *
     * @return result
     */
    Long move(String key, int dbIndex);

    /**
     * 返回某个key元素的数据类型 ( none:不存在,string:字符,list,set,zset,hash);
     *
     * @param key key
     * @return result
     */
    String type(String key);

    /**
     * 返回当前数据库的key的总数
     *
     * @return result
     */
    Long dbsize();

    /**
     * 设置某个key的过期时间(秒),(EXPIRE bruce 1000：设置bruce这个key1000秒后系统自动删除);注意：如果在还没有过期的时候，对值进行了改变，那么那个值会被清除。
     *
     * @return result
     */
    Long expire(String key, int seconds);

    /**
     * EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置生存时间。
     * 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp);。
     *
     * @return result
     */
    Long expireAt(String key, Long unixTime);

    /**
     * List
     * 通过key在list头部添加值
     *
     * @param key   key
     * @param value value
     * @return result  在 push 操作后的 list 长度。
     */
    Long lpush(String key, String value);

    /**
     * List
     * 向存于 key 的列表的尾部插入所有指定的值。如果 key 不存在，那么会创建一个空的列表然后再进行 push 操作。
     * 当 key 保存的不是一个列表，那么会返回一个错误。
     *
     * @param key   key
     * @param value value
     * @return result  在 push 操作后的列表长度
     */
    Long rpush(String key, String value);

    /**
     * List
     * 获取list的长度
     *
     * @param key key
     * @return result
     */
    Long llen(String key);

    /**
     * List
     * 返回存储在 key 的列表里指定范围内的元素
     *
     * @param key   key
     * @param start start 开始位置
     * @param end   end   结束位置 -1表示最后一个
     * @return result
     */
    List<String> lrange(String key, long start, long end);

    /**
     * List
     * 截取(trim);一个已存在的 list，这样 list 就会只包含指定范围的指定元素
     *
     * @param key   key
     * @param end   end   结束位置 -1表示最后一个
     * @param start start 开始位置
     * @return result
     */
    String ltrim(String key, long start, long end);

    /**
     * List
     * 通过key在list头部添加值
     * 只有当 key 已经存在并且存着一个 list 的时候，在这个 key 下面的 list 的头部插入 value。 与 LPUSH 相反，当 key 不存在的时候不会进行任何操作。
     *
     * @param key   key
     * @param value value
     * @return result  在 push 操作后的 list 长度。
     */
    Long lpushx(String key, String value);

    /**
     * rpushx
     *
     * @param key   key
     * @param value value
     * @return result
     */
    Long rpushx(String key, String value);

    /**
     * 弹出 List 的第一个元素
     *
     * @param key key
     * @return result
     */
    String lpop(String key);

    /**
     * rpop
     *
     * @param key key
     * @return result
     */
    String rpop(String key);

    /**
     * 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     */
    Long lrem(String key, long count, String value);

    /**
     * 设置 index 位置的list元素的值为 value
     *
     * @param key   key
     * @param index index
     * @param value value
     * @return result
     */
    String lset(String key, long index, String value);

    /**
     * 返回列表 key 中，下标为 index 的元素。
     *
     * @param key   key
     * @param index index
     * @return result
     */
    String lindex(String key, long index);

    /**
     * 命令 RPOPLPUSH 在一个原子时间内，执行以下两个动作：
     * 将列表 source 中的最后一个元素(尾元素);弹出，并返回给客户端。
     * 将 source 弹出的元素插入到列表 destination ，作为 destination 列表的的头元素。
     * 举个例子，你有两个列表 source 和 destination ， source 列表有元素 a, b, c ， destination 列表有元素 x, y, z ，
     * 执行 RPOPLPUSH source destination 之后， source 列表包含元素 a, b ， destination 列表包含元素 c, x, y, z ，
     * 并且元素 c 会被返回给客户端。
     *
     * @param srcKey srcKey
     * @param dstKey dstKey
     * @return result
     */
    String rpoplpush(String srcKey, String dstKey);

    /**
     * BRPOPLPUSH 是 RPOPLPUSH 的阻塞版本，当给定列表 source 不为空时， BRPOPLPUSH 的表现和 RPOPLPUSH 一样。
     * 当列表 source 为空时， BRPOPLPUSH 命令将阻塞连接，直到等待超时，或有另一个客户端对 source 执行 LPUSH 或 RPUSH 命令为止。
     * 超时参数 timeout 接受一个以秒为单位的数字作为值。超时参数设为 0 表示阻塞时间可以无限期延长(block indefinitely); 。
     *
     * @param source      source
     * @param destination destination
     * @param timeout     timeout
     * @return result
     */
    String brpoplpush(String source, String destination, int timeout);

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略(set不重复);。
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     *
     * @param key    key
     * @param member member
     * @return result
     */
    Long sadd(String key, String member);

    /**
     * 移除集合 key 中的一个 member 元素，不存在的 member 元素会被忽略。
     *
     * @param key    key
     * @param member member
     * @return result
     */
    Long srem(String key, String member);

    /**
     * 返回集合 key 中的所有成员。
     *
     * @param key key
     * @return result
     */
    Set<String> smembers(String key);

    /**
     * 判断 member 元素是否集合 key 的成员。
     *
     * @param key    key
     * @param member member
     * @return result
     */
    Boolean sismember(String key, String member);

    /**
     * 返回集合 key集合中元素的数量);。
     *
     * @param key key
     * @return result
     */
    Long scard(String key);

    /**
     * 将 member 元素从 source 集合移动到 destination 集合。
     * SMOVE 是原子性操作。
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
     * 当 source 或 destination 不是集合类型时，返回一个错误。
     *
     * @param srckey srckey
     * @param dstkey dstkey
     * @param member member
     * @return result
     */
    Long smove(String srckey, String dstkey, String member);

    /**
     * 移除并返回集合中的一个随机元素。
     *
     * @param key key
     * @return result
     */
    String spop(String key);

    /**
     * 返回集合中的一个随机元素
     *
     * @param key key
     * @return result
     */
    String srandmember(String key);

    /**
     * 返回给定集合的交集。
     *
     * @param keys keys
     * @return result
     */
    Set<String> sinter(String... keys);

    /**
     * 类似于 SINTER 命令，但它将结果保存到 destination 集合，而不是简单地返回结果集
     *
     * @param dstkey dstkey
     * @param keys   keys
     * @return result
     */
    Long sinterstore(String dstkey, String... keys);

    /**
     * 返回所有给定集合的并集
     *
     * @param keys keys
     * @return result
     */
    Set<String> sunion(String... keys);

    /**
     * sunionstore
     *
     * @param dstkey dstkey
     * @param keys   keys
     * @return result
     */
    Long sunionstore(String dstkey, String... keys);

    /**
     * 返回所有给定集合之间的差集。
     *
     * @param keys keys
     * @return result
     */

    Set<String> sdiff(String... keys);

    /**
     * sdiffstore
     *
     * @param dstkey dstkey
     * @param keys   keys
     * @return result
     */
    Long sdiffstore(String dstkey, String... keys);

    /**
     * server
     * 清空整个 Redis 服务器的数据(删除所有数据库的所有 key );。
     *
     * @return result
     */
    String flushAll();

    /**
     * 清空当前数据库中的所有 key。
     *
     * @return result
     */
    String flushDB();

    /**
     * 停止所有客户端
     * 如果有至少一个保存点在等待，执行 SAVE 命令
     * 如果 AOF 选项被打开，更新 AOF 文件
     * 关闭 redis 服务器(server);
     *
     * @return result
     */
    String shutdown();

    /**
     * sorted set
     * 将一个 member 元素及其 score值加入到有序集 key 当中。
     * 如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，
     * 并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。
     *
     * @param key    key
     * @param score  score
     * @param member member
     * @return result
     */
    Long zadd(String key, double score, String member);

    /**
     * sorted set
     * 移除有序集 key 中的一成员member，不存在的成员将被忽略。
     *
     * @param key    key
     * @param member member
     * @return result
     */
    Long zrem(String key, String member);

    /**
     * sorted set
     * 返回集合 key集合中元素的数量
     *
     * @param key key
     * @return result
     */
    Long zcard(String key);

    /**
     * 返回有序集 key 中， score 值在 min 和 max 之间(默认包括 score 值等于 min 或 max );的成员的数量。
     *
     * @param key key
     * @param min min
     * @param max max
     * @return result
     */
    Long zcount(String key, double min, double max);

    /**
     * 返回有序集 key 中，成员 member 的 score 值。
     *
     * @param key    key
     * @param member member
     * @return result
     */
    Double zscore(String key, String member);

    /**
     * 为有序集 key 的成员 member 的 score 值加上增量"score"
     *
     * @param key    key
     * @param score  score
     * @param member member
     * @return result
     */
    Double zincrby(String key, double score, String member);

    /**
     * 返回有序集 key 中，指定区间内的成员。其中成员的位置按 score 值递增(从小到大);来排序。
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return result
     */
    Set<String> zrange(String key, int start, int end);

    /**
     * 其中成员的位置按 score 值递减(从大到小);来排列。
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return result
     */
    Set<String> zrevrange(String key, int start, int end);

    /**
     * 返回有序集 key 中， score 值介于 max 和 min 之间(默认包括等于 max 或 min );的所有的成员。
     * 有序集成员按 score 值递减(从大到小);的次序排列。
     *
     * @param key    key
     * @param max    max
     * @param min    min
     * @param offset offset
     * @param count  count
     * @return result
     */
    Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count);

    /**
     * zrevrangeByScore
     *
     * @param key key
     * @param max key
     * @param min key
     * @return result
     */
    Set<String> zrevrangeByScore(String key, double max, double min);

    /**
     * 返回有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max );的成员。
     * 有序集成员按 score 值递增(从小到大);次序排列。
     *
     * @param key key
     * @param min min
     * @param max max
     * @return result
     */
    Set<String> zrangeByScore(String key, double min, double max);

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递增(从小到大);顺序排列。
     * 排名以 0 为底，也就是说， score 值最小的成员排名为 0 。
     */
    Long zrank(String key, String member);

    /**
     * 返回有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减(从大到小);排序。
     * 排名以 0 为底，也就是说， score 值最大的成员排名为 0 。
     *
     * @param key    key
     * @param member member
     * @return result
     */
    Long zrevrank(String key, String member);

    /**
     * 移除有序集 key 中，指定排名(rank);区间内的所有成员。
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return result
     */
    Long zremrangeByRank(String key, int start, int end);

    /**
     * 移除有序集 key 中，所有 score 值介于 min 和 max 之间(包括等于 min 或 max );的成员。
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return result
     */
    Long zremrangeByScore(String key, double start, double end);

    /**
     * 计算给定的一个或多个有序集的交集，并将该交集(结果集);储存到 destination 。
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之和.
     *
     * @param dstkey dstkey
     * @param sets   sets
     * @return result
     */
    Long zinterstore(String dstkey, String... sets);

    /**
     * 计算给定的一个或多个有序集的并集，并将该并集(结果集);储存到 destination 。
     * 默认情况下，结果集中某个成员的 score 值是所有给定集下该成员 score 值之 和 。
     *
     * @param dstkey dstkey
     * @param sets   sets
     * @return result
     */
    Long zunionstore(String dstkey, String... sets);

    /**
     * String
     * 通过key获取储存在redis中的value
     * 并释放连接
     *
     * @param key key
     * @return result  成功返回value 失败返回null
     */
    String get(String key);

    /**
     * string
     * 向redis存入key和value,并释放连接资源
     * 如果key已经存在 则覆盖
     *
     * @param key   key
     * @param value value
     * @return result  成功 返回OK 失败返回 0
     */
    String set(String key, String value);

    /**
     * <p>
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     *
     * @param key   key
     * @param value value
     * @return result  成功返回1 如果存在 和 发生异常 返回 0
     */
    Long setnx(String key, String value);

    /**
     * string
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value);。
     *
     * @param key   key
     * @param value value
     * @return result
     */
    String getSet(String key, String value);

    /**
     * 返回所有(一个或多个);给定 key 的值。
     * 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 nil 。因此，该命令永不失败。
     *
     * @param keys keys
     * @return result
     */
    List<String> mget(String[] keys);

    /**
     * 同时设置一个或多个 key-value 对。
     * 有会覆盖
     *
     * @param keysvalues keysvalues
     */
    void mset(String... keysvalues);

    /**
     * key不存在时才插入
     *
     * @param keysvalues keysvalues
     */
    void msetnx(String... keysvalues);

    /**
     * 将 key 所储存的值加上增量 increment 。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     *
     * @param key     key
     * @param integer integer
     * @return result
     */
    Long incrBy(String key, Integer integer);

    /**
     * 返回 key 所储存的字符串值的长度。
     *
     * @param key key
     * @return result
     */
    Long strlen(String key);

    /**
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
     *
     * @param key key
     * @return result  加值后的结果
     */
    Long incr(String key);

    /**
     * 对key的值做减减操作,如果key不存在,则设置key为-1
     *
     * @param key key
     * @return result
     */
    Long decr(String key);

    /**
     * 减去指定的值
     *
     * @param key     key
     * @param integer integer
     * @return result
     */
    Long decrBy(String key, Integer integer);

    /**
     * 通过key向指定的value值追加值
     *
     * @param key key
     * @param str str
     * @return result  成功返回 添加后value的长度 失败 返回 添加的 value 的长度 异常返回0L
     */
    Long append(String key, String str);

    /**
     * 截取字符
     *
     * @param key   key
     * @param start start
     * @param end   end
     * @return result
     */
    String subStr(String key, int start, int end);

    /**
     * 设置key value并制定这个键值的有效期
     *
     * @param key     key
     * @param value   value
     * @param seconds 单位:秒
     * @return result  成功返回OK 失败和异常返回null
     */
    String setex(String key, String value, int seconds);

    /**
     * 用 value 参数覆写(overwrite);给定 key 所储存的字符串值，从偏移量 offset 开始。
     *
     * @param key    key
     * @param offset offset
     * @param value  value
     * @return result
     */
    Long setRange(String key, long offset, String value);

    /**
     * 获取range
     *
     * @param key         key
     * @param startOffset startOffset
     * @param endOffset   endOffset
     * @return result
     */
    String getRange(String key, long startOffset, long endOffset);

    /**
     * 查找所有符合给定模式 pattern 的 key 。
     *
     * @param key key
     * @return result
     */
    Set<String> keys(String key);

    /**
     * 排序
     *
     * @param params params
     * @return result  List
     */
    List<String> sort(String key, SortingParams params);

    /**
     * 检测给定key的剩余生存时间，单位秒
     *
     * @param key key
     * @return result  returns -2 if the key does not exist.returns -1 if the key exists but has no associated expire.
     */
    long ttl(String key);

    /**
     * 检测给定key的剩余生存时间，单位毫秒
     *
     * @param key key
     * @return result  returns -2 if the key does not exist.returns -1 if the key exists but has no associated expire.
     */
    long pttl(String key);


}
