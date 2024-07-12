import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Sidebar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleSidebar = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={`sidebar ${isOpen ? 'open' : ''}`}>
      <button className="toggle-btn" onClick={toggleSidebar}>Menu</button>
      <nav className="menu">
        <ul>
          <li><Link to="/clientes">Lista de Clientes</Link></li>
          <li><Link to="/clientes/cadastrar">Cadastrar Cliente</Link></li>
          <li><Link to="/vendas">Lista de Vendas</Link></li>
          <li><Link to="/vendas/cadastrar">Cadastrar Venda</Link></li>
          <li><Link to="/relatorios">Relatórios</Link></li>
        </ul>
      </nav>
    </div>
  );
};

export default Sidebar;