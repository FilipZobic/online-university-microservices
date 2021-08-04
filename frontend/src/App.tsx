import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

import Home from './pages/Home/Home';

class App extends React.Component {
    render(): JSX.Element {
        return (
            <div>
                <Home />
            </div>
        );
    }
}

export default App;
