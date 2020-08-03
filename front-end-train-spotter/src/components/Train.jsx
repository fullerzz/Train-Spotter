import React, { Component } from "react";
import ListAllTrains from "./ListAllTrains";
import TrainDataService from "../service/TrainDataService";

class Train extends Component {
  constructor(props) {
    super(props);
    this.state = {
      trains: [],
    };
    this.refreshTrains = this.refreshTrains.bind(this);
  }

  componentDidMount() {
    this.refreshTrains();
  }

  refreshTrains() {
    TrainDataService.retrieveTrainInfo(2018).then((response) => {
      console.log(response);
      this.setState({ courses: response.data });
    });
  }

  render() {
    return (
      <>
        <h2 className="Table-header">Trains Spotted</h2>
        <ListAllTrains />
      </>
    );
  }
}

export default Train;
//<ListTrains data={data} />
