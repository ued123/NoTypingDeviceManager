import React, { Component} from 'react';
import axios from 'axios';
import DevicePrimaryComp from './DevicePrimaryComp';
import DeviceDetailComp from './DeviceDetailComp';
import magnifier from '../../img/magnifier.png';
import triangleDown from '../../img/triangleDown.png';
import  './Device.css';


class Device extends Component {

  render() {
    /*
      1. submit 버튼을 누르면 todos 페이지로 전환하게 하자
    */
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
                  <span className="input-group-text">
                    <img className="magnifier-cmp" src={triangleDown}/>
                  </span>
                </div>
              </div>
            </div>
            {/*
              리스트결과 영역
            */}
            <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 nopadding">
            {/*
              TODO: 리스트는 옅은 파랑  - 선택된 개체는 옅은빨강
              */}
              <ul class="list-group w-100">
                <DevicePrimaryComp></DevicePrimaryComp>
              </ul>
            </div>
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
              <ul class="list-group w-100">
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
