import React from "react";
import Navbar from "./navbar";
import { Sheet } from "@mui/joy";
import { Grid, Stack, tableBodyClasses } from "@mui/material";
import { useState, useEffect } from "react";
import axios from 'axios';
import {Table}  from "react-bootstrap";
import {Card ,CardMedia,CardContent,Typography} from "@mui/material";
import { Link } from "react-router-dom";

const History=()=>{

    const [accthistory,setAccthistory] = useState([]);
    useEffect(()=> {
        console.log("This is running");

        const getData = async () => {
            const loggedInID = localStorage.getItem('id');
            const token = localStorage.getItem('token');
            const header = {
            
                Authorization: `Bearer ${token}`
                };
              try {  
             const response = await axios.get(`http://localhost:8080/transactions/${loggedInID}`, {headers: header});
    
            console.log(response.data);
            setAccthistory(response.data);   
              } catch(error) {
                console.log(error);
              }
        }
        getData();
    },[]);

    return(
        <>
        <Stack spacing={4} alignItems="center" >
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
            <Grid container>
        <Grid>
        <Card sx={{ width: 100, height:100 }}>
                <CardMedia
                    component="img"
                    alt="green iguana"
                    

                    image="https://img.freepik.com/premium-vector/anonymous-user-circle-icon-vector-illustration-flat-style-with-long-shadow_520826-1931.jpg"
                />
                
                    
               
            </Card>
            </Grid>
            <Grid xs={6}>
            <Typography gutterBottom variant="h5" component="div">
             
            
                    </Typography>
                    <Typography variant="body2" color="text.secondary" style={{textAlign:"center"}}>
                    <b>Welcome !!</b> to your Account
                    {!accthistory && <div style={{textAlign:"center"}}><p><b>No Transactions Found</b></p></div>} 

                    </Typography>
            </Grid>
            </Grid>
        </Sheet>

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
            <h3 align = "center"> Transactions</h3>
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>Date</th>
                <th> Type</th>
                <th> From Account</th>
                <th> To Account</th>
                <th>Amount</th>
                <th>Remark</th>
                {/* <th> Status</th> */}
            </tr>
            </thead>
            <tbody>
           {!accthistory && <div ><p><Link to="/accounttransactions">Send Money</Link></p></div>} 
            {accthistory.map((val,key)=>{
                return(
                    <tr key = {key}>
                        <td>{val.date}</td>
                        <td>{val.type}</td>
                        <td>{val.fromAccount}</td>
                        <td>{val.toAccount}</td>
                        <td>${val.amount}</td>
                        <td>{val.remark}</td>
                        {/* <td>{val.status}</td> */}
                    </tr>
                )
            })}</tbody>
        </Table>
        </Sheet>

        </Stack>
</>
    )
}
export default History;
