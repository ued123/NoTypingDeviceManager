import React, {Component} from "react";
import  './Dashboard.css';
import LoginTemplate from "../login/LoginTemplate";
import Device from "../device/Device";


class Dashboard extends Component {

    state = {
        currentCmp : 'dashboard'
    }

    handlePageType = (currentCmp) => {
        this.setState(
            {currentCmp : currentCmp}
        );
    }


    render() {

        const {currentCmp} = this.state;

        return <div className='dashboard-div'>대시보드</div>

    }

}

export default Dashboard;