import React from 'react';
import { Button, Card, Form } from 'react-bootstrap';
import { connect } from 'react-redux';
import { Dispatch } from 'redux';
import { NewUserAction, UserAction } from '../../../models/users/user';
import { createUser, getAllUsers } from '../../../store/actions/users/actions';
import { authReducerState } from '../../../store/reducers/auth';
import { UserReducerState } from '../../../store/reducers/users';
import classes from './AdminCreateUser.module.css';

interface UserCreateState {
    usrername: string;
    password: string;
    email: string;
    isEnabled: boolean;
    error: boolean;
    errorMessage: string;
}

interface UserCreateStateProps {
    getAllUsers: (token: string) => UserAction;
    createNewUser: (username: string, password: string, email: string, isEnabled: boolean) => NewUserAction;
    auth: authReducerState;
    users: UserReducerState;
}

class adminCreateUser extends React.Component<UserCreateStateProps, UserCreateState> {
    state: UserCreateState = {
        usrername: '',
        password: '',
        email: '',
        isEnabled: false,
        error: false,
        errorMessage: '',
    };

    usrernameChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ usrername: event.target.value });
    };

    passwordChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ password: event.target.value });
    };

    emailChangeHandler = (event: React.ChangeEvent<HTMLInputElement>): void => {
        this.setState({ email: event.target.value });
    };

    onAddUserButtonClickedHandler = (): void => {
        const usrername = this.state.usrername.trim();
        const password = this.state.password.trim();
        const email = this.state.email.trim();

        if (usrername && password && email) {
            this.setState({ error: false, errorMessage: '' });
            console.log(usrername + ' ', password + ' ' + email);
            this.props.createNewUser(usrername, password, email, false);
            // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
            // this.props.getAllUsers(this.props.auth.token!);
        } else {
            this.setState({ error: true, errorMessage: 'Invalid Form' });
            console.log(this.state.errorMessage);
        }
    };

    render(): JSX.Element {
        let errorComponent = null;
        if (this.props.users.error) {
            errorComponent = (
                <Card.Subtitle className="mb-2 text-danger">{this.props.users.errorMessage}</Card.Subtitle>
            );
        } else if (this.state.error) {
            errorComponent = <Card.Subtitle className="mb-2 text-danger">{this.state.errorMessage}</Card.Subtitle>;
        }

        return (
            <Card className={classes.VerticalCenter} style={{ width: '18rem' }}>
                <Card.Title>Add User</Card.Title>
                {errorComponent}
                <Card.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicText">
                            <Form.Label>Username</Form.Label>

                            <Form.Control
                                type="usrername"
                                placeholder="Username"
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
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Email</Form.Label>

                            <Form.Control
                                type="email"
                                placeholder="Email"
                                defaultValue={this.state.email}
                                onChange={this.emailChangeHandler}
                            />
                        </Form.Group>

                        <Button variant="primary" onClick={this.onAddUserButtonClickedHandler}>
                            Add User
                        </Button>
                    </Form>
                </Card.Body>
            </Card>
        );
    }
}

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        createNewUser: (username: string, password: string, email: string, isEnabled: boolean) =>
            dispatch(createUser(username, password, email, isEnabled)),
        getAllUsers: (token: string) => dispatch(getAllUsers(token, null)),
    };
};

const mapStateToProps = (state: { auth: authReducerState; users: UserReducerState }) => {
    return {
        auth: state.auth,
        users: state.users,
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(adminCreateUser);
