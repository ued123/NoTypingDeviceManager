import React, { Component} from 'react';
import { Card, ListGroup} from 'react-bootstrap';
import  './Device.css';
import modifyText from '../../img/modifyText.png';

/**
 상세 장비 정보에서 부품 부분 UI 담당
**/
class DeviceDetailCompParts extends Component {

  constructor(props) {
    	super(props);
      this.state = {
        partId : {
          prefix: "",
          value : "",
          clearTextField : false,
          readOnly : false
        },
        partCategory : {
          prefix: "분류:",
          value : "",
          clearTextField : false,
          readOnly : false
        },
        partModel : {
          prefix: "부품명:",
          value : "",
          clearTextField : false,
          readOnly : false
        },
        partManufactor : {
          prefix: "제조사:",
          value : [],
          clearTextField : false,
          readOnly : false
        },
        partFieldKeys : ["partModel", "partManufactor", "partCategory"],
        updateUI : false,
        selectedTab : "theme",
      };
  };
  // 수정 UI 클릭 이벤트 리스너
  onClickPartInfoModifer = (e) => {
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
  // 장비 부품 selectBox 선택시 함수 이벤트 처리
  onSelectedPartEventHandler = (e) => {
    // device 장비 정보가 있는 dropdown 이벤트일때만 처리
    const selectPartIndex = parseInt(e.target.value);
    const {parts} = this.props;
    const {partFieldKeys} = this.state;
    let isClearTextField = false;
    let updateUI = true;
    let deviceContainer = {};
    // 장비정보를 추가해야할때, 기존 textField 초기화
    // updateUI 비활성화 처리한다.
    if (isNaN(selectPartIndex)){
      isClearTextField = !isClearTextField;
      updateUI = !updateUI;
    }
    const partFields = [];
    // DeviceDetailComp에서 전달해준 변수중에 deviceTextField 매핑되는 UI를 생성
    for (const [index, value] of partFieldKeys.entries()) {
        //items.push(<li key={index}>{value}</li>)
        const partComp = this.state[value];
        deviceContainer[value] = {
          ...this.state[value],
          value : isNaN(selectPartIndex)? "" : parts[selectPartIndex][value],
          clearTextField : isClearTextField,
          // updateUI 수정 버튼 노출 여부이고, 이게 활성화 되더라도
          // 바로 수정할수는 없다
          readOnly : updateUI
        };
    }

    deviceContainer.updateUI = updateUI;
    this.setState (deviceContainer);
  }
  //  추가 부품 데이터를 상위 컴포넌트로 전달
  propaGateAddModifyPart = (e) => {
    const {partFieldKeys, updateUI, selectedTab} = this.state;
    const {parts} = this.props;
    const partsIndex = parseInt(selectedTab);
    let rsetPartComps = {};
    let toAddPartInfo = {
      'partId' : isNaN(partsIndex)? 0 : parts[partsIndex]['partId'],
    };

    // DeviceDetailComp에서 전달해준 변수중에 deviceTextField 매핑되는 UI를 생성
    for (const [index, value] of partFieldKeys.entries()) {
        // state 에 저장된 변수를 deep copy하여 저장
        const partComp = {...this.state[value]};
        toAddPartInfo [value] = partComp.value;
        if (partComp.value.length === 0) {
          alert("장비 정보를 입력해 주세요.");
          continue;
        }
        rsetPartComps[value] = {
          ...this.state[value],
          clearTextField : true,
          readOnly : false
        }
    }
    // (추가/변경)된 정보를 바탕으로 상위 데이터로 전달
    updateUI?  this.props.modifyDevice (toAddPartInfo) : this.props.addDevice (toAddPartInfo);
    // 정보 추가후 초기화
    this.setState({
      ...rsetPartComps,
      updateUI: false
    });
  };
  // deviceField 변경시 이벤트
  onChangeInputValue = (event) => {
      const partComp = this.state[event.target.name];
      this.setState({
          [event.target.name] : {
            ...partComp,
            value : event.target.value,
            clearTextField : false
          }
      });
   }

  render() {
    const { parts }= this.props;
    const {devicePartContainer} = this.props;
    const {updateUI, partFieldKeys} = this.state;
    const partFields = [];
    // DeviceDetailComp에서 전달해준 변수중에 deviceTextField 매핑되는 UI를 생성
    for (const [index, value] of partFieldKeys.entries()) {
        const partComp = this.state[value];
        let partField =
        (<ListGroup.Item>
          {partComp.prefix}
            <span>
              <input type="text" name={value} className={`devicePartField border-none outline-none ${updateUI ? (partComp.readOnly ? "" : "devicePartModifyBorder") :""}`}
                    value={ partComp.clearTextField? '' : partComp.value}
                    onChange={this.onChangeInputValue} readOnly = {partComp.readOnly}/>
              <img className={`deviceInfoModifer magnifier-cmp ${updateUI ? "" : "hidden" }`}
              src={modifyText} onClick={this.onClickPartInfoModifer} />
            </span>
        </ListGroup.Item>);
        partFields.push (partField);
    }
    return (
      <div className="col-xs col-sm col-md col-lg">
        <Card>
          <Card.Header className="text-center">
            <select className="form-control devicePart-text-overflow-hidden display-inline" aria-label="Default select example" onChange={this.onSelectedPartEventHandler}>
              <option value="theme" selected>부품 정보</option>
              { parts !== undefined && parts.length > 0
                && parts.map ((part,index) => {
                  let subStrPartModel = part.partModel;
                  if (subStrPartModel.length > 18 ) {
                      subStrPartModel = subStrPartModel.substr(0, 19).trim() + "...";
                  }
                  return (<option className="devicePart-text-overflow-hidden" value={index}>{subStrPartModel}</option>);
                })}
              </select>

          </Card.Header>
          <ListGroup variant="flush">
            {partFields}
            <ListGroup.Item>
              <div className="text-align-center">
                <button type="button" className={`partAddModify btn btn-display-inline mx-auto pl-4 pr-4 mb-2 ${updateUI? "btn-primary" : "btn-danger"}`}
                onClick={this.propaGateAddModifyPart}>{updateUI? "수정" : "추가"}</button>
              </div>
            </ListGroup.Item>
          </ListGroup>
        </Card>
      </div>
    );
  };
}

export default DeviceDetailCompParts;
