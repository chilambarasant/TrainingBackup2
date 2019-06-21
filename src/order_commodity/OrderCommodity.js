import React, { Component } from 'react';
import { TableBody, TableRow, TableCell, Grid, Paper, Button, TextField, Typography } from '@material-ui/core';
import Snackbar from '@material-ui/core/Snackbar';
import { MySnackbarContentWrapper } from '../MySnackbarContent'
import { Link } from 'react-router-dom'
import { connect } from "react-redux";
import { bindActionCreators } from 'redux'
import { orderCommodity } from './action'

class OrderCommodity extends Component {

    state = {
        comId: '',
        comName: '',
        location: '',
        unit:'',
        seller: '',
        price: '',
        address: '',
        open: false,
        message: '',
        butdis: false,
    }

    componentDidMount() {
        //const  { commodityId, commodity, location, unit, seller, price}  = this.props;

        const comId = this.props.commodityId
        const comName = this.props.commodity
        const location = this.props.location
        const unit = this.props.unit
        const seller = this.props.seller
        const price = this.props.price

        this.setState({
            comId: comId,
            comName: comName,
            location: location,
            unit: unit,
            seller: seller,
            price: price
        })
    }

    handleAddressChange = event => {
        this.setState({ address: event.target.value });
    };

    handleClose = event => {
        this.setState({ open: false });
    };

    saveOrder = event => {

        event.preventDefault();
        const { orderCommodity } = this.props

        orderCommodity(this.state.comId, this.state.address)

        setTimeout(() => {
            if (this.props.orderStatus === 'success') {
                this.setState({ 
                    open: true,
                    message: "Data Insertion Successfully!"
                });
                this.setState({
                    commodityId: '',
                    locationId: '',
                    price: '',
                })
            } else {
                this.setState({ 
                    open: true,
                    message: "Data Insertion Failed!"
                });
            }
        }, 3000);
    };

    render() {
      
        return (
            <div>
                <Paper elevation={1}>

                    <Grid container spacing={8} alignItems="flex-end">
                        <Grid item md={true} sm={true} xs={true}>
                            <Typography gutterBottom variant="h5" component="h2">
                                Order Details
                                </Typography>
                        </Grid>
                    </Grid>

                    <TableBody>
                        <TableRow>
                            <TableCell align="right">Commodity Id</TableCell>
                            <TableCell align="right">{this.state.comId}</TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell align="right">Commodity Name</TableCell>
                            <TableCell align="right">{this.state.comName}</TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell align="right">Location</TableCell>
                            <TableCell align="right">{this.state.location}</TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell align="right">Seller</TableCell>
                            <TableCell align="right">{this.state.seller}</TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell align="right">Units</TableCell>
                            <TableCell align="right">{this.state.unit}</TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell align="right">Price</TableCell>
                            <TableCell align="right">{this.state.price}</TableCell>
                        </TableRow>

                        <TableRow>
                            <TableCell align="right">Address</TableCell>
                            <TableCell align="right">
                                <TextField id="price" name="price"
                                    value={this.state.address}
                                    onChange={this.handleAddressChange}
                                    type="text" fullWidth required />
                            </TableCell>
                        </TableRow>
                    </TableBody>

                    <Grid container justify="center" style={{ marginTop: '10px' }}>
                        <Button onClick={this.saveOrder} variant="outlined" disabled={this.state.butdis} color="primary" style={{ textTransform: "none" }}>
                            Place Order
                            </Button>
                    </Grid>
                </Paper>

                <Snackbar
                    anchorOrigin={{
                        vertical: 'bottom',
                        horizontal: 'right',
                    }}
                    open={this.state.open}
                    autoHideDuration={6000}
                    onClose={this.handleClose}
                >
                    <MySnackbarContentWrapper
                        onClose={this.handleClose}
                        variant="success"
                        message={this.state.message}
                    />
                </Snackbar>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        orderStatus: state.orderReducer.orderStatus,
    };
};

const mapDispatchToProps = dispatch => bindActionCreators({
    orderCommodity,
}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(OrderCommodity);