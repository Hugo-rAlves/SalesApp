package com.hugoalves.sales.model.repositories;

import com.hugoalves.sales.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    boolean existsByCnpj(String cnpj);

    Optional<Cliente> findByCnpj(String cnpj);

    void deleteByCnpj(String cnpj);

}
