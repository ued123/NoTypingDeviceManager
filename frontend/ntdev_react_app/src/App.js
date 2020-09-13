//import React from 'react';
import React, { Component} from 'react';
import Device from './components/device/Device';
import './components/common/Common.css';
import 'bootstrap/dist/css/bootstrap.min.css';

class App extends Component {


  render() {
    return  <div className="container-fluid">
              <Device />
              <footer className="page-footer font-small blue">
                <div class="footer-copyright text-center py-3">© 2020 Copyright:
                  <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
                </div>
              </footer>;

            </div>
          };


}
export default App;
