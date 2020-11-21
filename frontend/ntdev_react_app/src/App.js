//import React from 'react';
import React, { Component} from 'react';
import {BrowserRouter as Router, Route,} from 'react-router-dom';
import Device from './components/device/Device';
import LoginTemplate from './components/login/LoginTemplate';
import UserTemplate from './components/user/UserTemplate';
import './components/common/Common.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Header from './components/common/Header';

class App extends Component {

  render () {
    return (
    <Router>
        { /* 로그인 및 회원 가입 페이지 */}
        <Route exact path="/device" component={Device}/>
        <Route exact path="/user" component={UserTemplate}/>
        <Route exact path="/" component={LoginTemplate}/>
        {/* 정의된 url을 벗어난 경우 root path로 redirect 수행 */}
    </Router>
   );

  }
}
export default App;
