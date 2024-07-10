package com.hugoalves.sales.model.repositories;

import com.hugoalves.sales.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {

    @Query("SELECT v FROM venda v WHERE v.cliente.cnpj = :cnpj")
    List<Venda> findByCnpj(@Param("cnpj") String cnpj);

}
