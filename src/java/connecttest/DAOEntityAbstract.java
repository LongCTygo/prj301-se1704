/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecttest;

import java.util.Vector;

/**
 *
 * @author LongCT_
 * @param <E>
 */
public abstract class DAOEntityAbstract<E> extends DBConnect{
    public abstract int add(E entity);
    public abstract int update(E entity);
    public abstract Vector<E> getAll(String sql);
    public int remove(String s){
        throw new UnsupportedOperationException("Does not implement.");
    }
    public int remove(String s1, String s2){
        throw new UnsupportedOperationException("Does not implement.");
    }
}
