import React, { Component} from 'react';
import { Card, ListGroup} from 'react-bootstrap';
import  './Device.css';

/**
 상세 장비 정보에서 부품 부분 UI 담당
**/
class DeviceDetailCompParts extends Component {
  /**
    추가 되거나 변경될 part 변수 정의
  **/
  state = {
            partId : 0,
            partCategory : "",
            partModel : "",
            partManufactor : "",
  };

  // 장비 부품 selectBox 선택시 함수 이벤트 처리
  onSelectedPartEventHandler = (e) => {
    // 부품 정보화면 초기화
    this.rsetPartInfoElementValues ();
    // selectBox 선택된 값이 숫자일때는 정보를 노출한다.
    const selectPartIndex = parseInt(e.target.value);
    if (isNaN(selectPartIndex)){
      return;
    }
    this.showPartInfo(selectPartIndex);
  }
  // 장비 부품 초기화
  rsetPartInfoElementValues = () => {
    let listGroupItems = document.getElementsByClassName("partInfo");
    // 부품 정보 초기화
    for (let i = 0; i < listGroupItems.length; i++) {
      listGroupItems[i].value = "";
    }
  }
  // 부품 정보 노출
  showPartInfo = (selectPartIndex) => {
    // const index = e.currentTarget.attributes.index.value;
    const parts = this.props.parts;
    let listGroupItems = document.getElementsByClassName("partInfo");
    listGroupItems[0].value = parts[selectPartIndex].partModel;
    listGroupItems[1].value = parts[selectPartIndex].partManufactor;
    listGroupItems[2].value = parts[selectPartIndex].partCategory;
    listGroupItems[3].value = parts[selectPartIndex].partId;
  }

  //  추가 부품 데이터를 상위 컴포넌트로 전달
  propaGateAddPart = (e) => {
    // 추가할 부품 정보 있는지 체크
    let listGroupItems = document.getElementsByClassName("partInfo");
    for (let i = 0; i < listGroupItems.length - 1; i++) {
        if (listGroupItems[i].value.length === 0) {
          alert("부품 정보를 입력해 주세요.");
          return;
        }
    }
    // 추가된 정보를 바탕으로 상위 데이터로 전달
    this.props.addPart ({
      "partModel" : listGroupItems[0].value,
      "partManufactor" : listGroupItems[1].value,
      "partCategory" : listGroupItems[2].value,
    });
  };

  render() {
    const { parts }= this.props;
    this.rsetPartInfoElementValues ();
    return (
      <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4">
        <Card style={{ width: '18rem' }}>
          <Card.Header className="text-center">
            <select className="form-control devicePart-text-overflow-hidden" aria-label="Default select example" onChange={this.onSelectedPartEventHandler}>
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
            <ListGroup.Item>부품명:<span><input type="text" className="partInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>제조사:<span><input type="text" className="partInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>분류:<span><input type="text" className="partInfo border-none outline-none"/></span></ListGroup.Item>
            <span className="hidden"><input type="text" className="partInfo border-none outline-none"/></span>
            <ListGroup.Item>
              <div className="text-align-center">
                <button type="button" className="btn btn-danger btn-display-inline mx-auto pl-4 pr-4 mb-2" onClick={this.propaGateAddPart}>추가</button>
              </div>
            </ListGroup.Item>
          </ListGroup>
        </Card>
      </div>
    );
  };
}

export default DeviceDetailCompParts;
