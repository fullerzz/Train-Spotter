import React from "react";
import "./App.css";
import Train from "./components/Train";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
//import { Navigation, Footer, Home, About, Contact } from "./components";
import Navigation from "./components/Navigation";
import Footer from "./components/Footer";
import Home from "./components/Home";
import About from "./components/About";

function App() {
  return (
    <div className="App">
      <Router>
        <Navigation />
        <Switch>
          <Route path="/" exact component={() => <Home />} />
          <Route path="/about" exact component={() => <About />} />
          <Route path="/train" exact component={() => <Train />} />
        </Switch>
        <Footer />
      </Router>
    </div>
  );
}

export default App;
