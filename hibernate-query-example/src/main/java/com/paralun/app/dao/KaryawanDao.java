/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.dao;

import com.paralun.app.model.Karyawan;
import java.util.List;

public interface KaryawanDao {
    
    public void insert(Karyawan karyawan);
    public void update(Karyawan karyawan);
    public void delete(Karyawan karyawan);
    public Karyawan getKaryawan(long id);
    public List<Karyawan> getKaryawans();
}
