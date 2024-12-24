package com.codegym.testmodule4.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "khach_hang")
@Data
public class KhachHang {
    @Id
    @Column(name = "ma_khach_hang")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhachHang;

    private String tenKhachHang;
    private String soDienThoai;
    private String email;
}