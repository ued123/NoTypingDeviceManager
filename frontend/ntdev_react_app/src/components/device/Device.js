import React, { Component} from 'react';
import axios from 'axios';
import DeviceDetailComp from './DeviceDetailComp';
import DevicePrimarySearch from './DevicePrimarySearch';
import DevicePrimaryCompList from './DevicePrimaryCompList';
import Header from '../common/Header';
import {Auth, goRoot} from '../common/Auth';
import magnifier from '../../img/magnifier.png';
import triangleDown from '../../img/triangleDown.png';
import triangleUp from '../../img/triangleUp.png';
import { Redirect, useHistory } from 'react-router-dom';
import  './Device.css';


class Device extends Component {
  // 초기화
  state = {
    devicePartContainer : {
            partId : 0,
            partCategory : "",
            partModel : "",
            partManufactor : "",
            deviceId : 0,
            deviceCategory : "",
            deviceModel : "",
            deviceSerialNumber : "",
            cpuInfo : "",
            ramInfo : "",
            volumeInfo : "",
            deviceInfo : "",
            primaryName : "",
            doSearchDefault : false
    },
    isSearchOne : true,
    showDetail : false,
    isSearchList : true,
    devicePrimaryCompListIsMount : false,
    isAuth : false
  };

  // 검색 질의 요청시, 컬럼 정의가 아래와 같이 되어있기떄문,.. 저형태로 씀
  doSearch = (devicePartContainer) => {

      this.setState({ isSearchList : true,
                     'devicePartContainer' : devicePartContainer
                    });
  };

  // 장비 하나 선택 후 deviceJs로 전달
  doSearchOne = (devicePart) => {

      // console.log (devicePart);
      const {devicePartContainer} = this.state;
      // lifeCycle Event 발생하지 않음
      devicePartContainer['deviceId'] = devicePart.deviceId;
      devicePartContainer['partId'] = devicePart.partId;
      // lifeCycle 발생
      this.setState({
        'isSearchOne' : false
      });

  };

  // DevicePrimaryCompList 호출후 isSearch 초기화 로직
  doSearchInitialize = () => {
      // this.setState 함수를 사용시, state 값이 변하면 component 렌더링을 수행한다.
      // thist.state.xx 를 통해 값을 변경할 경우는 렌더링을 수행하지 않는다.
      this.setState({
                      isSearchOne : true,
                      isSearchList : false,
                      devicePrimaryCompListIsMount : true
                    });
  };

  render() {
    const history = this.props.history;
    //장비,부품 질의에 필요한 변수, 상세검색 노출 , 검색 체크
    const {devicePartContainer,showDetail,isSearchOne, isSearchList} = this.state;
    return (
        <div className="row height-100">
          {/* Header */}
          <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 pb-2 headerWrapper">
            <Header />
          </div>
          {/* 검색, 리스트 창 영억
              col-xs viewport width 300
              col-sm viewport width 800
              col-md viewport width 1000
              col-lg viewport width 1500
            */}
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 height-100">
             {/*검색 영역 */}
            <DevicePrimarySearch doSearch = {this.doSearch}/>
            {/* 리스트결과 영역 */}
            <DevicePrimaryCompList devicePartContainer={devicePartContainer} isSearchList = {isSearchList}
            doSearchInitialize = {this.doSearchInitialize} doSearchOne = {this.doSearchOne} history = {history}/>
          </div>
          {/* 장비 상세정보 창 영역
              col-xs viewport width 300
              col-sm viewport width 800
              col-md viewport width 1000
              col-lg viewport width 1500
            */}
          {/*
          <div className="col-xs-8 col-sm-8 col-md-8 col-lg-8 device-bg-1 deviceDetailWrapper">
          <div className="col-xs-auto col-sm-auto col-md-auto col-lg-auto device-bg-1 deviceDetailWrapper">
          */}
          <div className="col-xs-8 col-sm-8 col-md-8 col-lg-8 device-bg-1 deviceDetailWrapper">
            <div className="row ml-3 h-100">
              <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 h-20 p-3 text-Center">
                <h5><span className="badge badge-primary w-50 detail-title p-3">컴퓨터 관리</span></h5>
              </div>
              <ul className="list-group w-100">
                <DeviceDetailComp isSearchOne = {isSearchOne} doSearchInitialize = {this.doSearchInitialize} devicePartContainer={devicePartContainer} history = {history}/>
              </ul>
              <button type="button" className="btn btn-danger mx-auto pl-4 pr-4 mb-2">저장</button>
            </div>
          </div>
        </div>
    );
  }
}
export default Device;
