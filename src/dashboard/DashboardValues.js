import React, { Component } from "react";
import Styled from 'styled-components';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import { getData, getWeatherData } from './DashboardAction';
import RecentCommodityDesc from './RecentCommodityDesc';
import { withStyles } from '@material-ui/core/styles';
import TableCell from '@material-ui/core/TableCell';

import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import { isUndefined } from "util";

const CustomTableCell = withStyles(theme => ({
    head: {
        backgroundColor: '#3B6746',
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const styles = theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing.unit * 3,
        overflowX: 'auto',
    },
    table: {
        minWidth: 700,
    },
    row: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.background.default,
        },
    },
});

class DashboardValues extends Component {
    async componentDidMount() {
        this.props.getData();
        this.props.getWeatherData('Porur');
    }
    render() {
        const { newlyAddedcomodity, weatherData } = this.props;
        return (
            <div>
                <RecentCommodity >
                    {
                        newlyAddedcomodity.map(recentData =>
                            <RecentCommodityDesc key={recentData.commodityId}
                                data={recentData}
                                desc={recentData.commodityName}
                            />)
                    }
                </RecentCommodity>
                <div>
                    <h7>Weather Forecasting </h7>
                </div>{
                Object.keys(JSON.stringify(weatherData)).length>2 ?<div>
                    <div className="div1">
                        <Paper className={styles.root}>
                            <Table className={styles.table}>
                                <TableHead>
                                    <TableRow>
                                        <CustomTableCell>Place</CustomTableCell>
                                        <CustomTableCell align="right">Temp</CustomTableCell>
                                        <CustomTableCell align="right">Pressure</CustomTableCell>
                                        <CustomTableCell align="right">Humidity</CustomTableCell>
                                        <CustomTableCell align="right">Min Temp</CustomTableCell>
                                        <CustomTableCell align="right">Max Temp</CustomTableCell>
                                        <CustomTableCell align="right">Description</CustomTableCell>
                                        <CustomTableCell align="right"></CustomTableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow className={styles.row}>
                                        <CustomTableCell component="th" scope="row">
                                            {weatherData.name}
                                        </CustomTableCell>
                                        <CustomTableCell align="right">{weatherData.main.temp}</CustomTableCell>
                                        <CustomTableCell align="right">{weatherData.main.pressure}</CustomTableCell>
                                        <CustomTableCell align="right">{weatherData.main.humidity}</CustomTableCell>
                                        <CustomTableCell align="right">{weatherData.main.temp_min}</CustomTableCell>
                                        <CustomTableCell align="right">{weatherData.main.temp_max}</CustomTableCell>
                                        <CustomTableCell align="right">{weatherData.weather[0].description}</CustomTableCell>
                                        <CustomTableCell align="right"><img src={`http://openweathermap.org/img/w/${weatherData.weather[0].icon}.png`}></img></CustomTableCell>
                                    </TableRow>
                                </TableBody>
                            </Table>
                        </Paper>
                    </div>
                </div>:<div></div>}
            </div>
        );
    }
}

const RecentCommodity = Styled.div`
            display:grid;
            padding:1rem;
            grid-template-columns:repeat(5,1fr);
            grid-row-gap:1rem;
            
            `;


const mapStateToProps = state => ({
    newlyAddedcomodity: state.DashBoardReducer.newlyAddedcomodity,
    weatherData: state.DashBoardReducer.weatherData,
})

const mapDispatchToProps = dispatch => bindActionCreators({
    getData,
    getWeatherData,
}, dispatch)

export default connect(mapStateToProps, mapDispatchToProps)(DashboardValues);
