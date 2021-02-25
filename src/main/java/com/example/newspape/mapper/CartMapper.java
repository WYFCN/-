package com.example.newspape.mapper;

import com.example.newspape.bean.Cart;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CartMapper {

    @Insert("insert into cart(cart_name,press,date,price,amount,num,url,username) values(#{cart_name},#{press},#{date},#{price},#{amount},#{num},#{url},#{username})")
    public boolean insertCart(Cart cart);

    @Select("select * from cart where username = #{username}")
    public List<Cart> cartListByUserName(String username);

    @Delete("delete from cart where id = #{id}")
    public boolean delCartById(int id);
    @Select( " <script>" +
            " select *" +
            " from cart where id in "+
            " <foreach collection='ids' open='(' item='id' separator=',' close=')'> #{id}</foreach> "+
            " </script>" )
    public List<Cart> cartList(@Param("ids") int[] ids);
}
