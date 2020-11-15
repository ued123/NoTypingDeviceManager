import React, { Component} from 'react';
import Navbar from 'react-bootstrap/Navbar'
import { faKey, faCubes, faUser, faHome, faTools } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import toolBox from "../../img/tool-box.png"

class Header extends Component {

  render() {
    return (
      <>
        <Navbar className="headerWrapper">
          <Navbar.Brand href="#home">
            <span className="headerLogo">
              Management <img src={toolBox}/>
            </span>
          </Navbar.Brand>
          <Navbar.Brand href="#home">
            <span className=" badge-pill badge-primary headerFont">
              <FontAwesomeIcon icon={faHome} />
            </span>
          </Navbar.Brand>
          <Navbar.Brand href="#home">
            <span className=" badge-pill badge-primary headerFont">
              <FontAwesomeIcon icon={faCubes} /> 장비
            </span>
          </Navbar.Brand>
          <Navbar.Brand href="#home">
            <span className=" badge-pill badge-primary headerFont">
              <FontAwesomeIcon icon={faUser} /> 사원
            </span>
          </Navbar.Brand>
          <Navbar.Brand href="#home">
            <span className=" badge-pill badge-primary headerFont">
              <FontAwesomeIcon icon={faTools} /> 관리
            </span>
          </Navbar.Brand>
        </Navbar>
      </>
    );
  }
}
export default Header;
