import React, { useState} from 'react';
import {Redirect} from 'react-router-dom';
import axios from 'axios';

{/*
  JWT 인증 검사
  */}
export function Auth() {
  // 계정 유효성 검사
  axios.defaults.headers.common['Content-Type'] = 'application/json';
  axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token');

  return axios.post('/user/doAuth', {
    headers: {
      'Content-Type': 'application/json',
    }
  });
}

export async function requestProxy(urlInfo, headerInfo, dataInfo, state) {
  const response = await axios({
    method : 'post',
    url : urlInfo,
    headers : headerInfo,
    data : dataInfo
  }).catch(function (error) {
    alert ("요청 실패로 인해 작업을 중단합니다.");
  });
  // 요청 실패
  if (response === undefined) {
    state.history.push("/");
    return false;
  }
  // 인증 실패
  if (response.data.response.indexOf("403") > -1) {
    alert ("유효하지 않은 계정 정보이므로, 로그인 창으로 이동합니다.");
    state.history.push("/");
    return false;
  }

  return response.data;

}



{/* redirect*/}
export function goBack() {

  return (<Redirect to={{pathname: '/'}}/>);
}
