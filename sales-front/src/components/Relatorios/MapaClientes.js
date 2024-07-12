import React, { useEffect } from 'react';
import L from 'leaflet';

const MapaClientes = ({ clientes }) => {
  useEffect(() => {
    const map = L.map('map').setView([-23.55052, -46.633308], 10);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(map);

    clientes.forEach(cliente => {
      const { lat, lng } = cliente.localizacao;
      L.marker([lat, lng]).addTo(map)
        .bindPopup(cliente.nome)
        .openPopup();
    });

    return () => {
      map.remove();
    };
  }, [clientes]);

  return <div id="map" style={{ height: '400px', width: '100%' }}></div>;
};

export default MapaClientes;