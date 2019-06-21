import React, { Component } from "react";

import PropTypes from 'prop-types';
import classNames from 'classnames';
import { withStyles } from '@material-ui/core/styles';
import CssBaseline from '@material-ui/core/CssBaseline';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import { mainListItems, secondaryListItems } from '../listItems';
import { Redirect } from "react-router-dom";
import Tooltip from '@material-ui/core/Tooltip';
import { connect } from "react-redux";
import { logout } from "../login/LoginActions";
import Badge from '@material-ui/core/Badge';
import NotificationsIcon from '@material-ui/icons/Notifications'
import AgentCommodityGetCommodity from '../commodity/GetCommodity';
import OrderStatus from '../order_commodity/OrderStatus';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DashboardValues from './DashboardValues'
import DashboardIcon from '@material-ui/icons/Dashboard';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import PaymentOutlinedIcon from '@material-ui/icons/PaymentOutlined'
import BarChartIcon from '@material-ui/icons/BarChart';
import LayersIcon from '@material-ui/icons/Layers';

import PowerSettingsNewIcon from '@material-ui/icons/PowerSettingsNew'

import AcceptOrder from '../order_commodity/AcceptOrder';

const drawerWidth = 240;

const styles = theme => ({
    root: {
        display: 'flex',
    },
    toolbar: {
        paddingRight: 24, // keep right padding when drawer closed
    },
    toolbarIcon: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: '0 8px',
        ...theme.mixins.toolbar,
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    appBarShift: {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginLeft: 12,
        marginRight: 36,
    },
    menuButtonHidden: {
        display: 'none',
    },
    title: {
        flexGrow: 1,
    },
    drawerPaper: {
        position: 'relative',
        whiteSpace: 'nowrap',
        width: drawerWidth,
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    drawerPaperClose: {
        overflowX: 'hidden',
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        width: theme.spacing.unit * 7,
        [theme.breakpoints.up('sm')]: {
            width: theme.spacing.unit * 9,
        },
    },
    appBarSpacer: theme.mixins.toolbar,
    content: {
        flexGrow: 1,
        padding: theme.spacing.unit * 3,
        height: '100vh',
        overflow: 'auto',
    },
    chartContainer: {
        marginLeft: -22,
    },
    tableContainer: {
        height: 320,
    },
    h5: {
        marginBottom: theme.spacing.unit * 2,
    },
});


class Dashboard extends Component {


    state = {
        open: false,
        dashboardClick: true,
        commodityClick: false,
        orderStatusClick: false,
        sellingStatus: false,
    };


    dashBoaredClick = val => {
        this.setState({
            dashboardClick: true,
            commodityClick: false,
            orderStatusClick: false,
            sellingStatus: false,
        })
    }

    CommodityClick = val => {
        this.setState({
            commodityClick: true,
            dashboardClick: false,
            orderStatusClick: false,
            sellingStatus: false,
        })
    }


    OrderStatusClick = val => {

        this.setState({
            commodityClick: false,
            dashboardClick: false,
            orderStatusClick: false,
            sellingStatus: true,
        })
    }


    SellingStatusClick = val => {

        this.setState({
            commodityClick: false,
            dashboardClick: false,
            orderStatusClick: true,
            sellingStatus: false,
        })
    }

    doLogOut = (val) => {
        this.props.dispatch(logout());
    };

    handleDrawerOpen = () => {
        this.setState({ open: true });
    };

    handleDrawerClose = () => {
        this.setState({ open: false });
    };


    render() {

        const { login, userType, classes, backAcceptCommodity, backUpdateOrderStatus } = this.props;

        // alert('backAcceptCommodity :: '+ backAcceptCommodity);
        //alert('backUpdateOrderStatus :: '+ backUpdateOrderStatus);
        //alert('orderStatusClick :: '+ this.state.orderStatusClick)

        if (login === false) {
            return <Redirect to="/auth" />;
        }

        return (
            <div className={classes.root}>
                <CssBaseline />
                <AppBar
                    position="absolute"
                    className={classNames(classes.appBar, this.state.open && classes.appBarShift)}
                >
                    <Toolbar disableGutters={!this.state.open} className={classes.toolbar}>
                        <IconButton
                            color="inherit"
                            aria-label="Open drawer"
                            onClick={this.handleDrawerOpen}
                            className={classNames(
                                classes.menuButton,
                                this.state.open && classes.menuButtonHidden,
                            )}>

                            <MenuIcon />
                        </IconButton>
                        <Typography
                            component="h1"
                            variant="h6"
                            color="inherit"
                            noWrap
                            className={classes.title}
                        >
                            <h7 style={{ textAlign: "left" }}>Agrieasy</h7>
                        </Typography>

                        {/* <IconButton color="inherit">
                            <Badge badgeContent={4} color="secondary">
                                <NotificationsIcon />
                            </Badge>
                        </IconButton> */}
                        <Tooltip title="LogOut" aria-label="LogOut">
                            <IconButton color="inherit" onClick={this.doLogOut}>
                                <PowerSettingsNewIcon />
                            </IconButton>
                        </Tooltip>
                    </Toolbar>
                </AppBar>
                <Drawer
                    variant="permanent"
                    classes={{
                        paper: classNames(classes.drawerPaper, !this.state.open && classes.drawerPaperClose),
                    }}
                    open={this.state.open}
                >
                    <div className={classes.toolbarIcon}>
                        <IconButton onClick={this.handleDrawerClose}>
                            <ChevronLeftIcon />
                        </IconButton>
                    </div>
                    <Divider />
                    <List>
                        <ListItem button onClick={this.dashBoaredClick}>
                            <ListItemIcon>
                                <DashboardIcon style={{ color: 'red' }} />
                            </ListItemIcon>
                            <ListItemText primary="Dashboard" />
                        </ListItem>{mainListItems}</List>
                    <ListItem button onClick={this.CommodityClick}>
                        <ListItemIcon>
                            <ShoppingCartIcon style={{ color: 'green' }} />
                        </ListItemIcon>
                        <ListItemText primary="Commodity" />
                    </ListItem>
                    {
                        userType === 'agent' ?
                            <ListItem button onClick={this.SellingStatusClick}>
                                <ListItemIcon>
                                    <PaymentOutlinedIcon style={{ color: 'coral' }} />
                                </ListItemIcon>
                                <ListItemText primary="Order Status" style={{ color: 'coral' }} />
                            </ListItem> :
                            <ListItem button onClick={this.OrderStatusClick}>
                                <ListItemIcon>
                                    <PaymentOutlinedIcon style={{ color: 'coral' }} />
                                </ListItemIcon>
                                <ListItemText primary="Selling Status" style={{ color: 'coral' }} />
                            </ListItem>
                    }
                    <ListItem button>
                        <ListItemIcon>
                            <BarChartIcon style={{ color: 'blueviolet' }} />
                        </ListItemIcon>
                        <ListItemText primary="Reports" />
                    </ListItem>
                    <ListItem button>
                        <ListItemIcon>
                            <LayersIcon style={{ color: 'palevioletred' }} />
                        </ListItemIcon>
                        <ListItemText primary="Integrations" />
                    </ListItem>
                    <Divider />
                    {/* <List>{secondaryListItems}</List> */}
                </Drawer>

                <main className={classes.content}>
                    <Typography variant="h4" gutterBottom component="h2">
                        <div>
                            <h7>Dashboard</h7>
                        </div>
                        <div>
                            {
                                <div>
                                    <Typography className='welcome'> Hi {this.props.user_name}  !       ({this.props.userType})</Typography>
                                </div>
                            }

                            {

                                this.state.dashboardClick ?
                                    <div>
                                        <DashboardValues />
                                    </div>
                                    : this.state.commodityClick ?
                                        <div>
                                            <AgentCommodityGetCommodity userId={this.props.userId} />
                                        </div>
                                        : this.state.sellingStatus ?
                                            <div>
                                                <OrderStatus />
                                            </div> : this.state.orderStatusClick ?
                                                <div>
                                                    <AcceptOrder />
                                                </div> : <div></div>
                            }
                        </div>
                    </Typography>
                </main>
            </div>
        );
    }
}

Dashboard.propTypes = {
    classes: PropTypes.object.isRequired,
};

const mapStateToProps = state => ({
    login: state.login.login,
    userType: state.login.userType,
    user_name: state.login.user_name,
    userId: state.login.userId,
    backAcceptCommodity: state.orderReducer.backAcceptCommodity,
    backUpdateOrderStatus: state.orderReducer.backUpdateOrderStatus,
});

export default connect(mapStateToProps)(withStyles(styles)(Dashboard));