package com.lt.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.lt.anno.Cache_Find;
import com.lt.enu.KEY_ENUM;
import com.lt.redisUtil.ObjectMapperUtil;
import redis.clients.jedis.JedisCluster;
//不懂有时间加强
@Component //交给spring容器管理
@Aspect //标识切面
public class RedisAspect {
	//只有用户使用时才会初始化
	//利用AOP操作Redis集群
	@Autowired(required = false)
	private JedisCluster jedis;
	//@Autowired(required = false)
	//private RedisService jedis;//利用AOP利用工具API操作哨兵机制
	//private ShardedJedis jedis;//利用Aop操作分片(多台redis)
	//private Jedis jedis;//利用AOP操作单台Redis
	//使用该方法可以直接获取注解的对象
	@SuppressWarnings("unchecked")
	@Around("@annotation(cache_find)")//切入点表达式
	public Object around(ProceedingJoinPoint joinPoint,Cache_Find cache_find) {
		//获取key的值
		String key = getkey(joinPoint,cache_find);
		String result = jedis.get(key);
		Object data = null;
		try {
			//判断缓存是否为空
			if (StringUtils.isEmpty(result)) {
				data=joinPoint.proceed();//如果为空，就去执行我的业务代码
				String string = ObjectMapperUtil.json(data);
				if (cache_find.secoondes()==0) {
					jedis.set(key, string);
				}
				else {
					jedis.setex(key, cache_find.secoondes(), string);
				}
			}else {
				//如果缓存中有数据就把json数据转换成对象
				Class targetClass = getClass(joinPoint);
				data=ObjectMapperUtil.object(result, targetClass);
			}
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return data;
	}
	private Class getClass(ProceedingJoinPoint joinPoint) {
		MethodSignature signature=(MethodSignature) joinPoint.getSignature();
		return signature.getReturnType();
	}
	private String getkey(ProceedingJoinPoint joinPoint, Cache_Find cache_find) {
		//获取key类型
		KEY_ENUM key_ENUM=cache_find.keyType();
		//判断key的类型
		if (key_ENUM.equals(KEY_ENUM.EMPTY)) {
			return cache_find.key(); //表示使用用户自己的key
		}
		String strargs = String.valueOf(joinPoint.getArgs()[0]);
		String key=cache_find.key()+"_"+strargs;
		return key;
	}
}
