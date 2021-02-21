import React, { Component} from 'react';
import { Card, ListGroup} from 'react-bootstrap';
import  './Device.css';

/**
 상세 장비 정보에서 장비 부분 UI 담당
**/
class DeviceDetailCompDevice extends Component {

  // 부품 정보 노출
  showDeivceInfo = (selectPartIndex) => {
    const devicePartContainer = this.props.devicePartContainer;

    let deviceInfoToDisplays = document.getElementsByClassName("deviceInfo");
    // device 정보란 input tag ReadOnly 활성화 처리
    for (let i = 0; i < deviceInfoToDisplays.length; i++) {
      deviceInfoToDisplays[i].readOnly= true;
    }
    deviceInfoToDisplays[0].value = devicePartContainer.deviceModel;
    deviceInfoToDisplays[1].value = devicePartContainer.deviceSerialNumber;
    deviceInfoToDisplays[2].value = devicePartContainer.cpuInfo;
    deviceInfoToDisplays[3].value = devicePartContainer.ramInfo;
    deviceInfoToDisplays[4].value = devicePartContainer.volumeInfo;
    deviceInfoToDisplays[5].value = devicePartContainer.deviceCategory;
    deviceInfoToDisplays[6].value = devicePartContainer.deviceId;
  }

  // 장비 부품 selectBox 선택시 함수 이벤트 처리
  onSelectedDeviceEventHandler = (e) => {
    // 부품 정보화면 초기화
    this.rsetDeviceInfoElementValues ();
    // selectBox 선택된 값이 숫자일때는 정보를 노출한다.
    const selectPartIndex = parseInt(e.target.value);
    if (isNaN(selectPartIndex)){
      return;
    }
    this.showDeivceInfo(selectPartIndex);
  }
  // 장비 부품 초기화
  rsetDeviceInfoElementValues = () => {
    let deviceInfoToDisplays = document.getElementsByClassName("deviceInfo");
    // 부품 정보 초기화
    for (let i = 0; i < deviceInfoToDisplays.length; i++) {
      deviceInfoToDisplays[i].value = "";
      deviceInfoToDisplays[i].readOnly= false;
    }
  }

  // 추가할 장비 데이터를 상위 컴포넌트로 전달
  propaGateAddDevice = (e) => {
    // 추가할 부품 정보 있는지 체크
    const deviceInfoToDisplays = document.getElementsByClassName("deviceInfo");
    for (let i = 0; i < deviceInfoToDisplays.length - 1; i++) {
        if (deviceInfoToDisplays[i].value.length === 0) {
          alert("장비 정보를 입력해 주세요.");
          return;
        }
    }
    // 추가된 정보를 바탕으로 상위 데이터로 전달
    this.props.addDevice ({
      "deviceModel" : deviceInfoToDisplays[0].value,
      "deviceSerialNumber" : deviceInfoToDisplays[1].value,
      "cpuInfo" : deviceInfoToDisplays[2].value,
      "ramInfo" : deviceInfoToDisplays[3].value,
      "volumeInfo" : deviceInfoToDisplays[4].value,
      "deviceCategory" : deviceInfoToDisplays[5].value,
    });
  };


  render() {
    const devicePartContainer = this.props.devicePartContainer;
    this.rsetDeviceInfoElementValues ();
    let subStrDeviceModel = devicePartContainer.deviceModel;
    if (subStrDeviceModel.length > 18 ) {
        subStrDeviceModel = subStrDeviceModel.substr(0, 19).trim() + "...";
    }
    return (
      <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
        <Card style={{ width: '18rem' }}>
          <Card.Header className="text-center">
            <select className="form-control devicePart-text-overflow-hidden" aria-label="Default select example" onChange={this.onSelectedDeviceEventHandler}>
              <option value="theme" selected>장비 정보</option>
              { devicePartContainer.deviceId > 0 &&
                <option className="devicePart-text-overflow-hidden" value="0">{subStrDeviceModel}</option>
              }
            </select>
          </Card.Header>
          <ListGroup variant="flush">
            <ListGroup.Item>장비명: <span><input type="text" className="deviceInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>S/N: <span><input type="text" className="deviceInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>CPU: <span><input type="text" className="deviceInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>RAM: <span><input type="text" className="deviceInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>볼륨: <span><input type="text" className="deviceInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>분류: <span><input type="text" className="deviceInfo border-none outline-none"/></span></ListGroup.Item>
            <span className="hidden"><input type="text" className="deviceInfo border-none outline-none"/></span>
            <ListGroup.Item>
              <div className="text-align-center">
                <button type="button" className="btn btn-danger btn-display-inline mx-auto pl-4 pr-4 mb-2" onClick={this.propaGateAddDevice}>추가</button>
              </div>
            </ListGroup.Item>
          </ListGroup>
        </Card>
      </div>
    );
  };
}

export default DeviceDetailCompDevice;
