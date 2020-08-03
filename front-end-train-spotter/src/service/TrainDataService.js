import axios from "axios";
import Train from "../components/Train";

const TRAIN = "2018";
const ALL_TRAIN_API_URL = "http://localhost:8080/trains";
const SINGLE_TRAIN_API_URL = `${ALL_TRAIN_API_URL}/${TRAIN}`;

class TrainDataService {
  retrieveAllTrains() {
    return axios.get(`${ALL_TRAIN_API_URL}`);
  }
  retrieveTrainInfo(trainNum) {
    return axios.get(`${ALL_TRAIN_API_URL}` + "/" + trainNum);
  }
}

export default new TrainDataService();
