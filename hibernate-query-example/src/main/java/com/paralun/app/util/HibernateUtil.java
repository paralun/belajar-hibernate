/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.util;

import com.paralun.app.dao.KaryawanDao;
import com.paralun.app.dao.KaryawanDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
    
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static KaryawanDao karyawanDao;

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            ServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            return configuration.buildSessionFactory(registry);
        }catch(Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static KaryawanDao getKaryawanDao() {
        if(karyawanDao == null) {
            karyawanDao = new KaryawanDaoImpl(sessionFactory);
        }
        return karyawanDao;
    }
}
