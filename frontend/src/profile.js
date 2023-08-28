import React from "react";
// import Navbar from "./components/navbar";
import { Card, Typography } from "@mui/joy";
import axios from "axios";
import { useState, useEffect } from "react";
import Navbar from "./components/navbar";
import { CardMedia, Divider, Grid, Stack } from "@mui/material";
import { CardContent } from "@mui/material";
import {Sheet} from "@mui/joy"

const Profile = () => {

    const [detail, setDetail] = useState({});
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(true);

    // useEffect

    useEffect( () => {
        const getData = async () => {
            console.log("i run")
            const loggedInID = localStorage.getItem('id');
            const token = localStorage.getItem('token');
            const header = `Authorization: Bearer ${token}`;
            try {
                const res = await axios.get(`http://localhost:8080/customer-details/${loggedInID}`, { headers: header });
                setDetail(res.data);
                console.log(res);
                setError(null);
            } catch (error) {
                setError(error);
                setDetail(null);
            } finally {
                setLoading(false);
            }
        };
        getData();
    }, []);


    return (
        <>
            <Stack spacing={5}>
            <Navbar />
            {loading && <div>A moment please...</div>}
            {error && (
                <div>{`There is a problem fetching the post data - ${error}`}</div>
            )}
            <Grid container>
                <Grid >
            <Card sx={{ maxWidth: 345 }}>
                <CardMedia
                    component="img"
                    alt="green iguana"

                    image="https://img.freepik.com/premium-vector/anonymous-user-circle-icon-vector-illustration-flat-style-with-long-shadow_520826-1931.jpg"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="div">
                       {detail.firstName} {detail.lastName}
            
                    </Typography>
                    <Typography variant="body2" color="text.secondary">
                      <b>Welcome !!</b> to your Account.
                      <p> Your Customer Id : <b>{detail.id}   </b></p>
                    </Typography>
                </CardContent>
            </Card>
            </Grid>
            
            <Grid xs={6} >
               <Sheet  sx={{
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
          variant="outlined">
               <p><b>Address :</b>  {detail.permanentAddress} </p>
               <Divider></Divider>
               <p> <b>Mobile Number :</b> {detail.mobileNumber}</p>
               <Divider>
               </Divider>
               <p><b>PAN :</b>  {detail.pan} </p>
               <Divider></Divider>
               <p><b>Date Of Birth :</b>  {detail.dob} </p>
               <Divider></Divider>
               <p><b>Occupation :</b>  {detail.occupation} </p>
               <Divider></Divider>

               </Sheet>
            </Grid>
            
            </Grid>
            </Stack>
        </>
    )
}
export default Profile;