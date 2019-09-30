/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package leeue.mapper;

import leeue.entity.Users;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年7月30日 下午2:11:28<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@CacheConfig(cacheNames = "userCache")  //这个注解表示配置缓存的信息 cacheName 表示缓存的名称
public interface UserMapper {
	@Select("SELECT ID ,NAME,AGE FROM users where id=#{id}")
	@Cacheable  //加上这个注解表示 这个方法是有缓存的，不加是不走缓存的，直接去查数据库
	List<Users> getUsers(@Param("id") Long id);


	@Select("SELECT ID ,NAME,AGE FROM users where id=#{id}")
	@Cacheable  //加上这个注解表示 这个方法是有缓存的，不加是不走缓存的，直接去查数据库
	Users getUser(@Param("id") Long id);

	/**
	 @CacheConfig : 配置缓存基本信息 cacheNames表示缓存名称
	 @Cacheable: 该方法查询数据库完毕之后存入到缓存中
	 */


}
