//import React from 'react';
import React, { Component} from 'react';
import LoginTemplate from './components/login/LoginTemplate';

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

    const {currentCmp } = this.state;

    if (currentCmp === 'login') {
      return <LoginTemplate handlePageType = {this.handlePageType}/>;
    }

  /*  if (currentCmp === 'userRegister') {
      return <UserRegisterTemplate handlePageType = {this.handlePageType}/>;
    }

    return (
        <TodosManager></TodosManager>
    );*/
  }
}
export default App;
