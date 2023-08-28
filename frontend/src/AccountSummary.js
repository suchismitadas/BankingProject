import React from "react";
import { useState,useEffect } from "react";
import { Link } from "react-router-dom";
//import {  MDBCheckbox,MDBTable, MDBTableHead, MDBTableBody } from 'mdb-react-ui-kit';
import { Table } from "react-bootstrap";
import Navbar from "./components/navbar";
import { Sheet } from "@mui/joy";
import axios from "axios";

const AccountSummary= ()=>{

        const [details,setDetails]=useState([]);
       
        useEffect( () => {

          const getData = async () => {
            console.log("I am running");
            const loggedInID = localStorage.getItem('id');
            const token = localStorage.getItem('token');
            const header = {
            
              Authorization: `Bearer ${token}`
              };

            try {
            const res = await axios.get(`http://localhost:8080/accounts/${loggedInID}`, {headers: header});
          
                console.log(res);
                setDetails(res.data);
                console.log(details);
            } catch(error) {
              console.log(error.message);
            }
                // console.log(details);
          }
          getData();

        }, [])

    return (
        <div >

        <Navbar/>
        <Sheet
          sx={{
            width: 600,
            mx: 'auto', // margin left & right
            my: 4, // margin top & bottom
            py: 3, // padding top & bottom
            px: 2, // padding left & right
            display: 'flex',
            flexDirection: 'column',
            gap: 2,
            borderRadius: 'sm',
            boxShadow: 'md',
          }}
          variant="outlined"
        >
        <h3 style={{fontFamily:"Times New Roman"}} >Account summary</h3>
        <Table striped bordered hover>
      <tbody>
        {details.map((val, key) => {
          return (<><tr>
          <th>TYPE</th>
          <th>{val.type}</th>
        </tr>
        <tr>
          <td>IFSC Code</td>
          <td>{val.ifscCode}</td>
          
        </tr>
       
        <tr>
          <td>Account Number</td>
          <td>{val.accountNumber}</td>
        </tr>
        <tr>
          <td>Available Balance</td>
          <td>{val.balance}</td>
        </tr>
        <tr>
          <td>Status</td>
          <td>{val.status}</td>
        </tr></>
        )
        })}
      
      </tbody>
    </Table>
        <Link to="/login/welcome">Go to dashboard</Link>
        </Sheet>
        </div>
    )
}
export default AccountSummary;