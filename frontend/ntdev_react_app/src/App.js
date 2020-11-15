//import React from 'react';
import React, { Component} from 'react';
import { Link, Switch, BrowserRouter, Route, Router, Redirect } from 'react-router-dom';
import Device from './components/device/Device';
import LoginTemplate from './components/login/LoginTemplate';
import './components/common/Common.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from './components/common/Header';

class App extends Component {

  render () {
    return (
    <BrowserRouter>
        { /* 로그인 및 회원 가입 페이지 */}
        <Route exact path="/device" component={Device}/>
        <Route exact path="/" component={LoginTemplate}/>
        {/* 정의된 url을 벗어난 경우 root path로 redirect 수행 */}
    </BrowserRouter>
   );

  }
}
export default App;
