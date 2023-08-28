import React from "react";

// import Navbar from "./navbar";
import { useForm } from "react-hook-form";
// import { Sheet } from "@mui/joy";
import { Button, Field, Form, Input } from "./Forms";
import { Row, Col } from "react-bootstrap";
import { Sheet, CssVarsProvider } from "@mui/joy";
import Navbar from "./navbar";
import axios from "axios";
import { useState } from "react";

const Withdraw = () => {

    const { handleSubmit, register, formState: { errors } } = useForm();
    const [success, setSuccess] = useState();
    const [error, setError] = useState({});
const [hasError, setHasError] = useState(false);
const saveData = async (data) => {
//     // setState({ ...state, customerDetails: data });
//     //   navigate("/confirm");
//     let date = new Date();
//     let month = date.getMonth()+1;
//     let currentDate= date.getFullYear()+"-"+(month < 10 ? "0"+month:month)+"-"+date.getDate();
//     data = {...data, fromAccount: parseInt(data.fromAccount), toAccount: parseInt(data.toAccount), 
//         amount: parseInt(data.amount), date: currentDate, status: "completed"};
    console.log(data);
//     console.log(JSON.stringify(data));
    try {
    const token = localStorage.getItem('token');
    const header = {
        
    Authorization: `Bearer ${token}`
    };
    // const loggedInID = localStorage.getItem('id');
    const res = await axios.post(`http://localhost:8080/transactions/withdrawal`,data, {headers: header});

    console.log(res);
    setSuccess(true);
    } catch(error) {
        setHasError(true);
  setError(error.response.data);
  console.log(error.response.data);
    }

};


    return (
        < div className="container d-flex align-items-center flex-column" style={{ color: "white" }}>
            <Navbar />
            <CssVarsProvider>
                <Sheet sx={{
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


                    <h3> Withdraw </h3>
                    <Form onSubmit={handleSubmit(saveData)}>
                        <Row>
                            <Col>
                                <Field label="Account Number " error={errors?.accountNumber}>
                                    <Input
                                        {...register("accountNumber")}
                                        id="accountNumber"
                                        type="number"
                                        // value={localStorage.getItem("id")}
                                        // disabled
                                    />
                                </Field>
                            </Col>
                       
                        </Row>
                        <Row>
                        <Col>
                                <Field label="Amount" error={errors?.amount}>
                                    <Input
                                        {...register("amount", { required: "Valid amount no is required" 
                                        ,min:{value:0 , message:"Amount no cannot be negative"}})}
                                        id="amount"
                                        type="number"
                                    />
                                </Field>
                            </Col>
                        </Row>
                        <Row>
                        <Col>
                                <Field label="Remark" error={errors?.remark}>
                                    <Input
                                        {...register("remark")}
                                        id="remark"
                                        type="string"
                                    />
                                </Field>
                            </Col>
                            
                        </Row>

                   

                        <Button>Send</Button>


                    </Form>

                    {/* <Link to="/login/welcome"><p style={{ color: "white" }}>Go to dashboard</p></Link> */}
                    {success && <p><b>Transaction is Successfull </b></p>}
                    {hasError && <div>
        <p style={{textAlign: 'center'}}> <strong>{error.property}</strong>: {error.code}. <br></br>
        <strong>Description: </strong>{error.message}</p>
        </div>}
                </Sheet>
            </CssVarsProvider>
        </div>
    )

}
export default Withdraw;