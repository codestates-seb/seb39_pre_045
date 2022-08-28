import './App.css';
import Nav from './Components/Nav';
import SideBar from './Components/SideBar';
import { Route, Routes } from 'react-router-dom';
import { useState } from 'react';
import Login from './Pages/Login';
import Signup from './Pages/Signup';
import MyPage from './Pages/MyPage';
import MainLogout from './Pages/MainLogout';
import SearchResult from './Pages/SearchResult';

function App() {
  const [openMobileMenu, setOpenMobileMenu] = useState(false);

  const handleMobileMenuOpen = () => {
    setOpenMobileMenu(!openMobileMenu);
  };

  return (
    <div className="App">
      <Nav handleMobileMenuOpen={handleMobileMenuOpen} />
      <div className="container">
        <SideBar openMobileMenu={openMobileMenu} />
        <Routes>
          <Route path={'/'} element={<MainLogout />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/search" element={<SearchResult />} />
        </Routes>
      </div>
    </div>
  );
}

export default App;
