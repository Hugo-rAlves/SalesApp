package com.hugoalves.sales.model.interfaces;

import com.hugoalves.sales.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteInterface extends JpaRepository<Cliente, Long> {
}
