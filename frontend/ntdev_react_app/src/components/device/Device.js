import React, { Component} from 'react';
import axios from 'axios';
import DeviceDetailComp from './DeviceDetailComp';
import DevicePrimaryCompList from './DevicePrimaryCompList';
import magnifier from '../../img/magnifier.png';
import triangleDown from '../../img/triangleDown.png';
import  './Device.css';


class Device extends Component {
  // 초기화
  state = {
    devicePartContainer : {}
  };
  // 검색 질의 요청시, 컬럼 정의가 아래와 같이 되어있기떄문,.. 저형태로 씀
  doSearch = () => {
    let devicePartContainer = {
            part_id : 0,
            part_category : "",
            part_model : "",
            part_manufactor : "",
            device_id : 0,
            device_category : "",
            device_model : "",
            device_serial_number : "",
            cpu_info : "",
            ram_info : "",
            volume_info : "",
            device_info : "",
            primaryName : ""
    };

    this.setSate ({devicePartContainer});
  };
  // 검색 버튼 질의시 부품장비 리스트 호출
  getDevicePartList = async () => {
    /*
    const {devicePartContainer} = this.state;
    var self = this;
    await axios({
      method : 'post',
      url : '/devicePart/getList',
      header : {
        'Content-Type': 'application/json'
      },
      data : {
        "devicePartContainer" : devicePartContainer
      }
    }).then(function (response) {
      const {devicePartList} = response.data;
      this.setState({"devicePartList" : devicePartList});
    }).catch(function (error) {
      alert ("검색중 오류가 발생하여 작업을 중단합니다.");
    });
    */
  };

  render() {
    /*
      1. submit 버튼을 누르면 todos 페이지로 전환하게 하자
    */
    const {devicePartContainer} = this.state;
    return (
        <div className="row height-100">
          {/* 검색, 리스트 창 영억
              col-xs viewport width 300
              col-sm viewport width 800
              col-md viewport width 1000
              col-lg viewport width 1500
            */}
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 device-bg-1 height-100">
            {/*
              검색 영역
            */}
            <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 nopadding">
              <div className="input-group">
                <div className="input-group-prepend">
                  <span className="input-group-text" id="basic-addon1">
                    <img className="magnifier-cmp" src={magnifier}/>
                  </span>
                </div>
                <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                <div className="input-group-append">
                  <span className="input-group-text" onClick={this.doSearch}>
                    <img className="magnifier-cmp" src={triangleDown}/>
                  </span>
                </div>
              </div>
            </div>
            {/*
              리스트결과 영역
            */}
            <DevicePrimaryCompList devicePartContainer = {devicePartContainer}/>
          </div>
          {/* 장비 상세정보 창 영역
              col-xs viewport width 300
              col-sm viewport width 800
              col-md viewport width 1000
              col-lg viewport width 1500
            */}
          <div className="col-xs-8 col-sm-8 col-md-8 col-lg-8">
            <div className="row ml-3 device-bg-1 height-100">
              <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 h-25 p-4 text-Center">
                <h5><span className="badge badge-primary w-50 detail-title p-4">컴퓨터 관리</span></h5>
              </div>
              <ul className="list-group w-100">
                <DeviceDetailComp/>
              </ul>
              <button type="button" className="btn btn-danger mx-auto pl-4 pr-4 mb-2">저장</button>
            </div>
          </div>
        </div>

    );
  }
}
export default Device;
