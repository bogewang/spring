package com.bogewang.ch8_2.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * 此列中的接口继承JpaRepository, 让我们具备了JpaRepository索提供的方法;继承了JpaSpecificationExecutor,让我们
 * 具备使用Specification的能力;
 * @param <T>
 * @param <ID>
 */
@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        JpaSpecificationExecutor<T>{
    Page<T> findByAuto(T example, Pageable pageable);
}
