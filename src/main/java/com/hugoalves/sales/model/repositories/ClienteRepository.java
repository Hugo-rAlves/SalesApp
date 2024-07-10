package com.hugoalves.sales.model.repositories;

import com.hugoalves.sales.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCnpj(String cnpj);

    Cliente findByCnpj(String cnpj);

    void deleteByCnpj(String cnpj);

}
