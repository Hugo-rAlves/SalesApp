import React from 'react';
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem('user'));

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    navigate('/login');
  };

  return (
    <header className="header">
      <div className="user-info">
        <span>{user?.name}</span>
        <span className="role">{user?.role}</span>
        <button onClick={handleLogout}>Logout</button>
      </div>
    </header>
  );
};

export default Header;