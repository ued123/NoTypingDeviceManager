import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import './components/common/Common.css';

//import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.render(
  <React.StrictMode>
  <div className="container-fluid">
      <App />
     <footer className="page-footer font-small blue">
       <div className="footer-copyright text-center py-3">© 2020 Copyright:
         <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
       </div>
     </footer>;
  </div>

  </React.StrictMode>,
  document.getElementById('root')
);

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
//serviceWorker.unregister();
