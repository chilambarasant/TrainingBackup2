import React, { Component } from "react";
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import MUIDataTable from "mui-datatables";
import { getCommodityList, getCommodity, getLocation, saveCommodity } from './Action'
import { Link } from 'react-router-dom'
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import { withStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Typography from '@material-ui/core/Typography';
import InputLabel from "@material-ui/core/InputLabel";
import FormControl from "@material-ui/core/FormControl";
import Input from "@material-ui/core/Input";
import { Select, TextField, Paper, MenuItem } from '@material-ui/core';
import Snackbar from '@material-ui/core/Snackbar';
import { MySnackbarContentWrapper } from '../MySnackbarContent'
import { createMuiTheme, MuiThemeProvider } from '@material-ui/core/styles';
import { Redirect } from "react-router-dom";
import OrderCommodity from '../order_commodity/OrderCommodity'
const DialogTitle = withStyles(theme => ({
    root: {
        borderBottom: `1px solid ${theme.palette.divider}`,
        margin: 0,
        padding: theme.spacing.unit * 2,
    },
    closeButton: {
        position: 'absolute',
        right: theme.spacing.unit,
        top: theme.spacing.unit,
        color: theme.palette.grey[500],
    },
}))(props => {
    const { children, classes, onClose } = props;
    return (
        <MuiDialogTitle disableTypography className={classes.root}>
            <Typography variant="h6">{children}</Typography>
            {onClose ? (
                <IconButton aria-label="Close" className={classes.closeButton} onClick={onClose}>
                    <CloseIcon />
                </IconButton>
            ) : null}
        </MuiDialogTitle>
    );
});

const DialogContent = withStyles(theme => ({
    root: {
        margin: 0,
        padding: theme.spacing.unit * 2,
    },
}))(MuiDialogContent);

const DialogActions = withStyles(theme => ({
    root: {
        borderTop: `1px solid ${theme.palette.divider}`,
        margin: 0,
        padding: theme.spacing.unit,
    },
}))(MuiDialogActions);

const styles = theme => ({
    fab: {
        margin: theme.spacing.unit,
    },
    extendedIcon: {
        marginRight: theme.spacing.unit,
    },
});


class AgentCommodityGetCommodity extends Component {

    async componentDidMount() {

        const { getCommodityList, getCommodity, getLocation } = this.props;

        getCommodityList();
        getLocation();
        getCommodity();
    }

    getMuiTheme = () => createMuiTheme({
        overrides: {
            MUIDataTableBodyCell: {
                root: {
                    backgroundColor: "#FF0000"
                }
            }
        }
    })

    state = {
        commodityId: '',
        locationId: '',
        price: '',
        message: '',
        open: false,
        snackbaropen: false,
        rowClick: false,
        rowData: '',
        units: '',
    };

    handleCommodityChange = event => {
        this.setState({ commodityId: event.target.value });
    };

    handleLocationChange = event => {
        this.setState({ locationId: event.target.value });
    };

    handleUnitChange = event => {
        this.setState({ units: event.target.value });
    };

    handlePriceChange = event => {
        this.setState({ price: event.target.value });
    };

    handleCloseSnack = event => {
        this.setState({ snackbaropen: false });
    };

    saveCommodityPrice = (event) => {

        event.preventDefault();

        const { saveCommodity } = this.props
        if (this.state.commodityId.length == 0) {
            alert("Please Select Commodity .");
            return;
        }

        if (this.state.locationId == 0) {
            alert("Please Select Location .");
            return;
        }

        if (this.state.price.length == 0) {
            alert("Price Can't be empty !");
            return;
        }

        if (this.state.units.length == 0) {
            alert("Units Can't be empty !");
            return;
        }

        saveCommodity(this.state.commodityId, this.state.locationId, this.state.price, this.state.units, this.props.userId)

        setTimeout(() => {
            if (this.props.saveStatus === 'success') {
                this.setState({
                    commodityId: '',
                    locationId: '',
                    price: '',
                    open: false,
                    message: "Order Placed Successfully!",
                });
                const { getCommodityList } = this.props;
                getCommodityList();

            } else {
                this.setState({
                    open: true,
                    message: "Data Insertion Failed!",
                });
            }
        }, 3000);
    };

    handleClickOpen = () => {
        this.setState({
            open: true,
        });
    };

    handleClose = () => {
        this.setState({
            commodityId: '',
            locationId: '',
            price: '',
            open: false,
        })

    };

    handleClick = (event) => {
        this.setState({
            rowClick: true,
            rowData: event,
        })
    }

    render() {

        const columns = ["CommodityId", "Commodity", "Location", "Units", "Seller", "Price"];
        const { commodity_list, commodity_master_list, location_master_list, userType } = this.props;

        let options = '';
        if (userType !== 'agent') {
            options = {
                filterType: "dropdown",
                responsive: "scroll",
                onRowClick: (event) => this.handleClick(event)
            };
        } else {
            options = {
                filterType: "dropdown",
                responsive: "scroll",
            };
        }

        {
            if (this.state.rowClick === true) {
                const { rowData } = this.state;
                return <div>OrderCommodity<OrderCommodity commodityId={rowData[0]} commodity={rowData[1]} location={rowData[2]} unit={rowData[3]} seller={rowData[4]} price={rowData[5]} /></div>
            }
        }

        return (
            <div>
                {
                    this.props.userType === 'agent' ?
                        <div className="addButton">
                            <Fab color="primary" aria-label="Add" onClick={this.handleClickOpen}>
                                <AddIcon />
                            </Fab>
                        </div > : <div className="addButton"></div>
                }
                <Paper>

                    <div className='modalCSS'>
                        <Dialog
                            onClose={this.handleClose}
                            aria-labelledby="customized-dialog-title"
                            open={this.state.open}
                        >
                            <DialogTitle id="customized-dialog-title" onClose={this.handleClose}>
                                <span>Add Commodity</span>
                            </DialogTitle>
                            <DialogContent>
                                <div>
                                    <FormControl margin="normal" required fullWidth>
                                        <InputLabel htmlFor="Comodity"> Comodity </InputLabel>

                                        <Select
                                            fullWidth required
                                            value={this.state.commodityId}
                                            onChange={this.handleCommodityChange}
                                            inputProps={{
                                                name: 'commodityId',
                                                id: 'commodityId',
                                            }}>

                                            {commodity_master_list.map(commodity => (

                                                <MenuItem value={commodity.cmId} key={commodity.cmId}>
                                                    {commodity.cmName}
                                                </MenuItem>
                                            ))}

                                        </Select>
                                    </FormControl>

                                    <FormControl margin="normal" required fullWidth>
                                        <InputLabel htmlFor="Location"> Location </InputLabel>


                                        <Select
                                            fullWidth required
                                            value={this.state.locationId}
                                            onChange={this.handleLocationChange}
                                            inputProps={{
                                                name: 'locationId',
                                                id: 'locationId',
                                            }}>

                                            {location_master_list.map(location => (
                                                <MenuItem value={location.lmId} key={location.lmId}>
                                                    {location.lmName}
                                                </MenuItem>
                                            ))}

                                        </Select>
                                    </FormControl>

                                    <FormControl htmlFor="Units" margin="normal" required fullWidth>
                                        <TextField
                                            id="Units"
                                            label="Units"
                                            autoComplete="off"
                                            margin="normal"
                                            value={this.state.units}
                                            onChange={this.handleUnitChange}
                                            required="true"
                                            type="text"
                                        />
                                    </FormControl>

                                    <FormControl htmlFor="Price" margin="normal" required fullWidth>
                                        <TextField
                                            id="Price"
                                            label="Price"
                                            autoComplete="off"
                                            margin="normal"
                                            value={this.state.price}
                                            onChange={this.handlePriceChange}
                                            required="true"
                                            type="number"
                                        />
                                    </FormControl>
                                </div>


                            </DialogContent>
                            <DialogActions>

                                <Button onClick={this.saveCommodityPrice} color="primary">
                                    Save changes
            </Button>

                            </DialogActions>
                        </Dialog>
                    </div>
                    <div>
                        <MUIDataTable
                            title={"Commodity List"}
                            data={commodity_list}
                            columns={columns}
                            options={options}
                        />
                    </div>

                    <Snackbar
                        anchorOrigin={{
                            vertical: 'bottom',
                            horizontal: 'right',
                        }}
                        snackbaropen={this.state.snackbaropen}
                        autoHideDuration={6000}
                        onClose={this.handleCloseSnack}
                    >
                        <MySnackbarContentWrapper
                            onClose={this.handleCloseSnack}
                            variant="success"
                            message={this.state.message}
                        />
                    </Snackbar>
                </Paper>
            </div>
        );
    }
}


const mapStateToProps = state => ({
    commodity_list: state.commodityReducer.commodity_list,
    commodity_master_list: state.commodityReducer.commodity_master_list,
    location_master_list: state.commodityReducer.location_master_list,
    saveStatus: state.commodityReducer.saveStatus,
    userType: state.login.userType
})

const mapDispatchToProps = dispatch => bindActionCreators({
    getCommodityList,
    getCommodity,
    getLocation,
    saveCommodity,
}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(AgentCommodityGetCommodity)
