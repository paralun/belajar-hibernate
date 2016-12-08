/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app;

import com.paralun.app.dao.KaryawanDao;
import com.paralun.app.model.Karyawan;
import com.paralun.app.util.HibernateUtil;
import java.util.Date;
import java.util.List;

public class MainApp {
    
    public static void main(String[] args) {
        KaryawanDao dao = HibernateUtil.getKaryawanDao();
        
        Karyawan karyawan1 = new Karyawan("Agus", "Jakarta", 20, new Date());
        Karyawan karyawan2 = new Karyawan("Bambang", "Bandung", 24, new Date());
        Karyawan karyawan3 = new Karyawan("Indah", "Bekasi", 27, new Date());
        Karyawan karyawan4 = new Karyawan("Silvi", "Banjar", 26, new Date());
        
        dao.insert(karyawan1);
        dao.insert(karyawan2);
        dao.insert(karyawan3);
        dao.insert(karyawan4);
        
        List<Karyawan> list = dao.getKaryawans();
        for(Karyawan k : list) {
            System.out.println("Nama Karyawan : " + k.getNamaKaryawan());
        }
    }
}
