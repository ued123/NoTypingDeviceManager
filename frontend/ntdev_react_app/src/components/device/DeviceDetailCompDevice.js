import React, { Component} from 'react';
import { Card, ListGroup} from 'react-bootstrap';
import  './Device.css';
import '../../components/common/Common.css';
import modifyText from '../../img/modifyText.png';

/**
 상세 장비 정보에서 장비 부분 UI 담당
**/
class DeviceDetailCompDevice extends Component {
  constructor(props) {
    	super(props);
      this.state = {
        deviceId : {
          prefix: "",
          value : (props.deviceId === undefined)? "" : props.deviceId,
          clearTextField : false,
          readOnly : false
        },
        deviceCategory : {
          prefix: "분류:",
          value : (props.deviceCategory === undefined)? "" : props.deviceCategory,
          clearTextField : false,
          readOnly : false
        },
        deviceModel : {
          prefix: "장비명:",
          value : (props.deviceModel === undefined)? "" : props.deviceModel,
          clearTextField : false,
          readOnly : false
        },
        deviceSerialNumber : {
          prefix: "S/N:",
          value : (props.deviceSerialNumber === undefined)? "" : props.deviceSerialNumber,
          clearTextField : false,
          readOnly : false
        },
        cpuInfo : {
          prefix: "CPU:",
          value : (props.cpuInfo === undefined)? "" : props.cpuInfo,
          clearTextField : false,
          readOnly : false
        },
        ramInfo : {
          prefix: "RAM:",
          value : (props.ramInfo === undefined)? "" : props.ramInfo,
          clearTextField : false,
          readOnly : false
        },
        volumeInfo : {
          prefix: "볼륨:",
          value : (props.volumeInfo === undefined)? "" : props.volumeInfo,
          clearTextField : false,
          readOnly : false
        },
        deviceInfo : {
          prefix: "장비정보:",
          value : (props.deviceInfo === undefined)? "" : props.deviceInfo,
          clearTextField : false,
          readOnly : false
        },
        deviceFieldKeys : ["deviceModel", "deviceSerialNumber", "cpuInfo", "ramInfo", "volumeInfo"],
        updateUI : false,
      };
  };
  // 수정 UI 클릭 이벤트 리스너
  onClickDeviceInfoModifer = (e) => {
    const deviceCompName = e.target.previousSibling.name
    // 수정 UI 클릭시, 그 이전 element를 호출한다.
    const deviceComp = this.state[deviceCompName];
    // state에 변경 옵션을 노출한다.
    // deviceField를 수정 가능하게 처리한다.
    this.setState ({
      [deviceCompName] :{
        ...deviceComp,
        readOnly : !deviceComp.readOnly
      }
    });
  }
  // dropdown에서 장비정보 선택시 이벤트 핸들러
  onSelectedDeviceEventHandler = (e) => {
    // device 장비 정보가 있는 dropdown 이벤트일때만 처리
    const selectDeviceIndex = parseInt(e.target.value);
    const {devicePartContainer} = this.props;
    const {deviceFieldKeys} = this.state;
    let isClearTextField = false;
    let updateUI = true;
    let readOnly = true;
    let deviceContainer = {};
    // 장비정보를 추가해야할때, 기존 textField 초기화
    // updateUI 비활성화 처리한다.
    if (isNaN(selectDeviceIndex)){
      isClearTextField = true;
      updateUI = false;
      readOnly = false;
    }
    const deviceFields = [];
    // DeviceDetailComp에서 전달해준 변수중에 deviceTextField 매핑되는 UI를 생성
    for (const [index, value] of deviceFieldKeys.entries()) {
        //items.push(<li key={index}>{value}</li>)
        const deviceComp = this.state[value];
        deviceContainer[value] = {
          ...this.state[value],
          value : devicePartContainer[value],
          clearTextField : isClearTextField,
          // updateUI 수정 버튼 노출 여부이고, 이게 활성화 되더라도
          // 바로 수정할수는 없다
          'readOnly' : readOnly
        };
    }

    deviceContainer.updateUI = updateUI;
    this.setState (deviceContainer);
  }

  // 수정할 정보를 상위 컴포넌트로 전달
  propaGateAddModifyDevice = (e) => {
    const {deviceFieldKeys, updateUI} = this.state;
    const devicePartContainer = this.props.devicePartContainer;
    let rsetDeviceComps = {};
    let toAddDeviceInfo = {
      'deviceCategory' : devicePartContainer.deviceCategory,
      'deviceInfo' : devicePartContainer.deviceInfo,
      'deviceId' : devicePartContainer.deviceId,
    };

    // DeviceDetailComp에서 전달해준 변수중에 deviceTextField 매핑되는 UI를 생성
    for (const [index, value] of deviceFieldKeys.entries()) {
        // state 에 저장된 변수를 deep copy하여 저장
        const deviceComp = {...this.state[value]};
        toAddDeviceInfo [value] = deviceComp.value;

        if (deviceComp.value.length === 0) {
          alert("장비 정보를 입력해 주세요.");
          continue;
        }
        rsetDeviceComps[value] = {
          ...this.state[value],
          clearTextField : true,
          readOnly : false
        }
    }
    // (추가/변경)된 정보를 바탕으로 상위 데이터로 전달
    updateUI?  this.props.modifyDevice (toAddDeviceInfo) : this.props.addDevice (toAddDeviceInfo);
    // 정보 추가후 초기화
    this.setState({
      ...rsetDeviceComps,
      updateUI: false
    });

  };

  // deviceField 변경시 이벤트
  onChangeInputValue = (event) => {
      const deviceComp = this.state[event.target.name];
      this.setState({
          [event.target.name] : {
            ...deviceComp,
            value : event.target.value,
            clearTextField : false
          }
      });
   }

  render() {
    const {devicePartContainer} = this.props;
    const {updateUI, deviceFieldKeys} = this.state;
    let subStrDeviceModel = devicePartContainer.deviceModel;
    if (subStrDeviceModel.length > 18 ) {
        subStrDeviceModel = subStrDeviceModel.substr(0, 19).trim() + "...";
    }
    const deviceFields = [];
    // DeviceDetailComp에서 전달해준 변수중에 deviceTextField 매핑되는 UI를 생성
    for (const [index, value] of deviceFieldKeys.entries()) {
        //items.push(<li key={index}>{value}</li>)
        const deviceComp = this.state[value];
        let deviceField =
        (<ListGroup.Item>
          {deviceComp.prefix}
            <span>
              <input type="text" name={value} className={`devicePartField border-none outline-none ${updateUI ? (deviceComp.readOnly ? "" : "devicePartModifyBorder") :""}`}
                    value={ deviceComp.clearTextField? '' : deviceComp.value}
                    onChange={this.onChangeInputValue} readOnly = {deviceComp.readOnly}/>
              <img className={`deviceInfoModifer magnifier-cmp ${updateUI ? "" : "hidden" }`}
              src={modifyText} onClick={this.onClickDeviceInfoModifer} />
            </span>
        </ListGroup.Item>);
        deviceFields.push (deviceField);
    }
    return (
      <div className="col-xs col-sm col-md col-lg">
        <Card>
          <Card.Header className="text-center">
            <select className="form-control devicePart-text-overflow-hidden display-inline" aria-label="Default select example" onClick={this.onSelectedDeviceEventHandler}>
              <option value="theme" selected>장비 정보</option>
              { devicePartContainer.deviceId > 0 &&
                <option className="devicePart-text-overflow-hidden" value="0">{subStrDeviceModel}</option>
              }
            </select>
          </Card.Header>
          <ListGroup variant="flush">
            {deviceFields}
            <ListGroup.Item>
              <div className="text-align-center">
                <button type="button" className={`deviceAddModify btn btn-display-inline mx-auto pl-4 pr-4 mb-2 ${updateUI? "btn-primary" : "btn-danger"}`}
                onClick={this.propaGateAddModifyDevice}>{updateUI? "수정" : "추가"}</button>
              </div>
            </ListGroup.Item>
          </ListGroup>
        </Card>
      </div>
    );
  };
}

export default DeviceDetailCompDevice;
