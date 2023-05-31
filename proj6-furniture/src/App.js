
import './App.css';

import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import React, { useEffect, useState } from "react";
import Register from './components/Register';
import Shop from './components/Shop';
import Checkout from './components/Checkout';
import Navbar from './components/Navbar';
import Confirmation from './components/Confirmation';
import Orders from './components/Orders';

function App() {

  const [user, setUser] = useState(undefined);

  return (
    <div className="App">
      
      <BrowserRouter>
      <Navbar setUser={setUser}/>
        <Routes>
          <Route path="/login" element= {<Login setUser={setUser}/>}></Route>
          <Route path="/register" element= {<Register setUser={setUser}/>}></Route>
          <Route path="/shop" element= {<Shop user={user} setUser={setUser}/>}></Route>
          <Route path="/checkout" element= {<Checkout user={user} setUser={setUser}/>}></Route>
          <Route path="/confirmation" element= {<Confirmation user={user}/>}></Route>
          <Route path="/orders" element= {<Orders user={user}/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
