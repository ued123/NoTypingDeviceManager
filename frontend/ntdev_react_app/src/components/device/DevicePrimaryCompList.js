import React, { Component} from 'react';
import axios from 'axios';
import DevicePrimaryComp from './DevicePrimaryComp';


/*
장비 검색시 기본 정보포함 UI 생성
*/
class DevicePrimaryCompList extends Component {

    state = {
      devicePartList : []
    };

    // 검색 버튼 질의시 부품장비 리스트 호출
    getDevicePartList = async (devicePartContainer) => {
      const response = await axios({
        method : 'post',
        url : '/devicePart/getList',
        header : {
          'Content-Type': 'application/json'
        },
        data : {
          "devicePartContainer" : devicePartContainer
        }
      }).catch(function (error) {
        alert ("검색중 오류가 발생하여 작업을 중단합니다.");
      });
      this.setState ({'devicePartList' : response.data.devicePartList});
    };

  componentDidMount() {
    const { devicePartContainer } = this.props;
    this.getDevicePartList(devicePartContainer);
  }


  render() {
    const { devicePartList } = this.state;

    return (
          <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 nopadding">
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
