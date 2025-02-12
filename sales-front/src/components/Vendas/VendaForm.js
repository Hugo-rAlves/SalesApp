import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigation } from 'react-router-dom';
import api from '../../services/api';

const VendaForm = () => {
    const [cliente, setCliente] = useState('');
    const [data, setData] = useState('');
    const [status, setStatus] = useState('Aguardando pagamento');
    const [valor, setValor] = useState('');
    const [clientes, setClientes] = useState([]);
    const navigate = useNavigation();

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
      await axios.post('/venda', { cliente, data, status, valor });
      navigate('/vendas');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="venda-form">
      <form onSubmit={handleSubmit}>
        <select value={cliente} onChange={(e) => setCliente(e.target.value)} required>
          <option value="">Selecione o cliente</option>
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
        <button type="submit">Cadastrar Venda</button>
      </form>
    </div>
  );
};

export default VendaForm;