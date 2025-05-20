/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author hieph
 */
public interface DAOInterface<T> {
    public int them(T t);

    public int sua(T t);

    public int xoa(T t);

    public ArrayList<T> xuat();

    public T timkiem(T t);


}
