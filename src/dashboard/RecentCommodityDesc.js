
import React, { Component } from 'react';
import styled from 'styled-components'

export default class RecentCommodityDesc extends Component {

    render() {
        const { data, desc } = this.props;
        //const POSTER_PATH = data.commodityImage;
        const POSTER_PATH = data.commodityImage;
        return (
            <div >
                <Hover>
                    <Poster src={`${POSTER_PATH}`} alt={data.commodityName} />
                    <div>
                        <p className="dashboardValues">{desc}</p>
                        <p className="dashboardValues">Rs. {data.price}</p>

                    </div>
                </Hover>
            </div>
        );
    }
}

export const Poster = styled.img`
	box-shadow: 0 0 10px green;
    border:2px solid green;
    height: 200px;
  width: 200px;
`;

export const Hover = styled.div`
&:hover {
    transform: scale(1.2);
  };
`;
