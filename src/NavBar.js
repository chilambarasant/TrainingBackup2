import React, { Component } from "react";

import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import { Button, Typography } from "@material-ui/core";
import { connect } from "react-redux";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import { logout } from "./login/LoginActions";


class NavBar extends Component {
  doLogOut = val => {
    this.props.dispatch(logout());
  };

  render() { 
    const { login } = this.props;

    return (
      <div>
        {login ? (
          <div>
            <AppBar position="static" className='test'>
              <Toolbar className="leftAlign">

                <Button htmlFor="logout">
                  <Link
                    name="logout"
                    role="button"
                    color="inherit"
                    to={`/auth`}
                    onClick={this.doLogOut}
                  >
                    Logout
                  </Link>
                </Button>

                <Button color="inherit">Dashboard</Button>
              </Toolbar>
            </AppBar>
          </div>
        ) : (
            <div>
              <AppBar position="static" style={{ color: 'red !important' }}>
                <Toolbar  style={{ backgroundColor: 'red !important' }}>
                  <Typography style={{ textAlign: "left" }} >
                    <h2 style={{ color: 'white', textAlign: 'left',fontSize: 'initial' }}>Agrieasy</h2>
                  </Typography> 
                <div className="leftAlign">
                    <Button htmlFor="login">
                      <Link name="login" role="button" color="inherit" to={`/auth`}>
                        Login
                  </Link>
                    </Button>
                    <Button htmlFor="register">
                      <Link
                        name="register"
                        role="button"
                        color="inherit"
                        to={`/user-signup`}
                      >
                        Register
                  </Link>
                    </Button>
                  </div>
                </Toolbar>
              </AppBar>
            </div>
          )}
      </div>
    );
  }
}

const mapStateToProps = state => ({
  login: state.login.login
});

export default connect(mapStateToProps)(NavBar);
