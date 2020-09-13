import React, { Component} from 'react';
import axios from 'axios';
import labtop from '../../img/labtop.png';
import  './Device.css';


/*
장비 검색시 기본 정보포함 UI 생성
*/
class DeviceDetailComp extends Component {

  render() {

    return (
      <div>
      {/* 관리번호 사용자  용도row*/}
        <li class="list-group-item device-bg-1 border-none">
        <div className="row">
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  관리 번호
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  사용자
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  용도
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
        </div>
        </li>
        {/* 모델명, 시리얼 넘버 row*/}
        <li class="list-group-item device-bg-1 border-none">
        <div className="row">
          <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  모델명
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
          <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  시리얼넘버
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
        </div>
        </li>
        {/*
          cpu_info VARCHAR(20) default '',
          ram_info VARCHAR(20) default '',
          volume_info VARCHAR(20) default '',
          device_info VARCHAR(20) default '',
          CPU, RAM, VOLUME, DEVICE_INFO

          */}
        <li class="list-group-item device-bg-1 border-none">
        <div className="row">
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  CPU
                </span>
                </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  RAM
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
          <div className="col-xs-4 col-sm-4 col-md-4 col-lg-4 text-Center ">
            <div className="input-group">
              <div className="input-group-prepend">
                <span className="input-group-text" id="basic-addon1">
                  VOLUME
                </span>
              </div>
              <input type="text" className="form-control search-text" aria-label="Amount (to the nearest dollar)" />
            </div>
          </div>
        </div>
        </li>
        {/* TODO PART제품 정보도 포함하기 row*/}
        <li class="list-group-item device-bg-1 border-none">
          <div className="row">
            <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-Center ">
              <div class="form-group">
                <label for="comment">TODO: 파트정보</label>

              </div>
            </div>
          </div>
        </li>
        {/* 고객사 이력사항 row*/}
        <li class="list-group-item device-bg-1 border-none">
          <div className="row">
            <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-Center ">
              <div class="form-group">
                <label for="comment">장비 이력</label>
                <textarea class="form-control" rows="5" id="comment" name="text"></textarea>
              </div>
            </div>
          </div>
        </li>
        </div>
    );
  }
}
export default DeviceDetailComp;
