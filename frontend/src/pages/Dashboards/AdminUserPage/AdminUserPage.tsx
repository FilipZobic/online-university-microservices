import React from 'react';
import { Table, Row, Col, Container, Button, Spinner } from 'react-bootstrap';
import { connect } from 'react-redux';
import { Redirect } from 'react-router';
import { Dispatch } from 'redux';
import { disableUser, softDeleteUser } from '../../../api/users/users';
import { UserAction } from '../../../models/users/user';
import { getAllUsers } from '../../../store/actions/users/actions';
import { authReducerState } from '../../../store/reducers/auth';
import { UserReducerState } from '../../../store/reducers/users';

interface AdminUserPageState {
    isLoading: boolean;
    createUserRedirect: boolean;
}

interface AdminUserPageProps {
    getAllUsers: (token: string) => UserAction;
    auth: authReducerState;
    users: UserReducerState;
}

class AdminUserPage extends React.Component<AdminUserPageProps, AdminUserPageState> {
    state: AdminUserPageState = {
        isLoading: false,
        createUserRedirect: false,
    };

    componentDidMount() {
        // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
        this.props.getAllUsers(this.props.auth.token!);
    }

    onCreateUserButtonClickHandler = (): void => {
        this.setState({
            createUserRedirect: !this.state.createUserRedirect,
        });
    };

    onDeleteClickHandler = (id: string): void => {
        // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
        softDeleteUser(id, this.props.auth.token!).then((_) => {
            console.log(_);
            // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
            this.props.getAllUsers(this.props.auth.token!);
        });
    };

    onEnableClickHandler = (id: string, email: string, username: string, isEnabled: boolean): void => {
        // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
        disableUser(id, email, username, isEnabled, this.props.auth.token!)
            .then((_) => {
                console.log(_);
                // eslint-disable-next-line @typescript-eslint/no-non-null-assertion
                this.props.getAllUsers(this.props.auth.token!);
            })
            .catch((_) => {
                console.log(_);
            });
    };

    render(): JSX.Element {
        let redirect = null;
        let content = null;

        if (this.props.users.isLoading) {
            content = <Spinner animation={'border'} />;
        } else {
            if (!this.props.users.error) {
                content = (
                    <Table striped bordered hover>
                        <tr>
                            <th>#</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Enabled</th>
                            <th>Update</th>
                            <th>Delete</th>
                        </tr>
                        {this.props.users.users?.map((value, index) => {
                            if (value.isDeleted) {
                                return;
                            }
                            return (
                                // eslint-disable-next-line react/jsx-key
                                <tr>
                                    <td>{index + 1}</td>
                                    <td>{value.username}</td>
                                    <td>{value.email}</td>
                                    <td>{value.isEnabled.toString()}</td>
                                    <td>
                                        <button
                                            onClick={() =>
                                                this.onEnableClickHandler(
                                                    value.id,
                                                    value.email,
                                                    value.username,
                                                    !value.isEnabled,
                                                )
                                            }
                                        >
                                            Update
                                        </button>
                                    </td>
                                    <td>
                                        <button onClick={() => this.onDeleteClickHandler(value.id)}>Delete</button>
                                    </td>
                                    <td></td>
                                </tr>
                            );
                        })}
                    </Table>
                );
            } else {
                content = <h1>{this.props.users.errorMessage}</h1>;
            }
        }

        if (this.state.createUserRedirect && !this.state.isLoading) {
            redirect = <Redirect to="/adminCreateUser" />;
        }
        return (
            <Container>
                {redirect}
                <br />
                <Row>
                    <Col></Col>
                    <Col xs={10}>
                        <Button variant="primary" onClick={this.onCreateUserButtonClickHandler}>
                            Create User
                        </Button>
                    </Col>
                    <Col></Col>
                </Row>
                <br />
                <Row>
                    <Col></Col>
                    <Col xs={10}>{content}</Col>
                    <Col></Col>
                </Row>
            </Container>
        );
    }
}

const mapDispatchToProps = (dispatch: Dispatch) => {
    return {
        getAllUsers: (token: string) => dispatch(getAllUsers(token, null)),
    };
};

const mapStateToProps = (state: { auth: authReducerState; users: UserReducerState }) => {
    return {
        auth: state.auth,
        users: state.users,
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(AdminUserPage);
