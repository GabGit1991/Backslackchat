package com.tp.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tp.chat.entity.Canal;

@Repository
public interface CanalRepository extends JpaRepository<Canal, Integer> {

}
