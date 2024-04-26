package com.example.demo.service;

import com.example.demo.entity.TestEntity;
import com.example.demo.reponsitory.TestRepository;
import com.example.demo.request.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class UserService {

    private final TestRepository res;

    public UserService(TestRepository res) {
        this.res = res;
    }

//   public UserSe(Testre repository)
//    {this.res = repository;}


    public ResponseEntity<TestEntity> create(TestEntity request) {
        TestEntity user1 = new TestEntity();
        user1.setEmail(request.getEmail());
        user1.setName(request.getName());
        user1.setAge(request.getAge());
        res.save(user1);
        return ResponseEntity.ok(user1);
    }

    public String updateUser(Long id, UpdateUserRequest request) {
        // bước 1 lấy ra đối tượng em muốn cập nhật theo id
        var user = res.findById(id).orElse(null);



        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        // bước 2 là cập nhật data

        res.save(user);
        // bước 3 lưu vào database


        return "Cập nhật oke xong ";  // trả response cho FE hoặc người dùng client
    }

    public String deletedUser(Long id) {
        // bước 1 lấy ra đối tượng em muốn cập nhật theo id
        var user = res.findById(id).orElse(null);

        // bước 2 thực hiện xóa sử dụng jpa

        res.deleteById(id);

        return "Xóa thành công bản ghi";
    }

    public Optional<TestEntity> getDetailUser(Long id) {
        // đúng 1 bước

        return res.findById(id);
    }


    public Page<TestEntity> getUserPageAndSearch(String name, String email, Long age, Pageable pageable) {
   // th1 hiển thị danh sách bản ghi phân trang mà không tìm kiếm
        if(name == null && email == null && age == null){
            return res.findAll(pageable);
        }

        if(name != null && email== null && age == null){
            return res.findByNameContainingIgnoreCase(name,pageable);
        }


        // tìm theo email
        if(name == null && email != null && age==null){

            return res.findByEmailTimKiem(email,pageable);
        }
        if (name== null && email ==null && age != null){
            return res.timKiemTheoTuoi(age, pageable);

        }

        return res.findByNameContainingIgnoreCaseAndEmail(name,email,age,pageable);
    }
}