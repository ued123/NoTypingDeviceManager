import React, { Component} from 'react';
//import  './LoginTemplate.css';
import axios from 'axios';

import { Form } from 'react-bootstrap';
import { Button } from 'react-bootstrap';


class LoginTemplate extends Component {
  // 초기화
  state = {
    username : '',
    password : ''
  };

  goDashBd = async () => {

    // const {user} = this.state;

    const response = await axios({
      method : 'post',
      url : '/login.json',
      header : {
        'Content-Type': 'application/json'
      },
      data : {
        "userName" : this.state.username,
        "password" :  this.state.password
      }
    });

    if(response.data === true) {
      alert('로그인에 성공 하였습니다');
      this.props.handlePageType('dashboard');
    } else if (response.data === false) {
      alert('로그인에 실패 하였습니다');
    }



  };

  doChange = (e) => {

    var inputName = e.target.name;
    var inputValue = e.target.value;

    if('user.username' === inputName) {
      this.state.username = inputValue;
    } else if('user.password' === inputName) {
      this.state.password = inputValue;
    }

  };

  render() {

    //console.log(this.props);
    /*
      1. submit 버튼을 누르면 todos 페이지로 전환하게 하자
    */
    return (

      <main className="login-form">
        {/*<h1>장비관리자 페이지</h1>*/}

    	{/*	<input type="text" name="user.username" placeholder="Username" onChange={this.doChange}/>*/}
		{/*		<input type="password" name="user.password" placeholder="Password" onChange={this.doChange}/>*/}
        {/*<input value="로그인" className="doLogin" type="submit" onClick={this.doLogin}/>*/}
        {/*<input value="회원가입" className="register" type="submit" onClick={this.doReister}/>*/}

        <div className="container h-100">
          <h1>장비관리자페이지</h1>
          <Form>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>아이디</Form.Label>
              <Form.Control type="userId" placeholder="Enter userid" name="user.username" onChange={this.doChange}/>
              <Form.Text className="text-muted">
                We'll never share your userId with anyone else.
              </Form.Text>
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Label>비밀번호</Form.Label>
              <Form.Control type="password" placeholder="Password" name="user.password" onChange={this.doChange}/>
            </Form.Group>
            <Form.Group controlId="formBasicCheckbox">
              <Form.Check type="checkbox" label="Check me out" />
            </Form.Group>

            <button
                    type="button" onClick={this.goDashBd}>
              login
            </button>

                {/*<Button variant="primary" type="submit" onClick={this.goDashBd}>*/}
            {/*  Submit*/}
            {/*</Button>*/}
          </Form>
        </div>
      </main>

    );
  }
}
export default LoginTemplate;
