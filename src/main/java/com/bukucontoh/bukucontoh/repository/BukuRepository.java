package com.bukucontoh.bukucontoh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bukucontoh.bukucontoh.model.BukuContoh;

public interface BukuRepository extends JpaRepository<BukuContoh, Long> {
	List<BukuContoh> findByDescription(String description);
	List<BukuContoh> findByNama(String nama); 
}