package com.codegym.testmodule4.repository;

import com.codegym.testmodule4.model.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    List<KhachHang> findAll();
}