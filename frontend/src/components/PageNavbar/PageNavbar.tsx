import React from 'react';
import { Container, Nav, Navbar } from 'react-bootstrap';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Dispatch } from 'redux';
import { authReducerState } from '../../store/reducers/auth';
import { AuthAction, userLogout } from '../../store/actions/auth/actions';

interface NavBarProps {
    logout: () => AuthAction;
    auth: authReducerState;
}
class PageNavbar extends React.Component<NavBarProps> {
    onLogoutButtonClickedHandler = (): void => {
        this.props.logout();
    };

    render(): JSX.Element {
        let authNav = null;
        const regularNav = (
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
            </Nav>
        );

        if (this.props.auth.user !== undefined) {
            const stateRole = this.props.auth.user.authorities[0];
            let role = '';

            switch (stateRole) {
                case 'ROLE_ADMIN':
                    role = 'Admin: ';
                    break;
                default:
                    role = 'User: ';
                    break;
            }

            const adminNav = (
                <Nav.Link as={Link} to="/adminUserPage">
                    Users
                </Nav.Link>
            );

            authNav = (
                <Nav className="me-auto">
                    <Nav.Link>{role + this.props.auth.user.sub}</Nav.Link>
                    <Nav.Link as={Link} to="/dashboard">
                        Dashboard
                    </Nav.Link>
                    {this.props.auth.user?.authorities[0] === 'ROLE_ADMIN' && this.props.auth.token ? adminNav : null}
                    <Nav.Link onClick={this.props.logout} as={Link} to="/">
                        Logout
                    </Nav.Link>
                </Nav>
            );
        }

        return (
            <Navbar bg="dark" variant="dark">
                <Container>
                    <Navbar.Brand as={Link} to="/">
                        Online University
                    </Navbar.Brand>
                    {this.props.auth.token !== null && this.props.auth.user !== undefined ? authNav : regularNav}
                </Container>
            </Navbar>
        );
    }
}

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        logout: () => dispatch(userLogout()),
    };
};

const mapStateToProps = (state: { auth: authReducerState }) => {
    return {
        auth: state.auth,
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(PageNavbar);
