import React, { Component} from 'react';
import axios from 'axios';
import { requestProxy} from '../common/Auth';
import labtop from '../../img/labtop.png';
import { Card, Button, ListGroup, Nav} from 'react-bootstrap';
import { faPlus} from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
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
            deviceId : 0,
            deviceCategory : "",
            deviceModel : "",
            deviceSerialNumber : "",
            cpuInfo : "",
            ramInfo : "",
            volumeInfo : "",
            deviceInfo : "",
            primaryName : "",
            partId : 0,
            partCategory : "",
            partModel : "",
            partManufactor : "",
            // 장비에 매핑되는 parts들
            parts : []
          },
          isChange : false,
          history : this.props.history,
  };
  // 첫 렌더링 마친후 일어나느 이벤트
  componentDidMount(){
    const { devicePartList, isChange, isSearchOne} = this.state;
    // 무한루프 방지, APP 실행시 첫 마운트에만 수행
    if (!isSearchOne) {
      return;
    }
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
    const { isChange } = this.state;
    if (!isSearchOne) {
      return;
    }
    this.getDevicePart(devicePartContainer);
    this.props.doSearchInitialize ();
  }

  // 장비, 부품 리스트중 하나를 선택시 데이터를 얻는 로직
  getDevicePart = async (devicePartContainer) => {
    // 검색 요청이 없을때 수행하지 않음
    if (devicePartContainer === null) {
      return;
    }
    // 초기화
    let eles = document.getElementsByClassName("partInfo");
    for (let i = 0; i < eles.length; i++) {
        eles[i].innerHTML = "";
    }
    document.querySelectorAll("partTab").forEach(el => el.remove());

    const urlInfo = '/devicePart/getDevicePart';
    const headerInfo = {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    };
    const data = await requestProxy(urlInfo, headerInfo, devicePartContainer, this.state).then(function(res) {
        return res;
    });
    if (data.devicePartContainer === undefined || data.devicePartContainer === null) {
      return;
    }
    // 초기화
    this.setState ({'devicePartContainer' : data.devicePartContainer});
  };

  doPartInfo = (e) => {
    const index = e.currentTarget.attributes.index.value;
    let listGroupItems = document.getElementsByClassName("partInfo");
    const parts = this.state.devicePartContainer.parts;
    listGroupItems[0].innerText = parts[index].partModel;
    listGroupItems[1].innerText = parts[index].partManufactor;
    listGroupItems[2].innerText = parts[index].partCategory;

  };
  render() {
    const {devicePartContainer} = this.state;
    const parts = devicePartContainer.parts;

    return (
      <div>
        <div className="row">
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <Card style={{ width: '18rem' }}>
              <Card.Header className="text-center">관리정보</Card.Header>
              <ListGroup variant="flush">
                <ListGroup.Item>관리번호: TODO 구현</ListGroup.Item>
                <ListGroup.Item>사용자: TODO 구현</ListGroup.Item>
                <ListGroup.Item>용도: TODO 구현</ListGroup.Item>
              </ListGroup>
            </Card>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <Card style={{ width: '18rem' }}>
              <Card.Header className="text-center">장비정보</Card.Header>
              <ListGroup variant="flush">
                <ListGroup.Item>장비명: <span className="deviceInfo">{devicePartContainer.deviceModel}</span></ListGroup.Item>
                <ListGroup.Item>시리얼넘버: <span className="deviceInfo">{devicePartContainer.deviceSerialNumber}</span></ListGroup.Item>
                <ListGroup.Item>CPU: <span className="deviceInfo">{devicePartContainer.cpuInfo}</span></ListGroup.Item>
                <ListGroup.Item>RAM: <span className="deviceInfo">{devicePartContainer.ramInfo}</span></ListGroup.Item>
                <ListGroup.Item>VOLUME: <span className="deviceInfo">{devicePartContainer.volumeInfo}</span></ListGroup.Item>
              </ListGroup>
            </Card>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <Card style={{ width: '18rem' }}>
              <Card.Header className="text-center">
                <Nav variant="tabs">
                { parts !== undefined && parts.length > 0
                  && parts.map ((part,index) => {
                    const number = index +1;
                    return (<Nav.Item>
                              <Nav.Link index={index} className="partTab" onClick={this.doPartInfo}>부품{index + 1}</Nav.Link>
                            </Nav.Item>);
                  })}
                  <Nav.Item>
                    <Nav.Link>
                        <FontAwesomeIcon icon={faPlus} />
                    </Nav.Link>
                  </Nav.Item>
                </Nav>
              </Card.Header>
              <ListGroup variant="flush">
                <ListGroup.Item>부품명:<span className="partInfo"></span></ListGroup.Item>
                <ListGroup.Item>제조사:<span className="partInfo"></span></ListGroup.Item>
                <ListGroup.Item>분류:<span className="partInfo"></span></ListGroup.Item>
              </ListGroup>
            </Card>
          </div>
          <div class="w-100"></div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <Card style={{ width: '18rem' }}>
              <Card.Header className="text-center">장비이력</Card.Header>
              <Card.Body>
                <div className="form-group">
                  <Card.Text>
                    <textarea className="form-control" rows="5" id="comment" name="text" style={{ fontSize: '8px' }}></textarea>
                  </Card.Text>
                </div>
              </Card.Body>
            </Card>
          </div>
        </div>
      </div>
    );
  }
}
export default DeviceDetailComp;
