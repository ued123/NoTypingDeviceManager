import React, { Component} from 'react';
import magnifier from '../../img/magnifier.png';
import triangleDown from '../../img/triangleDown.png';
import triangleUp from '../../img/triangleUp.png';
import  './Device.css';



/*
   검색조건 UI 생성
*/
class DevicePrimarySearch extends Component {
  state = {
    devicePartContainer : {
            partId : 0,
            partCategory : "",
            partModel : "",
            partManufactor : "",
            deviceId : 0,
            deviceCategory : "",
            deviceModel : "",
            deviceSerialNumber : "",
            cpuInfo : "",
            ramInfo : "",
            volumeInfo : "",
            deviceInfo : "",
            primaryName : "",
            doSearchDefault : false
    },
    showDetail : false,
    isSearchList : false
  };
  // 검색 조건 초기화
  searchInitialize = (e) => {
    // state -> user 아래의 변수에 값 할당
    // 태그 이름을 쪼개어, state 변수에 할당한다.
    const searchTextList = document.getElementsByClassName("search-text");
    const length = searchTextList.length;
    for (let i=0; i < length; i++) {
      searchTextList[i].value = "";
    }
    let {devicePartContainer} = this.state;
    devicePartContainer['doSearchDefault'] = false;
  };


  doChange = (e) => {
    // state -> user 아래의 변수에 값 할당
    // 태그 이름을 쪼개어, state 변수에 할당한다.
    const dataTypeList = e.target.name.split(".");
    const value = e.target.value;
    const dataTypeLeng = dataTypeList.length;
    let currentDataType = this.state;
    for (let i=0;i<dataTypeLeng;i++){
      let dataType = dataTypeList[i];
      if (i === (dataTypeLeng-1)) {
          // 변수값 입력
          currentDataType[dataType] = value;
          continue;
      }

      if (currentDataType[dataType] === undefined ) {
          currentDataType[dataType]= {};
      }
      currentDataType = currentDataType[dataType];
    }
  };

  // 상세검색
  searchDetail = () => {
    const {showDetail} = this.state;
    this.setState({
                    showDetail : !showDetail
                  });

    let {devicePartContainer} = this.state;
    let searchWrapper = document.getElementsByClassName("search-wrapper")[0];
    devicePartContainer['partModel'] = "";
    devicePartContainer['deviceModel'] = "";
    devicePartContainer['doSearchDefault'] = false;
    //기본 검색 TEXT 초기화
    document.getElementsByClassName("search-text")[0].value = "";
    // 상세보기 태그 활성화 비활성화
    if (showDetail) {
      searchWrapper.classList.add("hidden");
    } else {
      this.searchInitialize();
      searchWrapper.classList.remove("hidden");
    }

  };

  // 기본 검색 [part_model, device_model 검색한다.]
  searchDefault = (e) => {
    this.setState ({
      devicePartContainer : {
        'partModel' : e.target.value,
        'deviceModel' : e.target.value,
        'doSearchDefault' : true
      }
    }, () => {
      this.props.doSearch (this.state.devicePartContainer);
    });

  };

  doSearch = (e) => {
    this.props.doSearch (this.state.devicePartContainer);
    this.searchInitialize ();
    document.getElementsByClassName("search-wrapper")[0].classList.add("hidden");
    this.setState({
                    showDetail : false
                  });
  }

  render() {
    /*
    장비 검색을 위한 필요한 필드

    part_model = Characters.BLANK;
    part_manufactor = Characters.BLANK;

    device_model = Characters.BLANK;
    cpu_info = Characters.BLANK;
    ram_info = Characters.BLANK;
    volume_info = Characters.BLANK;
    device_info = Characters.BLANK;

    */
    const {showDetail,isSearchList} = this.state;
    return (
          <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 nopadding search">
            {/*일반 검색 및 상세 버튼*/}
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1" onClick={this.doSearch}>
                  <img className="magnifier-cmp" src={magnifier}/>
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" onChange={this.searchDefault}/>
              <div className="input-group-append">
                <span className="input-group-text" onClick={this.searchDetail}>
                  <img className="magnifier-cmp" src={showDetail? triangleUp : triangleDown} />
                </span>
              </div>
            </div>
            {/* 상세검색*/}
            <div className="search-wrapper row nopadding pt-3 hidden">
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      장비 모델
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.deviceModel" className="form-control search-text" aria-label="Amount (to the nearest dollar)" onChange={this.doChange}/>
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      CPU 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.cpuInfo" className="form-control search-text" aria-label="Amount (to the nearest dollar)"  onChange={this.doChange}/>
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      RAM 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.ramInfo" className="form-control search-text" onChange={this.doChange}/>
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      볼륨 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.volumeInfo" className="form-control search-text" onChange={this.doChange}/>
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      기타 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.deviceInfo" className="form-control search-text" onChange={this.doChange}/>
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      부품 모델
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.partModel" className="form-control search-text" onChange={this.doChange}/>
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      부품 제조사
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.partManufactor" className="form-control search-text" onChange={this.doChange}/>
                </div>
              </div>

            </div>
          </div>
    );
  }
}
export default DevicePrimarySearch;
