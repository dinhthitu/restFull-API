package com.example.demo.controller;

import com.example.demo.entity.TestEntity;
import com.example.demo.request.UpdateUserRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TestController {

    @Autowired
    private UserService service;


    // api thêm mới
    @PostMapping("/create")
    public ResponseEntity<TestEntity> createEntityTest(@RequestBody TestEntity request) {
        return service.create(request);
    }


    // api update data
//    Ctrl + ALt + L  ---> format code

    @PutMapping("update/{id}")
    String updateUser(@PathVariable Long id,
                      @RequestBody UpdateUserRequest request) {
        return service.updateUser(id, request);
    }


    // API xóa
    @DeleteMapping("delete/{id}")
    String deleteUser(@PathVariable Long id) {
        return service.deletedUser(id);
    }

    // API chi tiết
    @GetMapping("/detail/user")
    Optional<TestEntity> getDetailUser(@RequestParam Long id) {
        return service.getDetailUser(id);
    }

    // API Get danh sách + Phân Trang + tìm kiếm

    @GetMapping("/user")
    ResponseEntity<Page<TestEntity>> getUserPageAndSearch(@RequestParam(required = false) String name,
                                                          @RequestParam(required = false) String email,
                                                            @RequestParam(required = false) Long age,
      Pageable pageable) {

        Page<TestEntity> users = service.getUserPageAndSearch(name,email,age,pageable);

        return ResponseEntity.ok(users);

    }


}
