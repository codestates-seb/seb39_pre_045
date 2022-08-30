import './App.css';
import Nav from './Components/Nav';
import SideBar from './Components/SideBar';
import { Route, Routes } from 'react-router-dom';
import { useEffect, useState, lazy, Suspense } from 'react';
import useStore from './Store/store';

const Login = lazy(() => import('./Pages/Login'));
const Signup = lazy(() => import('./Pages/Signup'));
const MyPage = lazy(() => import('./Pages/MyPage'));
const MainLogout = lazy(() => import('./Pages/MainLogout'));
const SearchResult = lazy(() => import('./Pages/SearchResult'));
const WriteQuestion = lazy(() => import('./Pages/WriteQuestion'));
const EditQuestion = lazy(() => import('./Pages/EditQuestion'));
const EditAnswer = lazy(() => import('./Pages/EditAnswer'));
const DetailQuestion = lazy(() => import('./Pages/DetailQuestion'));
const Footer = lazy(() => import('./Components/Footer'));
const Loading = lazy(() => import('./Components/Loading'));

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
      <Suspense fallback={'error' || <Loading />}>
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
      </Suspense>
    </div>
  );
}

export default App;
