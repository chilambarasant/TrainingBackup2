import React, { Component } from 'react';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import { connect } from "react-redux";


class RenderSelectInput extends Component {

    state = {
        securityQuestionsId: [],
        userType: '',
    }

    handleChange = event => {
        this.setState({ securityQuestionsId: event.target.value })
    }

    checkHandleChange = name => event => {
        this.setState({ userType: event.target.checked });
    };

    render() {
        return (
            <div>
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
            </div>
        );
    }
}

const mapStateToProps = state => ({
    
    SecurityQuestions: state.SignUp.SecurityQuestions,
    securityQuestionsId: state.SignUp.securityQuestionsId,
});

export default connect(mapStateToProps)(RenderSelectInput);