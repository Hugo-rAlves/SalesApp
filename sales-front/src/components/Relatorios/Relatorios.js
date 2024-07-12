import React, { useEffect, useState } from 'react';
import { Bar } from 'react-chartjs-2';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import api from '../../services/api';

const Relatorios = () => {
  const [totalVendasAno, setTotalVendasAno] = useState(0);
  const [clienteMaisVendasMes, setClienteMaisVendasMes] = useState('');
  const [clienteMaiorFaturamentoMes, setClienteMaiorFaturamentoMes] = useState({ nome: '', valor: 0 });
  const [clienteMaiorFaturamentoAno, setClienteMaiorFaturamentoAno] = useState({ nome: '', valor: 0 });
  const [locationData, setLocationData] = useState([]);
  const [faturamento, setFaturamento] = useState([]);

  useEffect(() => {
    const fetchRelatorios = async () => {
      try {
        const responseClientes = await api.get('/cliente');
        const clientes = responseClientes.data;

        const responseVendas = await api.get('/venda');
        const vendas = responseVendas.data;

        const totalAno = vendas.reduce((total, venda) => {
          const ano = new Date(venda.data).getFullYear();
          if (ano === new Date().getFullYear()) {
            return total + venda.valor;
          }
          return total;
        }, 0);
        setTotalVendasAno(totalAno);

        const vendasMes = vendas.filter(venda => {
          const data = new Date(venda.data);
          return data.getMonth() === new Date().getMonth() && data.getFullYear() === new Date().getFullYear();
        });

        const clienteVendasMes = vendasMes.reduce((acc, venda) => {
          acc[venda.cliente] = (acc[venda.cliente] || 0) + 1;
          return acc;
        }, {});
        const clienteMaisVendas = Object.keys(clienteVendasMes).reduce((a, b) => clienteVendasMes[a] > clienteVendasMes[b] ? a : b, '');
        setClienteMaisVendasMes(clienteMaisVendas);

        const clienteFaturamentoMes = vendasMes.reduce((acc, venda) => {
          acc[venda.cliente] = (acc[venda.cliente] || 0) + venda.valor;
          return acc;
        }, {});
        const clienteMaiorFaturamentoM = Object.keys(clienteFaturamentoMes).reduce((a, b) => clienteFaturamentoMes[a] > clienteFaturamentoMes[b] ? a : b, '');
        setClienteMaiorFaturamentoMes({ nome: clienteMaiorFaturamentoM, valor: clienteFaturamentoMes[clienteMaiorFaturamentoM] });

        const clienteFaturamentoAno = vendas.reduce((acc, venda) => {
          const ano = new Date(venda.data).getFullYear();
          if (ano === new Date().getFullYear()) {
            acc[venda.cliente] = (acc[venda.cliente] || 0) + venda.valor;
          }
          return acc;
        }, {});
        const clienteMaiorFaturamentoA = Object.keys(clienteFaturamentoAno).reduce((a, b) => clienteFaturamentoAno[a] > clienteFaturamentoAno[b] ? a : b, '');
        setClienteMaiorFaturamentoAno({ nome: clienteMaiorFaturamentoA, valor: clienteFaturamentoAno[clienteMaiorFaturamentoA] });

        const locationData = clientes.map(cliente => ({
          nome: cliente.nome,
          cnpj: cliente.cnpj,
          latitude: cliente.latitude,
          longitude: cliente.longitude,
        }));
        setLocationData(locationData);

        const faturamentoPorMes = vendas.reduce((acc, venda) => {
          const data = new Date(venda.data);
          const mes = data.toLocaleString('pt-BR', { month: 'long' });
          const ano = data.getFullYear();

          if (!acc[ano]) acc[ano] = {};
          if (!acc[ano][mes]) acc[ano][mes] = { numeroVendas: 0, valor: 0 };

          acc[ano][mes].numeroVendas += 1;
          acc[ano][mes].valor += venda.valor;

          return acc;
        }, {});

        const faturamentoArray = Object.keys(faturamentoPorMes).flatMap(ano =>
          Object.keys(faturamentoPorMes[ano]).map(mes => ({
            ano,
            mes,
            numeroVendas: faturamentoPorMes[ano][mes].numeroVendas,
            valor: faturamentoPorMes[ano][mes].valor
          }))
        );

        setFaturamento(faturamentoArray);
      } catch (error) {
        console.error("Erro ao buscar dados dos relatórios:", error);
      }
    };

    fetchRelatorios();
  }, []);

  const exportCSV = () => {
    const csvData = faturamento.map(d => `${d.mes}/${d.ano},${d.numeroVendas},${d.valor}\n`).join('');
    const blob = new Blob([`Mês/Ano,Número de Vendas,Total Faturado\n${csvData}`], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.setAttribute('download', 'faturamento.csv');
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  return (
    <div className="relatorios">
      <div className="cards">
        <div className="card">Valor total de vendas do ano: {totalVendasAno.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</div>
        <div className="card">Cliente com mais vendas no mês: {clienteMaisVendasMes}</div>
        <div className="card">Cliente com maior faturamento no mês: {clienteMaiorFaturamentoMes.nome} - {clienteMaiorFaturamentoMes.valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</div>
        <div className="card">Cliente com maior faturamento no ano: {clienteMaiorFaturamentoAno.nome} - {clienteMaiorFaturamentoAno.valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</div>
      </div>
      <div className="tabs">
        <div className="tab">
          <h3>Mapa de Localização dos Clientes</h3>
          <MapContainer center={[-15.77972, -47.92972]} zoom={4} style={{ height: '400px' }}>
            <TileLayer
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            />
            {locationData.map((cliente, index) => (
              <Marker key={index} position={[cliente.latitude, cliente.longitude]}>
                <Popup>
                  {cliente.nome}<br />{cliente.cnpj}
                </Popup>
              </Marker>
            ))}
          </MapContainer>
        </div>
        <div className="tab">
          <h3>Faturamento por Mês</h3>
          <div className="chart">
            <Bar
              data={{
                labels: faturamento.map(d => `${d.mes}/${d.ano}`),
                datasets: [
                  {
                    label: 'Faturamento',
                    data: faturamento.map(d => d.valor),
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderColor: 'rgba(75, 192, 192, 1)',
                    borderWidth: 1,
                  },
                ],
              }}
              options={{
                scales: {
                  y: {
                    beginAtZero: true,
                  },
                },
              }}
            />
          </div>
          <div className="table">
            <table>
              <thead>
                <tr>
                  <th>Mês/Ano</th>
                  <th>Número de Vendas</th>
                  <th>Total Faturado</th>
                </tr>
              </thead>
              <tbody>
                {faturamento.map((d, index) => (
                  <tr key={index}>
                    <td>{d.mes}/{d.ano}</td>
                    <td>{d.numeroVendas}</td>
                    <td>{d.valor.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' })}</td>
                  </tr>
                ))}
              </tbody>
            </table>
            <button onClick={exportCSV}>Exportar para CSV</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Relatorios;