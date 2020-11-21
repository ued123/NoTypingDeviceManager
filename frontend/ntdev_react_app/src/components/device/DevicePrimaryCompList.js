import React, { Component} from 'react';
import axios from 'axios';
import DevicePrimaryComp from './DevicePrimaryComp';
import { requestProxy} from '../common/Auth';

/*
장비 검색시 기본 정보포함 UI 생성
*/
class DevicePrimaryCompList extends Component {

    state = {
      devicePartList : [],
      isChange : false,
      history : this.props.history,
      redirectPath : ""
    };

    // 장비 하나 선택 후 deviceJs로 전달
    doSearchOne = (devicePart) => {
        // console.log (devicePart);
        this.props.doSearchOne(devicePart);
    };

    // 검색 버튼 질의시 부품장비 리스트 호출
    getDevicePartList = async (devicePartContainer) => {
      // 검색 요청이 없을때 수행하지 않음
      if (devicePartContainer === null) {
        return;
      }
      const urlInfo = '/devicePart/getList';
      const headerInfo = {
        'Content-Type': 'application/json',
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      };
      const data = await requestProxy(urlInfo, headerInfo, devicePartContainer, this.state).then(function(res) {
          return res;
      });
      this.setState ({'devicePartList' : data.devicePartList});
    };

    // componentDidUpdate 이후 발생 이벤트
    componentDidMount(){
      const { devicePartContainer } = this.props;
      const { devicePartList, isChange } = this.state;
      if (isChange) {
        return;
      }
      // device Component에 있는 search 변수 초기화
      this.setState ({isChange : true});
    }
    // state,props 변경시 일어나는 이벤트
    // 부모딴에서 비지니스 로직제어해야함
    // 컴포넌트 변경시 두번의 렌더링 업데이트 수행하므로 제어
    componentDidUpdate(prevProps, prevState){
      let { devicePartContainer,isSearchList } = this.props;
      // 무한루프 방지
      if (!isSearchList) {
        return;
      }
      this.getDevicePartList(devicePartContainer);
      this.props.doSearchInitialize ();
    }

  render() {
    const { devicePartList } = this.state;
    return (
          <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 device-bg-1 nopadding devicePartListWrapper">
            <ul className="list-group w-100">
                {devicePartList !== undefined && devicePartList.length > 0 &&
                  devicePartList.map((devicePart) => {
                    if (devicePart.deviceId !== undefined) {
                      devicePart.primaryName = devicePart.deviceModel + ' ' + devicePart.cpuInfo + ' ' + devicePart.ramInfo;
                    } else if (devicePart.partId !== undefined) {
                      devicePart.primaryName = devicePart.partModel + ' ' + devicePart.partManufactor;
                    }
                return (
                      <DevicePrimaryComp devicePart={devicePart} doSearchOne = {this.doSearchOne}/>
                    );
                  })}
            </ul>
          </div>
    );
  }
}
export default DevicePrimaryCompList;
