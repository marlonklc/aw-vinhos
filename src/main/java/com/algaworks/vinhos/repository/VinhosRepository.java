package com.algaworks.vinhos.repository;

import com.algaworks.vinhos.model.Vinho;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Marlon on 13/02/2017.
 */
public interface VinhosRepository extends JpaRepository<Vinho, Long> {
}
