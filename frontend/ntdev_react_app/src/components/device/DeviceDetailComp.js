import React, { Component} from 'react';
import axios from 'axios';
import { requestProxy} from '../common/Auth';
import labtop from '../../img/labtop.png';
import  './Device.css';


/*
장비 검색시 기본 정보포함 UI 생성
*/
class DeviceDetailComp extends Component {
  /**
    1. 장비리스트중 선택시 -> 장비의 대한 내용 업데이트 처리하도록 하기
    2. 장비리스트중 선택하지 않은 경우 -> 장비 /부품정보 추가
    3. 장비(parent)를 부품정보(child)를 참조할수 있다. (1:n)
    4. 부품정보는 오로지 하나의 장비를 매핑한다 (1:1)
    부품이 선택된경우 부품 정보만 노출
    장비가 선택된경우 연관된 부품정보 노출
  **/
  state = {
    devicePartContainer : {
            device_id : 0,
            device_category : "",
            device_model : "",
            device_serial_number : "",
            cpu_info : "",
            ram_info : "",
            volume_info : "",
            device_info : "",
            primaryName : "",
            /*
            device_id : 1,
            device_category : "cateTESET",
            device_model : "modelTest",
            device_serial_number : "serialTEST",
            cpu_info : "CPUTEST",
            ram_info : "RAMTEST",
            volume_info : "VOLUMETEST",
            device_info : "DEIVCEINFOTEST",
            primaryName : "ss",
            */
            part_id : 0,
            part_category : "",
            part_model : "",
            part_manufactor : "",
            // 장비에 매핑되는 parts들
            partList : []
          },
          isChange : false,
          history : this.props.history,
  };
  // 첫 렌더링 마친후 일어나느 이벤트
  componentDidMount(){
    const { devicePartList, isChange } = this.state;
    // 무한루프 방지, APP 실행시 첫 마운트에만 수행
    if (isChange) {
      return;
    }
    this.setState ({isChange : true});
  }
  //state,props 변경시 일어나는 이벤트
  // 부모딴에서  비지니스 로직제어해야함
  // 컴포넌트 변경시 두번의 렌더링 업데이트 수행하므로 제어
  componentDidUpdate(prevProps, prevState){
    let { devicePartContainer,isSearchOne } = this.props;
    //  선택한 부품/장비만 검색하도록 처리
    if (isSearchOne) {
      return;
    }
    // debug
    //console.log (devicePartContainer.doSearchDefault);
    this.getDevicePart(devicePartContainer);
    // devic Component에 있는 search 변수 초기화
    this.props.doSearchInitialize ();
  }

  // 장비, 부품 리스트중 하나를 선택시 데이터를 얻는 로직
  getDevicePart = async (devicePartContainer) => {
    // 검색 요청이 없을때 수행하지 않음
    if (devicePartContainer === null) {
      return;
    }
    const urlInfo = '/devicePart/getDevicePart';
    const headerInfo = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    };
    const data = await requestProxy(urlInfo, headerInfo, devicePartContainer, this.state).then(function(res) {
        return res;
    });
    this.setState ({'devicePartContainer' : data.devicePart});
  };

  render() {
    const {devicePartContainer} = this.state;

    return (
      <div>
      {/* 관리번호 사용자  용도row*/}
        <li className="list-group-item device-bg-1 border-none">
        <div className="row">
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  관리 번호
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" value= {devicePartContainer.device_id}/>
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  사용자
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)"  value="TODO 구현"/>
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  용도
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" value ="TODO 구현"/>
            </div>
          </div>
        </div>
        </li>
        {/* 모델명, 시리얼 넘버 row*/}
        <li className="list-group-item device-bg-1 border-none">
        <div className="row">
          <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  모델명
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)"  value= {devicePartContainer.device_model}/>
            </div>
          </div>
          <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  시리얼넘버
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" value= {devicePartContainer.device_serial_number}/>
            </div>
          </div>
        </div>
        </li>
        {/*
          cpu_info VARCHAR(20) default '',
          ram_info VARCHAR(20) default '',
          volume_info VARCHAR(20) default '',
          device_info VARCHAR(20) default '',
          CPU, RAM, VOLUME, DEVICE_INFO

          */}
        <li className="list-group-item device-bg-1 border-none">
        <div className="row">
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  CPU
                </span>
                </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" value= {devicePartContainer.cpu_info}/>
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  RAM
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)"  value= {devicePartContainer.ram_info}/>
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  VOLUME
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)"  value= {devicePartContainer.volume_info}/>
            </div>
          </div>
        </div>
        </li>
        {/* TODO PART제품 정보도 포함하기 row*/}
        <li className="list-group-item device-bg-1 border-none">
          <div className="row">
            <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-Center ">
              <div className="form-group">
                <label>TODO: 파트정보</label>

              </div>
            </div>
          </div>
        </li>
        {/* 고객사 이력사항 row*/}
        <li className="list-group-item device-bg-1 border-none">
          <div className="row">
            <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-Center ">
              <div className="form-group">
                <label>장비 이력</label>
                <textarea className="form-control" rows="5" id="comment" name="text"></textarea>
              </div>
            </div>
          </div>
        </li>
        </div>
    );
  }
}
export default DeviceDetailComp;
