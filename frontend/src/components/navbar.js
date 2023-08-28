import React    from "react";
import Tabs from '@mui/joy/Tabs';
import TabList from '@mui/joy/TabList';
import Tab from '@mui/joy/Tab';
import { Link } from "react-router-dom";

const doLogout = () => {
    localStorage.deleteItem("token");
    localStorage.deleteItem("id");
}


 const Navbar=()=>{
    return(
        <div className="container">
            <nav>
        <Tabs aria-label="Basic tabs" >
        <TabList>
        <Tab><Link to="/profile">Profile</Link></Tab>
        <Tab><Link to="/accountsummary">Account Summary</Link></Tab>
        <Tab><Link to="/accounttransactions">Make a Payment</Link></Tab>
        <Tab><Link to="/history">Transaction History</Link></Tab>
        <Tab><Link to="/withdraw">Withdraw</Link></Tab>
        <Tab><Link to="/" onClick={doLogout}>Log out</Link></Tab>
        </TabList>
        </Tabs>
        <b style={{color:"white"}}> Logged in as USER </b>
       
        </nav>
        </div>
    )
}
export default Navbar;