import React from "react";
import { Button } from "react-bootstrap";
import { useState ,useEffect} from "react";
import axios from 'axios';
import {Table} from 'react-bootstrap';
import {Sheet } from '@mui/joy';

const Admin=()=>{
    const [alluser,setAlluser] = useState([]);
    // const loggedInID = localStorage.getItem('id');
            const token = localStorage.getItem('token');
    const header = {
        Authorization: `Bearer ${token}`
        };

        const getData = async () => {
            
           
            try {  
           const response = await axios.get(`http://localhost:8080/customer-details`, {headers: header});
  
          console.log(response.data);
          setAlluser(response.data);   
            } catch(error) {
              console.log(error);
            }
      }

    useEffect(()=> {
        console.log("This is running")
        getData();
    },[]);

    // useEffect

    const postdata= async (key)=>{
        console.log(key)
        console.log("runningggg")
        const res=  await axios.post(`http://localhost:8080/approve-customer/${key}`, {}, {headers: header});
        console.log(res.data);
        getData();
    }
 const deletedata = async(key)=>{

    try {
    const res = await axios.post(`http://localhost:8080/deny-customer/${key}`, {}, {headers: header});
    console.log(res.data);
    getData();

    } catch(error) {
        console.log(error);
    }
 }

    return (
        <>
        <b  style={{color:"white"}}> Logged in as Admin.</b>
        <Sheet
          sx={{
            width: 1500,
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
<Table striped bordered hover>
            <thead>
            <tr>
                <th>User Id</th>
                <th> Name</th>
                <th> Date Of Birth</th>
                <th>Mobile Number</th>
                <th>Adhar No</th>
                <th> Status</th>
            </tr>
            </thead>

            <tbody>
            {alluser.map((val,key)=>{
                return(
                    <tr key = {key}>
                        <td>{val.id}</td>
                        <td>{val.firstName}  {val.lastName}</td>
                        
                        <td>{val.dob}</td>
                        <td>{val.mobileNumber}</td>
                        <td>${val.aadhar}</td>
                        {!val.customer.verifiedUser && <td><Button onClick={()=> postdata(val.id)} color="success">Approve</Button>
                        <Button onClick={()=> deletedata(val.id)}>Reject </Button> </td>}
                        {val.customer.verifiedUser && <td>Approved</td>}
                    </tr>
                )
            })}</tbody>
            </Table>
        </Sheet>
        
        
        </>
    )
}
export default Admin;