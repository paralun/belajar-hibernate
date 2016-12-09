/*
 * Copyright (c) 2016 | James Kusmambang
 * Source : https://github.com/paralun
 */
package com.paralun.app.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "t_karyawan")
public class Karyawan implements Serializable{
    
    @Id @GeneratedValue
    private long id;
    @Column(name = "nama_karyawan", nullable = false, length = 255)
    private String namaKaryawan;
    @Column(name = "alamat", nullable = true, length = 255)
    private String alamat;
    @Column(name = "umur", nullable = true, length = 3)
    private int umur;
    @Column(name = "gaji")
    private BigDecimal gaji;
    @Temporal(TemporalType.DATE)
    @Column(name = "gabung")
    private Date gabung;

    public Karyawan() {
    }

    public Karyawan(String namaKaryawan, String alamat, int umur, BigDecimal gaji, Date gabung) {
        this.namaKaryawan = namaKaryawan;
        this.alamat = alamat;
        this.umur = umur;
        this.gaji = gaji;
        this.gabung = gabung;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaKaryawan() {
        return namaKaryawan;
    }

    public void setNamaKaryawan(String namaKaryawan) {
        this.namaKaryawan = namaKaryawan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public BigDecimal getGaji() {
        return gaji;
    }

    public void setGaji(BigDecimal gaji) {
        this.gaji = gaji;
    }

    public Date getGabung() {
        return gabung;
    }

    public void setGabung(Date gabung) {
        this.gabung = gabung;
    }
}
