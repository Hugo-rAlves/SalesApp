import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import api from '../../services/api';
import { MapContainer, TileLayer, Marker, useMapEvents } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';

const ClienteForm = () => {
  const [nome, setNome] = useState('');
  const [cnpj, setCnpj] = useState('');
  const [email, setEmail] = useState('');
  const [telefone, setTelefone] = useState('');
  const [uf, setUf] = useState('');
  const [localizacao, setLocalizacao] = useState('');
  const [ufs, setUfs] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchUfs = async () => {
      try {
        const response = await axios.get('https://servicodados.ibge.gov.br/api/v1/localidades/estados');
        setUfs(response.data);
      } catch (error) {
        console.error("Erro ao buscar UFs:", error);
      }
    };

    fetchUfs();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/cliente', { nome, cnpj, email, telefone, uf, localizacao });
      navigate('/clientes');
    } catch (error) {
      console.error(error);
    }
  };

  const LocationMarker = () => {
    useMapEvents({
      click(e) {
        setLocalizacao([e.latlng.lat, e.latlng.lng]);
      },
    });

    return localizacao ? (
      <Marker position={localizacao}></Marker>
    ) : null;
  };

  return (
    <div className="cliente-form">
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Nome"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="CNPJ"
          value={cnpj}
          onChange={(e) => setCnpj(e.target.value)}
          required
        />
        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          type="text"
          placeholder="Telefone"
          value={telefone}
          onChange={(e) => setTelefone(e.target.value)}
          required
        />
        <select value={uf} onChange={(e) => setUf(e.target.value)} required>
          <option value="">Selecione o estado</option>
          {ufs.map((uf) => (
            <option key={uf.id} value={uf.sigla}>{uf.nome}</option>
          ))}
        </select>
        <input
          type="text"
          placeholder="Localização"
          value={localizacao}
          onChange={(e) => setLocalizacao(e.target.value)}
          required
        />
        <div className="map">
          <MapContainer center={localizacao} zoom={4} style={{ height: '400px', width: '100%' }}>
            <TileLayer
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            <LocationMarker />
          </MapContainer>
        </div>
        <button type="submit">Cadastrar Cliente</button>
      </form>
    </div>
  );
};

export default ClienteForm;