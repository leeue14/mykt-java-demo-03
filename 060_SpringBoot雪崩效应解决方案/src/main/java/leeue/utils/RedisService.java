/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package leeue.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月1日 下午4:07:32<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */

@Component
public class RedisService {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// public void set(String key, Object object, Long time) {
	// stringRedisTemplate.opsForValue();
	// // 存放String 类型
	// if (object instanceof String) {
	// setString(key, object);
	// }
	// // 存放 set类型
	// if (object instanceof Set) {
	// setSet(key, object);
	// }
	// // 设置有效期 以秒为单位
	// stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
	// }
	//
	public void setString(String key, Object object) {
		// 开启事务权限
		// stringRedisTemplate.setEnableTransactionSupport(true);
		try {
			// 开启事务 begin
			// stringRedisTemplate.multi();
			String value = (String) object;
			stringRedisTemplate.opsForValue().set(key, value);
			System.out.println("存入完毕,马上开始提交redis事务");
			// 提交事务
			// stringRedisTemplate.exec();
		} catch (Exception e) {
			// 需要回滚事务
			// stringRedisTemplate.discard();
		}
	}

	public void setSet(String key, Object object) {
		Set<String> value = (Set<String>) object;
		for (String oj : value) {
			stringRedisTemplate.opsForSet().add(key, oj);
		}
	}

	public String getString(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

}
