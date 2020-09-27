import React, { Component} from 'react';
import  './Device.css';


/*
   검색조건 UI 생성
*/
class DevicePrimarySearch extends Component {
  state = {
    devicePartContainer : {
            part_id : 0,
            part_category : "",
            part_model : "",
            part_manufactor : "",
            device_id : 0,
            device_category : "",
            device_model : "",
            device_serial_number : "",
            cpu_info : "",
            ram_info : "",
            volume_info : "",
            device_info : "",
            primaryName : ""
    }

  };
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

    return (
            <div className="search-wrapper row nopadding pt-3">
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 ">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      장비 모델
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.device_model" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      CPU 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.cpu_info" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      RAM 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.ram_info" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      볼륨 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.volume_info" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      기타 정보
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.device_info" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      부품 모델
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.part_model" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>
              <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                <div className="input-group">
                  <div className="input-group-prepend">
                    <span className="input-group-text search-label-font" id="basic-addon1">
                      부품 제조사
                    </span>
                  </div>
                  <input type="text" name="devicePartContainer.part_manufactor" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
                </div>
              </div>

            </div>

    );
  }
}
export default DevicePrimarySearch;
