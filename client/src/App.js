import './App.css';
import Nav from './Components/Nav';
import LeftSideBar from './Components/LeftSideBar';
import { Route, Routes } from 'react-router-dom';
import { useEffect, useState, lazy, Suspense } from 'react';
import useSideBarStore from './Store/store-sidebar';
import NoResult from './Components/NoResult';
import MobileLeftSideBar from './Components/MobileLeftSideBar';
import useLoginSuccessStore from './Store/store-loginSuccess';

const Login = lazy(() => import('./Pages/Login'));
const Signup = lazy(() => import('./Pages/Signup'));
const MyPage = lazy(() => import('./Pages/MyPage'));
const MainLogout = lazy(() => import('./Pages/MainLogout'));
const MainLogin = lazy(() => import('./Pages/MainLogin'));
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
  const { leftSideBarHidden } = useSideBarStore((state) => state);
  const { setLoginSuccess, loginSuccess } = useLoginSuccessStore(
    (state) => state
  );

  const handleMobileMenuOpen = () => {
    setOpenMobileMenu(!openMobileMenu);
  };

  useEffect(() => {
    setLoginStyle(leftSideBarHidden);
  });

  useEffect(() => {
    if (localStorage.getItem('ACCESS_TOKEN')) {
      setLoginSuccess(true);
    }
  }, []);

  return (
    <div className="App">
      <Suspense fallback={<Loading />}>
        <Nav handleMobileMenuOpen={handleMobileMenuOpen} />
        <div className="container">
          <LeftSideBar login={loginStyle} />
          <MobileLeftSideBar
            openMobileMenu={openMobileMenu}
            setOpenMobileMenu={setOpenMobileMenu}
          />
          <Routes>
            <Route
              path="/g"
              element={loginSuccess ? <MainLogin /> : <Login />}
            />
            <Route path="/" element={<MainLogout />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/mypage" element={<MyPage />} />
            <Route path="/search" element={<SearchResult />} />
            <Route path="/write" element={<WriteQuestion />} />
            <Route path="/questions/edit/:id" element={<EditQuestion />} />
            <Route path="/answers/edit/:id" element={<EditAnswer />} />
            <Route path="/questions/:id" element={<DetailQuestion />} />
            <Route
              path="*"
              element={
                <NoResult keyword={'Page not found'} status={'notFound'} />
              }
            />
          </Routes>
        </div>
        <Footer />
      </Suspense>
    </div>
  );
}

export default App;
