import React, { Component} from 'react';
import axios from 'axios';
import DevicePrimaryComp from './DevicePrimaryComp';


/*
장비 검색시 기본 정보포함 UI 생성
*/
class DevicePrimaryCompList extends Component {

    state = {
      devicePartList : [],
    };

    // 검색 버튼 질의시 부품장비 리스트 호출
    getDevicePartList = async (devicePartContainer) => {
      // 검색 요청이 없을때 수행하지 않음
      if (devicePartContainer === null) {
        return;
      }

      const response = await axios({
        method : 'post',
        url : '/devicePart/getList',
        header : {
          'Content-Type': 'application/json'
        },
        data : devicePartContainer
      }).catch(function (error) {
        alert ("검색중 오류가 발생하여 작업을 중단합니다.");
      });

      if (response === undefined) {
        return;
      }

      this.setState ({'devicePartList' : response.data.devicePartList});
    };

    componentDidUpdate(prevProps, prevState){
      const { devicePartContainer,isSearch } = this.props;
      // 무한루프 방지
      if (!isSearch) {
        return;
      }
      this.getDevicePartList(devicePartContainer);
      // devic Component에 있는 search 변수 초기화
      this.props.doSearchInitialize ();
    }

  render() {
    const { devicePartList } = this.state;
    return (
          <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 device-bg-1 nopadding devicePartListWrapper">
            <ul className="list-group w-100">
                {devicePartList.length > 0 &&
                  devicePartList.map((devicePart) => {
                    if (devicePart.deviceId !== undefined) {
                      devicePart.primaryName = devicePart.deviceModel + ' ' + devicePart.cpuInfo + ' ' + devicePart.ramInfo;
                    } else if (devicePart.partId !== undefined) {
                      devicePart.primaryName = devicePart.partModel + ' ' + devicePart.partManufactor;
                    }
                return (
                      <DevicePrimaryComp
                        devicePart={devicePart}
                      />
                    );
                  })}
            </ul>
          </div>
    );
  }
}
export default DevicePrimaryCompList;
