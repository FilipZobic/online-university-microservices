import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';

class Home extends React.Component {
    render(): JSX.Element {
        return (
            <Container>
                <Row>
                    <Col></Col>
                    <Col>
                        <br />
                        <h1>Online Universety</h1>
                    </Col>
                    <Col></Col>
                </Row>
            </Container>
        );
    }
}

export default Home;
