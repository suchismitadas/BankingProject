import { useForm } from "react-hook-form";
// import { useNavigate, Link } from "react-router-dom";
// import { useAppState } from "./components/state";
import { Button, Field, Form, Input } from "./components/Forms";
import { Row, Col } from "react-bootstrap";
import { Sheet, CssVarsProvider } from "@mui/joy";
import Navbar from "./components/navbar";
import axios from "axios";
import { useState } from "react";
import { Card, Grid,CardMedia, CardContent,Typography } from "@mui/material";
import { useEffect } from "react";
const AccountTransactions = () => {

// 
    const [success, setSuccess] = useState();
    const [error, setError] = useState({});
    const[details, setDetails ]= useState({});
    const [loading, setLoading] = useState(true);
const [hasError, setHasError] = useState(false);
    // console.log(state);
    const { handleSubmit, register, formState: { errors } } = useForm();
    // const navigate = useNavigate();
    const getBalance = async() => {
        try {
            const token = localStorage.getItem('token');
            const header = {
                
            Authorization: `Bearer ${token}`
            };
            const loggedInID = localStorage.getItem('id');
            const res = await axios.get(`http://localhost:8080/customer-details/${loggedInID}`, {headers: header});
    
            console.log(res.data);
            setDetails(res.data);
            setLoading(false);
            // setSuccess(true);
    }
    catch(error) {
        // setError(error);
        console.log(error.response.data);
    }
    }
    useEffect(() => {
        getBalance();
    }, []);

    const saveData = async (data) => {
        // setState({ ...state, customerDetails: data });
        //   navigate("/confirm");
        let date = new Date();
        let month = date.getMonth()+1;
        let currentDate= date.getFullYear()+"-"+(month < 10 ? "0"+month:month)+"-"+date.getDate();
        data = {...data, fromAccount: parseInt(data.fromAccount), toAccount: parseInt(data.toAccount), 
            amount: parseInt(data.amount), date: currentDate, status: "completed"};
        console.log(data);
        console.log(JSON.stringify(data));
        try {
        const token = localStorage.getItem('token');
        const header = {
            
        Authorization: `Bearer ${token}`
        };
        const loggedInID = localStorage.getItem('id');
        const res = await axios.post(`http://localhost:8080/transactions/`,data, {headers: header});

        console.log(res);
        setSuccess(true);
        } catch(error) {
            setHasError(true);
      setError(error.response.data);
      console.log(error.response.data);
        }

    };

    if(loading) return <h1>Loading...</h1>
    return (
        // < div className="container d-flex align-items-center flex-column" style={{ color: "white" }}>
        <>
        
            <Navbar />
            
           
                <Sheet sx={{
                    width: 1000,
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
<Grid container>
<Grid item xs={8} >
      

                    <h3> Transfer Funds </h3>
                    <Form onSubmit={handleSubmit(saveData)}>
                        <Row>
                            <Col>
                                <Field label="FROM " error={errors?.fromAccount}>
                                    <Input
                                        {...register("fromAccount", { required: "Valid account no is required"
                                    ,min:{value:0 , message:"Account no cannot be negative"}
                                    })}
                                        id="fromAccount"
                                        type="number"
                                    />
                                </Field>
                            </Col>
                            <Col>
                                <Field label="TO" error={errors?.toAccount}>
                                    <Input
                                        {...register("toAccount", { required: "Valid account no is required" 
                                        ,min:{value:0 , message:"Account no cannot be negative"}})}
                                        id="toAccount"
                                        type="number"
                                    />
                                </Field>
                            </Col>
                        </Row>

                        <div className="row mb-3">
                            <Field label="AMOUNT"  error={errors?.amount}>
                                <Input
                                    {...register("amount", {
                                        required: "Amount is required",
                                        min:{ value:0, message:"Enter only positive numbers"},
                                        
                                    })}
                                    id="amount"
                                    type="number"
                                />
                            </Field>
                        </div>

                        <div className="row mb-3">
                        <Field label="REMARK"  error={errors?.Remark}>
                                <Input
                                    {...register("remark", {
                                        required: "Remark is required",
                        
                                    })}
                                    id="remark"
                                    type="text"
                                />
                            </Field>
                        </div>
                        <fieldset className="row mb-3">
                            <legend className="col-form-label  pt-0">TYPE OF TRANSACTION </legend>
                            <div  className="row mb-3" align="center" >
                            <Field label="NEFT"  error={errors?.type}>
                                <input
                                    {...register("type")}
                                    id="type"
                                    type="radio"
                                    value="NEFT"
                                    
                                />
                            </Field>
                            <Field label="IMPS"  error={errors?.type}>
                                <input
                                    {...register("type")}
                                    id="type"
                                    type="radio"
                                    value="IMPS"
                                />
                            </Field><Field label="RTGS"  error={errors?.type}>
                                <input
                                    {...register("type")}
                                    id="type"
                                    type="radio"
                                    value="RTGS"
                                />
                            </Field>
                            </div>
                        </fieldset>

                        <Button>Send </Button>


                    </Form>

                    {/* <Link to="/login/welcome"><p style={{ color: "white" }}>Go to dashboard</p></Link> */}
                    {success && <p><b>Transaction is Successfull </b></p>}
                    {hasError && <div>
        <p style={{textAlign: 'center'}}> <strong>{error.property}</strong>: {error.code}. <br></br>
        <strong>Description: </strong>{error.message}</p>
        </div>}
        </Grid>
                
                <Grid  item xs={4}> 
                <Card sx={{ width: 250, height:250 }}>
                <CardMedia
                    component="img"
                    alt="green iguana"
                    

                    image="https://img.freepik.com/premium-vector/anonymous-user-circle-icon-vector-illustration-flat-style-with-long-shadow_520826-1931.jpg"
                />
            </Card>
            <Typography gutterBottom variant="h5" component="div">
             </Typography>
             <Typography variant="body2" color="text.secondary" style={{textAlign:"left"}}>
            <b>Welcome !!</b> {details.firstName} {details.lastName}
             <p>Customer Id: {details.customer.id}</p> 
             <p>Email: {details.customer.emailId}</p>
             <p>Account Number: {details.customer.accounts[0].accountNumber}</p>

            <p>Balance: {details.customer.accounts[0].balance}</p>

             </Typography>
                </Grid>
        </Grid>
                </Sheet>
                
             
            
        {/* </div> */}
        </>
    )
}
export default AccountTransactions;