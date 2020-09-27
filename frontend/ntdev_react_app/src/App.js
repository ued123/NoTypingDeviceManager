
import React, { Component} from 'react';
import './components/common/Common.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import LoginTemplate from "./components/login/LoginTemplate";
import Device from './components/device/Device';
import Dashboard from "./components/dashboard/Dashboard";
import Header from './components/common/Header';

class App extends Component {

    state = {
        currentCmp : 'login'
    }

    handlePageType = (currentCmp) => {
        this.setState(
            {currentCmp : currentCmp}
        );
    }

  render() {
      const {currentCmp} = this.state;

      if (currentCmp === 'login') {

          return (
              <LoginTemplate handlePageType={this.handlePageType}/>
        );
      } else if (currentCmp == 'dashboard') {

          return (
              <div className="container-fluid">
                  <Header/>
                  <Dashboard/>
              </div>
          );

      }
  }
}
export default App;
