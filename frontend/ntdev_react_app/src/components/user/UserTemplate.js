import React, { Component} from 'react';
//import  './LoginTemplate.css';
import axios from 'axios';
import { withRouter } from "react-router-dom";
import { Form } from 'react-bootstrap';
import { Button } from 'react-bootstrap';


class UserTemplate extends Component {

  render() {
    return (
        <div className="container h-100">
          <h1>유저관리페이지</h1>
        </div>
    );
  }
}
export default UserTemplate;
