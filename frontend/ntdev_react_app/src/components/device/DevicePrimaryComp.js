import React, { Component} from 'react';
import axios from 'axios';
import labtop from '../../img/labtop.png';
import  './Device.css';


/*
장비 검색시 기본 정보포함 UI 생성
*/
class DevicePrimaryComp extends Component {

  render() {
    /*
    장비 검색후 기본 정보 UI
    */
    return (
            <li class="list-group-item m-2 labtop-color">
              <span className="p-2">
                <img className="magnifier-cmp" src={labtop}/>
              </span>
              <a href="#" className="black-font">LG-Gram 9401 cpu i7 RAM 8G 번호99</a>
            </li>


    );
  }
}
export default DevicePrimaryComp;
