import React from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';

class PageNavbar extends React.Component {
    render(): JSX.Element {
        return (
            <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand as={Link} to="/">
                        Online University
                    </Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link as={Link} to="/">
                            About
                        </Nav.Link>
                        <Nav.Link as={Link} to="/">
                            Contact
                        </Nav.Link>
                        <Nav.Link as={Link} to="/login">
                            Login
                        </Nav.Link>
                        <Nav.Link as={Link} to="/signup">
                            Signup
                        </Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        );
    }
}

export default PageNavbar;
