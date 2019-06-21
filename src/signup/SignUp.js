import React, { Component } from "react";
import FormControl from "@material-ui/core/FormControl";
import Button from "@material-ui/core/Button";
import { withStyles } from "@material-ui/core/styles";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Avatar from "@material-ui/core/Avatar";
import InputLabel from "@material-ui/core/InputLabel";
import { connect } from "react-redux";
import { Grid, MenuItem } from "@material-ui/core";
import { bindActionCreators } from 'redux';
import Input from "@material-ui/core/Input";
import { getSecurityQuestion } from './SignUpActions';
import Select from '@material-ui/core/Select';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import { userSignUp } from './SignUpActions'

import NavBar from '../NavBar'

class SignUp extends Component {

    submit = event => {

        const obj = {};
        const obj2 = [];
        const sec = {};
        sec.securityQuestionsId = this.state.securityQuestionsId;
        sec.answer = this.state.SecurityQuestionsAns;
        sec.id = 0;
        obj2.push(sec);

        obj.id = 0;
        obj.firstName = this.state.firstName;
        obj.lastName = this.state.lastName;
        obj.loginId = this.state.userID;
        obj.mobileNo = this.state.mobileNo;
        obj.password = this.state.password;
        obj.user_type = this.state.userType;
        obj.userSecurityQuestion = obj2;
        this.props.userSignUp(obj);
    };

    state = {
        securityQuestionsId: [],
        userType: '',
        firstName: '',
        lastName: '',
        userID: '',
        mobileNo: '',
        password: '',
        SecurityQuestionsAns: ''
    }
    async componentDidMount() {
        this.props.getSecurityQuestion();
    }

    SecurityQuestionsAnsChanges = event => {
        this.setState({ SecurityQuestionsAns: event.target.value })
    }

    handleChange = event => {
        this.setState({ securityQuestionsId: event.target.value })
    }

    checkHandleChange = event => {
        this.setState({ userType: event.target.value });
    };

    firstNameOnchange = firstname => {
        this.setState({ firstName: firstname.target.value });
    }

    lastNameOnchange = lastName => {
        this.setState({ lastName: lastName.target.value });
    }
    userIDOnchange = userid => {
        this.setState({ userID: userid.target.value });
    }

    mobileNoOnchange = mobileno => {
        this.setState({ mobileNo: mobileno.target.value });
    }
    passowrdOnchange = password => {
        this.setState({ password: password.target.value });
    }

    render() {

        return (
            <div className="backgroundStyle">
                <NavBar />
                <Grid container direction="row" justify="flex-end">

                    <div className="divWidth">
                        <form onSubmit={this.submit}>
                            <div>
                                <div className="headderH1">
                                    <Avatar className="greenAvatar">
                                        <LockOutlinedIcon />
                                    </Avatar>
                                    Sign Up
                                </div>

                                <FormControl margin="normal" required fullWidth >
                                    <InputLabel htmlFor="FirstName"> FirstName </InputLabel>
                                    <Input
                                        fullWidth
                                        id="FirstName"
                                        name="FirstName"
                                        type="alphaNumeric"
                                        autoComplete="off"
                                        ref="FirstName"
                                        value={this.state.firstName}
                                        onChange={this.firstNameOnchange}
                                        autoFocus
                                    />
                                </FormControl>
                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="LastName"> LastName </InputLabel>
                                    <Input
                                        fullWidth
                                        id="LastName"
                                        name="LastName"
                                        type="alphaNumeric"
                                        autoComplete="off"
                                        ref="LastName"
                                        onChange={this.lastNameOnchange}
                                        label={"LastName"}
                                        value={this.state.lastName}
                                    />
                                </FormControl>

                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="UserName"> UserID </InputLabel>

                                    <Input
                                        fullWidth
                                        id="UserName"
                                        name="UserName"
                                        type="alphaNumeric"
                                        autoComplete="off"
                                        ref="UserName"
                                        label={"UserName"}
                                        value={this.state.userID}
                                        onChange={this.userIDOnchange}

                                    />
                                </FormControl>
                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="MobileNO"> MobileNO </InputLabel>
                                    <Input
                                        fullWidth
                                        id="MobileNO"
                                        name="MobileNO"
                                        type="alphaNumeric"
                                        autoComplete="off"
                                        ref="MobileNO"
                                        label={"MobileNO"}
                                        value={this.state.mobileNo}
                                        onChange={this.mobileNoOnchange}

                                    />
                                </FormControl>

                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="SecurityQuestions"> SecurityQuestions </InputLabel>
                                    <Select
                                        fullWidth
                                        value={this.state.securityQuestionsId}
                                        inputProps={{
                                            id: 'SecurityQuestions',
                                            name: 'SecurityQuestions'
                                        }}
                                        className="form-control" onChange={this.handleChange}>

                                        {this.props.SecurityQuestions.map(dataObj => (
                                            <MenuItem value={dataObj.id} key={dataObj.question}>  {dataObj.question}
                                            </MenuItem>
                                        ))}
                                    </Select>

                                </FormControl>

                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="SecurityQuestionsAns"> Security Answer </InputLabel>
                                    <Input
                                        fullWidth
                                        id="SecurityQuestionsAns"
                                        name="SecurityQuestionsAns"
                                        type="alphaNumeric"
                                        autoComplete="off"
                                        ref="SecurityQuestionsAns"
                                        onChange={this.SecurityQuestionsAnsChanges}
                                        label={"SecurityQuestionsAns"}
                                        value={this.state.SecurityQuestionsAns}
                                    />
                                </FormControl>

                                <FormControl component="fieldset">
                                    <RadioGroup>
                                        <FormControlLabel id="Farmer" name="Farmer" onChange={this.checkHandleChange} value="agent" control={<Radio />} label="Farmer" />
                                        <FormControlLabel id="Farmer" name="Agent" onChange={this.checkHandleChange} value="farmer" control={<Radio />} label="Agent" />
                                    </RadioGroup>

                                </FormControl>

                                <FormControl margin="normal" required fullWidth>
                                    <InputLabel htmlFor="Password"> Password </InputLabel>
                                    <Input
                                        fullWidth
                                        id="Password"
                                        name="Password"
                                        type="password"
                                        autoComplete="Password"
                                        ref="Password"
                                        label={"Password"}
                                        value={this.state.password}
                                        onChange={this.passowrdOnchange}
                                    />
                                </FormControl>
                                <FormControl margin="normal">
                                    <StyledButton type="submit" value="Submit" variant="contained">
                                        Sign UP
                                    </StyledButton>
                                </FormControl>
                            </div>

                        </form>
                    </div>
                </Grid >
            </div>
        );
    }
}

const form = {

}

const StyledButton = withStyles({
    root: {
        background: "linear-gradient(45deg,  #3B6746 100%,  #3B6746 100%)",
        borderRadius: 3,
        border: 1,
        color: "white",
        height: 48,
        padding: "0 30px",
        boxShadow: "1px 3px 5px 2px  #3B6746"
    },
    label: {
        textTransform: "capitalize"
    }
})(Button);

const mapStateToProps = state => ({
    signup: state.SignUp.signup,
    isLoading: state.SignUp.isLoading,
    SecurityQuestions: state.SignUp.SecurityQuestions,
    securityQuestionsId: state.SignUp.securityQuestionsId,
});

const mapDispatchToProps = dispatch => bindActionCreators({
    getSecurityQuestion,
    userSignUp,
}, dispatch)


export default connect(mapStateToProps, mapDispatchToProps)(SignUp);
