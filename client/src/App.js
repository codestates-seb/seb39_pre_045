import './App.css';
import Nav from './Components/Nav';
import SideBar from './Components/SideBar';
import { Route, Routes } from 'react-router-dom';
import Login from './Pages/Login';

function App() {
  return (
    <div className="App">
      <Nav />
      <div className="container">
        <SideBar />
        <Routes>
          <Route path="/login" element={<Login />}></Route>
        </Routes>
      </div>
    </div>
  );
}

export default App;
