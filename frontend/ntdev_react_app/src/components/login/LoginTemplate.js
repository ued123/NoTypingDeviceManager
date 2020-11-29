import React, { Component} from 'react';
import styles  from './LoginTemplate.css';
import axios from 'axios';
import { withRouter } from "react-router-dom";
import { faKey, faCubes, faDollyFlatbed, faUser } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"

class LoginTemplate extends Component {
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
      headers : {
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
    // 로그인 성공시 LocalStorage에 jwt 토큰 저장
    localStorage.setItem("token", response.data.Authorization);
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
      <div className="container d-flex justify-content-center h-100">
      <link rel="stylesheet" type="text/css" href='LoginTemplate.css' />
        {/* 로그인 Frame*/}
        <div className="card loginCard">
          <div className="card-header">
            <h3>장비 관리 서비스</h3>
            <div className="d-flex justify-content-end social_icon">
              <span>
                <FontAwesomeIcon icon={faCubes} />
              </span>
              <span>
                <FontAwesomeIcon icon={faDollyFlatbed} />
              </span>
            </div>
          </div>
          <div className="card-body">
            <form className="loginForm">
              <div className="input-group form-group">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text">
                      <FontAwesomeIcon icon={faUser} />
                    </span>
                  </div>
                  <input type="text" name="user.userName" className="form-control" placeholder="userName" onChange={this.doChange}/>
                </div>
               </div>
               <div className="input-group form-group">
                 <div className="input-group">
                   <div className="input-group-prepend">
                     <span className="input-group-text">
                       <FontAwesomeIcon icon={faKey} />
                     </span>
                   </div>
                   <input type="text" name="user.password" className="form-control" placeholder="password" onChange={this.doChange}/>
                 </div>
                </div>
                <div className="row align-items-center remember">
      						<input type="checkbox"/>Remember Me
      					</div>
                <div className="form-group">
                  <input type="button" value="login" className="btn float-right login_btn" onClick={this.doLogin}/>
                </div>
            </form>
          </div>
          <div className="card-footer">
            <div class="d-flex justify-content-center links">
    					Don't have an account?<a href="#">Sign Up</a>
    				</div>
            <div class="d-flex justify-content-center">
    					<a href="#">Forgot your password?</a>
    				</div>
          </div>
        </div>
      </div>

    );
  }
}
export default LoginTemplate;
