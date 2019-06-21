
import React from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import ListSubheader from '@material-ui/core/ListSubheader';
import DashboardIcon from '@material-ui/icons/Dashboard';
import ShoppingCartIcon from '@material-ui/icons/ShoppingCart';
import PeopleIcon from '@material-ui/icons/People';
import PaymentOutlinedIcon from '@material-ui/icons/PaymentOutlined'
import BarChartIcon from '@material-ui/icons/BarChart';
import LayersIcon from '@material-ui/icons/Layers';
import AssignmentIcon from '@material-ui/icons/Assignment';




export const mainListItems = (
  <div>
    {/* <ListItem button>
      <ListItemIcon>
        <DashboardIcon style={{color: 'red'}}/>
      </ListItemIcon>
      <ListItemText primary="Dashboard" />
    </ListItem> */}
    {/* <ListItem button>
      <ListItemIcon>
        <ShoppingCartIcon style={{color: 'green'}}/>
      </ListItemIcon>
      <ListItemText primary="Commodity" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <PaymentOutlinedIcon style={{color: 'coral'}} />
      </ListItemIcon>
      <ListItemText primary="Payments" style={{color: 'coral'}}/>
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <BarChartIcon  style={{color: 'blueviolet'}}/>
      </ListItemIcon>
      <ListItemText primary="Reports"/>
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <LayersIcon style={{color: 'palevioletred'}}/>
      </ListItemIcon>
      <ListItemText primary="Integrations" />
    </ListItem> */}
  </div>
);

export const secondaryListItems = (
  <div>
    <ListSubheader inset>Saved reports</ListSubheader>
    <ListItem button>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Current month" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Last quarter" />
    </ListItem>
    <ListItem button>
      <ListItemIcon>
        <AssignmentIcon />
      </ListItemIcon>
      <ListItemText primary="Year-end sale" />
    </ListItem>
  </div>
);