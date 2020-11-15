import React, { Component} from 'react';
//import  './LoginTemplate.css';
import axios from 'axios';
import { withRouter } from "react-router-dom";
import { Form } from 'react-bootstrap';
import { Button } from 'react-bootstrap';


class RegisterTemplate extends Component {
  // 초기화
  state = {};

  // 로그인후 todos 페이지 이동
  doLogin = async () => {
    // 유저정보 체크
    const {user} = this.state;

    if( user === undefined) {
      alert("유저정보를 입력해주세요.");
      return;
    }
    // 계정 유효성 검사
    const response = await axios({
      method : 'post',
      url : '/user/doLogin',
      header : {
        'Content-Type': 'application/json'
      },
      data : {
        "userName" : user.userName,
        "password" : user.password
      }
    });

    if (response.data.response.indexOf("403") > -1 || response.data.response.indexOf("500") > -1) {
      alert(response.data.response);
      return;
    }

    alert("로그인 성공하였습니다.");
    // 하위컴포넌트에서 redirect시withRouter API import 하여 아래처럼 push
    this.props.history.push('/device');
  };

  // 회워가입 페이지 이동
  doReister = () => {
    // 하위컴포넌트에서 redirect시withRouter API import 하여 아래처럼 push
    this.props.history.push('/register');
  };

  doChange = (e) => {
    // state -> user 아래의 변수에 값 할당
    // 태그 이름을 쪼개어, state 변수에 할당한다.
    const dataTypeList = e.target.name.split(".");
    const value = e.target.value;
    const dataTypeLeng = dataTypeList.length;
    let currentDataType = this.state;
    for (let i=0;i<dataTypeLeng;i++){
      let dataType = dataTypeList[i];
      if (i === (dataTypeLeng-1)) {
          // 변수값 입력
          currentDataType[dataType] = value;
          continue;
      }

      if (currentDataType[dataType] === undefined ) {
          currentDataType[dataType]= {};
      }
      currentDataType = currentDataType[dataType];
    }
  };

  render() {
    const {user} = this.state;
    return (
      <main className="login-form">
        {/*<h1>장비관리자 페이지</h1>*/}

    	{/*	<input type="text" name="user.username" placeholder="Username" onChange={this.doChange}/>*/}
		  {/*	<input type="password" name="user.password" placeholder="Password" onChange={this.doChange}/>*/}
      {/*<input value="로그인" className="doLogin" type="submit" onClick={this.doLogin}/>*/}
      {/*<input value="회원가입" className="register" type="submit" onClick={this.doReister}/>*/}

        <div className="container h-100">
          <h1>장비관리자페이지</h1>
          <Form>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control name="user.userName" type="email" placeholder="Enter email" onChange={this.doChange} />
              <Form.Text className="text-muted">
                We'll never share your email with anyone else.
              </Form.Text>
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control name="user.password" type="password" placeholder="Password" onChange={this.doChange} />
            </Form.Group>
            <Form.Group controlId="formBasicCheckbox">
              <Form.Check type="checkbox" label="Check me out" />
            </Form.Group>
            <Button variant="primary" type="button" onClick={this.doLogin}>
              Submit
            </Button>
          </Form>
        </div>
      </main>

    );
  }
}
export default LoginTemplate;
