/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.dao;

import com.paralun.app.model.Karyawan;
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
        
        Query query = session.createQuery("insert into Karyawan("
                + "namaKaryawan, alamat, umur, gabung) values("
                + ":nama, :alamat, :umur, :gabung)");
        query.setString("nama", karyawan.getNamaKaryawan());
        query.setString("alamat", karyawan.getAlamat());
        query.setInteger("umur", karyawan.getUmur());
        query.setDate("gabung", karyawan.getGabung());
        query.executeUpdate();
        
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Karyawan karyawan) {
        Session session = factory.openSession();
        session.beginTransaction();
        
        Query query = session.createQuery("update Karyawan set namaKaryawan = :nama,"
                + "alamat = :alamat, umur = :umur, gabung = :gabung where id = :id");
        query.setString("nama", karyawan.getNamaKaryawan());
        query.setString("alamat", karyawan.getAlamat());
        query.setInteger("umur", karyawan.getUmur());
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
        
        return null;
    }

    @Override
    public List<Karyawan> getKaryawans() {
        Session session = factory.openSession();
        Query query = session.createQuery("from Karyawan");
        query.setFirstResult(1);
        query.setMaxResults(5);
        List<Karyawan> list = query.list();
        session.close();
        return list;
    }
    
}
