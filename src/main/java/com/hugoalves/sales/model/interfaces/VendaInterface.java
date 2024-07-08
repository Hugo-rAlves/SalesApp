package com.hugoalves.sales.model.interfaces;

import com.hugoalves.sales.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaInterface extends JpaRepository<Venda, Long> {
}
