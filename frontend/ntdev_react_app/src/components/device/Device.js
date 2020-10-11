import React, { Component} from 'react';
import axios from 'axios';
import DeviceDetailComp from './DeviceDetailComp';
import DevicePrimarySearch from './DevicePrimarySearch';
import DevicePrimaryCompList from './DevicePrimaryCompList';
import magnifier from '../../img/magnifier.png';
import triangleDown from '../../img/triangleDown.png';
import triangleUp from '../../img/triangleUp.png';
import  './Device.css';


class Device extends Component {
  // 초기화
  state = {
    devicePartContainer : {
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
    },
    showDetail : false,
    isSearch : true,
    devicePrimaryCompListIsMount : false
  };

  // 검색 질의 요청시, 컬럼 정의가 아래와 같이 되어있기떄문,.. 저형태로 씀
  doSearch = (devicePartContainer) => {
      this.setState({isSearch : true,
                     'devicePartContainer' : devicePartContainer
                    });
  };

  // DevicePrimaryCompList 호출후 isSearch 초기화 로직
  doSearchInitialize = () => {
      this.setState({
                      isSearch : false,
                      devicePrimaryCompListIsMount : true,
                    });
  };

  render() {
    //장비,부품 질의에 필요한 변수, 상세검색 노출 , 검색 체크
    const {devicePartContainer,showDetail,isSearch} = this.state;
    return (
        <div className="row height-100">
          {/* 검색, 리스트 창 영억
              col-xs viewport width 300
              col-sm viewport width 800
              col-md viewport width 1000
              col-lg viewport width 1500
            */}
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 height-100">
            {/*검색 영역 */}
            <DevicePrimarySearch doSearch = {this.doSearch}></DevicePrimarySearch>
            {/* 리스트결과 영역 */}
            <DevicePrimaryCompList devicePartContainer={devicePartContainer} isSearch = {isSearch} doSearchInitialize = {this.doSearchInitialize} />
          </div>
          {/* 장비 상세정보 창 영역
              col-xs viewport width 300
              col-sm viewport width 800
              col-md viewport width 1000
              col-lg viewport width 1500
            */}
          <div className="col-xs-8 col-sm-8 col-md-8 col-lg-8 device-bg-1 deviceDetailWrapper">
            <div className="row ml-3 h-100">
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
