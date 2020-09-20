import React, {Component} from "react";
import  './Dashboard.css';
import { Container,Row,Col } from 'react-bootstrap';
import { Image } from "react-bootstrap";


class Dashboard extends Component {

    state = {};

    render() {

        return <main className="dashboard-div">

            <nav>
                <h1> 대시보드 페이지 </h1>
            </nav>

            <Container>
                <Row>
                    <Col xs={6} md={4}>
                        <Image src="desktop.js/171x180" rounded />
                    </Col>
                    <Col xs={6} md={4}>
                        <Image src="holder.js/171x180" roundedCircle />
                    </Col>
                    <Col xs={6} md={4}>
                        <Image src="holder.js/171x180" thumbnail />
                    </Col>
                </Row>
            </Container>


        </main>
    }

}

export default Dashboard;