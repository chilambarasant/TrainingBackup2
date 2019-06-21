import React, { PureComponent } from "react";
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import MUIDataTable from "mui-datatables";
import { getOderStatus } from './action'
import { Link } from 'react-router-dom'
import AcceptCommodity from "./AcceptCommodity";

import { updateAcceptOrderStatus } from './action'

class AcceptOrder extends PureComponent {

  state = {
    acceptRowClick: false,
    acceptRowData: '',
  };


  async componentDidMount() {
    const { getOderStatus } = this.props
    getOderStatus();
  }

  handleClick = (event) => {
    this.setState({
      orderRowClick: true,
      acceptRowData: event,
    })


  }

  render() {

    const columns = ["Order ID", "Commodity", "Location", "Units", "Address", "Order Date", "Delivery Date", "Status"];
    const { order_list } = this.props;

    const options = {
      filterType: "dropdown",
      responsive: "scroll",
      onRowClick: (event) => this.handleClick(event)
    };

    {
      if (this.state.orderRowClick === true) {
        const { acceptRowData } = this.state;
        return <div>OrderCommodity<AcceptCommodity orderId={acceptRowData[0]} commodity={acceptRowData[1]} location={acceptRowData[2]} unit={acceptRowData[3]} seller={acceptRowData[4]} price={acceptRowData[5]} /></div>
      }
    }

    return (
      <div>
        <MUIDataTable
          title={"Order Status List"}
          data={order_list}
          columns={columns}
          options={options}
        />
      </div>
    );
  }
}

const mapStateToProps = state => ({
  order_list: state.orderReducer.order_list,
})

const mapDispatchToProps = dispatch => bindActionCreators({
  getOderStatus,
  updateAcceptOrderStatus,
}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(AcceptOrder)
