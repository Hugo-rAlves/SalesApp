import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('USER');

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await axios.post('/auth/register', { login, password, role });
    } catch (error) {
      console.error("Erro ao registrar:", error);
    }
  };

  return (
    <div className="register-form">
      <form onSubmit={handleRegister}>
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
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="USER">User</option>
          <option value="ADMIN">Admin</option>
        </select>
        <button type="submit">Registrar</button>
      </form>
    </div>
  );
};

export default Register;