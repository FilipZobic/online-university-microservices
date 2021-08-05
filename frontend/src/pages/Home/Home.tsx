import React from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import classes from './Home.module.css';

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
                        <Link to="/login">
                            <Button className={classes.Button} size="lg" variant="primary">
                                Login
                            </Button>
                        </Link>

                        <Link to="/signup">
                            <Button className={classes.Button} size="lg" variant="primary">
                                Signup
                            </Button>
                        </Link>
                    </Col>
                    <Col></Col>
                </Row>
            </Container>
        );
    }
}

export default Home;
