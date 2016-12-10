/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.dao;

import com.paralun.app.model.Karyawan;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class KaryawanDaoCriteriaImpl implements KaryawanDao {
    
    private final SessionFactory factory;

    public KaryawanDaoCriteriaImpl(SessionFactory factory) {
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
        session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Karyawan.class)
                .add(Restrictions.eq("id", id));
        Karyawan karyawan = (Karyawan) criteria.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        return karyawan;
    }

    @Override
    public List<Karyawan> getKaryawans() {
        Session session = factory.openSession();
        
        Criteria criteria = session.createCriteria(Karyawan.class);
        List<Karyawan> list = criteria.list();
        
        session.close();
        return list;
    }

    @Override
    public List<Karyawan> getKaryawans(int first, int max) {
        Session session = factory.openSession();
        
        Criteria criteria = session.createCriteria(Karyawan.class)
                .setFirstResult(first)
                .setMaxResults(3);
        List<Karyawan> list = criteria.list();
        
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
