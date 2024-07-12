import React, { useState, useEffect } from 'react';
import axios from 'axios';
import VendaEditModal from './VendaEditModal';
import api from '../../services/api';

const VendasList = () => {
  const [vendas, setVendas] = useState([]);
  const [search, setSearch] = useState('');
  const [isAdmin, setIsAdmin] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedVenda, setSelectedVenda] = useState(null);

  useEffect(() => {
    const fetchVendas = async () => {
      try {
        const response = await axios.get('/venda');
        setVendas(response.data);
      } catch (error) {
        console.error("Erro ao buscar vendas:", error);
      }
      
    };

    const user = JSON.parse(localStorage.getItem('user'));
    setIsAdmin(user.role === 'ADMIN');
    fetchVendas();
  }, []);

  const handleSearch = (e) => {
    setSearch(e.target.value);
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/venda/${id}`);
      setVendas(vendas.filter(venda => venda.id !== id));
    } catch (error) {
      console.error("Erro ao deletar venda:", error);
    }
  };

  const handleEdit = (venda) => {
    setSelectedVenda(venda);
    setShowEditModal(true);
  };

  const closeModal = () => {
    setShowEditModal(false);
    setSelectedVenda(null);
  };

  return (
    <div className="vendas-list">
      <input
        type="text"
        placeholder="Buscar venda..."
        value={search}
        onChange={handleSearch}
      />
      {isAdmin && <button onClick={() => window.location.href = '/vendas/cadastrar'}>Cadastrar Venda</button>}
      <table>
        <thead>
          <tr>
            <th>Cliente</th>
            <th>Data</th>
            <th>Status</th>
            <th>Valor</th>
            {isAdmin && <th>Ações</th>}
          </tr>
        </thead>
        <tbody>
          {vendas.filter(venda => venda.cliente.includes(search)).map(venda => (
            <tr key={venda.id}>
              <td>{venda.cliente}</td>
              <td>{new Date(venda.data).toLocaleDateString()}</td>
              <td>{venda.status}</td>
              <td>{venda.valor}</td>
              {isAdmin && (
                <td>
                  <button onClick={() => handleEdit(venda)}>Editar</button>
                  <button onClick={() => handleDelete(venda.id)}>Excluir</button>
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
      {showEditModal && (
        <VendaEditModal
          vanda={selectedVenda}
          closeModal={closeModal}
        />
      )}
    </div>
  );
};

export default VendasList;