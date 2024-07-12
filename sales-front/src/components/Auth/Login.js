import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('/auth/login', { login, password });
      localStorage.setItem('token', response.data.token);
      const user = JSON.parse(atob(response.data.token.split('.')[1]));
      localStorage.setItem('user', JSON.stringify(user));
      navigate('/clientes');
    } catch (error) {
      console.error("Erro ao fazer login:", error);
    }
  };

  return (
    <div className="login-form">
      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Login"
          value={login}
          onChange={(e) => setLogin(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Senha"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;