import React from 'react';
import { BrowserRouter as Route, Routes } from 'react-router-dom';
import Login from './components/Auth/Login';
import Register from './components/Auth/Register';
import ClientesList from './components/Clientes/ClientesList';
import ClienteForm from './components/Clientes/ClienteForm';
import VendasList from './components/Vendas/VendasList';
import VendaForm from './components/Vendas/VendaForm';
import Relatorios from './components/Relatorios/Relatorios';
import Header from './components/Layout/Header';
import Sidebar from './components/Layout/Sidebar';

function App() {
  return (
    <div className='App'>
      <Header />
      <Sidebar />
      <div className="main-content">
        <Routes>
          <Route path="/login" component={Login} />
          <Route path="/register" component={Register} />
          <Route path="/clientes" component={ClientesList} />
          <Route path="/clientes/cadastrar" component={ClienteForm} />
          <Route path="/vendas" component={VendasList} />
          <Route path="/vendas/cadastrar" component={VendaForm} />
          <Route path="/relatorios" component={Relatorios} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
