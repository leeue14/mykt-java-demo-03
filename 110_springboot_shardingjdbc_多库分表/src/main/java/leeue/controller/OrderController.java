package leeue.controller;

import leeue.entity.OrderEntity;
import leeue.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    // 查询所有的订单信息
    @RequestMapping("/getOrderAll")
    public List<OrderEntity> getOrderAll() {
        return (List<OrderEntity>) orderRepository.findAll();
    }

    @RequestMapping("/findIdByOrder")
    public Optional<OrderEntity> findIdByOrder(Long id) {
        return orderRepository.findById(id);
    }

    @RequestMapping("/findIdByUserId")
    public List<OrderEntity> findIdByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    // 使用in条件查询
    @RequestMapping("/inOrder")
    public List<OrderEntity> inOrder() {
        List<String> ids = new ArrayList<>();
        ids.add("2");
        ids.add("3");
        ids.add("4");
        ids.add("5");
        return orderRepository.findExpiredOrderState(ids);

    }

    // 增加
    @RequestMapping("/inserOrder")
    public String inserOrder(OrderEntity orderEntity) {
        for (int i = 0; i < 10; i++) {

            OrderEntity order = new OrderEntity();
            order.setOrderId((long) i);

            //根据user_id进行分片存放
            order.setUserId((long) i);

            orderRepository.save(order);
        }
        return "success";
    }

}
