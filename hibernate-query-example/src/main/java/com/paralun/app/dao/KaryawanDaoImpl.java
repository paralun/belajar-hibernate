/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.dao;

import com.paralun.app.model.Karyawan;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class KaryawanDaoImpl implements KaryawanDao {
    
    private final SessionFactory factory;

    public KaryawanDaoImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void insert(Karyawan karyawan) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(karyawan);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Karyawan karyawan) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(karyawan);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Karyawan karyawan) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(karyawan);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Karyawan getKaryawan(long id) {
        Session session = factory.openSession();
        Karyawan karyawan = (Karyawan) session.get(Karyawan.class, id);
        session.close();
        return karyawan;
    }

    @Override
    public List<Karyawan> getKaryawans() {
        Session session = factory.openSession();
        List<Karyawan> list = session.createQuery("from Karyawan").list();
        session.close();
        return list;
    }

    @Override
    public List<Karyawan> getKaryawans(int first, int max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getMaxGaji() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Long getMinGaji() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getTotal() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Double getRataRata() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
