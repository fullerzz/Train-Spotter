import React, { Component } from "react";
import TrainDataService from "../service/TrainDataService";
import { BootstrapTable, TableHeaderColumn } from "react-bootstrap-table";
import "../../node_modules/react-bootstrap-table/css/react-bootstrap-table.css";

function onSelectRow(row, isSelected, e) {
  if (isSelected) {
    //button to view more info about that train is rendered
  }
}

const selectRowProp = {
  mode: "checkbox",
  clickToSelect: true,
  onSelect: onSelectRow,
  bgColor: "gold",
};

class ListAllTrains extends Component {
  constructor(props) {
    super(props);
    this.state = {
      trains: [],
    };
    this.refreshTrains = this.refreshTrains.bind(this);
    console.log(this.state.trains);
  }

  componentDidMount() {
    this.refreshTrains();
  }

  refreshTrains() {
    TrainDataService.retrieveAllTrains().then((response) => {
      console.log(response);
      this.setState({ trains: response.data });
    });
  }

  render() {
    return (
      <div>
        <BootstrapTable data={this.state.trains} selectRow={selectRowProp}>
          <TableHeaderColumn
            isKey
            dataField="number"
            dataAlign="center"
            headerAlign="center"
            width="100"
          >
            Train Number
          </TableHeaderColumn>
          <TableHeaderColumn
            dataField="numSightings"
            dataAlign="center"
            headerAlign="center"
            width="100"
          >
            Number of Sightings
          </TableHeaderColumn>
          <TableHeaderColumn
            dataField="lastSighting"
            dataAlign="center"
            headerAlign="center"
            width="300"
          >
            Last Sighting
          </TableHeaderColumn>
        </BootstrapTable>
      </div>
    );
  }
}

export default ListAllTrains;
