/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app;

import com.paralun.app.dao.KaryawanDao;
import com.paralun.app.model.Karyawan;
import com.paralun.app.util.HibernateUtil;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MainApp {
    
    public static void main(String[] args) {
        
        KaryawanDao dao = HibernateUtil.getKaryawanDaoQuery();
        
//        Karyawan karyawan1 = new Karyawan("Agus", "Jakarta", 20, new BigDecimal("12000"), new Date());
//        Karyawan karyawan2 = new Karyawan("Bambang", "Bandung", 24, new BigDecimal("7000"), new Date());
//        Karyawan karyawan3 = new Karyawan("Indah", "Bekasi", 27, new BigDecimal("15000"), new Date());
//        Karyawan karyawan4 = new Karyawan("Silvi", "Banjar", 26, new BigDecimal("12500"), new Date());
//        
//        dao.insert(karyawan1);
//        dao.insert(karyawan2);
//        dao.insert(karyawan3);
//        dao.insert(karyawan4);
//        
//        List<Karyawan> list = dao.getKaryawans(0, 2);
//        for(Karyawan k : list) {
//            System.out.println("Nama Karyawan : " + k.getNamaKaryawan());
//        }
//        
//        System.out.println("Max Gaji : " + dao.getMaxGaji());
//        System.out.println("Min Gaji : " + dao.getMinGaji());
//        System.out.println("Rata - Rata Gaji : " + dao.getRataRata());
//        System.out.println("Jumlah Record : " + dao.getTotal());

        Karyawan k = dao.getKaryawan(13);
        System.out.println("Nama Karyawan : " + k.getNamaKaryawan());
        
        k.setNamaKaryawan("Zulham");
        k.setAlamat("Cidaun");
        k.setUmur(30);
        k.setGaji(new BigDecimal("2500"));
        k.setGabung(new Date());
        
        dao.update(k);
        
        //dao.delete(k);
    }
}
