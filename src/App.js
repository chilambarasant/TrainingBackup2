import React, { Component } from 'react';
import './App.css';
import Login from './login/Login'
import SignUp from './signup/SignUp'

import logger from 'redux-logger'
import thunk from 'redux-thunk'
import { Provider } from 'react-redux';

import RootReducer from './RootReducer';
import { createStore, applyMiddleware } from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension'

import { save, load } from 'redux-localstorage-simple'

import Dashboard from './dashboard/Dashboard'
import OrderCommodity from './order_commodity/OrderCommodity'

import {
  BrowserRouter as Router,
  Route,
  Switch,
} from 'react-router-dom'

const middleeware = [logger, thunk];

const store = createStore(
  RootReducer,
  load(),
  composeWithDevTools(applyMiddleware(...middleeware, 
    save()
    ))
);

class App extends Component {
  
  render() {
    return (
      <Provider store={store}>
        <div className="App">
          <Router>
            <div>
              {/* <NavBar /> */}
              <Switch>
                <Route exact path="/" component={Login} />
                <Route path="/auth" component={Login} />
                <Route path="/user-signup" component={SignUp} />
                <Route path="/agrieasy" component={Dashboard} />
                <Route path='/orderCommodity/:comId/:comName/:location/:seller/:price' component={OrderCommodity} />
                <Route render={function(){
                    return <p>Page Not Found !</p>
                }}/>
              </Switch>
            </div>
          </Router>
        </div>
      </Provider>
    );
  }
}
export default App;
