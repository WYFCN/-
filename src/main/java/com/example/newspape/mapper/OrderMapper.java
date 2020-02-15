package com.example.newspape.mapper;

import com.example.newspape.bean.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {
    @Select("select * from `order` where userId=#{id}")
    public List<Order> getOrders(Integer id);
    @Select("select * from `order` where num=#{num}")
    public List<Order> getOrders02(String num);
    @Insert("insert into `order`(num,name,amount,address,userId) values(#{num},#{name},#{amount},#{address},#{userId})")
    public void addOrder(Order order);
    @Update("update `order` set state=#{s} where num=#{num}")
    public void updateOrder(String num,String s);
    @Select("select * from `order`")
    public List<Order> getAllOrders();
    @Select("select `user`.`name` from `user`,`order` where `order`.userId=`user`.id")
    public List<String> getNameByOr_User();
    @Delete("delete from `order` where id=#{id}")
    public void deleteById(Integer id);
    @Select("select * from `order` where id=#{id}")
    public Order getOrderById(Integer id);
    @Select("select name from user where id=#{userId}")
    public String getName(Integer userId);
    @Update("update `order` set amount=#{amount},address=#{address} where id=#{id}")
    public void updateOrder02(Order order);
    @Update("update `order` set state='成功发货' where id=#{id}")
    public void updateOrder03(Integer id);
    @Update("update `order` set state='货物到达' where id=#{id}")
    public void updateOrder04(Integer id);
    @Update("update `order` set state='收货成功' where id=#{id}")
    public void updateOrder05(Integer id);
    @Update("update `order` set state='收货成功' where num=#{num}")
    public void updateOrder06(String num);
    @Update("update `order` set state=#{s} where id=#{id}")
    public void update_p(String s,Integer id);
}
