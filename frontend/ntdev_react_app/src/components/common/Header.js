import React, { Component} from 'react';
//import {Nav} from 'react-bootstrap';
//import Image from 'react-bootstrap/Image'
//import './Common.

import homeIcon from '../../img/home.png';
import monitorIcon from '../../img/monitor.png';
import usersIcon from '../../img/users.png';
import staticticsIcon from '../../img/statictics.png';

class Header extends Component {

  render() {
    return (

        <div className="row">
            <div className="col-xs-1 col-sm-1"/>
            <div className="col-xs-1 col-sm-1"><h1>로고</h1></div>
            <div className="col-xs-1 col-sm-1"/>
            <div className="col-xs-1 col-sm-1"><img src={homeIcon} className= 'headerIcon'/></div>
            <div className="col-xs-1 col-sm-1"/>
            <div className="col-xs-1 col-sm-1"><img src={monitorIcon} className= 'headerIcon'/></div>
            <div className="col-xs-1 col-sm-1"/>
            <div className="col-xs-1 col-sm-1"><img src={usersIcon} className= 'headerIcon'/></div>
            <div className="col-xs-1 col-sm-1"/>
            <div className="col-xs-1 col-sm-1"><img src={staticticsIcon} className= 'headerIcon'/></div>
            <div className="col-xs-1 col-sm-1"/>
        </div>

    );
  }
}
export default Header;
