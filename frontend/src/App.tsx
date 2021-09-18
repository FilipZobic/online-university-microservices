import React from 'react';
import { connect } from 'react-redux';
import { Switch, Route } from 'react-router-dom';

import PageNavbar from './components/PageNavbar/PageNavbar';
import adminCreateUser from './pages/Dashboards/AdminCreateUser/AdminCreateUser';
import AdminUserPage from './pages/Dashboards/AdminUserPage/AdminUserPage';
import Dashbaord from './pages/Dashboards/Dashboard/Dashboard';
import Home from './pages/Home/Home';
import Login from './pages/Login/Login';
import NotFound from './pages/NotFound/NotFound';
import { authReducerState } from './store/reducers/auth';

interface AppProps {
    auth: authReducerState;
}
class App extends React.Component<AppProps> {
    render(): JSX.Element {
        const authRoutes = <Route path="/dashboard" exact component={Dashbaord}></Route>;

        const adminAuthRoutes = (
            <div>
                <Route path="/adminUserPage" exact component={AdminUserPage}></Route>
                <Route path="/adminCreateUser" exact component={adminCreateUser}></Route>
            </div>
        );

        const routes = (
            <Switch>
                {this.props.auth.user?.authorities[0] === 'ROLE_ADMIN' && this.props.auth.token
                    ? adminAuthRoutes
                    : null}
                {this.props.auth.user !== undefined && this.props.auth.token ? authRoutes : null}
                <Route path="/login" exact component={Login} />
                <Route path="/" exact component={Home} />
                <Route path="/" component={NotFound} />
            </Switch>
        );

        return (
            <div>
                <header>
                    <PageNavbar />
                </header>
                <main>{routes}</main>
            </div>
        );
    }
}

const mapStateToProps = (state: { auth: authReducerState }) => {
    return {
        auth: state.auth,
    };
};

export default connect(mapStateToProps)(App);
