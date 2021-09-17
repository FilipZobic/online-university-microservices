import React from 'react';
import { Form, Button, Card } from 'react-bootstrap';
import { connect } from 'react-redux';
import { Dispatch } from 'redux';
import { Redirect } from 'react-router';
import { UserAuthAction, userLogin } from '../../store/actions/auth/actions';
import { authReducerState } from '../../store/reducers/auth';
import classes from './Login.module.css';
import Spinner from '../../components/Spinner/Spinner';

interface LoginState {
    usrername: string;
    password: string;
    error: boolean;
    errorMessage: string;
}
interface LoginProps {
    loginUser: (username: string, password: string) => UserAuthAction;
    auth: authReducerState;
}

class Login extends React.Component<LoginProps, LoginState> {
    state: LoginState = {
        usrername: '',
        password: '',
        error: false,
        errorMessage: '',
    };

    usrernameChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ usrername: event.target.value });
    };

    passwordChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ password: event.target.value });
    };

    onLoginButtonClickedHandler = (): void => {
        const usrername = this.state.usrername.trim();
        const password = this.state.password.trim();

        if (usrername && password) {
            this.setState({ error: false, errorMessage: '' });
            this.props.loginUser(usrername, password);
        } else {
            this.setState({ error: true, errorMessage: 'Invalid Form' });
        }
    };

    render(): JSX.Element {
        let errorComponent = null;
        let authRedirect = null;
        if (this.props.auth.error) {
            errorComponent = <Card.Subtitle className="mb-2 text-danger">{this.props.auth.errorMessage}</Card.Subtitle>;
        } else if (this.state.error) {
            errorComponent = <Card.Subtitle className="mb-2 text-danger">{this.state.errorMessage}</Card.Subtitle>;
        }
        if (this.props.auth.loading === false) {
            if (this.props.auth.user !== undefined && this.props.auth.token !== null && this.props.auth.token !== '') {
                authRedirect = <Redirect to="/dashboard" />;
            }
        }

        return (
            <Card className={classes.VerticalCenter} style={{ width: '18rem' }}>
                {authRedirect}
                <Card.Body>
                    <Card.Title>Login</Card.Title>
                    {errorComponent}
                    {this.props.auth.loading ? (
                        <Spinner />
                    ) : (
                        <Form>
                            <Form.Group className="mb-3" controlId="formBasicText">
                                <Form.Label>Username address</Form.Label>

                                <Form.Control
                                    type="usrername"
                                    placeholder="Enter usrername"
                                    defaultValue={this.state.usrername}
                                    onChange={this.usrernameChangeHandler}
                                />
                            </Form.Group>

                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                    type="password"
                                    placeholder="Password"
                                    defaultValue={this.state.password}
                                    onChange={this.passwordChangeHandler}
                                />
                            </Form.Group>

                            <Button onClick={this.onLoginButtonClickedHandler} variant="primary">
                                Login
                            </Button>
                        </Form>
                    )}
                </Card.Body>
            </Card>
        );
    }
}

// eslint-disable-next-line @typescript-eslint/no-unused-vars
const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        loginUser: (username: string, password: string) => dispatch(userLogin(username, password)),
    };
};

const mapStateToProps = (state: { auth: authReducerState }) => {
    return {
        auth: state.auth,
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Login);
