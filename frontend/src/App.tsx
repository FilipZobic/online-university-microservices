import React from 'react';
import { Switch, Route } from 'react-router-dom';

import PageNavbar from './components/PageNavbar/PageNavbar';
import Home from './pages/Home/Home';
import Login from './pages/Login/Login';

class App extends React.Component {
    render(): JSX.Element {
        const routes = (
            <Switch>
                <Route path="/login" exact component={Login} />
                <Route path="/" exact component={Home} />
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

export default App;
