/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.leeue.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

/**
 * 功能说明: <br>
 * 创建作者:每特教育-余胜军<br>
 * 创建时间:2018年8月4日 下午3:31:42<br>
 * 教育机构:每特教育|蚂蚁课堂<br>
 * 版权说明:上海每特教育科技有限公司版权所有<br>
 * 官方网站:www.itmayiedu.com|www.meitedu.com<br>
 * 联系方式:qq644064779<br>
 * 注意:本内容有每特教育学员共同研发,请尊重原创版权
 */
@Component
public class EhCacheUtils {

	// @Autowired
	// private CacheManager cacheManager;
	@Autowired
	private EhCacheCacheManager ehCacheCacheManager;

	// cacheName 和 key 区别 就是 redis中的db库 组
	// 添加本地缓存 (相同的key 会直接覆盖)
	public void put(String cacheName, String key, Object value) {
		Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	// 获取本地缓存
	public Object get(String cacheName, String key) {
		Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	public void remove(String cacheName, String key) {
		Cache cache = ehCacheCacheManager.getCacheManager().getCache(cacheName);
		cache.remove(key);
	}

}
