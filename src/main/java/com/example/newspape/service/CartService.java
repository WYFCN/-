package com.example.newspape.service;

import com.example.newspape.bean.Cart;

import java.util.List;

public interface CartService {

    public boolean InsertCartByBookId(Integer id,String username);

    public List<Cart> cartListByUserName(String username);

    public boolean delCartById(int id);

    public List<Cart> cartList(int[] ids);

    public void sub_Carts(List<Cart> cartList,int[] nn,String address,int id);
}
