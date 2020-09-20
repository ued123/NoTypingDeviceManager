
import React, { Component} from 'react';
import './components/common/Common.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import LoginTemplate from "./components/login/LoginTemplate";
import Device from "./components/device/Device";
import Dashboard from "./components/dashboard/Dashboard";

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
            <Dashboard/>
          );

      } else if (currentCmp == 'device') {

          return (<div className="container-fluid">

              <Device/>
              <footer className="page-footer font-small blue">
                  <div class="footer-copyright text-center py-3">© 2020 Copyright:
                      <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
                  </div>
              </footer>


          </div>);
      }
  }


      /*  if (currentCmp === 'userRegister') {
          return <UserRegisterTemplate handlePageType = {this.handlePageType}/>;
        }

        return (
            <TodosManager></TodosManager>
        );*/


      /*<div className="container-fluid">

              <Device />
              <footer className="page-footer font-small blue">
                <div class="footer-copyright text-center py-3">© 2020 Copyright:
                  <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
                </div>
              </footer>;

            </div>
          };*/

}
export default App;
