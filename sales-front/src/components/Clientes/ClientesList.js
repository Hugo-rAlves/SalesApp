import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ClienteEditModal from './ClienteEditModal';
import api from '../../services/api';

const ClientesList = () => {
  const [clientes, setClientes] = useState([]);
  const [search, setSearch] = useState('');
  const [isAdmin, setIsAdmin] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [selectedCliente, setSelectedCliente] = useState(null);

  useEffect(() => {
    const fetchClientes = async () => {
        try{
            const response = await axios.get('/cliente');
            setClientes(response.data);
        } catch (error) {
            console.log("Erro ao buscar clientes:", error);
        }
    };

    const user = JSON.parse(localStorage.getItem('user'));
    setIsAdmin(user.role === 'ADMIN');
    fetchClientes();
  }, []);

  const handleSearch = (e) => {
    setSearch(e.target.value);
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/cliente/${id}`);
      setClientes(clientes.filter(cliente => cliente.id !== id));
    } catch (error) {
      console.error("Erro ao deletar cliente:", error);
    }
  };

  const handleEdit = (cliente) => {
    setSelectedCliente(cliente);
    setShowEditModal(true);
  };

  const closeModal = () => {
    setShowEditModal(false);
    setSelectedCliente(null);
  };

  return (
    <div className="clientes-list">
      <input
        type="text"
        placeholder="Buscar cliente..."
        value={search}
        onChange={handleSearch}
      />
      {isAdmin && <button onClick={() => window.location.href = '/clientes/cadastrar'}>Cadastrar Cliente</button>}
      <table>
        <thead>
          <tr>
            <th>Nome</th>
            <th>CNPJ</th>
            <th>Email</th>
            <th>Telefone</th>
            {isAdmin && <th>Ações</th>}
          </tr>
        </thead>
        <tbody>
          {clientes.filter(cliente => cliente.nome.includes(search)).map(cliente => (
            <tr key={cliente.id}>
              <td>{cliente.nome}</td>
              <td>{cliente.cnpj}</td>
              <td>{cliente.email}</td>
              <td>{cliente.telefone}</td>
              {isAdmin && (
                <td>
                  <button onClick={() => handleEdit(cliente)}>Editar</button>
                  <button onClick={() => handleDelete(cliente.id)}>Excluir</button>
                </td>
              )}
            </tr>
          ))}
        </tbody>
      </table>
      {showEditModal && (
        <ClienteEditModal
          cliente={selectedCliente}
          closeModal={closeModal}
        />
      )}
    </div>
  );
};

export default ClientesList;