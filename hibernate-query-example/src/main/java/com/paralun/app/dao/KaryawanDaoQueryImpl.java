/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.dao;

import com.paralun.app.model.Karyawan;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class KaryawanDaoQueryImpl implements KaryawanDao {
    
    private final SessionFactory factory;

    public KaryawanDaoQueryImpl(SessionFactory factory) {
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
        
        Query query = session.createQuery("update Karyawan set namaKaryawan = :nama,"
                + "alamat = :alamat, umur = :umur, gaji = :gaji, gabung = :gabung where id = :id");
        query.setString("nama", karyawan.getNamaKaryawan());
        query.setString("alamat", karyawan.getAlamat());
        query.setInteger("umur", karyawan.getUmur());
        query.setBigDecimal("gaji", karyawan.getGaji());
        query.setDate("gabung", karyawan.getGabung());
        query.setParameter("id", karyawan.getId());
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Karyawan karyawan) {
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("delete from Karyawan where id = :id");
        query.setLong("id", karyawan.getId());
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Karyawan getKaryawan(long id) {
        
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("from Karyawan where id = :id");
        query.setLong("id", id);
        
        Karyawan karyawan = (Karyawan) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return karyawan;
    }

    @Override
    public List<Karyawan> getKaryawans() {
        Session session = factory.openSession();
        Query query = session.createQuery("from Karyawan");
        List<Karyawan> list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<Karyawan> getKaryawans(int first, int max) {
        Session session = factory.openSession();
        Query query = session.createQuery("from Karyawan");
        query.setFirstResult(first);
        query.setMaxResults(max);
        List<Karyawan> list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long getMaxGaji() {
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("select max(gaji) from Karyawan");
        
        BigDecimal max = (BigDecimal) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return max.longValue();
    }

    @Override
    public Long getMinGaji() {
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("select min(gaji) from Karyawan");
        
        BigDecimal min = (BigDecimal) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return min.longValue();
    }

    @Override
    public int getTotal() {
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("select count(id) from Karyawan");
        
        Long count = (Long) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return count.intValue();
    }

    @Override
    public Double getRataRata() {
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("select avg(gaji) from Karyawan");
        
        Double rata = (Double) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return rata;
    }
    
}
