import React, { useState, useEffect } from 'react';
import axios from 'axios';
import api from '../../services/api';

const VendaEditModal = ({ venda, closeModal }) => {
  const [cliente, setCliente] = useState(venda.cliente);
  const [data, setData] = useState(venda.data);
  const [status, setStatus] = useState(venda.status);
  const [valor, setValor] = useState(venda.valor);
  const [clientes, setClientes] = useState([]);

  useEffect(() => {
    const fetchClientes = async () => {
      try {
        const response = await api.get('/cliente');
        setClientes(response.data);
      } catch (error) {
        console.error("Erro ao buscar clientes:", error);
      }
    };

    fetchClientes();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`/venda/${venda.id}`, { cliente, data, status, valor });
      closeModal();
      window.location.reload();
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="venda-edit-modal">
      <form onSubmit={handleSubmit}>
        <select value={cliente} onChange={(e) => setCliente(e.target.value)} required>
          {clientes.map(cliente => (
            <option key={cliente.cnpj} value={cliente.cnpj}>{cliente.nome}</option>
          ))}
        </select>
        <input
          type="date"
          value={data}
          onChange={(e) => setData(e.target.value)}
          required
        />
        <select value={status} onChange={(e) => setStatus(e.target.value)} required>
          <option value="AGUARDANDO_PAGAMENTO">Aguardando pagamento</option>
          <option value="PAGAMENTO_APROVADO">Pagamento aprovado</option>
          <option value="AGUARDANDO_ENVIO">Aguardando envio</option>
          <option value="A_CAMINHO">À caminho</option>
          <option value="FINALIZADO">Finalizado</option>
        </select>
        <input
          type="number"
          placeholder="Valor"
          value={valor}
          onChange={(e) => setValor(e.target.value)}
          required
        />
        <button type="submit">Salvar Alterações</button>
        <button type="button" onClick={closeModal}>Cancelar</button>
      </form>
    </div>
  );
};

export default VendaEditModal;