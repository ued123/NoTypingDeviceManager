import React, { Component} from 'react';
import { Card, ListGroup} from 'react-bootstrap';
import  './Device.css';
import modifyText from '../../img/modifyText.png';

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
    let partAddModifyBtn =document.getElementsByClassName("partAddModify")[0];
    if (isNaN(selectPartIndex)){
      // 수정 버튼을 추가 버튼으로 변경
      partAddModifyBtn.classList.add('btn-danger');
      partAddModifyBtn.classList.remove('btn-primary');
      partAddModifyBtn.innerText="추가";
      return;
    }
    this.showPartInfo(selectPartIndex);
    // 추가 버튼을 수정 버튼으로 변경
    partAddModifyBtn.classList.remove('btn-danger');
    partAddModifyBtn.classList.add('btn-primary');
    partAddModifyBtn.innerText="수정";
  }
  // 장비 부품 초기화
  rsetPartInfoElementValues = () => {
    let partsInfoToDisplays = document.getElementsByClassName("partInfo");
    // 부품 정보 초기화
    for (let i = 0; i < partsInfoToDisplays.length; i++) {
      partsInfoToDisplays[i].value = "";
    }
  }
  // 부품 정보 노출
  showPartInfo = (selectPartIndex) => {
    // const index = e.currentTarget.attributes.index.value;
    const parts = this.props.parts;

    let partsInfoToDisplays = document.getElementsByClassName("partInfo");
    partsInfoToDisplays[0].value = parts[selectPartIndex].partModel;
    partsInfoToDisplays[1].value = parts[selectPartIndex].partManufactor;
    partsInfoToDisplays[2].value = parts[selectPartIndex].partCategory;
    partsInfoToDisplays[3].value = parts[selectPartIndex].partId;
  }

  //  추가 부품 데이터를 상위 컴포넌트로 전달
  propaGateAddPart = (e) => {
    // 추가할 부품 정보 있는지 체크
    let partsInfoToDisplays = document.getElementsByClassName("partInfo");
    for (let i = 0; i < partsInfoToDisplays.length-1; i++) {
        if (partsInfoToDisplays[i].value.length === 0) {
          alert("부품 정보를 입력해 주세요.");
          return;
        }
    }
    // 추가된 정보를 바탕으로 상위 데이터로 전달
    this.props.addPart ({
      "partModel" : partsInfoToDisplays[0].value,
      "partManufactor" : partsInfoToDisplays[1].value,
      "partCategory" : partsInfoToDisplays[2].value,
    });
  };

  render() {
    const { parts }= this.props;

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
            <ListGroup.Item>부품명:<span><input type="text" className="partInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>제조사:<span><input type="text" className="partInfo border-none outline-none"/></span></ListGroup.Item>
            <ListGroup.Item>분류:<span><input type="text" className="partInfo border-none outline-none"/></span></ListGroup.Item>
            <span className="hidden"><input type="text" className="partInfo border-none outline-none"/></span>
            <ListGroup.Item>
              <div className="text-align-center">
                <button type="button" className="partAddModify btn btn-danger btn-display-inline mx-auto pl-4 pr-4 mb-2" onClick={this.propaGateAddPart}>추가</button>
              </div>
            </ListGroup.Item>
          </ListGroup>
        </Card>
      </div>
    );
  };
}

export default DeviceDetailCompParts;
