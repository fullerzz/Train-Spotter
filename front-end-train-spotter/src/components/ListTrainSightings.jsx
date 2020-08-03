import React, { Component } from "react";
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

class ListTrainSightings extends Component {
  render() {
    return (
      <div>
        <BootstrapTable data={this.props.data} selectRow={selectRowProp}>
          <TableHeaderColumn
            isKey
            dataField="trainNumber"
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
            width="500"
          >
            Last Sighting
          </TableHeaderColumn>
        </BootstrapTable>
      </div>
    );
  }
}

export default ListTrainSightings;
