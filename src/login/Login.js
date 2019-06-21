import React, { Component } from "react";

import FormControl from "@material-ui/core/FormControl";
import Button from "@material-ui/core/Button";
import { withStyles } from "@material-ui/core/styles";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Avatar from "@material-ui/core/Avatar";
import InputLabel from "@material-ui/core/InputLabel";
import { connect } from "react-redux";
import { reduxForm, Field, SubmissionError } from "redux-form";
import RenderField from "../RenderField";
import { checkAuth } from "./LoginActions";
import { Redirect } from "react-router-dom";
import { Grid } from "@material-ui/core";
import Link from "@material-ui/core/Link";

import NavBar from '../NavBar'

import LinearProgress from '@material-ui/core/LinearProgress';

const minLength = min => value =>
  value && value.length < min ? `Must be ${min} characters or more` : undefined;
const minLength2 = minLength(2);

const alphaNumeric = value =>
  value && /[^a-zA-Z0-9 ]/i.test(value)
    ? 'Only alphanumeric characters'
    : undefined



const styles = {
  root: {
    flexGrow: 1,
  },
};

class Login extends Component {
  submit = values => {
    const { UserName, Password } = values;
    this.props.dispatch(checkAuth(UserName, Password));
  };

  render() {
    const { handleSubmit, login, classes } = this.props;

    // if(login.login === false){
    //   return <div><LinearProgress color="secondary" /></div>
    // }

    if (login.login === true) {
      return <Redirect to="/agrieasy" />;
    }


    return (
      <div>
       
        <div  className="backgroundStyle" >
        <NavBar />
          <Grid container direction="row" justify="flex-end" >
            <div className="divWidth">
              <form onSubmit={handleSubmit(this.submit)}>
                <div className="headderH1">
                  <Avatar className="greenAvatar">
                    <LockOutlinedIcon />
                  </Avatar>
                  Sign in
              </div>

                <FormControl margin="normal" required fullWidth className="whiteLabel">
                  <InputLabel htmlFor="UserName"> UserName </InputLabel>

                  <Field
                    id="UserName"
                    name="UserName"
                    type="alphaNumeric"
                    autoComplete="off"
                    ref="UserName"
                    validate={[minLength2]}
                    component={RenderField}
                    label={"UserName"}
                    autoFocus
                  />
                </FormControl>

                <FormControl margin="normal" required fullWidth className="whiteLabel">
                  <InputLabel htmlFor="Password"> Password </InputLabel>

                  <Field
                    fullWidth
                    id="Password"
                    name="Password"
                    type="password"
                    autoComplete="off"
                    ref="Password"
                    component={RenderField}
                    label={"Password"}
                  />
                </FormControl>

                <div className="forgotPSW">
                  <Link
                    component="button"
                    variant="body2"
                    onClick={() => {

                    }}
                  >
                    Forgot Password ?
                </Link>
                </div>

                <FormControl margin="normal">
                  <StyledButton type="submit" value="Submit" variant="contained">
                    Sign in
                </StyledButton>
                </FormControl>
              </form>
            </div>
          </Grid>
        </div>
      </div>
    );
  }
}

const StyledButton = withStyles({
  root: {
    background: "linear-gradient(45deg, #3B6746 100%, #3B6746 100%)",
    borderRadius: 3,
    border: 1,
    color: "white",
    height: 48,
    padding: "0 30px",
    boxShadow: "1px 3px 5px 2px #3B6746"
  },
  label: {
    textTransform: "capitalize"
  }
})(Button);

const mapStateToProps = state => ({
  login: state.login
});

const signInForm = reduxForm({
  form: "login"
})(Login);

export default connect(mapStateToProps)(signInForm);
