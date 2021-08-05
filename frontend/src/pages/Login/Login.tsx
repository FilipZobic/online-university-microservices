import React from 'react';
import { Form, Button, Card } from 'react-bootstrap';
import classes from './Login.module.css';

interface LoginState {
    email: string;
    password: string;
    error: boolean;
    errorMessage: string;
}

class Login extends React.Component {
    state: LoginState = {
        email: '',
        password: '',
        error: false,
        errorMessage: '',
    };

    emailChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ email: event.target.value });
    };

    passwordChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ password: event.target.value });
    };

    onLoginButtonClickedHandler = (): void => {
        const email = this.state.email.trim();
        const password = this.state.password.trim();

        if (email && password) {
            console.log(email);
            console.log(password);
            this.setState({ error: false, errorMessage: '' });
        } else {
            this.setState({ error: true, errorMessage: 'Invalid Form' });
        }
    };

    render(): JSX.Element {
        let errorComponent = null;
        if (this.state.error) {
            errorComponent = <Card.Subtitle className="mb-2 text-danger">{this.state.errorMessage}</Card.Subtitle>;
        }

        return (
            <Card className={classes.VerticalCenter} style={{ width: '18rem' }}>
                <Card.Body>
                    <Card.Title>Login</Card.Title>
                    {errorComponent}
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Email address</Form.Label>

                            <Form.Control
                                type="email"
                                placeholder="Enter email"
                                defaultValue={this.state.email}
                                onChange={this.emailChangeHandler}
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
                </Card.Body>
            </Card>
        );
    }
}

export default Login;
