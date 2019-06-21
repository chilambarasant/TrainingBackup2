import React, { PureComponent } from "react";
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'
import MUIDataTable from "mui-datatables";
import { getOderStatus } from './action'
import { Link } from 'react-router-dom'

class OrderStatus extends PureComponent {

  async componentDidMount() {
    const { getOderStatus } = this.props
    getOderStatus();
  }

  render() {

    const columns = ["Order ID", "Commodity", "Location", "Units", "Address", "Order Date", "Delivery Date", "Status"];

    const { order_list } = this.props;

    const options = {
      filterType: "dropdown",
      responsive: "scroll",
    };

    return (
      <div>
        <Link to='/dashboard'>Back</Link>
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
}, dispatch);

export default connect(mapStateToProps, mapDispatchToProps)(OrderStatus)
