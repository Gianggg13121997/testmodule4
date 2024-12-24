package com.codegym.testmodule4.repository;

import com.codegym.testmodule4.model.GiaoDich;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiaoDichRepository extends JpaRepository<GiaoDich, Long> {
    List<GiaoDich> findByKhachHang_TenKhachHang(String tenKhachHang);
}