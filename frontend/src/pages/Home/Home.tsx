import React from 'react';
import classes from './Home.module.css';
import { Container, Row, Col, Button } from 'react-bootstrap';

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
                <Row>
                    <Col></Col>
                    <Col>
                        <br />
                        <Button className={classes.Button} size="lg" variant="primary">
                            Login
                        </Button>
                        <Button className={classes.Button} size="lg" variant="primary">
                            Signup
                        </Button>
                    </Col>
                    <Col></Col>
                </Row>
            </Container>
        );
    }
}

export default Home;
