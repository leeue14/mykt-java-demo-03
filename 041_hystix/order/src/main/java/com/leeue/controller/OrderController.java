/**
 * 功能说明:
 * 功能作者:
 * 创建日期:
 * 版权归属:每特教育|蚂蚁课堂所有 www.itmayiedu.com
 */
package com.leeue.controller;

import com.alibaba.fastjson.JSONObject;
import com.leeue.hystrix.OrderHysrtrixCommand;
import com.leeue.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/orderIndex")
	public Object orderIndex() throws InterruptedException {
		JSONObject member = memberService.getMember();
		System.out.println("当前线程名称:" + Thread.currentThread().getName() + ",订单服务调用会员服务:member:" + member);
		return member;
	}

	/**
	 * 使用分割线程池 来实现服务的隔离
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping("/orderIndexHystrix")
	public Object orderIndexHystrix() throws InterruptedException {
		return new OrderHysrtrixCommand(memberService).execute();
	}
/*
	@RequestMapping("/orderIndexHystrix2")
	public Object orderIndexHystrix2() throws InterruptedException {
		return new OrderHystrixCommand2(memberService).execute();
	}*/

	@RequestMapping("/findOrderIndex")
	public Object findIndex() {
		System.out.println("当前线程:" + Thread.currentThread().getName() + ",findOrderIndex");
		return "findOrderIndex";
	}
}
