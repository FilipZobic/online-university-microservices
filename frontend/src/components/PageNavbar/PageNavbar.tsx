import React from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';

class PageNavbar extends React.Component {
    render(): JSX.Element {
        return (
            <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand href="#home">Online University</Navbar.Brand>
                    <Nav className="me-auto">
                        <Nav.Link href="#about">About</Nav.Link>
                        <Nav.Link href="#contact">Contact</Nav.Link>
                        <Nav.Link href="#login">Login</Nav.Link>
                        <Nav.Link href="#signup">Signup</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
        );
    }
}

export default PageNavbar;
