package com.example.demo.reponsitory;

import com.example.demo.entity.TestEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    Page<TestEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);


    @Query("select te from TestEntity te where  te.email = ?1")
    Page<TestEntity> findByEmails(String email, Pageable pageable);


    //    @Query("select te from TestEntity te where upper(te.name) like upper(concat('%', ?1,'%')) and upper(te.email) like upper(concat('%', ?2,'%')) ")
    Page<TestEntity> findByNameContainingIgnoreCaseAndEmail(String name, String email,Long age, Pageable pageable);


    @Query("select  s from TestEntity s where  upper(s.email) like upper(concat('%', ?1 , '%')) ")
    Page<TestEntity> findByEmailTimKiem(String email, Pageable pageable);


    @Query("select s from TestEntity s where s.age = ?1")
    Page<TestEntity> timKiemTheoTuoi(Long age, Pageable pageable);



}
