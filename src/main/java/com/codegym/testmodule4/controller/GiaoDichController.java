package com.codegym.testmodule4.controller;

import com.codegym.testmodule4.model.GiaoDich;
import com.codegym.testmodule4.model.KhachHang;
import com.codegym.testmodule4.repository.GiaoDichRepository;
import com.codegym.testmodule4.repository.KhachHangRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class GiaoDichController {

    @Autowired
    private GiaoDichRepository giaoDichRepository;

    @Autowired
    private KhachHangRepository khachHangRepository;

    @GetMapping("/giao-dich")
    public String getAllGiaoDich(Model model) {
        List<GiaoDich> giaoDichList = giaoDichRepository.findAll();
        model.addAttribute("giaoDichList", giaoDichList);
        return "giao_dich_list";
    }

    @GetMapping("/giao-dich/tim-kiem")
    public String searchGiaoDich(@RequestParam("tenKhachHang") String tenKhachHang, Model model) {
        List<GiaoDich> giaoDichList = giaoDichRepository.findByKhachHang_TenKhachHang(tenKhachHang);
        model.addAttribute("giaoDichList", giaoDichList);
        return "giao_dich_list";
    }

    @GetMapping("/giao-dich/them")
    public String showAddForm(Model model) {
        model.addAttribute("giaoDich", new GiaoDich());
        model.addAttribute("khachHangs", khachHangRepository.findAll());
        return "giao_dich_form";
    }

    @PostMapping("/giao-dich/them")
    public String addGiaoDich(@ModelAttribute @Valid GiaoDich giaoDich, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("khachHangs", khachHangRepository.findAll());
            return "giao_dich_form";
        }

        // Kiểm tra khách hàng có hợp lệ không
        if (giaoDich.getKhachHang() == null || giaoDich.getKhachHang().getMaKhachHang() == null) {
            model.addAttribute("errorMessage", "Vui lòng chọn khách hàng.");
            model.addAttribute("khachHangs", khachHangRepository.findAll());
            return "giao_dich_form";
        }

        try {
            giaoDichRepository.save(giaoDich);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra, vui lòng thử lại.");
            model.addAttribute("khachHangs", khachHangRepository.findAll());
            return "giao_dich_form";
        }

        return "redirect:/giao-dich"; // Redirect sau khi lưu thành công
    }
}