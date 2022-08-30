import './App.css';
import Nav from './Components/Nav';
import SideBar from './Components/SideBar';
import { Route, Routes } from 'react-router-dom';
import { useEffect, useState } from 'react';
import Login from './Pages/Login';
import Signup from './Pages/Signup';
import MyPage from './Pages/MyPage';
import MainLogout from './Pages/MainLogout';
import SearchResult from './Pages/SearchResult';
import WriteQuestion from './Pages/WriteQuestion';
import EditQuestion from './Pages/EditQuestion';
import EditAnswer from './Pages/EditAnswer';
import DetailQuestion from './Pages/DetailQuestion';
import Footer from './Components/Footer';
import useStore from './Store/store';

function App() {
  const [openMobileMenu, setOpenMobileMenu] = useState(false);
  const [loginStyle, setLoginStyle] = useState(false);
  const { isLoginMode } = useStore((state) => state);
  // const [path, setPath] = useState('');

  const handleMobileMenuOpen = () => {
    setOpenMobileMenu(!openMobileMenu);
  };

  useEffect(() => {
    setLoginStyle(isLoginMode);
  });

  return (
    <div className="App">
      <Nav handleMobileMenuOpen={handleMobileMenuOpen} />
      <div className="container">
        <SideBar login={loginStyle} openMobileMenu={openMobileMenu} />
        <Routes>
          <Route path={'/'} element={<MainLogout />} />
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/mypage" element={<MyPage />} />
          <Route path="/search" element={<SearchResult />} />
          <Route path="/write" element={<WriteQuestion />} />
          <Route path="/questions/edit" element={<EditQuestion />} />
          <Route path="/answer/edit" element={<EditAnswer />} />
          <Route path="/questions" element={<DetailQuestion />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
