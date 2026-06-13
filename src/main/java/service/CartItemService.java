package service;

import database.CartItemDao;
import model.CartItem;

import java.util.List;

public class CartItemService {
    CartItemDao cartItemDao;



    public int insert(CartItem cartItem) {
        return cartItemDao.insert(cartItem);
    }

    public int update(CartItem cartItem) {
        return cartItemDao.update(cartItem);
    }

    public int delete(CartItem cartItem) {
        return cartItemDao.delete(cartItem);
    }

    public CartItem selectById(int id) {
        return cartItemDao.selectById(id);
    }

    }
