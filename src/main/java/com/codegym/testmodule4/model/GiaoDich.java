package com.codegym.testmodule4.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "giao_dich")
@Data
public class GiaoDich {
    @Id
    @Column(name = "ma_giao_dich")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maGiaoDich;

    private String loaiDichVu;
    private double donGia;
    private double dienTich;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khach_hang")
    private KhachHang khachHang;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayGiaoDich;
}