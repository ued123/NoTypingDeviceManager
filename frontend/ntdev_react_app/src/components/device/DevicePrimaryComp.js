import React, { Component} from 'react';
import axios from 'axios';
import labtop from '../../img/labtop.png';
import desktopBody from '../../img/desktopBody.png';
import monitor from '../../img/monitor.png';
import disk from '../../img/disk.png';
import ram from '../../img/ram.png';

import  './Device.css';


/*
장비 검색시 기본 정보포함 UI 생성
*/
class DevicePrimaryComp extends Component {

  render() {
    /*
     * device_category
     * 			C : 컴퓨터
     * 			M:  모니터
     * 			D : DESKTOP
     *
     * part_category
     *          R : ram
     *          H:  HardDsik
     *          S:  SSD
     */
      /*
      장비 검색후 기본 정보 UI
      */
      const {devicePart} = this.props;

      return (
              <li className="list-group-item m-2 labtop-color">
                <span className="p-2">
                  {
                    (function() {
                      if (devicePart.deviceCategory === 'M') {
                        return (<img className="magnifier-cmp" src={monitor}/>);
                      } else if (devicePart.deviceCategory === 'D') {
                        return (<img className="magnifier-cmp" src={desktopBody}/>);
                      } else if (devicePart.partCategory === 'S' ||devicePart.partCategory === 'H') {
                        return (<img className="magnifier-cmp" src={disk}/>);
                      } else if (devicePart.partCategory === 'R' ) {
                        return (<img className="magnifier-cmp" src={ram}/>);
                      } else {
                        return (<img className="magnifier-cmp" src={labtop}/>);
                      }
                    })()
                  }

                </span>
                <a href="#" className="black-font">{devicePart.primaryName}</a>
              </li>
    );
  }
}
export default DevicePrimaryComp;
